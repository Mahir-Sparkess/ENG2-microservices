package uk.ac.york.eng2.thm.controllers;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/trending")
public class TrendingController {

    @Get("/")
    public String hello(){
        return "Hello World!";
    }
}
