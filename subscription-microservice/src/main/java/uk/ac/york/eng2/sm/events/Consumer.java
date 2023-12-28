package uk.ac.york.eng2.sm.gen.events;

/* protected region imports on begin */
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.Topic;

import uk.ac.york.eng2.sm.gen.domain.*;
import uk.ac.york.eng2.sm.gen.dto.*;
/* protected region imports end */

@KafkaListener(groupId="todo")
public class Consumer {
	/* protected region Injects on begin */
	/* protected region Injects end */
	
	public static final String TOPIC_VIEW = "view-video";
	public static final String TOPIC_LIKE = "like-video";
	public static final String TOPIC_DISLIKE = "dislike-video";
	
	@Topic(TOPIC_VIEW)
	public /* protected region Return on begin */void/* protected region Return end */ viewVideo(
		/* protected region parameters on begin */
		@KafkaKey Long userId, 
		Video video
		/* protected region parameters end */) {
		
		/* protected region Method Implementation on begin */
		return null;
		/* protected region Method Implementation end */
		}
	
	@Topic(TOPIC_LIKE)
	public /* protected region Return on begin */void/* protected region Return end */ likeVideo(
		/* protected region parameters on begin */
		@KafkaKey Long userId, 
		Video video
		/* protected region parameters end */) {
		
		/* protected region Method Implementation on begin */
		return null;
		/* protected region Method Implementation end */
		}
	
	@Topic(TOPIC_DISLIKE)
	public /* protected region Return on begin */void/* protected region Return end */ dislikeVideo(
		/* protected region parameters on begin */
		@KafkaKey Long userId, 
		Video video
		/* protected region parameters end */) {
		
		/* protected region Method Implementation on begin */
		return null;
		/* protected region Method Implementation end */
		}
	
	@Topic(TOPIC_LIKE)
	public /* protected region Return on begin */void/* protected region Return end */ likeVideo(
		/* protected region parameters on begin */@KafkaKey Long userId, 
		Video video
		/* protected region parameters end */) {
		
		/* protected region Method Implementation on begin */
		return null;
		/* protected region Method Implementation end */
		}
	
	@Topic(TOPIC_DISLIKE)
	public /* protected region Return on begin */void/* protected region Return end */ dislikeVideo(
		/* protected region parameters on begin */@KafkaKey Long userId, 
		Video video
		/* protected region parameters end */) {
		
		/* protected region Method Implementation on begin */
		return null;
		/* protected region Method Implementation end */
		}
	
}