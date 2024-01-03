package uk.ac.york.eng2.hrm.client;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
import uk.ac.york.eng2.hrm.domains.HashtagExt;

@Client("/hrm")
public interface RecommendationControllerClient {

    @Get("/")
    public /* protected region return on begin */Iterable<HashtagExt>/* protected region return end */ getHealthCheck(/* protected region parameters on begin */
            /* protected region parameters end */);

    @Get("/{hashtag}")
    public /* protected region return on begin */Iterable<HashtagExt>/* protected region return end */ getRecommendation(/* protected region parameters on begin */
            String hashtag	/* protected region parameters end */);
}
