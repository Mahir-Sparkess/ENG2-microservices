package uk.ac.york.eng2.vm.events;

import io.micronaut.configuration.kafka.serde.SerdeRegistry;
import io.micronaut.configuration.kafka.streams.ConfiguredStreamBuilder;
import io.micronaut.context.annotation.Factory;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import uk.ac.york.eng2.vm.domain.VideoExt;

import java.time.Duration;
import java.util.Properties;

@Factory
public class Stream {
    public static final String TOPIC_UPLOADS_BY_DAY = "video-uploads-by-day";

    @Inject
    private SerdeRegistry serdeRegistry;

    @Singleton
    public KStream<WindowedIdentifier, Long> uploadsByDay(ConfiguredStreamBuilder builder){
        Properties props = builder.getConfiguration();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "upload-metrics");
        props.put(StreamsConfig.PROCESSING_GUARANTEE_CONFIG, StreamsConfig.EXACTLY_ONCE);

        KStream<Long, VideoExt> videosStream = builder
                .stream(Producer.TOPIC_UPLOAD, Consumed.with(Serdes.Long(), serdeRegistry.getSerde(VideoExt.class)));

        KStream<WindowedIdentifier, Long> stream = videosStream.groupByKey()
                .windowedBy(TimeWindows.of(Duration.ofDays(1)).advanceBy(Duration.ofDays(1)))
                .count(Materialized.as("uploads-by-day"))
                .toStream()
                .selectKey((k, v) -> new WindowedIdentifier(k.key(), k.window().start(), k.window().end()));

        stream.to(TOPIC_UPLOADS_BY_DAY,
                Produced.with(serdeRegistry.getSerde(WindowedIdentifier.class), Serdes.Long()));

        return stream;
    }
}
