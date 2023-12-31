package uk.ac.york.eng2.cli.commands.users;

/* protected region imports on begin */
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.http.client.annotation.Client;
import uk.ac.york.eng2.cli.domain.User;
import uk.ac.york.eng2.cli.domain.Video;
import uk.ac.york.eng2.cli.dto.UserDTO;
/* protected region imports end */

@Client("${users.url:`http://localhost:8080/users`}")
public interface UserControllerClient {

	@Get("/")
	public /* protected region return on begin */Iterable<User>/* protected region return end */ getUsers(/* protected region parameters on begin */
	/* protected region parameters end */);
	@Get("/{userId}/videos")
	public /* protected region return on begin */Iterable<Video>/* protected region return end */ getUserVideos(/* protected region parameters on begin */
	Long userId	/* protected region parameters end */);
	@Post("/")
	public /* protected region return on begin */HttpResponse<String>/* protected region return end */ addUser(/* protected region parameters on begin */
	@Body UserDTO details	/* protected region parameters end */);

}