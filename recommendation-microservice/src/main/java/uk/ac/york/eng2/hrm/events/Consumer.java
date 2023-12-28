package uk.ac.york.eng2.hrm.gen.events;

/* protected region imports on begin */
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.Topic;

import uk.ac.york.eng2.hrm.gen.domain.*;
import uk.ac.york.eng2.hrm.gen.dto.*;
/* protected region imports end */

@KafkaListener(groupId="todo")
public class Consumer {
	/* protected region Injects on begin */
	/* protected region Injects end */
	
	public static final String TOPIC_SUBSCRIBE = "subscribe-hashtag";
	public static final String TOPIC_UNSUBSCRIBE = "unsubscribe-hashtag";
	
	@Topic(TOPIC_SUBSCRIBE)
	public /* protected region Return on begin */void/* protected region Return end */ subscribeHashtag(
		/* protected region parameters on begin */
		@KafkaKey Long userId, 
		Hashtag hashtag
		/* protected region parameters end */) {
		
		/* protected region Method Implementation on begin */
		return null;
		/* protected region Method Implementation end */
		}
	
	@Topic(TOPIC_UNSUBSCRIBE)
	public /* protected region Return on begin */void/* protected region Return end */ unsubscribeHashtag(
		/* protected region parameters on begin */
		@KafkaKey Long userId, 
		Hashtag hashtag
		/* protected region parameters end */) {
		
		/* protected region Method Implementation on begin */
		return null;
		/* protected region Method Implementation end */
		}
	
	@Topic(TOPIC_UNSUBSCRIBE)
	public /* protected region Return on begin */void/* protected region Return end */ unsubscribeHashtag(
		/* protected region parameters on begin */@KafkaKey Long userId, 
		Hashtag hashtag
		/* protected region parameters end */) {
		
		/* protected region Method Implementation on begin */
		return null;
		/* protected region Method Implementation end */
		}
	
}