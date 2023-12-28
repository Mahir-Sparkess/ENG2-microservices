package uk.ac.york.eng2.sm.controllers;
/* protected region imports on begin */
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;

import javax.transaction.Transactional;
import java.net.URI;

import uk.ac.york.eng2.sm.gen.domain.*;
import uk.ac.york.eng2.sm.gen.dto.*;
/* protected region imports end */

@Controller("/subscription")
public class SubscriptionController {

	/* protected region injects on begin */
	/* protected region injects end */

	@Get("/")
	public /* protected region return on begin */Object/* protected region return end */ getHealthCheck(/* protected region parameters on begin */
	/* protected region parameters end */) {
	/* protected region Method Implementation on begin */
		return null;
	/* protected region Method Implementation end */
	}
	
	@Get("/{userId}/{hashtagId}/nextVideos")
	public /* protected region return on begin */Object/* protected region return end */ getSubscription(/* protected region parameters on begin */
	Long userId, 
	Long hashtagId	/* protected region parameters end */) {
	/* protected region Method Implementation on begin */
		return null;
	/* protected region Method Implementation end */
	}
	
	@Put("/{hastagId}/subscribe/{userId}")
	public /* protected region return on begin */HttpResponse<String>/* protected region return end */ subscribeHashtag(/* protected region parameters on begin */
	Long userId, 
	Long hashtagId	/* protected region parameters end */) {
	/* protected region Method Implementation on begin */
		return null;
	/* protected region Method Implementation end */
	}
	
	@Put("/{hastagId}/unsubscribe/{userId}")
	public /* protected region return on begin */HttpResponse<String>/* protected region return end */ unsubscribeHashtag(/* protected region parameters on begin */
	Long userId, 
	Long hashtagId	/* protected region parameters end */) {
	/* protected region Method Implementation on begin */
		return null;
	/* protected region Method Implementation end */
	}
	
}