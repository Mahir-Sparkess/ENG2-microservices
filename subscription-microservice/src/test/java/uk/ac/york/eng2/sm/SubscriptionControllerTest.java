package uk.ac.york.eng2.sm;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ac.york.eng2.sm.client.SubscriptionControllerClient;
import uk.ac.york.eng2.sm.domains.HashtagExt;
import uk.ac.york.eng2.sm.domains.UserExt;
import uk.ac.york.eng2.sm.domains.VideoExt;
import uk.ac.york.eng2.sm.events.Producer;
import uk.ac.york.eng2.sm.repositories.HashtagRepository;
import uk.ac.york.eng2.sm.repositories.UserRepository;
import uk.ac.york.eng2.sm.repositories.VideoRepository;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(transactional = false)
public class SubscriptionControllerTest {

    @Inject
    SubscriptionControllerClient client;

    @Inject
    UserRepository uRepo;

    @Inject
    HashtagRepository hRepo;

    @Inject
    VideoRepository vRepo;

    @BeforeEach
    public void clean() {
        vRepo.deleteAll();
        uRepo.deleteAll();
        hRepo.deleteAll();
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

        HttpResponse<String> r = client.subscribeHashtag(newUser.getUserId(), testTag);
        assertEquals(r.getStatus(), HttpStatus.OK,
                "Service should be successful in subscribing");

        Iterable<HashtagExt> userTags = client.getUserHashtags(newUser.getUserId());
        HashtagExt tag = userTags.iterator().next();

        assertEquals(tag.getName(), testTag,
                "User should have tags subscribed to");
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

        HttpResponse<String> r = client.unsubscribeHashtag(newUser.getUserId(), testTag);
        assertEquals(r.getStatus(), HttpStatus.OK,
                "Service should be successful in unsubscribing");
        UserExt user = uRepo.findByUserId(1L).get();
        assertTrue(user.getSubscriptions().isEmpty(),
                "User subscription list should be empty");
    }

    @Test
    public void getUserWatchlist(){
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

        VideoExt newVideo1 = new VideoExt();
        newVideo1.setTags(new HashSet<>());
        newVideo1.getTags().add(newTag);
        newVideo1.setTitle("video1");
        newVideo1.setViewers(new HashSet<>());
        newVideo1.setVideoId(1L);
        vRepo.save(newVideo1);

        VideoExt newVideo2 = new VideoExt();
        newVideo2.setTags(new HashSet<>());
        newVideo2.setTitle("video2");
        newVideo2.setViewers(new HashSet<>());
        newVideo2.setVideoId(2L);
        vRepo.save(newVideo2);

        VideoExt newVideo3 = new VideoExt();
        newVideo3.setTags(new HashSet<>());
        newVideo3.getTags().add(newTag);
        newVideo3.setTitle("video3");
        newVideo3.setViewers(new HashSet<>());
        newVideo3.getViewers().add(newUser);
        newVideo3.setVideoId(3L);
        vRepo.save(newVideo3);

        Iterable<VideoExt> videos = client.getSubscription(newUser.getUserId());

        assertTrue(videos.iterator().hasNext(),
                "User should have video in watch list");
        assertEquals(((Collection<?>) videos).size(), 1,
                "There should only be 1 video in the user watch list");
        assertEquals(videos.iterator().next().getId(), newVideo1.getId(),
                "The correct video in the watch list should be Video1");
    }
}
