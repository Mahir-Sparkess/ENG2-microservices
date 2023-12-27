package uk.ac.york.eng2.vm.gen.controllers;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;

import javax.transaction.Transactional;
import java.net.URI;

@Controller("/users")
public abstract class UserControllerGen {

	@Get("/")
	public abstract HttpResponse<Void> getUsers();
	
	@Get("/{userId}/videos")
	public abstract HttpResponse<Void> getUserVideos();
	
	@Post("/")
	public abstract HttpResponse<Void> addUser();
	
}