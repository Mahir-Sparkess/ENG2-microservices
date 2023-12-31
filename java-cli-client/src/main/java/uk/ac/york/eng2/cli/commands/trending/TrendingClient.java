package uk.ac.york.eng2.cli.commands.trending;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
import uk.ac.york.eng2.cli.domain.TrendingTag;

@Client("${trending.url:`http://localhost:8081/trending`}")
public interface TrendingClient {

    @Get("/")
    public Iterable<TrendingTag> trending();
}
