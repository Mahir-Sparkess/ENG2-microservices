package uk.ac.york.eng2.cli.commands.subscription;

/* protected region imports on begin */
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.http.client.annotation.Client;
import uk.ac.york.eng2.cli.domain.Video;
/* protected region imports end */

@Client("${subscription.url:`http://localhost:8082/sm`}")
public interface SubscriptionControllerClient {

	@Get("/")
	public /* protected region return on begin */Object/* protected region return end */ getHealthCheck(/* protected region parameters on begin */
	/* protected region parameters end */);
	@Get("/{userId}/next")
	public /* protected region return on begin */Iterable<Video>/* protected region return end */ getSubscription(/* protected region parameters on begin */
	Long userId /* protected region parameters end */);
	@Put("/{hashtag}/subscribe/{userId}")
	public /* protected region return on begin */HttpResponse<String>/* protected region return end */ subscribeHashtag(/* protected region parameters on begin */
	String hashtag,
	Long userId	/* protected region parameters end */);
	@Put("/{hashtag}/unsubscribe/{userId}")
	public /* protected region return on begin */HttpResponse<String>/* protected region return end */ unsubscribeHashtag(/* protected region parameters on begin */
	String hashtag,
	Long userId	/* protected region parameters end */);

}