package uk.ac.york.eng2.vm.gen.events;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;
import uk.ac.york.eng2.vm.domain.Video;

@KafkaClient
public interface ProducerGen {

	String TOPIC_VIEW = "view-video";
	String TOPIC_LIKE = "like-video";
	String TOPIC_DISLIKE = "dislike-video";
	String TOPIC_UPLOAD = "upload-video";
	
	@Topic(TOPIC_VIEW)
	void viewVideo(@KafkaKey Long userId, Video video);
	
	@Topic(TOPIC_LIKE)
	void likeVideo(@KafkaKey Long userId, Video video);
	
	@Topic(TOPIC_DISLIKE)
	void dislikeVideo(@KafkaKey Long userId, Video video);
	
	@Topic(TOPIC_UPLOAD)
	void uploadVideo(@KafkaKey Long videoId, Video video);
	
}