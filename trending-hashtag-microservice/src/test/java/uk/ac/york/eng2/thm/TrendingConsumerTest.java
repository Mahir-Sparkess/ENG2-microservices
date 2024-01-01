package uk.ac.york.eng2.thm;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ac.york.eng2.thm.domain.HashTag;
import uk.ac.york.eng2.thm.events.TrendingConsumer;
import uk.ac.york.eng2.thm.repositories.HashTagsRepository;

import java.time.Instant;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
@MicronautTest(transactional = false, environments = "no_streams")
public class TrendingConsumerTest {

    @Inject
    HashTagsRepository repo;

    @Inject
    TrendingConsumer consumer;

    @BeforeEach
    public void clean(){
        repo.deleteAll();
    }

    @Test
    public void noHashtagLike() {
        String testTag = "[\"test\"]";
        consumer.likeHashtag(0L, testTag);

        Optional<HashTag> oTag = repo.findByName("test");

        assertEquals(oTag.get().getName(), "test",
                "Consumer should have created tag named test");
        assertEquals(oTag.get().getLikes(), 1,
                "Consumer should have registered like on creation.");
    }

    @Test
    public void noHashtagDislike() {
        String testTag = "[\"test\"]";
        consumer.dislikeHashtag(0L, testTag);

        Optional<HashTag> oTag = repo.findByName("test");

        assertEquals(oTag.get().getName(), "test",
                "Consumer should have created tag named test");
        assertEquals(oTag.get().getDislikes(), 1,
                "Consumer should have registered dislike on creation.");
    }

    @Test
    public void likeDislikeHashtag() {
        String testTag = "[\"test\"]";
        HashTag newTag = new HashTag();
        newTag.setLikes(0);
        newTag.setDislikes(0);
        newTag.setTrendingActivity(0L);
        newTag.setName("test");
        newTag.setLatestActivity(Instant.now().toEpochMilli() - TimeUnit.HOURS.toMillis(2));
        repo.save(newTag);

        consumer.dislikeHashtag(0L, testTag);
        consumer.likeHashtag(0L, testTag);

        Optional<HashTag> oTag = repo.findByName("test");
        assertEquals(oTag.get().getDislikes(), 1,
                "Consumer should have registered dislike");
        assertEquals(oTag.get().getLikes(), 1,
                "Consumer should have registered like");
    }

    @Test
    public void updateTrending() {
        HashTag newTag = new HashTag();
        newTag.setLikes(0);
        newTag.setDislikes(0);
        newTag.setTrendingActivity(0L);
        newTag.setName("test");
        newTag.setLatestActivity(0L);
        repo.save(newTag);

        String jsonString = String.format("{\"id\":%d,\"startMillis\":%d,\"endMillis\":%d}",
                newTag.getId(), Instant.now().toEpochMilli(), Instant.now().toEpochMilli());

        consumer.trendingHashtag(jsonString, 11L);
        HashTag tag = repo.findById(newTag.getId()).get();

        assertEquals(tag.getTrendingActivity(), 11L,
                "tag trending activity should match mock message from Kafka stream");
    }

}
