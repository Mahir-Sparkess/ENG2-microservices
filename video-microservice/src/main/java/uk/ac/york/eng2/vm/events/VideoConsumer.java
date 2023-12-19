package uk.ac.york.eng2.vm.events;

import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.Topic;
import uk.ac.york.eng2.vm.domain.User;
import uk.ac.york.eng2.vm.domain.Video;

@KafkaListener(groupId = "videos-debug")
public class VideoConsumer {
    @Topic(VideosProducer.TOPIC_VIEW)
    public void videoViewsMetric(@KafkaKey Long id, User user){
        System.out.printf("Video %d viewed by: %s%n", id, user.getUsername());
    }

    @Topic(VideosProducer.TOPIC_UPLOAD)
    public void videoUploadMetric(@KafkaKey Long id, Video video){
        System.out.printf("Video uploaded: %d%n", id);
    }
}
