package uk.ac.york.eng2.thm.clients;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
import uk.ac.york.eng2.thm.domain.HashTag;

@Client("/trending")
public interface TrendingControllerClient {
    @Get("/")
    public Iterable<HashTag> trending();

    @Get("/all")
    public Iterable<HashTag> getAll();
}
