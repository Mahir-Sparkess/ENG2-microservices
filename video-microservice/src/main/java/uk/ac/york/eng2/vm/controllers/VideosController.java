package uk.ac.york.eng2.vm.controllers;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;
import uk.ac.york.eng2.vm.domain.HashTag;
import uk.ac.york.eng2.vm.domain.User;
import uk.ac.york.eng2.vm.domain.Video;
import uk.ac.york.eng2.vm.dto.HashTagDTO;
import uk.ac.york.eng2.vm.dto.VideoDTO;
import uk.ac.york.eng2.vm.events.VideosProducer;
import uk.ac.york.eng2.vm.repositories.HashTagsRepository;
import uk.ac.york.eng2.vm.repositories.UsersRepository;
import uk.ac.york.eng2.vm.repositories.VideosRepository;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.Optional;
import java.util.Set;

@Controller("/videos")
public class VideosController {

    @Inject
    VideosRepository videosRepo;

    @Inject
    UsersRepository usersRepo;

    @Inject
    HashTagsRepository hashTagsRepo;

    @Inject
    VideosProducer producer;

    @Get("/")
    public Iterable<Video> list() {
        return videosRepo.findAll();
    }

    @Get("/{id}")
    public VideoDTO getVideo (long id) {
        return videosRepo.findOne(id).orElse(null);
    }

    @Get("/{id}/viewers")
    public Iterable<User> getViewers(Long id){
        Optional<Video> video = videosRepo.findById(id);
        if (video.isEmpty()){
            return null;
        }
        return video.get().getViewers();
    }

    @Get("/{id}/hashtags")
    public Iterable<HashTag> getTags(long id){
        Optional<Video> video = videosRepo.findById(id);
        if (video.isEmpty()){
            return null;
        }
        return video.get().getTags();
    }

    @Post("/")
    public HttpResponse<Void> addVideo(@Body VideoDTO videoDetails) {
        Video video = new Video();
        video.setTitle(videoDetails.getTitle());

        videosRepo.save(video);
        producer.uploadVideo(video.getId(), video);

        return HttpResponse.created(URI.create("/videos/" + video.getId()));
    }

    @Put("/{id}")
    @Transactional
    public HttpResponse<Void> updateVideo(long id, @Body VideoDTO videoDetails){
        Optional<Video> video = videosRepo.findById(id);
        if (video.isEmpty()) {
            return HttpResponse.notFound();
        }

        Video v = video.get();
        if (videoDetails.getTitle() != null){
            v.setTitle(videoDetails.getTitle());
        }
        videosRepo.update(v);
        return HttpResponse.ok();
    }

    @Put("/{videoId}/viewers/{userId}")
    @Transactional
    public HttpResponse<String> updateViewer(long videoId, long userId){
        Optional<Video> video = videosRepo.findById(videoId);
        Optional<User> user = usersRepo.findById(userId);

        if (user.isEmpty()){
            return HttpResponse.notFound(String.format("User %d not found", userId));
        }
        if (video.isEmpty()){
            return HttpResponse.notFound(String.format("Video %d not found", videoId));
        }
        Video v = video.get();
        User u = user.get();

        if (v.getViewers().add(u)){
            videosRepo.update(v);
            producer.viewVideo(videoId, u);
        }
        return HttpResponse.ok(String.format("User %d has viewed video %d", userId, videoId));
    }

    @Put("/{videoId}/hashtags/{tagId}")
    @Transactional
    public HttpResponse<String> updateHashTags(long videoId, long tagId){
        Optional<Video> video = videosRepo.findById(videoId);
        Optional<HashTag> tag = hashTagsRepo.findById(tagId);

        if (video.isEmpty()){
            return HttpResponse.notFound(String.format("Video %d not found", videoId));
        }
        if (tag.isEmpty()){
            return HttpResponse.notFound(String.format("HashTag %d not found", tagId));
        }
        Video v = video.get();
        HashTag ht = tag.get();

        if (v.getTags().add(ht)){
            videosRepo.update(v);
        }
        return HttpResponse.ok(String.format("HashTag %d added to video %d", tagId, videoId));
    }

    @Delete("/{id}")
    @Transactional
    public HttpResponse<Void> deleteVideo(long id) {
        Optional<Video> video = videosRepo.findById(id);
        if (video.isEmpty()) {
            return HttpResponse.notFound();
        }
        Video v = video.get();
        videosRepo.delete(v);
        return HttpResponse.ok();
    }

    @Delete("/{videoId}/viewers/{userId}")
    @Transactional
    public HttpResponse<String> removeViewer(long videoId, long userId){
        Optional<Video> video = videosRepo.findById(videoId);
        if (video.isEmpty()){
            return HttpResponse.notFound(String.format("Video %d not found", videoId));
        }
        Video v = video.get();
        v.getViewers().removeIf(u -> userId == u.getId());
        videosRepo.update(v);

        return HttpResponse.ok();
    }

    @Delete("/{videoId}/hashtags/{tagId}")
    @Transactional
    public HttpResponse<String> removeTag(long videoId, long tagId){
        Optional<Video> video = videosRepo.findById(videoId);
        if (video.isEmpty()){
            return HttpResponse.notFound(String.format("Video %d not found", videoId));
        }
        Video v = video.get();
        v.getTags().removeIf(ht -> tagId == ht.getId());
        videosRepo.update(v);

        return HttpResponse.ok();
    }

    @Put("/{id}/like")
    @Transactional
    public HttpResponse<Void> likeVideo(Long id){
        Optional<Video> video = videosRepo.findById(id);
        if (video.isEmpty()){
            return HttpResponse.notFound();
        }

        Set<HashTag> tags = video.get().getTags();
        producer.likeDislikeVideo(id, "like");

        return HttpResponse.ok();
    }
    @Put("/{id}/dislike")
    @Transactional
    public HttpResponse<Void> dislikeVideo(Long id){
        Optional<Video> video = videosRepo.findById(id);
        if (video.isEmpty()){
            return HttpResponse.notFound();
        }

        Set<HashTag> tags = video.get().getTags();
        producer.likeDislikeVideo(id, "dislike");

        return HttpResponse.ok();
    }
}
