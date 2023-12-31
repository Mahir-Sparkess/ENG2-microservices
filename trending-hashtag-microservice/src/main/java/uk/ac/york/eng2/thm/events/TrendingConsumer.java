package uk.ac.york.eng2.thm.events;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.Topic;
import jakarta.inject.Inject;
import uk.ac.york.eng2.thm.domain.HashTag;
import uk.ac.york.eng2.thm.repositories.HashTagsRepository;

import javax.transaction.Transactional;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@KafkaListener(groupId="THM")
public class TrendingConsumer {

    @Inject
    TrendingProducer producer;

    @Inject
    HashTagsRepository repo;
    public static final String TOPIC_LIKES_BY_HOUR = "hashtag-likes-by-hour";
    public static final  String TOPIC_LIKE = "like-video";
    public static final  String TOPIC_DISLIKE = "dislike-video";

    @Topic(TOPIC_LIKE)
    public void likeHashtag(@KafkaKey Long id, String string) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<String> tags = objectMapper.readValue(string, new TypeReference<List<String>>() {});

            for (String name : tags){
                Optional<HashTag> oTag = repo.findByName(name);
                if (oTag.isEmpty()){
                    HashTag tag = new HashTag();
                    tag.setName(name);
                    tag.setLikes(1);
                    tag.setDislikes(0);
                    tag.setTrendingActivity(0L);
                    tag.setLatestActivity(0L);
                    repo.save(tag);

                    System.out.println(String.format("#%s received like!", name));
                    producer.trendingHashtag(tag.getId(), tag);
                } else {
                    HashTag tag = oTag.get();
                    tag.setLikes(tag.getLikes() + 1);
                    repo.update(tag);

                    System.out.println(String.format("#%s received like!", name));
                    producer.trendingHashtag(tag.getId(), tag);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Topic(TOPIC_DISLIKE)
    @Transactional
    public void dislikeHashtag(@KafkaKey Long id, String string) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<String> tags = objectMapper.readValue(string, new TypeReference<List<String>>() {});

            for (String name : tags){
                Optional<HashTag> oTag = repo.findByName(name);
                if (oTag.isEmpty()){
                    HashTag tag = new HashTag();
                    tag.setName(name);
                    tag.setLikes(0);
                    tag.setDislikes(1);
                    tag.setTrendingActivity(0L);
                    tag.setLatestActivity(0L);
                    repo.save(tag);

                    System.out.println(String.format("#%s received dislike!", name));
//                    producer.trendingHashtag(tag.getId(), tag);
                } else {
                    HashTag tag = oTag.get();
                    tag.setDislikes(tag.getDislikes() + 1);
                    repo.update(tag);

                    System.out.println(String.format("#%s received dislike!", name));
//                    producer.trendingHashtag(tag.getId(), tag);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Topic(TOPIC_LIKES_BY_HOUR)
    @Transactional
    public void trendingHashtag(@KafkaKey String string, Long count){
        System.out.println(string);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            HashMap<String, Object> msg = objectMapper.readValue(string, new TypeReference<HashMap<String, Object>>() {});

            Long id = ((Number) msg.get("id")).longValue();
            Long startWindow = ((Number) msg.get("startMillis")).longValue();

            HashTag tag = repo.findById(id).get();
            if (startWindow >= tag.getLatestActivity()){
                tag.setLatestActivity(startWindow);
                tag.setTrendingActivity(count);

                repo.update(tag);
                System.out.println(String.format("#%s current trending like %d", tag.getName(), count));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
