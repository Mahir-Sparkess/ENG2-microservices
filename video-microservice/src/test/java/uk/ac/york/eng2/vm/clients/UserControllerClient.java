package uk.ac.york.eng2.vm.clients;

/* protected region imports on begin */

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;
import uk.ac.york.eng2.vm.domain.UserExt;
import uk.ac.york.eng2.vm.domain.VideoExt;
import uk.ac.york.eng2.vm.gen.dto.UserDTO;
/* protected region imports end */

@Client("/users")
public interface UserControllerClient {

	@Get("/")
	public /* protected region return on begin */Iterable<UserExt>/* protected region return end */ getUsers(/* protected region parameters on begin */
	/* protected region parameters end */);
	@Get("/{userId}/videos")
	public /* protected region return on begin */Iterable<VideoExt>/* protected region return end */ getUserVideos(/* protected region parameters on begin */
	Long userId	/* protected region parameters end */);
	@Post("/")
	public /* protected region return on begin */HttpResponse<String>/* protected region return end */ addUser(/* protected region parameters on begin */
	@Body UserDTO details	/* protected region parameters end */);

}