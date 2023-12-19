package uk.ac.york.eng2.cli.commands.videos;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.http.client.annotation.Client;
import uk.ac.york.eng2.cli.domain.HashTag;
import uk.ac.york.eng2.cli.domain.User;
import uk.ac.york.eng2.cli.domain.Video;
import uk.ac.york.eng2.cli.dto.VideoDTO;

@Client("${videos.url:`http://localhost:8080/videos`}")
public interface VideosClient {

    @Get("/")
    public Iterable<Video> list();

    @Get("/{id}")
    public VideoDTO getVideo (long id);

    @Get("/{id}/viewers")
    public Iterable<User> getViewers(Long id);

    @Get("/{id}/hashtags")
    public Iterable<HashTag> getTags(long id);

    @Post("/")
    public HttpResponse<Void> addVideo(@Body VideoDTO videoDetails);

    @Put("/{id}")
    public HttpResponse<Void> updateVideo(long id, @Body VideoDTO videoDetails);

    @Put("/{videoId}/viewers/{userId}")
    public HttpResponse<String> updateViewer(long videoId, long userId);

    @Put("/{videoId}/hashtags/{tagId}")
    public HttpResponse<String> updateHashTags(long videoId, long tagId);

    @Delete("/{id}")
    public HttpResponse<Void> deleteVideo(long id);

    @Delete("/{videoId}/viewers/{userId}")
    public HttpResponse<String> removeViewer(long videoId, long userId);

    @Delete("/{videoId}/hashtags/{tagId}")
    public HttpResponse<String> removeTag(long videoId, long tagId);
}
