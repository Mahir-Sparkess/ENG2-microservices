package uk.ac.york.eng2.vm.events;

import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.Topic;
import jakarta.inject.Inject;
import uk.ac.york.eng2.vm.domain.HashTag;
import uk.ac.york.eng2.vm.domain.User;
import uk.ac.york.eng2.vm.domain.Video;
import uk.ac.york.eng2.vm.repositories.VideosRepository;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;
import java.util.Set;

@KafkaListener(groupId = "videos-debug")
public class VideoConsumer {
    @Inject
    VideosProducer producer;

    @Inject
    VideosRepository videosRepository;

    @Topic(VideosProducer.TOPIC_VIEW)
    public void videoViewsMetric(@KafkaKey Long id, User user){
        System.out.printf("Video %d viewed by: %s%n", id, user.getUsername());
    }

    @Topic(VideosProducer.TOPIC_UPLOAD)
    public void videoUploadMetric(@KafkaKey Long id, Video video){
        System.out.printf("Video uploaded: %d%n", id);
    }

    @Topic(VideosProducer.TOPIC_LIKE_DISLIKE)
    public void hashTagStatus(@KafkaKey Long id, String likeOrDislike){
        if (likeOrDislike.equals("like")) {
            Optional<Video> video = videosRepository.findById(id);
            Set<HashTag> tags = video.get().getTags();
            if (!tags.isEmpty()){
                for (HashTag tag : tags){
                    producer.likeTag(tag.getId(), tag);
                }
            }
        }
    }
}
