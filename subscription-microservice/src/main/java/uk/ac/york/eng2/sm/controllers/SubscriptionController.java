package uk.ac.york.eng2.sm.controllers;
/* protected region imports on begin */
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import uk.ac.york.eng2.sm.domains.HashtagExt;
import uk.ac.york.eng2.sm.domains.UserExt;
import uk.ac.york.eng2.sm.domains.VideoExt;
import uk.ac.york.eng2.sm.events.Producer;
import uk.ac.york.eng2.sm.gen.domain.*;
import uk.ac.york.eng2.sm.gen.dto.*;
import uk.ac.york.eng2.sm.repositories.HashtagRepository;
import uk.ac.york.eng2.sm.repositories.UserRepository;
import uk.ac.york.eng2.sm.repositories.VideoRepository;
/* protected region imports end */

@Controller("/sm")
public class SubscriptionController {

	/* protected region injects on begin */
	@Inject
	VideoRepository vRepo;

	@Inject
	UserRepository uRepo;

	@Inject
	HashtagRepository hRepo;

	@Inject
	Producer producer;
	/* protected region injects end */

	@Get("/")
	public /* protected region return on begin */Iterable<String>/* protected region return end */ getHealthCheck(/* protected region parameters on begin */
	/* protected region parameters end */) {
	/* protected region Method Implementation on begin */
		Iterable<HashtagExt> tags = hRepo.findAll();
		return StreamSupport.stream(tags.spliterator(), false)
				.map(Hashtag::getName)
				.collect(Collectors.toList());
	/* protected region Method Implementation end */
	}

	@Get("/{userId}")
	public Iterable<HashtagExt> getUserHashtags(Long userId){
		Optional<UserExt> oUser = uRepo.findByUserId(userId);
        return oUser.<Iterable<HashtagExt>>map(UserExt::getSubscriptions).orElse(null);
    }
	
	@Get("/{userId}/next")
	public /* protected region return on begin */Iterable<VideoExt>/* protected region return end */ getSubscription(/* protected region parameters on begin */
	Long userId
	/* protected region parameters end */) {
	/* protected region Method Implementation on begin */
		Optional<UserExt> oUser = uRepo.findByUserId(userId);
		if (oUser.isEmpty()) {
			return null;
		}
		Set<HashtagExt> tags = oUser.get().getSubscriptions();

		List<String> tagNames = tags.stream()
				.map(Hashtag::getName)
				.collect(Collectors.toList());
		System.out.println(String.format("Find top 10 videos by viewer count with tags %s not watched by user %d", tagNames, userId));
		return vRepo.findTop10ByTagsNameAndViewerUserIdNot(tagNames, userId);
	/* protected region Method Implementation end */
	}

	@Transactional
	@Put("/{hashtag}/subscribe/{userId}")
	public /* protected region return on begin */HttpResponse<String>/* protected region return end */ subscribeHashtag(/* protected region parameters on begin */
	Long userId, 
	String hashtag	/* protected region parameters end */) {
	/* protected region Method Implementation on begin */
		Optional<UserExt> oUser = uRepo.findByUserId(userId);
		if (oUser.isEmpty()){
			System.out.println(String.format("User %d not found!", userId));
			return HttpResponse.notFound(String.format("User %d not found!", userId));
		}
		Optional<HashtagExt> oTag = hRepo.findByName(hashtag);
		if (oTag.isEmpty()){
			System.out.println(String.format("#%s not found!", hashtag));
			return HttpResponse.notFound(String.format("#%s not found!", hashtag));
		}
		UserExt user = oUser.get();
		user.getSubscriptions().add(oTag.get());
		uRepo.update(user);
		producer.subscribeHashtag(userId, hashtag);

		System.out.println(String.format("User %d Subscribed to #%s", userId, hashtag));
		return HttpResponse.ok(String.format("User %d Subscribed to #%s", userId, hashtag));
	/* protected region Method Implementation end */
	}

	@Transactional
	@Put("/{hashtag}/unsubscribe/{userId}")
	public /* protected region return on begin */HttpResponse<String>/* protected region return end */ unsubscribeHashtag(/* protected region parameters on begin */
	Long userId,
	String hashtag /* protected region parameters end */) {
	/* protected region Method Implementation on begin */
		Optional<UserExt> oUser = uRepo.findByUserId(userId);
		if (oUser.isEmpty()){
			System.out.println(String.format("User %d not found!", userId));
			return HttpResponse.notFound(String.format("User %d not found!", userId));
		}
		Optional<HashtagExt> oTag = hRepo.findByName(hashtag);
		if (oTag.isEmpty()){
			System.out.println(String.format("#%s not found!", hashtag));
			return HttpResponse.notFound(String.format("#%s not found!", hashtag));
		}
		UserExt user = oUser.get();
		user.getSubscriptions().remove(oTag.get());
		uRepo.update(user);
		producer.unsubscribeHashtag(userId, hashtag);

		System.out.println(String.format("User %d Unsubscribed to #%s", userId, hashtag));
		return HttpResponse.ok(String.format("User %d Unsubscribed to #%s", userId, hashtag));
	/* protected region Method Implementation end */
	}
	
}