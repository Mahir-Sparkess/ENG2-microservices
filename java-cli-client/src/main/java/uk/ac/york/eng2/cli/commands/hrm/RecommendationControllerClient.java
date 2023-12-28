package uk.ac.york.eng2.cli.commands.hrm;

/* protected region imports on begin */
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.http.client.annotation.Client;
/* protected region imports end */

@Client("${hrm.url:`http://localhost:8080/hrm}")
public interface RecommendationControllerClient {

	@Get("/")
	public /* protected region return on begin */Object/* protected region return end */ getHealthCheck(/* protected region parameters on begin */
	/* protected region parameters end */);
	@Get("/<hashtagId>")
	public /* protected region return on begin */Object/* protected region return end */ getRecommendation(/* protected region parameters on begin */
	Long hashtagId	/* protected region parameters end */);

}