package uk.ac.york.eng2.sm.gen.events;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;

@KafkaClient
public interface ProducerGen {

	String TOPIC_SUBSCRIBE = "subscribe-hashtag";
	String TOPIC_UNSUBSCRIBE = "unsubscribe-hashtag";
	
	@Topic(TOPIC_SUBSCRIBE)
	void subscribeHashtag(@KafkaKey Long userId, Hashtag hashtag);
	
	@Topic(TOPIC_UNSUBSCRIBE)
	void unsubscribeHashtag(@KafkaKey Long userId, Hashtag hashtag);
	
}