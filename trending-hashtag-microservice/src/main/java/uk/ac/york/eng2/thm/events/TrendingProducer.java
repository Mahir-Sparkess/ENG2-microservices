package uk.ac.york.eng2.thm.events;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;
import uk.ac.york.eng2.thm.domain.HashTag;


@KafkaClient
public interface TrendingProducer {
    String TOPIC_TRENDING_METRIC = "trending-metric";

    @Topic(TOPIC_TRENDING_METRIC)
    void trendingHashtag(@KafkaKey Long id, HashTag hashTag);
}
