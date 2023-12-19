package uk.ac.york.eng2.vm.events;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;
import uk.ac.york.eng2.vm.domain.User;
import uk.ac.york.eng2.vm.domain.Video;

@KafkaClient
public interface VideosProducer {
    String TOPIC_VIEW = "video-view";
    String TOPIC_UPLOAD = "video-upload";
    String TOPIC_LIKE = "video-like-dislike";

    @Topic(TOPIC_VIEW)
    void viewVideo(@KafkaKey Long id, User user);

    @Topic(TOPIC_UPLOAD)
    void uploadVideo(@KafkaKey Long id, Video video);
}
