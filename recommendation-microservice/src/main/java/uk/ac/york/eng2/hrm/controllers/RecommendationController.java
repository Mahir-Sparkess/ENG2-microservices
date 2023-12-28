package uk.ac.york.eng2.hrm.controllers;
/* protected region imports on begin */
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;

import javax.transaction.Transactional;
import java.net.URI;

import uk.ac.york.eng2.hrm.gen.domain.*;
import uk.ac.york.eng2.hrm.gen.dto.*;
/* protected region imports end */

@Controller("/hrm")
public class RecommendationController {

	/* protected region injects on begin */
	/* protected region injects end */

	@Get("/")
	public /* protected region return on begin */Object/* protected region return end */ getHealthCheck(/* protected region parameters on begin */
	/* protected region parameters end */) {
	/* protected region Method Implementation on begin */
		return null;
	/* protected region Method Implementation end */
	}
	
	@Get("/<hashtagId>")
	public /* protected region return on begin */Object/* protected region return end */ getRecommendation(/* protected region parameters on begin */
	Long hashtagId	/* protected region parameters end */) {
	/* protected region Method Implementation on begin */
		return null;
	/* protected region Method Implementation end */
	}
	
}