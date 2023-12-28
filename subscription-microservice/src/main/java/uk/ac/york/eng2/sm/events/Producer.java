package uk.ac.york.eng2.sm.gen.events;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;

import uk.ac.york.eng2.sm.gen.domain.*;
import uk.ac.york.eng2.sm.gen.dto.*;
import uk.ac.york.eng2.sm.domain.*;
import uk.ac.york.eng2.sm.dto.*;

@KafkaClient
public interface Producer {

	String TOPIC_SUBSCRIBE = "subscribe-hashtag";
	String TOPIC_UNSUBSCRIBE = "unsubscribe-hashtag";
	
	@Topic(TOPIC_SUBSCRIBE)
	void subscribeHashtag(/* protected region parameters on begin */@KafkaKey Long userId, Hashtag hashtag/* protected region parameters end */);
	
	@Topic(TOPIC_UNSUBSCRIBE)
	void unsubscribeHashtag(/* protected region parameters on begin */@KafkaKey Long userId, Hashtag hashtag/* protected region parameters end */);
	
}