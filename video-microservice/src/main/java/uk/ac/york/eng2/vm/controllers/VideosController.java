package uk.ac.york.eng2.vm.controllers;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;
import uk.ac.york.eng2.vm.domain.Video;
import uk.ac.york.eng2.vm.dto.VideoDTO;
import uk.ac.york.eng2.vm.repositories.VideosRepository;

import java.net.URI;

@Controller("/videos")
public class VideosController {

    @Inject
    VideosRepository videosRepo;

    @Get("/")
    public Iterable<Video> list() {
        return videosRepo.findAll();
    }

    @Post("/")
    public HttpResponse<Void> add(@Body VideoDTO videoDetails) {
        Video video = new Video();
        video.setTitle(videoDetails.getTitle());

        videosRepo.save(video);

        return HttpResponse.created(URI.create("/books/" + video.getId()));
    }
}
