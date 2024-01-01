package uk.ac.york.eng2.hrm;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ac.york.eng2.hrm.domains.HashtagExt;
import uk.ac.york.eng2.hrm.domains.SubscriberExt;
import uk.ac.york.eng2.hrm.events.Consumer;
import uk.ac.york.eng2.hrm.repositories.HashtagRepository;
import uk.ac.york.eng2.hrm.repositories.SubscriberRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@MicronautTest(transactional = false)
public class ConsumerTest {
    @Inject
    SubscriberRepository uRepo;

    @Inject
    HashtagRepository hRepo;

    @Inject
    Consumer consumer;

    @BeforeEach
    public void clean() {
        uRepo.deleteAll();
        hRepo.deleteAll();
    }

    @Test
    public void subscribeNewTagNewUser(){
        consumer.subscribeHashtag(1L, "tag1");
        Optional<SubscriberExt> user = uRepo.findByUserId(1L);
        Optional<HashtagExt> tag = hRepo.findByName("tag1");

        assertTrue(user.isPresent(),
                "New user should have been made through consumer");
        assertTrue(tag.isPresent(),
                "New hashtag should have been made through consumer");

        HashtagExt subbedTag = user.get().getSubscriptions().iterator().next();
        assertEquals(subbedTag.getName(), tag.get().getName(),
                "user should have tag in subscriptions");
    }

    @Test
    public void unsubNewTagNewUser(){
        consumer.unsubscribeHashtag(1L, "tag1");
        Optional<SubscriberExt> user = uRepo.findByUserId(1L);
        Optional<HashtagExt> tag = hRepo.findByName("tag1");
        assertTrue(user.isPresent(),
                "New user should have been made through consumer");
        assertTrue(tag.isPresent(),
                "New hashtag should have been made through consumer");
        assertTrue(user.get().getSubscriptions().isEmpty(),
                "user should have no tag in subscription");
    }
    @Test
    public void subUnsub(){
        consumer.subscribeHashtag(1L, "tag1");
        consumer.unsubscribeHashtag(1L, "tag1");
        Optional<SubscriberExt> user = uRepo.findByUserId(1L);
        Optional<HashtagExt> tag = hRepo.findByName("tag1");

        assertTrue(user.isPresent(),
                "New user should have been made through consumer");
        assertTrue(tag.isPresent(),
                "New hashtag should have been made through consumer");
        assertTrue(user.get().getSubscriptions().isEmpty(),
                "user should have no tag in subscription");
    }
}
