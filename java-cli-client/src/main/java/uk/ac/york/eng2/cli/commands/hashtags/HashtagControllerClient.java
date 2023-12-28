package uk.ac.york.eng2.cli.commands.hashtags;

/* protected region imports on begin */
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.http.client.annotation.Client;
/* protected region imports end */

@Client("${hashtags.url:`http://localhost:8080/hashtags}")
public interface HashtagControllerClient {

	@Get("/")
	public /* protected region return on begin */Object/* protected region return end */ getHashtag(/* protected region parameters on begin */
	/* protected region parameters end */);
	@Get("/{hashtagId}/videos")
	public /* protected region return on begin */Object/* protected region return end */ getHashtagVideos(/* protected region parameters on begin */
	Long hashtagId	/* protected region parameters end */);

}