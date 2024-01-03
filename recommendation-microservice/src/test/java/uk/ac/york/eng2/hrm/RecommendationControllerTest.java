package uk.ac.york.eng2.hrm;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ac.york.eng2.hrm.client.RecommendationControllerClient;
import uk.ac.york.eng2.hrm.domains.HashtagExt;
import uk.ac.york.eng2.hrm.domains.SubscriberExt;
import uk.ac.york.eng2.hrm.repositories.HashtagRepository;
import uk.ac.york.eng2.hrm.repositories.SubscriberRepository;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@MicronautTest(transactional = false)
public class RecommendationControllerTest {
    @Inject
    RecommendationControllerClient client;

    @Inject
    SubscriberRepository uRepo;

    @Inject
    HashtagRepository hRepo;

    @BeforeEach
    public void clean(){
        uRepo.deleteAll();
        hRepo.deleteAll();
    }

    @Test
    public void noTags() {
        Iterable<HashtagExt> iterTags = client.getHealthCheck();
        assertFalse(iterTags.iterator().hasNext(),
                "There should be no hashtags initially");
    }

    @Test
    public void getAllTags(){
        HashtagExt newTag = new HashtagExt();
        String test = "test";
        newTag.setName(test);
        hRepo.save(newTag);

        Iterable<HashtagExt> tags = client.getHealthCheck();
        HashtagExt tag = tags.iterator().next();

        assertEquals(tag.getName(), test,
                "test hashtag should be retrieved from get all");
    }

    @Test
    public void getNoRecommendations() {
        HashtagExt newTag = new HashtagExt();
        String test = "test";
        newTag.setName(test);
        newTag.setSubscribers(new HashSet<>());
        hRepo.save(newTag);

        Iterable<HashtagExt> iterTags = client.getRecommendation(test);

        assertFalse(iterTags.iterator().hasNext(),
                "There should not be any recommendations yet");
    }

    @Test
    public void getRecommendation(){
        HashtagExt newTag1 = new HashtagExt();
        newTag1.setName("tag1");
        newTag1.setSubscribers(new HashSet<>());
        hRepo.save(newTag1);

        HashtagExt newTag2 = new HashtagExt();
        newTag2.setName("tag2");
        newTag2.setSubscribers(new HashSet<>());
        hRepo.save(newTag2);

        HashtagExt newTag3 = new HashtagExt();
        newTag3.setName("tag3");
        newTag3.setSubscribers(new HashSet<>());
        hRepo.save(newTag3);

        SubscriberExt newUser1 = new SubscriberExt();
        newUser1.setUserId(1L);
        newUser1.setSubscriptions(new HashSet<>());
        newUser1.getSubscriptions().add(newTag1);
        uRepo.save(newUser1);

        SubscriberExt newUser2 = new SubscriberExt();
        newUser2.setUserId(2L);
        newUser2.setSubscriptions(new HashSet<>());
        newUser2.getSubscriptions().add(newTag1);
        newUser2.getSubscriptions().add(newTag3);
        uRepo.save(newUser2);

        Iterable<HashtagExt> tags = client.getHealthCheck();

        assertEquals(((Collection<?>) tags).size(), 3,
                "all 3 hashtags should be retrieved from get all");

        Iterable<HashtagExt> recommendations = client.getRecommendation(newTag1.getName());
        assertEquals(((Collection<?>) recommendations).size(), 1,
                "should return with only 1 recommendation");

        HashtagExt tag = recommendations.iterator().next();
        assertEquals(tag.getName(), newTag3.getName(),
                "Recommendation for tag1 should be tag3");

    }
}
