package uk.ac.york.eng2.vm;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ac.york.eng2.vm.clients.VideoControllerClient;
import uk.ac.york.eng2.vm.domain.HashtagExt;
import uk.ac.york.eng2.vm.domain.UserExt;
import uk.ac.york.eng2.vm.domain.VideoExt;
import uk.ac.york.eng2.vm.dto.VideoDTOExt;
import uk.ac.york.eng2.vm.events.Producer;
import uk.ac.york.eng2.vm.gen.dto.UserDTO;
import uk.ac.york.eng2.vm.repositories.HashtagRepository;
import uk.ac.york.eng2.vm.repositories.UserRepository;
import uk.ac.york.eng2.vm.repositories.VideoRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(transactional = false)
public class VideoControllerTest {

    @Inject
    VideoControllerClient client;

    @Inject
    HashtagRepository hRepo;

    @Inject
    VideoRepository vRepo;

    @Inject
    UserRepository uRepo;

    @BeforeEach
    public void clean() {
        vRepo.deleteAll();
        hRepo.deleteAll();
        uRepo.deleteAll();
    }

    @Test
    public void noVideos() {
        Iterable<VideoExt> iterVideos = client.getVideos();
        assertFalse(iterVideos.iterator().hasNext(),
                "Service should not list any videos initially");
    }

    @Test
    public void addVideoNoUser() {
        VideoDTOExt newVideo = new VideoDTOExt();
        newVideo.setTitle("test");
        newVideo.setTags(new ArrayList<>());

        HttpResponse<String> r = client.uploadVideo(1L, newVideo);

        assertEquals(r.getStatus(), HttpStatus.NOT_FOUND,
                "HTTP Response should have a not found status");

        Iterable<VideoExt> iterVideos = client.getVideos();
        assertFalse(iterVideos.iterator().hasNext(),
                "Service should not list any videos");
    }

    @Test
    public void newVideo() {
        String testUsername = "test";
        UserExt newUser = new UserExt();
        newUser.setUsername(testUsername);
        uRepo.save(newUser);

        VideoDTOExt newVideo = new VideoDTOExt();
        newVideo.setTitle("test");
        newVideo.setTags(new ArrayList<>());

        HttpResponse<String> r = client.uploadVideo(newUser.getId(), newVideo);

        assertEquals(r.getStatus(), HttpStatus.CREATED,
                "HTTP Response should have a not found status");

        Iterable<VideoExt> iterVideos = client.getVideos();
        assertTrue(iterVideos.iterator().hasNext(),
                "New video should exist in service");
    }

    @Test
    public void getVideoTags() {
        UserExt newUser = new UserExt();
        String testUsername = "test user";
        newUser.setUsername(testUsername);
        uRepo.save(newUser);

        HashtagExt newTag = new HashtagExt();
        String testTagName = "testTag";
        newTag.setName(testTagName);
        hRepo.save(newTag);

        VideoExt newVideo = new VideoExt();
        newVideo.setUploader(newUser);
        String testTitle = "test video";
        newVideo.setTitle(testTitle);
        newVideo.setTags(new HashSet<>());
        vRepo.save(newVideo);
        newVideo.getTags().add(newTag);
        vRepo.update(newVideo);

        Set<HashtagExt> tags = client.getVideoTags(newVideo.getId());

        assertEquals(tags.iterator().next().getName(), testTagName,
                "Video should have tag called " + testTagName);
    }

    @Test
    public void newVideoNewHashtag() {
        UserExt newUser = new UserExt();
        String testUsername = "test user";
        newUser.setUsername(testUsername);
        uRepo.save(newUser);

        VideoDTOExt newVideo = new VideoDTOExt();
        newVideo.setTitle("test");
        String testTagName = "testTag";
        newVideo.setTags(List.of(testTagName));
        client.uploadVideo(newUser.getId(), newVideo);

        assertTrue(hRepo.findByName(testTagName).isPresent(),
                String.format("Hashtag with name %s should have been created with Video", testTagName));
    }

    @Test
    public void viewVideo(){

        UserExt newUser = new UserExt();
        String testUsername = "test user";
        newUser.setUsername(testUsername);
        uRepo.save(newUser);

        HashtagExt newTag = new HashtagExt();
        String testTagName = "testTag";
        newTag.setName(testTagName);
        hRepo.save(newTag);

        VideoExt newVideo = new VideoExt();
        newVideo.setUploader(newUser);
        String testTitle = "test video";
        newVideo.setTitle(testTitle);
        newVideo.setTags(new HashSet<>());
        vRepo.save(newVideo);
        newVideo.getTags().add(newTag);
        vRepo.update(newVideo);

        HttpResponse<String> rView = client.viewVideo(newVideo.getId(), newUser.getId());
        assertEquals(rView.getStatus(), HttpStatus.OK,
                String.format("User %d viewing Video %d must return OK", newUser.getId(), newVideo.getId()));

        HttpResponse<String> rLike = client.likeVideo(newVideo.getId(), newUser.getId());
        assertEquals(rLike.getStatus(), HttpStatus.OK,
                String.format("User %d liking Video %d must return OK", newUser.getId(), newVideo.getId()));

        HttpResponse<String> rDislike = client.dislikeVideo(newVideo.getId(), newUser.getId());
        assertEquals(rDislike.getStatus(), HttpStatus.OK,
                String.format("User %d disliking Video %d must return OK", newUser.getId(), newVideo.getId()));

    }
}
