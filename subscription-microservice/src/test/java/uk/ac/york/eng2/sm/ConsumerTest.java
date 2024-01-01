package uk.ac.york.eng2.sm;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ac.york.eng2.sm.client.SubscriptionControllerClient;
import uk.ac.york.eng2.sm.domains.HashtagExt;
import uk.ac.york.eng2.sm.domains.UserExt;
import uk.ac.york.eng2.sm.domains.VideoExt;
import uk.ac.york.eng2.sm.dto.VideoDTO;
import uk.ac.york.eng2.sm.events.Consumer;
import uk.ac.york.eng2.sm.repositories.HashtagRepository;
import uk.ac.york.eng2.sm.repositories.UserRepository;
import uk.ac.york.eng2.sm.repositories.VideoRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(transactional = false)
public class ConsumerTest {
    @Inject
    Consumer consumer;

    @Inject
    UserRepository uRepo;

    @Inject
    HashtagRepository hRepo;

    @Inject
    VideoRepository vRepo;

    @BeforeEach
    public void clean(){
        vRepo.deleteAll();
        uRepo.deleteAll();
        hRepo.deleteAll();
    }

    @Test
    public void newUser(){
        String testUsername = "test username";
        consumer.newUser(1L, testUsername);
        UserExt userExt = uRepo.findByUserId(1L).get();
        assertEquals(userExt.getUsername(), testUsername,
                "test user should have been created through consumer");
    }

    @Test
    public void newVideo(){
        VideoDTO newVideo = new VideoDTO();
        String testTitle = "test title";
        newVideo.setTitle(testTitle);
        newVideo.setTags(new HashSet<>());

        consumer.newVideo(2L, newVideo);

        VideoExt video = vRepo.findByVideoId(2L).get();
        assertEquals(video.getTitle(), testTitle,
                "test video should have been created through consumer");
    }

    @Test
    public void newVideoNewTag(){
        UserExt newUser = new UserExt();
        String testUsername = "test username";
        newUser.setUsername(testUsername);
        newUser.setUserId(1L);
        uRepo.save(newUser);

        VideoDTO newVideo = new VideoDTO();
        String testTitle = "test title";
        newVideo.setTitle(testTitle);
        newVideo.setTags(new HashSet<>());
        String testTag = "test tag";
        newVideo.getTags().add(testTag);

        consumer.newVideo(2L, newVideo);
        VideoExt video = vRepo.findByVideoId(2L).get();
        assertEquals(video.getTitle(), testTitle,
                "test video should have been created through consumer");

        Set<HashtagExt> tags = video.getTags();
        HashtagExt tag = tags.iterator().next();
        assertEquals(tag.getName(), testTag,
                "New tag should have been added through video");
    }

    @Test
    public void addViewer() {
        UserExt newUser = new UserExt();
        String testUsername = "test username";
        newUser.setUsername(testUsername);
        newUser.setViewed(new HashSet<>());
        newUser.setUserId(1L);
        uRepo.save(newUser);

        VideoExt newVideo = new VideoExt();
        String testTitle = "test title";
        newVideo.setTitle(testTitle);
        newVideo.setTags(new HashSet<>());
        newVideo.setViewers(new HashSet<>());
        newVideo.setVideoId(2L);
        vRepo.save(newVideo);

        consumer.viewVideo(1L, 2L);
        VideoExt video = vRepo.findByVideoId(2L).get();
        Set<UserExt> viewers = video.getViewers();
        assertFalse(viewers.isEmpty(),
                "Video should have viewers");
        assertEquals(viewers.iterator().next().getUsername(), testUsername,
                "Test user should be added to video viewers");
    }
}
