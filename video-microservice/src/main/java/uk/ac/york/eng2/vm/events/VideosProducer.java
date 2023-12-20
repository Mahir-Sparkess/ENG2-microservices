package uk.ac.york.eng2.vm.events;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;
import uk.ac.york.eng2.vm.domain.HashTag;
import uk.ac.york.eng2.vm.domain.User;
import uk.ac.york.eng2.vm.domain.Video;

import java.util.Set;

@KafkaClient
public interface VideosProducer {
    String TOPIC_VIEW = "video-view";
    String TOPIC_UPLOAD = "video-upload";
    String TOPIC_LIKE = "video-like";
    String TOPIC_DISLIKE = "video-dislike";

    @Topic(TOPIC_VIEW)
    void viewVideo(@KafkaKey Long id, User user);

    @Topic(TOPIC_UPLOAD)
    void uploadVideo(@KafkaKey Long id, Video video);

    @Topic(TOPIC_LIKE)
    void likeVideo(@KafkaKey Long id, Set<HashTag> tags);

    @Topic(TOPIC_DISLIKE)
    void dislikeVideo(@KafkaKey Long id, Set<HashTag> tags);
}
