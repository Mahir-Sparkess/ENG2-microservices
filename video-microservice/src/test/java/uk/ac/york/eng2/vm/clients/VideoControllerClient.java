package uk.ac.york.eng2.vm.clients;

/* protected region imports on begin */

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.client.annotation.Client;
import uk.ac.york.eng2.vm.domain.HashtagExt;
import uk.ac.york.eng2.vm.domain.VideoExt;
import uk.ac.york.eng2.vm.dto.VideoDTOExt;

import java.util.Set;
/* protected region imports end */

@Client("/videos")
public interface VideoControllerClient {

	@Get("/")
	public /* protected region return on begin */Iterable<VideoExt>/* protected region return end */ getVideos(/* protected region parameters on begin */
	/* protected region parameters end */);
	@Get("/{id}")
	public /* protected region return on begin */VideoExt/* protected region return end */ getVideo(/* protected region parameters on begin */
	Long id	/* protected region parameters end */);
	@Get("/{id}/tags")
	public Set<HashtagExt> getVideoTags(/* protected region parameters on begin */
			Long id	/* protected region parameters end */);
	@Post("/upload/{userId}")
	public /* protected region return on begin */HttpResponse<String>/* protected region return end */ uploadVideo(/* protected region parameters on begin */
	Long userId, 
	@Body VideoDTOExt details	/* protected region parameters end */);
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