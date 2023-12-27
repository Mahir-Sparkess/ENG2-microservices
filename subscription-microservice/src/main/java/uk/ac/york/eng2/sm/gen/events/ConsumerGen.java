package uk.ac.york.eng2.sm.gen.events;

import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.Topic;

@KafkaListener(groupId="todo")
public abstract class ConsumerGen {
	
	@Topic(ProducerGen.TOPIC_VIEW)
	public abstract void viewVideo(@KafkaKey Long userId, Video video);
	
	@Topic(ProducerGen.TOPIC_LIKE)
	public abstract void likeVideo(@KafkaKey Long userId, Video video);
	
	@Topic(ProducerGen.TOPIC_DISLIKE)
	public abstract void dislikeVideo(@KafkaKey Long userId, Video video);
	
}