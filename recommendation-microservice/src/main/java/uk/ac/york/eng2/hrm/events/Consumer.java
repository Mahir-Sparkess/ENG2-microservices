package uk.ac.york.eng2.hrm.events;

/* protected region imports on begin */
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.Topic;
import jakarta.inject.Inject;
import uk.ac.york.eng2.hrm.domains.HashtagExt;
import uk.ac.york.eng2.hrm.domains.SubscriberExt;
import uk.ac.york.eng2.hrm.repositories.HashtagRepository;
import uk.ac.york.eng2.hrm.repositories.SubscriberRepository;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
/* protected region imports end */

@KafkaListener(groupId="HRM")
public class Consumer {
	/* protected region Injects on begin */
	@Inject
	HashtagRepository hRepo;

	@Inject
	SubscriberRepository sRepo;
	/* protected region Injects end */
	
	public static final String TOPIC_SUBSCRIBE = "subscribe-hashtag";
	public static final String TOPIC_UNSUBSCRIBE = "unsubscribe-hashtag";
	
	@Topic(TOPIC_SUBSCRIBE)
	@Transactional
	public /* protected region Return on begin */void/* protected region Return end */ subscribeHashtag(
		/* protected region parameters on begin */
		@KafkaKey Long userId, 
		String hashtag
		/* protected region parameters end */) {
		/* protected region Method Implementation on begin */
		Optional<SubscriberExt> oSubscriber = sRepo.findByUserId(userId);
		SubscriberExt subscriber;
		if (oSubscriber.isEmpty()){
			SubscriberExt newSubscriber = new SubscriberExt();
			newSubscriber.setUserId(userId);
			newSubscriber.setSubscriptions(new HashSet<>());
			sRepo.save(newSubscriber);

			System.out.println(String.format("Subscriber %d created", userId));
			subscriber = newSubscriber;
		} else { subscriber = oSubscriber.get(); }

		Optional<HashtagExt> oTag = hRepo.findByName(hashtag);
		HashtagExt tag;
		if (oTag.isEmpty()) {
			HashtagExt newTag = new HashtagExt();
			newTag.setName(hashtag);
			hRepo.save(newTag);

			System.out.println(String.format("#%s created", hashtag));
			tag = newTag;
		} else { tag = oTag.get(); }

		subscriber.getSubscriptions().add(tag);
		sRepo.update(subscriber);
		System.out.println(String.format("User %d subscribed to #%s", userId, hashtag));
		/* protected region Method Implementation end */
		}
	
	@Topic(TOPIC_UNSUBSCRIBE)
	@Transactional
	public /* protected region Return on begin */void/* protected region Return end */ unsubscribeHashtag(
		/* protected region parameters on begin */
		@KafkaKey Long userId, 
		String hashtag
		/* protected region parameters end */) {
		Optional<SubscriberExt> oSubscriber = sRepo.findByUserId(userId);
		SubscriberExt subscriber;
		if (oSubscriber.isEmpty()){
			SubscriberExt newSubscriber = new SubscriberExt();
			newSubscriber.setUserId(userId);
			newSubscriber.setSubscriptions(new HashSet<>());
			sRepo.save(newSubscriber);

			System.out.println(String.format("Subscriber %d created", userId));
			subscriber = newSubscriber;
		} else { subscriber = oSubscriber.get(); }

		Optional<HashtagExt> oTag = hRepo.findByName(hashtag);
		HashtagExt tag;
		if (oTag.isEmpty()) {
			HashtagExt newTag = new HashtagExt();
			newTag.setName(hashtag);
			hRepo.save(newTag);

			System.out.println(String.format("#%s created", hashtag));
			tag = newTag;
		} else { tag = oTag.get(); }

		subscriber.getSubscriptions().remove(tag);
		sRepo.update(subscriber);
		System.out.println(String.format("User %d unsubscribed from #%s", userId, hashtag));
		/* protected region Method Implementation on begin */
		/* protected region Method Implementation end */
		}

	
}