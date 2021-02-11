package com.example.demo;

import com.example.demo.dataproviders.MockMovieProvider;
import com.example.demo.dataproviders.MovieProvider;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    MovieProvider movieProvider() {
        return new MockMovieProvider();
    }
}
