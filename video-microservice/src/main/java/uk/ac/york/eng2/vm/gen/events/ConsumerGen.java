package uk.ac.york.eng2.vm.gen.events;

import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.Topic;
import uk.ac.york.eng2.vm.domain.Video;

@KafkaListener(groupId="todo")
public abstract class ConsumerGen {
	
	@Topic(ProducerGen.TOPIC_UPLOAD)
	public abstract void newVideo(@KafkaKey Long videoId, Video video);
	
}