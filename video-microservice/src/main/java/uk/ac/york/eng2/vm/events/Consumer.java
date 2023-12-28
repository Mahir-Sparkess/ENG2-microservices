package uk.ac.york.eng2.vm.events;

/* protected region imports on begin */
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.Topic;

import uk.ac.york.eng2.vm.domain.VideoExt;
import uk.ac.york.eng2.vm.gen.domain.*;
import uk.ac.york.eng2.vm.gen.dto.*;
/* protected region imports end */

@KafkaListener(groupId="todo")
public class Consumer {
	/* protected region Injects on begin */
	
	/* protected region Injects end */
	
	public static final String TOPIC_UPLOAD = "upload-video";
	
	@Topic(TOPIC_UPLOAD)
	public /* protected region Return on begin */void/* protected region Return end */ newVideo(
		/* protected region parameters on begin */
		@KafkaKey Long videoId, 
		VideoExt video
		/* protected region parameters end */) {
		
		/* protected region Method Implementation on begin */
		System.out.printf("Video uploaded: %d by user %s%n", videoId, video.getUploader().getUsername());
		/* protected region Method Implementation end */
		}
	
}