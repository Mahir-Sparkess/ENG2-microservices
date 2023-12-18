package uk.ac.york.eng2.vm.controllers;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;
import uk.ac.york.eng2.vm.domain.HashTag;
import uk.ac.york.eng2.vm.domain.Video;
import uk.ac.york.eng2.vm.dto.HashTagDTO;
import uk.ac.york.eng2.vm.repositories.HashTagsRepository;
import uk.ac.york.eng2.vm.repositories.UsersRepository;
import uk.ac.york.eng2.vm.repositories.VideosRepository;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.Optional;

@Controller("/hashtags")
public class HashTagsController {
    @Inject
    VideosRepository videosRepo;

    @Inject
    UsersRepository usersRepo;

    @Inject
    HashTagsRepository hashTagsRepo;

    @Get("/")
    public Iterable<HashTag> list(){
        return hashTagsRepo.findAll();
    }

    @Get("/{id}")
    public HashTagDTO getHashTag(long id){
        return hashTagsRepo.findOne(id).orElse(null);
    }

    @Get("/{id}/videos")
    public Iterable<Video> getTaggedVideos (long id){
        Optional<HashTag> hashtag = hashTagsRepo.findById(id);
        if (hashtag.isEmpty()){
            return null;
        }
        return hashtag.get().getTaggedBy();
    }

    @Post("/")
    public HttpResponse<Void> createHashTag (@Body HashTagDTO hashtagDetails){
        HashTag hashtag = new HashTag();
        hashtag.setName(hashtagDetails.getName());
        hashTagsRepo.save(hashtag);
        return HttpResponse.created(URI.create("/users/" + hashtag.getId()));
    }

    @Put("/{id}")
    @Transactional
    public HttpResponse<Void> updateHashTag (long id, @Body HashTagDTO hashtagDetails){
        Optional<HashTag> hashtag = hashTagsRepo.findById(id);
        if (hashtag.isEmpty()){
            return HttpResponse.notFound();
        }

        HashTag ht = hashtag.get();

        if (hashtagDetails.getName() != null){
            ht.setName(hashtagDetails.getName());
        }
        hashTagsRepo.update(ht);
        return HttpResponse.ok();
    }

    @Delete("/{id}")
    @Transactional
    public HttpResponse<Void> deleteHashTag(long id){
        Optional<HashTag> hashtag = hashTagsRepo.findById(id);
        if (hashtag.isEmpty()){
            return HttpResponse.notFound();
        }
        hashTagsRepo.delete(hashtag.get());
        return HttpResponse.ok();
    }
}
