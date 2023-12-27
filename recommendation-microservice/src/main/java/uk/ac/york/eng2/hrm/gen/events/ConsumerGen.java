package uk.ac.york.eng2.hrm.gen.events;

import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.Topic;

@KafkaListener(groupId="todo")
public abstract class ConsumerGen {
	
	@Topic(ProducerGen.TOPIC_SUBSCRIBE)
	public abstract void subscribeHashtag(@KafkaKey Long userId, Hashtag hashtag);
	
	@Topic(ProducerGen.TOPIC_UNSUBSCRIBE)
	public abstract void unsubscribeHashtag(@KafkaKey Long userId, Hashtag hashtag);
	
}