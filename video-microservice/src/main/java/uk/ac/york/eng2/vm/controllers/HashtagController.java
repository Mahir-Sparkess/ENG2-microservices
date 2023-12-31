package uk.ac.york.eng2.vm.controllers;
/* protected region imports on begin */
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.Optional;

import uk.ac.york.eng2.vm.domain.HashtagExt;
import uk.ac.york.eng2.vm.domain.VideoExt;
import uk.ac.york.eng2.vm.gen.domain.*;
import uk.ac.york.eng2.vm.gen.dto.*;
import uk.ac.york.eng2.vm.repositories.HashtagRepository;
import uk.ac.york.eng2.vm.repositories.VideoRepository;
/* protected region imports end */

@Controller("/hashtags")
public class HashtagController {

	/* protected region injects on begin */
	@Inject
	HashtagRepository hashtagRepository;

	@Inject
	VideoRepository videoRepository;
	/* protected region injects end */

	@Get("/")
	public /* protected region return on begin */Iterable<HashtagExt>/* protected region return end */ getHashtag(/* protected region parameters on begin */
	/* protected region parameters end */) {
	/* protected region Method Implementation on begin */
		return hashtagRepository.findAll();
	/* protected region Method Implementation end */
	}
	
	@Get("/{hashtagId}/videos")
	public Iterable<VideoExt> getHashtagVideos(/* protected region parameters on begin */
	Long hashtagId	/* protected region parameters end */) {
	/* protected region Method Implementation on begin */
		Optional<HashtagExt> oTag = hashtagRepository.findById(hashtagId);
		if (oTag.isEmpty()) {return null;} else {
			return oTag.get().getTagged();
		}
	/* protected region Method Implementation end */
	}
	
}