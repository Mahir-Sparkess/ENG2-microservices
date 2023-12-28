package uk.ac.york.eng2.sm.gen.events;

import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.Topic;

import uk.ac.york.eng2.sm.gen.domain.*;
import uk.ac.york.eng2.sm.gen.dto.*;

@KafkaListener(groupId="todo")
public abstract class Consumer {
	
	public static final String TOPIC_VIEW = "view-video";
	public static final String TOPIC_LIKE = "like-video";
	public static final String TOPIC_DISLIKE = "dislike-video";
	@Topic(TOPIC_VIEW)
	public abstract void viewVideo(@KafkaKey Long userId, Video video);
	
	@Topic(TOPIC_LIKE)
	public abstract void likeVideo(@KafkaKey Long userId, Video video);
	
	@Topic(TOPIC_DISLIKE)
	public abstract void dislikeVideo(@KafkaKey Long userId, Video video);
	
}