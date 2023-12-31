package uk.ac.york.eng2.sm.events;

/* protected region imports on begin */
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.Topic;

import jakarta.inject.Inject;
import uk.ac.york.eng2.sm.domains.HashtagExt;
import uk.ac.york.eng2.sm.domains.UserExt;
import uk.ac.york.eng2.sm.domains.VideoExt;
import uk.ac.york.eng2.sm.dto.VideoDTO;
import uk.ac.york.eng2.sm.gen.domain.*;
import uk.ac.york.eng2.sm.gen.dto.*;
import uk.ac.york.eng2.sm.repositories.HashtagRepository;
import uk.ac.york.eng2.sm.repositories.UserRepository;
import uk.ac.york.eng2.sm.repositories.VideoRepository;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
/* protected region imports end */

@KafkaListener(groupId="SM")
public class Consumer {
	/* protected region Injects on begin */
	@Inject
	VideoRepository vRepo;

	@Inject
	UserRepository uRepo;

	@Inject
	HashtagRepository hRepo;

	@Inject
	Producer producer;
	/* protected region Injects end */
	
	public static final String TOPIC_VIEW = "view-video";
	public static final String TOPIC_UPLOAD = "upload-video";
	public static final String TOPIC_NEW_USER = "new-user";
	public static final String TOPIC_VIDEO_TAGS = "video-tags";
	
	@Topic(TOPIC_VIEW)
	@Transactional
	public /* protected region Return on begin */void/* protected region Return end */ viewVideo(
		/* protected region parameters on begin */
		@KafkaKey Long userId, 
		Long videoId
		/* protected region parameters end */) {
		/* protected region Method Implementation on begin */

		Optional<UserExt> oUser = uRepo.findByUserId(userId);
		Optional<VideoExt> oVideo = vRepo.findByVideoId(videoId);

		UserExt user = oUser.get();
		VideoExt video = oVideo.get();
		video.getViewers().add(user);
		vRepo.update(video);

		System.out.println(String.format("User %d has watched %d", user.getUserId(), video.getVideoId()));
		/* protected region Method Implementation end */
		}

	@Topic(TOPIC_UPLOAD)
	public /* protected region Return on begin */void/* protected region Return end */ newVideo(
			/* protected region parameters on begin */
			@KafkaKey Long videoId,
			VideoDTO videoDTO
			/* protected region parameters end */) {

		/* protected region Method Implementation on begin */
		VideoExt newVideo = new VideoExt();
		newVideo.setVideoId(videoId);
		newVideo.setTitle(videoDTO.getTitle());
		newVideo.setTags(new HashSet<>());
		newVideo.setViewers(new HashSet<>());
		System.out.println(String.format("ADDED Video {vmId = %d, title = %s}", newVideo.getVideoId(), newVideo.getTitle()));

		Set<HashtagExt> vTags = new HashSet<>();

		for (String name : videoDTO.getTags()) {
			Optional<HashtagExt> oTag = hRepo.findByName(name);
			if (oTag.isEmpty()){
				HashtagExt newTag = new HashtagExt();
				newTag.setName(name);
				hRepo.save(newTag);
				System.out.println(String.format("ADDED Hashtag #%s", newTag.getName()));
				vTags.add(newTag);
			} else {
				vTags.add(oTag.get());
			}
		}

		if (!vTags.isEmpty()) { newVideo.setTags(vTags); }

		vRepo.save(newVideo);
		System.out.println(String.format("Video %d has been tagged with %s", newVideo.getVideoId(), videoDTO.getTags()));
		/* protected region Method Implementation end */
	}

	@Topic(TOPIC_NEW_USER)
	public /* protected region Return on begin */void/* protected region Return end */ newUser(
			/* protected region parameters on begin */
			@KafkaKey Long userId,
			String username
			/* protected region parameters end */) {

		/* protected region Method Implementation on begin */
		UserExt newUser = new UserExt();
		newUser.setUserId(userId);
		newUser.setUsername(username);

		uRepo.save(newUser);
		System.out.println(String.format("ADDED User {vmId = %d, username = %s}", newUser.getUserId(), newUser.getUsername()));
		/* protected region Method Implementation end */
	}
}