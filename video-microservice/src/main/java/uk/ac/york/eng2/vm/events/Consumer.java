package uk.ac.york.eng2.vm.events;

/* protected region imports on begin */
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.Topic;

import uk.ac.york.eng2.vm.domain.VideoExt;
import uk.ac.york.eng2.vm.gen.domain.*;
import uk.ac.york.eng2.vm.gen.dto.*;
/* protected region imports end */

@KafkaListener(groupId="VM")
public class Consumer {
	/* protected region Injects on begin */
	
	/* protected region Injects end */
}