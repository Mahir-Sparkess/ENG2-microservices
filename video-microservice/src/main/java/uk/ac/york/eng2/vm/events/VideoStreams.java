package uk.ac.york.eng2.vm.events;

import io.micronaut.configuration.kafka.serde.SerdeRegistry;
import io.micronaut.configuration.kafka.streams.ConfiguredStreamBuilder;
import io.micronaut.context.annotation.Factory;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.apache.kafka.common.protocol.types.Field;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import uk.ac.york.eng2.vm.domain.Video;

import java.time.Duration;
import java.util.Properties;

@Factory
public class VideoStreams {
    public static final String TOPIC_VIEWS_BY_DAY = "video-views-by-day";

    @Inject
    private SerdeRegistry serdeRegistry;

    @Singleton
    public KStream<WindowedIdentifier, Long> viewsByDay(ConfiguredStreamBuilder builder){
        Properties props = builder.getConfiguration();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "videos-metrics");
        props.put(StreamsConfig.PROCESSING_GUARANTEE_CONFIG, StreamsConfig.EXACTLY_ONCE);

        KStream<Long, Video> videosStream = builder
                .stream(VideosProducer.TOPIC_VIEW, Consumed.with(Serdes.Long(), serdeRegistry.getSerde(Video.class)));

        KStream<WindowedIdentifier, Long> stream = videosStream.groupByKey()
                .windowedBy(TimeWindows.of(Duration.ofDays(1)).advanceBy(Duration.ofDays(1)))
                .count(Materialized.as("views-by-day"))
                .toStream()
                .selectKey((k, v) -> new WindowedIdentifier(k.key(), k.window().start(), k.window().end()));

        stream.to(TOPIC_VIEWS_BY_DAY,
                Produced.with(serdeRegistry.getSerde(WindowedIdentifier.class), Serdes.Long()));

        return stream;
    }
}
