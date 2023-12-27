package uk.ac.york.eng2.vm.gen.controllers;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;

import javax.transaction.Transactional;
import java.net.URI;

@Controller("/videos")
public abstract class VideoControllerGen {

	@Get("/")
	public abstract HttpResponse<Void> getVideos();
	
	@Get("/{id}")
	public abstract HttpResponse<Void> getVideo();
	
	@Post("/")
	public abstract HttpResponse<Void> uploadVideo();
	
	@Put("/{VideoId}/view/{userId}")
	public abstract HttpResponse<Void> viewVideo();
	
	@Put("/{videoId}/like/{userId}")
	public abstract HttpResponse<Void> likeVideo();
	
	@Put("/{videoId}/dislike/{userId}")
	public abstract HttpResponse<Void> dislikeVideo();
	
}