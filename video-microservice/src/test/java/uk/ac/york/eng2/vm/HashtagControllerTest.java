package uk.ac.york.eng2.vm;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ac.york.eng2.vm.clients.HashtagControllerClient;
import uk.ac.york.eng2.vm.domain.HashtagExt;
import uk.ac.york.eng2.vm.domain.UserExt;
import uk.ac.york.eng2.vm.domain.VideoExt;
import uk.ac.york.eng2.vm.gen.domain.Hashtag;
import uk.ac.york.eng2.vm.repositories.HashtagRepository;
import uk.ac.york.eng2.vm.repositories.UserRepository;
import uk.ac.york.eng2.vm.repositories.VideoRepository;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@MicronautTest(transactional = false)
public class HashtagControllerTest {

    @Inject
    HashtagControllerClient client;

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
    public void noTag() {
        Iterable<HashtagExt> iterTags = client.getHashtag();
        assertFalse(iterTags.iterator().hasNext(),
                "Service should not list any users initially");
    }

    @Test
    public void getTagListandVideo(){
        UserExt newUser = new UserExt();
        String testUsername = "test user";
        newUser.setUsername(testUsername);
        uRepo.save(newUser);

        VideoExt newVideo = new VideoExt();
        newVideo.setUploader(newUser);
        String testTitle = "test video";
        newVideo.setTitle(testTitle);
        newVideo.setTags(new HashSet<>());
        vRepo.save(newVideo);

        HashtagExt newTag = new HashtagExt();
        String testTagName = "testTag";
        newTag.setName(testTagName);
        hRepo.save(newTag);

        newVideo.getTags().add(newTag);
        vRepo.update(newVideo);

        Iterable<HashtagExt> iterTags = client.getHashtag();
        HashtagExt tag = iterTags.iterator().next();

        assertEquals(tag.getName(), testTagName,
                String.format("Service should have a hashtag called %s", testTagName));

        Iterable<VideoExt> iterVideos = client.getHashtagVideos(tag.getId());
        VideoExt video = iterVideos.iterator().next();

        assertEquals(video.getTitle(), testTitle,
                "Service should have video with title: " + testTitle);
    }
}
