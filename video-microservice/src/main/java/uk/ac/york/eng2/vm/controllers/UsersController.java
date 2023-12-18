package uk.ac.york.eng2.vm.controllers;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;
import uk.ac.york.eng2.vm.domain.User;
import uk.ac.york.eng2.vm.domain.Video;
import uk.ac.york.eng2.vm.dto.UserDTO;
import uk.ac.york.eng2.vm.repositories.HashTagsRepository;
import uk.ac.york.eng2.vm.repositories.UsersRepository;
import uk.ac.york.eng2.vm.repositories.VideosRepository;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.Optional;

@Controller("/users")
public class UsersController {
    @Inject
    VideosRepository videosRepo;

    @Inject
    UsersRepository usersRepo;

    @Inject
    HashTagsRepository hashTagsRepo;

    @Get("/")
    public Iterable<User> list() {return usersRepo.findAll();}

    @Get("/{id}")
    public UserDTO getUser(long id){
        return usersRepo.findOne(id).orElse(null);
    }

    @Get("/{id}/viewed")
    public Iterable<Video> getViewed(long id){
        Optional<User> user = usersRepo.findById(id);
        if (user.isEmpty()){
            return null;
        }
        return user.get().getWatchedVideos();
    }

    @Post("/")
    public HttpResponse<Void> addUser(@Body UserDTO userDetails){
        User user = new User();
        user.setUsername(userDetails.getUsername());
        usersRepo.save(user);
        return HttpResponse.created(URI.create("/users/" + user.getId()));
    }

    @Put("/{id}")
    @Transactional
    public HttpResponse<Void> updateUser(long id, @Body UserDTO userDetails){
        Optional<User> user = usersRepo.findById(id);
        if (user.isEmpty()){
            return HttpResponse.notFound();
        }
        User u = user.get();
        if (userDetails.getUsername() != null){
            u.setUsername(userDetails.getUsername());
        }
        usersRepo.save(u);
        return HttpResponse.ok();
    }

    @Delete("/{id}")
    @Transactional
    public HttpResponse<Void> deleteUser(long id){
        Optional<User> user = usersRepo.findById(id);
        if (user.isEmpty()){
            return HttpResponse.notFound();
        }
        usersRepo.delete(user.get());
        return HttpResponse.ok();
    }
}
