package uk.ac.york.eng2.thm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.TestInputTopic;
import org.apache.kafka.streams.TestOutputTopic;
import org.apache.kafka.streams.TopologyTestDriver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.micronaut.configuration.kafka.serde.SerdeRegistry;
import io.micronaut.configuration.kafka.streams.ConfiguredStreamBuilder;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import uk.ac.york.eng2.thm.domain.HashTag;
import uk.ac.york.eng2.thm.events.TrendingConsumer;
import uk.ac.york.eng2.thm.events.TrendingProducer;
import uk.ac.york.eng2.thm.events.TrendingStream;
import uk.ac.york.eng2.thm.events.WindowedIdentifier;
import uk.ac.york.eng2.thm.repositories.HashTagsRepository;

import javax.transaction.Transactional;

/**
 * Example of a Test Double approach for testing our Kafka Streams logic, without
 * actually using a Kafka cluster. We use a simulated Kafka Streams driver included
 * with KS: the {@code TopologyTestDriver}.
 */
@MicronautTest(environments = "no_streams")
public class TestDoubleStreamsTest {

	@Inject
	private SerdeRegistry serdeRegistry;

	@Inject
	private TrendingStream streams;

	@Test
	public void topologyCheck() {
		final ConfiguredStreamBuilder builder = new ConfiguredStreamBuilder(new Properties());
		streams.likesByHour(builder);

		try (TopologyTestDriver testDriver = new TopologyTestDriver(builder.build())) {
			TestInputTopic<Long, HashTag> inputTopic = testDriver.createInputTopic(
				TrendingProducer.TOPIC_TRENDING_METRIC,
				new LongSerializer(), serdeRegistry.getSerializer(HashTag.class));

			final long tagId = 1L;
			final int eventCount = 2;
			for (int i = 0; i < eventCount; i++) {
				inputTopic.pipeInput(tagId, new HashTag());
			}

			TestOutputTopic<WindowedIdentifier, Long> outputTopic = testDriver.createOutputTopic(
				TrendingStream.TOPIC_LIKES_BY_HOUR,
				serdeRegistry.getDeserializer(WindowedIdentifier.class),
				new LongDeserializer());

			List<KeyValue<WindowedIdentifier, Long>> keyValues = outputTopic.readKeyValuesToList();
			assertFalse(keyValues.isEmpty());

			KeyValue<WindowedIdentifier, Long> lastKeyValue = keyValues.get(keyValues.size() - 1);
			assertEquals(tagId, lastKeyValue.key.getId());
			assertEquals(eventCount, lastKeyValue.value);
		}
	}
}
