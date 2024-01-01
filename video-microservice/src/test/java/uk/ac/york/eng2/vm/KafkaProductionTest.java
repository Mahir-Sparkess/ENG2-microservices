package uk.ac.york.eng2.vm;

import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.configuration.kafka.annotation.Topics;
import io.micronaut.context.annotation.Property;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ac.york.eng2.vm.clients.UserControllerClient;
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

import java.time.Duration;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Property(name = "spec.name", value = "KafkaProductionTest")
@MicronautTest(transactional = false)
public class KafkaProductionTest {
    @Inject
    VideoControllerClient vClient;

    @Inject
    UserControllerClient uClient;

    @Inject
    HashtagRepository hRepo;

    @Inject
    VideoRepository vRepo;

    @Inject
    UserRepository uRepo;

    private static final Map<Long, String> newUsers = new HashMap<>();
    private static final Map<Long, VideoExt> newVideos = new HashMap<>();
    private static final Map<Long, Long> viewVideos = new HashMap<>();
    private static final Map<Long, String> likeDislikeVideos = new HashMap<>();

    @BeforeEach
    public void setUp(){
        vRepo.deleteAll();
        uRepo.deleteAll();
        hRepo.deleteAll();
        newUsers.clear();
        newVideos.clear();
        viewVideos.clear();
        likeDislikeVideos.clear();
    }

    @Test
    public void addUser() {
        UserDTO newUser = new UserDTO();
        String testUsername = "test";
        newUser.setUsername(testUsername);
        HttpResponse<String> r = uClient.addUser(newUser);

        assertEquals(r.getStatus(), HttpStatus.CREATED,
                "adding user should be successful");
        Awaitility.await()
                .atMost(Duration.ofSeconds(30))
                .until(() -> newUsers.containsValue(testUsername));
    }

    @Test
    public void addVideo() {
        String testUsername = "test";
        UserExt newUser = new UserExt();
        newUser.setUsername(testUsername);
        uRepo.save(newUser);

        VideoDTOExt newVideo = new VideoDTOExt();
        newVideo.setTitle("test");
        String testTagName = "testTag";
        newVideo.setTags(List.of(testTagName));
        HttpResponse<String> r = vClient.uploadVideo(newUser.getId(), newVideo);

        VideoExt video = vRepo.findAll().iterator().next();

        assertEquals(r.getStatus(), HttpStatus.CREATED,
                "adding video should be successful");
        Awaitility.await()
                .atMost(Duration.ofSeconds(30))
                .until(() -> newVideos.containsKey(video.getId()));
    }

    @Test
    public void likeVideo(){

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

        HttpResponse<String> rLike = vClient.likeVideo(newVideo.getId(), newUser.getId());
        assertEquals(rLike.getStatus(), HttpStatus.OK,
                String.format("User %d liking Video %d must return OK", newUser.getId(), newVideo.getId()));
        Awaitility.await()
                .atMost(Duration.ofSeconds(30))
                .until(() -> likeDislikeVideos.containsKey(newUser.getId()));
    }

    @Test
    public void dislikeVideo(){

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

        HttpResponse<String> rDislike = vClient.dislikeVideo(newVideo.getId(), newUser.getId());
        assertEquals(rDislike.getStatus(), HttpStatus.OK,
                String.format("User %d disliking Video %d must return OK", newUser.getId(), newVideo.getId()));
        Awaitility.await()
                .atMost(Duration.ofSeconds(30))
                .until(() -> likeDislikeVideos.containsKey(newUser.getId()));
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

        HttpResponse<String> rView = vClient.viewVideo(newVideo.getId(), newUser.getId());
        assertEquals(rView.getStatus(), HttpStatus.OK,
                String.format("User %d viewing Video %d must return OK", newUser.getId(), newVideo.getId()));
        Awaitility.await()
                .atMost(Duration.ofSeconds(30))
                .until(() -> viewVideos.containsValue(newVideo.getId()));
    }

    @Requires(property = "spec.name", value = "KafkaProductionTest")
    @KafkaListener(groupId = "kafka-production-test")
    static class TestConsumer{
        @Topic(Producer.TOPIC_NEW_USER)
        void newUser(@KafkaKey Long userId, String username){
            newUsers.put(userId, username);
        }

        @Topic(Producer.TOPIC_UPLOAD)
        void newVideo(@KafkaKey Long videoId, VideoExt video){
            newVideos.put(videoId, video);
        }

        @Topic(Producer.TOPIC_VIEW)
        void viewVideo(@KafkaKey Long userId, Long videoId){
            viewVideos.put(userId, videoId);
        }

        @Topic({Producer.TOPIC_LIKE, Producer.TOPIC_DISLIKE})
        void rateVideo(@KafkaKey Long userId, String tagNames){
            likeDislikeVideos.put(userId, tagNames);
        }
    }
}
