package uk.ac.york.eng2.vm.controllers;
/* protected region imports on begin */
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.Optional;

import uk.ac.york.eng2.vm.domain.UserExt;
import uk.ac.york.eng2.vm.domain.VideoExt;
import uk.ac.york.eng2.vm.events.Producer;
import uk.ac.york.eng2.vm.gen.domain.*;
import uk.ac.york.eng2.vm.gen.dto.*;
import uk.ac.york.eng2.vm.repositories.UserRepository;
import uk.ac.york.eng2.vm.repositories.VideoRepository;
/* protected region imports end */

@Controller("/users")
public class UserController {

	/* protected region injects on begin */
	@Inject
	Producer producer;

	@Inject
	UserRepository userRepository;

	@Inject
	VideoRepository videoRepository;
	/* protected region injects end */

	@Get("/")
	public /* protected region return on begin */Iterable<UserExt>/* protected region return end */ getUsers(/* protected region parameters on begin */
	/* protected region parameters end */) {
	/* protected region Method Implementation on begin */
		return userRepository.findAll();
	/* protected region Method Implementation end */
	}
	
	@Get("/{userId}/videos")
	public Iterable<VideoExt> getUserVideos(/* protected region parameters on begin */
	Long userId	/* protected region parameters end */) {
	/* protected region Method Implementation on begin */
		Optional<UserExt> oUser = userRepository.findById(userId);
		if (oUser.isEmpty()){return null;} else {
			return videoRepository.findByUploader(oUser.get());
		}
	/* protected region Method Implementation end */
	}
	
	@Post("/")
	public HttpResponse<String> addUser(/* protected region parameters on begin */
	@Body UserDTO details	/* protected region parameters end */) {
	/* protected region Method Implementation on begin */
		UserExt newUser = new UserExt();
		newUser.setUsername(details.getUsername());

		userRepository.save(newUser);
		producer.newUser(newUser.getId(), newUser.getUsername());

		System.out.println(String.format("User %d created", newUser.getId()));
		return HttpResponse.created(String.format("User %d created", newUser.getId()));
	/* protected region Method Implementation end */
	}
	
}