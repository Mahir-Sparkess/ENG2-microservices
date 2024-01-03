package uk.ac.york.eng2.sm;

import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.context.annotation.Property;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ac.york.eng2.sm.client.SubscriptionControllerClient;
import uk.ac.york.eng2.sm.domains.HashtagExt;
import uk.ac.york.eng2.sm.domains.UserExt;
import uk.ac.york.eng2.sm.events.Producer;
import uk.ac.york.eng2.sm.repositories.HashtagRepository;
import uk.ac.york.eng2.sm.repositories.UserRepository;
import uk.ac.york.eng2.sm.repositories.VideoRepository;

import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Property(name = "spec.name", value = "KafkaProductionTest")
@MicronautTest(transactional = false)
public class KafkaProductionTest {
    @Inject
    SubscriptionControllerClient client;

    @Inject
    UserRepository uRepo;

    @Inject
    HashtagRepository hRepo;

    @Inject
    VideoRepository vRepo;

    private static final Map<Long, String> subUnsubTags = new HashMap<>();

    @BeforeEach
    public void clean() {
        vRepo.deleteAll();
        uRepo.deleteAll();
        hRepo.deleteAll();
        subUnsubTags.clear();
    }

    @Test
    public void getUserSubscribeHashtags() {
        UserExt newUser = new UserExt();
        newUser.setUserId(1L);
        newUser.setUsername("test user");
        uRepo.save(newUser);

        HashtagExt newTag = new HashtagExt();
        String testTag = "test tag";
        newTag.setName(testTag);
        hRepo.save(newTag);

        client.subscribeHashtag(newUser.getUserId(), testTag);
        Awaitility.await()
                .atMost(Duration.ofSeconds(30))
                .until(() -> (subUnsubTags.containsKey(newUser.getUserId()) && subUnsubTags.containsValue(testTag)));
    }

    @Test
    public void unsubscribeUserHashtags(){
        HashtagExt newTag = new HashtagExt();
        String testTag = "test tag";
        newTag.setName(testTag);
        hRepo.save(newTag);

        UserExt newUser = new UserExt();
        newUser.setUserId(1L);
        newUser.setUsername("test user");
        newUser.setSubscriptions(new HashSet<>());
        newUser.getSubscriptions().add(newTag);
        uRepo.save(newUser);

        client.unsubscribeHashtag(newUser.getUserId(), testTag);
        Awaitility.await()
                .atMost(Duration.ofSeconds(30))
                .until(() -> (subUnsubTags.containsKey(newUser.getUserId()) && subUnsubTags.containsValue(testTag)));

    }

    @Requires(property = "spec.name", value = "KafkaProductionTest")
    @KafkaListener(groupId = "kafka-production-test")
    static class TestConsumer{
        @Topic({Producer.TOPIC_UNSUBSCRIBE, Producer.TOPIC_SUBSCRIBE})
        void subUnsub(@KafkaKey Long userId, String hashtag){
            subUnsubTags.put(userId, hashtag);
        }
    }
}
