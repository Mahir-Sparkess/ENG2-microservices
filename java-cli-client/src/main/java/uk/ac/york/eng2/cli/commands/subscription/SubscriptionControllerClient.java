package uk.ac.york.eng2.cli.commands.subscription;

/* protected region imports on begin */
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.http.client.annotation.Client;
/* protected region imports end */

@Client("${subscription.url:`http://localhost:8080/subscription}")
public interface SubscriptionControllerClient {

	@Get("/")
	public /* protected region return on begin */Object/* protected region return end */ getHealthCheck(/* protected region parameters on begin */
	/* protected region parameters end */);
	@Get("/{userId}/{hashtagId}/nextVideos")
	public /* protected region return on begin */Object/* protected region return end */ getSubscription(/* protected region parameters on begin */
	Long userId, 
	Long hashtagId	/* protected region parameters end */);
	@Put("/{hastagId}/subscribe/{userId}")
	public /* protected region return on begin */HttpResponse<String>/* protected region return end */ subscribeHashtag(/* protected region parameters on begin */
	Long userId, 
	Long hashtagId	/* protected region parameters end */);
	@Put("/{hastagId}/unsubscribe/{userId}")
	public /* protected region return on begin */HttpResponse<String>/* protected region return end */ unsubscribeHashtag(/* protected region parameters on begin */
	Long userId, 
	Long hashtagId	/* protected region parameters end */);

}