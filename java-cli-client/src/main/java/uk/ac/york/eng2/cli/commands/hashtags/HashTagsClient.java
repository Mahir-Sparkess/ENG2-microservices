package uk.ac.york.eng2.cli.commands.hashtags;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.http.client.annotation.Client;
import uk.ac.york.eng2.cli.domain.HashTag;
import uk.ac.york.eng2.cli.domain.Video;
import uk.ac.york.eng2.cli.dto.HashTagDTO;

@Client("${users.url:`http://localhost:8080/hashtags`}")
public interface HashTagsClient {
    @Get("/")
    public Iterable<HashTag> list();

    @Get("/{id}")
    public HashTagDTO getHashTag(long id);

    @Get("/{id}/videos")
    public Iterable<Video> getTaggedVideos(long id);

    @Post("/")
    public HttpResponse<Void> createHashTag(@Body HashTagDTO hashtagDetails);

    @Put("/{id}")
    public HttpResponse<Void> updateHashTag(long id, @Body HashTagDTO hashtagDetails);

    @Delete("/{id}")
    public HttpResponse<Void> deleteHashTag(long id);

}
