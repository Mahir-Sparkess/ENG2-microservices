package uk.ac.york.eng2.vm.gen.controllers;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;

import javax.transaction.Transactional;
import java.net.URI;

@Controller("/hashtags")
public abstract class HashtagControllerGen {

	@Get("/")
	public abstract HttpResponse<Void> getHashtag();
	
	@Get("/{hashtagId}/videos")
	public abstract HttpResponse<Void> getHashtagVideos();
	
}