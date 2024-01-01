package uk.ac.york.eng2.vm.clients;

/* protected region imports on begin */

import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
import uk.ac.york.eng2.vm.domain.HashtagExt;
import uk.ac.york.eng2.vm.domain.VideoExt;
/* protected region imports end */

@Client("/hashtags")
public interface HashtagControllerClient {

	@Get("/")
	public /* protected region return on begin */Iterable<HashtagExt>/* protected region return end */ getHashtag(/* protected region parameters on begin */
	/* protected region parameters end */);
	@Get("/{hashtagId}/videos")
	public /* protected region return on begin */Iterable<VideoExt>/* protected region return end */ getHashtagVideos(/* protected region parameters on begin */
	Long hashtagId	/* protected region parameters end */);

}