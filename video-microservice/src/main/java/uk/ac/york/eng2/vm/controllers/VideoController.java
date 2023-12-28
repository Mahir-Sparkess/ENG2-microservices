package uk.ac.york.eng2.vm.controllers;
/* protected region imports on begin */
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

import uk.ac.york.eng2.vm.domain.HashtagExt;
import uk.ac.york.eng2.vm.domain.UserExt;
import uk.ac.york.eng2.vm.domain.VideoExt;
import uk.ac.york.eng2.vm.dto.VideoDTOExt;
import uk.ac.york.eng2.vm.events.Producer;
import uk.ac.york.eng2.vm.gen.domain.*;
import uk.ac.york.eng2.vm.gen.dto.*;
import uk.ac.york.eng2.vm.repositories.HashtagRepository;
import uk.ac.york.eng2.vm.repositories.UserRepository;
import uk.ac.york.eng2.vm.repositories.VideoRepository;
/* protected region imports end */

@Controller("/videos")
public class VideoController {

	/* protected region injects on begin */
	@Inject
	Producer producer;

	@Inject
	VideoRepository videoRepository;

	@Inject
	UserRepository userRepository;

	@Inject
	HashtagRepository hashtagRepository;
	/* protected region injects end */

	@Get("/")
	@Transactional
	public /* protected region return on begin */Iterable<VideoExt>/* protected region return end */ getVideos(/* protected region parameters on begin */
	/* protected region parameters end */) {
	/* protected region Method Implementation on begin */
		return videoRepository.findAll();
	/* protected region Method Implementation end */
	}
	
	@Get("/{id}")
	@Transactional
	public VideoExt getVideo(/* protected region parameters on begin */
	Long id	/* protected region parameters end */) {
	/* protected region Method Implementation on begin */
		return videoRepository.findOne(id).orElse(null);
	/* protected region Method Implementation end */
	}

	@Get("/{id}/tags")
	@Transactional
	public Set<HashtagExt> getVideoTags(/* protected region parameters on begin */
			Long id	/* protected region parameters end */) {
		/* protected region Method Implementation on begin */
		Optional<VideoExt> oVideo = videoRepository.findById(id);
		if (oVideo.isEmpty()) {
			return null;
		}
		return oVideo.get().getTags();
		/* protected region Method Implementation end */
	}
	
	@Post("/upload/{userId}")
	public HttpResponse<String> uploadVideo(/* protected region parameters on begin */
	Long userId, 
	@Body VideoDTOExt details	/* protected region parameters end */) {
	/* protected region Method Implementation on begin */
		Optional<UserExt> oUser = userRepository.findById(userId);
		if (oUser.isEmpty()) {
			return HttpResponse.notFound(String.format("user %d not found!", userId));
		}

		VideoExt newVideo = new VideoExt();
		newVideo.setTitle(details.getTitle());

		newVideo.setUploader(oUser.get());

		Iterable<String> tags = details.getTags();
		Set<HashtagExt> newTags = new HashSet<>();
		for (String t : tags){
			Optional<HashtagExt> oTag = hashtagRepository.findByName(t);
			if (oTag.isEmpty()){
				HashtagExt newTag = new HashtagExt();
				newTag.setName(t);
				hashtagRepository.save(newTag);
				newTags.add(newTag);
			} else {
				newTags.add(oTag.get());
			}
		}
		newVideo.setTags(newTags);
		videoRepository.save(newVideo);

		producer.uploadVideo(newVideo.getId(), newVideo);

		return HttpResponse.created(String.format("video %d created!", newVideo.getId()));

	/* protected region Method Implementation end */
	}
	
	@Put("/{videoId}/view/{userId}")
	public HttpResponse<String> viewVideo(/* protected region parameters on begin */
	Long videoId, 
	Long userId	/* protected region parameters end */) {
	/* protected region Method Implementation on begin */
		Optional<UserExt> oUser = userRepository.findById(userId);
		if (oUser.isEmpty()) {
			return HttpResponse.notFound(String.format("user %d not found!", userId));
		}

		Optional<VideoExt> oVideo = videoRepository.findById(videoId);
		if (oVideo.isEmpty()) {
			return HttpResponse.notFound(String.format("video %d not found!", videoId));
		}

		VideoExt v = oVideo.get();
		v.getViewers().add(oUser.get());
		videoRepository.update(v);

		Set<String> kafkaMessage = v.getTags().stream()
				.map(HashtagExt::getName)
				.collect(Collectors.toSet());

		producer.viewVideo(userId, kafkaMessage);

		return HttpResponse.ok(String.format("User %s viewed Video %d", oUser.get().getUsername(), v.getId()));
	/* protected region Method Implementation end */
	}
	
	@Put("/{videoId}/like/{userId}")
	public HttpResponse<String> likeVideo(/* protected region parameters on begin */
	Long videoId, 
	Long userId	/* protected region parameters end */) {
	/* protected region Method Implementation on begin */
		Optional<UserExt> oUser = userRepository.findById(userId);
		if (oUser.isEmpty()) {
			return HttpResponse.notFound(String.format("user %d not found!", userId));
		}

		Optional<VideoExt> oVideo = videoRepository.findById(videoId);
		if (oVideo.isEmpty()) {
			return HttpResponse.notFound(String.format("video %d not found!", videoId));
		}

		VideoExt v = oVideo.get();

		Set<String> kafkaMessage = v.getTags().stream()
				.map(HashtagExt::getName)
				.collect(Collectors.toSet());

		producer.likeVideo(userId, kafkaMessage);

		return HttpResponse.ok(String.format("User %s liked Video %d", oUser.get().getUsername(), v.getId()));
	/* protected region Method Implementation end */
	}
	
	@Put("/{videoId}/dislike/{userId}")
	public HttpResponse<String> dislikeVideo(/* protected region parameters on begin */
	Long videoId, 
	Long userId	/* protected region parameters end */) {
	/* protected region Method Implementation on begin */
		Optional<UserExt> oUser = userRepository.findById(userId);
		if (oUser.isEmpty()) {
			return HttpResponse.notFound(String.format("user %d not found!", userId));
		}

		Optional<VideoExt> oVideo = videoRepository.findById(videoId);
		if (oVideo.isEmpty()) {
			return HttpResponse.notFound(String.format("video %d not found!", videoId));
		}

		VideoExt v = oVideo.get();

		Set<String> kafkaMessage = v.getTags().stream()
				.map(HashtagExt::getName)
				.collect(Collectors.toSet());

		producer.dislikeVideo(userId, kafkaMessage);

		return HttpResponse.ok(String.format("User %s disliked Video %d", oUser.get().getUsername(), v.getId()));
	/* protected region Method Implementation end */
	}
	
}