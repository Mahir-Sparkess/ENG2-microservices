package uk.ac.york.eng2.vm.events;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;
import uk.ac.york.eng2.vm.domain.HashTag;
import uk.ac.york.eng2.vm.domain.User;
import uk.ac.york.eng2.vm.domain.Video;


@KafkaClient
public interface VideosProducer {
    String TOPIC_VIEW = "video-view";
    String TOPIC_UPLOAD = "video-upload";
    String TOPIC_LIKE_DISLIKE = "video-like-dislike";
    String TOPIC_TAG_LIKE = "tag-like";
    String TOPIC_CREATE_HASHTAG = "create-hashtag";

    @Topic(TOPIC_VIEW)
    void viewVideo(@KafkaKey Long id, User user);

    @Topic(TOPIC_UPLOAD)
    void uploadVideo(@KafkaKey Long id, Video video);

    @Topic(TOPIC_LIKE_DISLIKE)
    void likeDislikeVideo(@KafkaKey Long id, String likeOrDislike);

    @Topic(TOPIC_TAG_LIKE)
    void likeTag(@KafkaKey Long id, HashTag tag);

    @Topic(TOPIC_CREATE_HASHTAG)
    void createHashTag(@KafkaKey Long id, String name);

}
