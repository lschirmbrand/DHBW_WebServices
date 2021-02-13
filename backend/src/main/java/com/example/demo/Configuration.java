package com.example.demo;

import com.example.demo.dataproviders.MovieProvider;
import com.example.demo.dataproviders.TMDBMovieProvider;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    MovieProvider movieProvider() {
        return new TMDBMovieProvider(new RestTemplateBuilder());
    }

}
