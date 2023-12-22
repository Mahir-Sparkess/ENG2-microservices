package uk.ac.york.eng2.thm.events;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;
import uk.ac.york.eng2.thm.domain.HashTag;


@KafkaClient
public interface TrendingProducer {
    String TOPIC_LIKE_DISLIKE = "video-like-dislike";
    String TOPIC_TAG_LIKE = "tag-like";
    String TOPIC_CREATE_HASHTAG = "create-hashtag";

    @Topic(TOPIC_LIKE_DISLIKE)
    void likeDislikeVideo(@KafkaKey Long id, Boolean like);

    @Topic(TOPIC_TAG_LIKE)
    void TagLikeDislike(@KafkaKey Long id, HashTag tag);
}
