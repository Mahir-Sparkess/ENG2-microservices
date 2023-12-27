package uk.ac.york.eng2.hrm.gen.controllers;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;

import javax.transaction.Transactional;
import java.net.URI;

@Controller("/hrm")
public abstract class RecommendationControllerGen {

	@Get("/")
	public abstract HttpResponse<Void> getHealthCheck();
	
	@Get("/<hashtagId>")
	public abstract HttpResponse<Void> getRecommendation();
	
}