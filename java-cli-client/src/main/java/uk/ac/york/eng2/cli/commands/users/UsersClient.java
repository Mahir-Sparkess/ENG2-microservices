package uk.ac.york.eng2.cli.commands.users;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.http.client.annotation.Client;
import uk.ac.york.eng2.cli.domain.User;
import uk.ac.york.eng2.cli.domain.Video;
import uk.ac.york.eng2.cli.dto.UserDTO;

@Client("${users.url:`http://localhost:8080/users`}")
public interface UsersClient {

    @Get("/")
    public Iterable<User> list();

    @Get("/{id}")
    public UserDTO getUser(long id);

    @Get("/{id}/viewed")
    public Iterable<Video> getViewed(long id);

    @Post("/")
    public HttpResponse<Void> addUser(@Body UserDTO userDetails);

    @Put("/{id}")
    public HttpResponse<Void> updateUser(long id, @Body UserDTO userDetails);

    @Delete("/{id}")
    public HttpResponse<Void> deleteUser(long id);
}
