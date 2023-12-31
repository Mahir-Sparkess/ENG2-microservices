package uk.ac.york.eng2.thm.events;

import io.micronaut.configuration.kafka.serde.SerdeRegistry;
import io.micronaut.configuration.kafka.streams.ConfiguredStreamBuilder;
import io.micronaut.context.annotation.Factory;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import uk.ac.york.eng2.thm.domain.HashTag;

import java.time.Duration;
import java.util.Properties;

@Factory
public class TrendingStream {

    public static final String TOPIC_LIKES_BY_HOUR = "hashtag-likes-by-hour";

    @Inject
    private SerdeRegistry serdeRegistry;

    @Singleton
    public KStream<WindowedIdentifier, Long> likesByHour(ConfiguredStreamBuilder builder){
        Properties props = builder.getConfiguration();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "trend-metric");
        props.put(StreamsConfig.PROCESSING_GUARANTEE_CONFIG, StreamsConfig.EXACTLY_ONCE);

        KStream<Long, HashTag> hashTagStream = builder
                .stream(TrendingProducer.TOPIC_TRENDING_METRIC, Consumed.with(Serdes.Long(), serdeRegistry.getSerde(HashTag.class)));

        KStream<WindowedIdentifier, Long> stream = hashTagStream.groupByKey()
                .windowedBy(TimeWindows.of(Duration.ofHours(1)).advanceBy(Duration.ofHours(1)))
                .count(Materialized.as("likes-by-hour"))
                .toStream()
                .selectKey((k, v) -> new WindowedIdentifier(k.key(), k.window().start(), k.window().start()));

        stream.to(TOPIC_LIKES_BY_HOUR,
                Produced.with(serdeRegistry.getSerde(WindowedIdentifier.class), Serdes.Long()));

        return stream;
    }
}
