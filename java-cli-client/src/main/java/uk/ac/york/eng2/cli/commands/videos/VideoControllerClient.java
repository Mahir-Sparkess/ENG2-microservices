package uk.ac.york.eng2.cli.commands.videos;

/* protected region imports on begin */
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.http.client.annotation.Client;
import uk.ac.york.eng2.cli.domain.Video;
import uk.ac.york.eng2.cli.dto.VideoDTO;
/* protected region imports end */

@Client("${videos.url:`http://localhost:8080/videos`}")
public interface VideoControllerClient {

	@Get("/")
	public /* protected region return on begin */Iterable<Video>/* protected region return end */ getVideos(/* protected region parameters on begin */
	/* protected region parameters end */);
	@Get("/{id}")
	public /* protected region return on begin */Video/* protected region return end */ getVideo(/* protected region parameters on begin */
	Long id	/* protected region parameters end */);
	@Post("/upload/{userId}")
	public /* protected region return on begin */HttpResponse<String>/* protected region return end */ uploadVideo(/* protected region parameters on begin */
	Long userId, 
	@Body VideoDTO details	/* protected region parameters end */);
	@Put("/{videoId}/view/{userId}")
	public /* protected region return on begin */HttpResponse<String>/* protected region return end */ viewVideo(/* protected region parameters on begin */
	Long videoId, 
	Long userId	/* protected region parameters end */);
	@Put("/{videoId}/like/{userId}")
	public /* protected region return on begin */HttpResponse<String>/* protected region return end */ likeVideo(/* protected region parameters on begin */
	Long videoId, 
	Long userId	/* protected region parameters end */);
	@Put("/{videoId}/dislike/{userId}")
	public /* protected region return on begin */HttpResponse<String>/* protected region return end */ dislikeVideo(/* protected region parameters on begin */
	Long videoId, 
	Long userId	/* protected region parameters end */);

}