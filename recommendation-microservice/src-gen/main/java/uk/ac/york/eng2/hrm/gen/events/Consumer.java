package uk.ac.york.eng2.hrm.gen.events;

import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.Topic;

import uk.ac.york.eng2.hrm.gen.domain.*;
import uk.ac.york.eng2.hrm.gen.dto.*;

@KafkaListener(groupId="todo")
public abstract class Consumer {
	
	public static final String TOPIC_SUBSCRIBE = "subscribe-hashtag";
	public static final String TOPIC_UNSUBSCRIBE = "unsubscribe-hashtag";
	@Topic(TOPIC_SUBSCRIBE)
	public abstract void subscribeHashtag(@KafkaKey Long userId, Hashtag hashtag);
	
	@Topic(TOPIC_UNSUBSCRIBE)
	public abstract void unsubscribeHashtag(@KafkaKey Long userId, Hashtag hashtag);
	
}