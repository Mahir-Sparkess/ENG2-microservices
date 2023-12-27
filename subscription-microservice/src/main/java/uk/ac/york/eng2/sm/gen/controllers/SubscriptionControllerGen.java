package uk.ac.york.eng2.sm.gen.controllers;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;

import javax.transaction.Transactional;
import java.net.URI;

@Controller("/subscription")
public abstract class SubscriptionControllerGen {

	@Get("/")
	public abstract HttpResponse<Void> getHealthCheck();
	
	@Get("/{userId}/{hashtagId}/nextVideos")
	public abstract HttpResponse<Void> getSubscription();
	
	@Put("/{hastagId}/subscribe/{userId}")
	public abstract HttpResponse<Void> subscribeHashtag();
	
	@Put("/{hastagId}/unsubscribe/{userId}")
	public abstract HttpResponse<Void> unsubscribeHashtag();
	
}