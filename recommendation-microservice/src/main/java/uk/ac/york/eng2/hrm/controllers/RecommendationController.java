package uk.ac.york.eng2.hrm.controllers;
/* protected region imports on begin */
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;
import uk.ac.york.eng2.hrm.domains.HashtagExt;
import uk.ac.york.eng2.hrm.domains.SubscriberExt;
import uk.ac.york.eng2.hrm.repositories.HashtagRepository;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/* protected region imports end */

@Controller("/hrm")
public class RecommendationController {

	/* protected region injects on begin */
	@Inject
	HashtagRepository hRepo;
	/* protected region injects end */

	@Get("/")
	public /* protected region return on begin */Iterable<HashtagExt>/* protected region return end */ getHealthCheck(/* protected region parameters on begin */
	/* protected region parameters end */) {
	/* protected region Method Implementation on begin */
		return hRepo.findAll();
	/* protected region Method Implementation end */
	}
	
	@Get("/{hashtag}")
	public /* protected region return on begin */Iterable<HashtagExt>/* protected region return end */ getRecommendation(/* protected region parameters on begin */
	String hashtag	/* protected region parameters end */) {
	/* protected region Method Implementation on begin */
		System.out.println(hashtag);
		Optional<HashtagExt> oTag = hRepo.findByName(hashtag);
		if (oTag.isEmpty()) {
			return null;
		}
		HashtagExt tag = oTag.get();
		List<Long> userIds = tag.getSubscribers().stream()
				.map(SubscriberExt::getUserId)
				.collect(Collectors.toList());
		return hRepo.findTop10ByUserIdsAndExcludeHashtagName(userIds, hashtag);
	/* protected region Method Implementation end */
	}
	
}