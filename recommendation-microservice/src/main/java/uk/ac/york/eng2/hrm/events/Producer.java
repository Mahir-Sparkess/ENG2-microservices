package uk.ac.york.eng2.hrm.gen.events;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;

import uk.ac.york.eng2.hrm.gen.domain.*;
import uk.ac.york.eng2.hrm.gen.dto.*;
import uk.ac.york.eng2.hrm.domain.*;
import uk.ac.york.eng2.hrm.dto.*;

@KafkaClient
public interface Producer {

	
}