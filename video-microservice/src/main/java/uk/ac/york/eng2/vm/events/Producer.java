package uk.ac.york.eng2.vm.events;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;

import uk.ac.york.eng2.vm.gen.domain.*;
import uk.ac.york.eng2.vm.gen.dto.*;
import uk.ac.york.eng2.vm.domain.*;
import uk.ac.york.eng2.vm.dto.*;

import java.util.Collection;
import java.util.Set;

@KafkaClient
public interface Producer {

	String TOPIC_VIEW = "view-video";
	String TOPIC_LIKE = "like-video";
	String TOPIC_DISLIKE = "dislike-video";
	String TOPIC_UPLOAD = "upload-video";
	String TOPIC_NEW_USER = "new-user";
	
	@Topic(TOPIC_VIEW)
	void viewVideo(/* protected region parameters on begin */@KafkaKey Long userId, Long videoId/* protected region parameters end */);
	
	@Topic(TOPIC_LIKE)
	void likeVideo(/* protected region parameters on begin */@KafkaKey Long userId, Set<String> tagNames/* protected region parameters end */);
	
	@Topic(TOPIC_DISLIKE)
	void dislikeVideo(/* protected region parameters on begin */@KafkaKey Long userId, Set<String> tagNames/* protected region parameters end */);
	
	@Topic(TOPIC_UPLOAD)
	void uploadVideo(/* protected region parameters on begin */@KafkaKey Long videoId, VideoDTOExt video/* protected region parameters end */);

	@Topic(TOPIC_NEW_USER)
	void newUser(/* protected region parameters on begin */@KafkaKey Long userId, String username/* protected region parameters end */);
}