package uk.ac.york.eng2.sm.client;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.client.annotation.Client;
import uk.ac.york.eng2.sm.domains.HashtagExt;
import uk.ac.york.eng2.sm.domains.VideoExt;

@Client("/sm")
public interface SubscriptionControllerClient {
    @Get("/")
    public /* protected region return on begin */Iterable<String>/* protected region return end */ getHealthCheck(/* protected region parameters on begin */
            /* protected region parameters end */);

    @Get("/{userId}")
    public Iterable<HashtagExt> getUserHashtags(Long userId);

    @Get("/{userId}/next")
    public /* protected region return on begin */Iterable<VideoExt>/* protected region return end */ getSubscription(/* protected region parameters on begin */
            Long userId
            /* protected region parameters end */);

    @Put("/{hashtag}/subscribe/{userId}")
    public /* protected region return on begin */HttpResponse<String>/* protected region return end */ subscribeHashtag(/* protected region parameters on begin */
            Long userId,
            String hashtag	/* protected region parameters end */);

    @Put("/{hashtag}/unsubscribe/{userId}")
    public /* protected region return on begin */HttpResponse<String>/* protected region return end */ unsubscribeHashtag(/* protected region parameters on begin */
            Long userId,
            String hashtag /* protected region parameters end */);
}
