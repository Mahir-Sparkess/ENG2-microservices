package uk.ac.york.eng2.thm;

import io.micronaut.context.annotation.Property;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ac.york.eng2.thm.clients.TrendingControllerClient;
import uk.ac.york.eng2.thm.domain.HashTag;
import uk.ac.york.eng2.thm.repositories.HashTagsRepository;

import java.time.Instant;
import java.util.Collection;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(transactional = false)
public class TrendingControllerTest {

    @Inject
    TrendingControllerClient client;

    @Inject
    HashTagsRepository repo;

    @BeforeEach
    public void clean() {
        repo.deleteAll();
    }

    @Test
    public void noTags() {
        Iterable<HashTag> tags = client.getAll();
        assertFalse(tags.iterator().hasNext(),
                "Service should not have any tags initially");
    }

    @Test
    public void trendingTags() {
        for (int i = 0; i < 11; i++) {
            HashTag newTag = new HashTag();
            newTag.setLikes(new Random().nextInt(100) + 1);
            newTag.setDislikes(new Random().nextInt(100) + 1);
            newTag.setTrendingActivity(new Random().nextLong(100) + 1);
            newTag.setName(String.format("%d", i));
            newTag.setLatestActivity(Instant.now().toEpochMilli());
            repo.save(newTag);
        }
        Iterable<HashTag> tags = client.getAll();
        assertEquals(((Collection<?>) tags).size(), 11,
                "Should retrieve all 11 tags created");

        Iterable<HashTag> trends = client.trending();
        assertEquals(((Collection<?>) trends).size(), 10,
                "Should retrieve top 10 tags trending");
    }

    @Test
    public void outOfTrendTags() {
        HashTag newTag = new HashTag();
        newTag.setLikes(new Random().nextInt(100) + 1);
        newTag.setDislikes(new Random().nextInt(100) + 1);
        newTag.setTrendingActivity(new Random().nextLong(100) + 1);
        newTag.setName("test");
        newTag.setLatestActivity(Instant.now().toEpochMilli() - TimeUnit.HOURS.toMillis(2));
        repo.save(newTag);

        Iterable<HashTag> tags = client.getAll();
        assertTrue(tags.iterator().hasNext(),
                "Should retrieve 1 tags created");

        Iterable<HashTag> trends = client.trending();
        assertEquals(((Collection<?>) trends).size(), 0,
                "Should retrieve 0 tags trending");
    }

}
