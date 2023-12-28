package uk.ac.york.eng2.thm.controllers;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import jakarta.inject.Inject;
import uk.ac.york.eng2.thm.domain.HashTag;
import uk.ac.york.eng2.thm.repositories.HashTagsRepository;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

@Controller("/trending")
public class TrendingController {

    @Inject
    HashTagsRepository repo;

    @Get("/")
    public Iterable<HashTag> hello(){
        long currentTime = Instant.now().toEpochMilli();
        long oneHourAgo = currentTime - TimeUnit.HOURS.toMillis(1);
        return repo.findTop10ByLatestActivityGreaterThanEqualsOrderByLikesDesc(oneHourAgo);
    }

    @Get("/all")
    public Iterable<HashTag> getAll() {
        return repo.findAll();
    }
}
