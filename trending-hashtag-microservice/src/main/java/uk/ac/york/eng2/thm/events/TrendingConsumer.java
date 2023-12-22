package uk.ac.york.eng2.thm.events;

import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.Topic;
import jakarta.inject.Inject;
import uk.ac.york.eng2.thm.domain.HashTag;
import uk.ac.york.eng2.thm.repositories.HashTagsRepository;

@KafkaListener(groupId = "trending-debug")
public class TrendingConsumer {
    @Inject
    private HashTagsRepository repo;

    @Topic(TrendingProducer.TOPIC_CREATE_HASHTAG)
    public void createHashTag(@KafkaKey Long id, String name){
        HashTag tag = new HashTag();
        tag.setId(id);
        tag.setName(name);
        repo.save(tag);
        System.out.printf("HashTag %s created with ID %d", name, id);
    }
    @Topic(TrendingProducer.TOPIC_TAG_LIKE)
    public void hashTagStatus(@KafkaKey Long id, HashTag tag){
        System.out.println("Hashtag "+tag.getName()+" liked");
    }
}
