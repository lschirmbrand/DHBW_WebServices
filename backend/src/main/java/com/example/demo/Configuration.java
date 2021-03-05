package com.example.demo;

import com.example.demo.dataproviders.movies.MovieProvider;
import com.example.demo.dataproviders.movies.TMDBMovieProvider;
import com.example.demo.usecase.CreateGameUseCase;
import com.example.demo.usecase.MovieListUseCase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Value("${tmdb.bearerToken}")
    String tmdbBearerToken;

    @Bean
    MovieProvider movieProvider() {
        return new TMDBMovieProvider(new RestTemplateBuilder(), tmdbBearerToken);
    }

    @Bean
    MovieListUseCase movieListUseCase() {
        return new MovieListUseCase();
    }


    @Bean
    CreateGameUseCase createGameUsecase() {
        return new CreateGameUseCase(movieProvider(), movieListUseCase());
    }
}
