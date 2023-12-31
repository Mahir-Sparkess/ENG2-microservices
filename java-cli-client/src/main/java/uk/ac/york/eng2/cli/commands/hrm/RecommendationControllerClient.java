package uk.ac.york.eng2.cli.commands.hrm;

/* protected region imports on begin */
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.http.client.annotation.Client;
import uk.ac.york.eng2.cli.domain.HashTag;
/* protected region imports end */

@Client("${hrm.url:`http://localhost:8083/hrm`}")
public interface RecommendationControllerClient {

	@Get("/")
	public /* protected region return on begin */Object/* protected region return end */ getHealthCheck(/* protected region parameters on begin */
	/* protected region parameters end */);
	@Get("/{hashtag}")
	public /* protected region return on begin */Iterable<HashTag>/* protected region return end */ getRecommendation(/* protected region parameters on begin */
	String hashtag	/* protected region parameters end */);

}