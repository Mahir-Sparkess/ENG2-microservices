package uk.ac.york.eng2.vm;

import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.context.annotation.Property;
import io.micronaut.context.annotation.Requires;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ac.york.eng2.vm.clients.UserControllerClient;
import uk.ac.york.eng2.vm.domain.UserExt;
import uk.ac.york.eng2.vm.domain.VideoExt;
import uk.ac.york.eng2.vm.events.Producer;
import uk.ac.york.eng2.vm.gen.dto.UserDTO;
import uk.ac.york.eng2.vm.repositories.UserRepository;
import uk.ac.york.eng2.vm.repositories.VideoRepository;

import javax.validation.constraints.AssertFalse;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(transactional = false)
public class UserControllerTest {

    @Inject
    UserRepository userRepository;

    @Inject
    VideoRepository videoRepository;

    @Inject
    UserControllerClient client;

    @BeforeEach
    public void clean() {
        videoRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void noUser() {
        Iterable<UserExt> iterUsers = client.getUsers();
        assertFalse(iterUsers.iterator().hasNext(),
                "Service should not list any users initially");
    }

    @Test
    public void addUser() {
        String testUsername = "test";
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(testUsername);

        client.addUser(userDTO);

        Iterable<UserExt> users = client.getUsers();
        UserExt user = users.iterator().next();

        assertEquals(user.getUsername(), testUsername,
                String.format("Service should have a user named '%s'", testUsername));
    }

    @Test
    public void getVideoByUser() {
        String testUsername = "test";
        UserExt newUser = new UserExt();
        newUser.setUsername(testUsername);
        userRepository.save(newUser);

        String testVideo = "test title";
        VideoExt newVideo = new VideoExt();
        newVideo.setTitle(testVideo);
        newVideo.setUploader(newUser);
        videoRepository.save(newVideo);

        Iterable<VideoExt> videos = client.getUserVideos(newUser.getId());
        VideoExt video = videos.iterator().next();

        assertEquals(video.getUploader().getUsername(), testUsername,
                "Video uploader and user should be the same");
    }

    @Test
    public void getVideoByUserWrongId() {

        String testUsername = "test";
        UserExt newUser = new UserExt();
        newUser.setUsername(testUsername);
        userRepository.save(newUser);

        String testVideo = "test title";
        VideoExt newVideo = new VideoExt();
        newVideo.setTitle(testVideo);
        newVideo.setUploader(newUser);
        videoRepository.save(newVideo);

        Iterable<VideoExt> videos = client.getUserVideos(newUser.getId()+1);

        assertNull(videos,
                "There should be no video by a user that does not exist");
    }
}
