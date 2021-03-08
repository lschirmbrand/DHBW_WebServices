package com.example.demo;

import com.example.demo.dataproviders.movies.MovieProvider;
import com.example.demo.dataproviders.movies.TMDBMovieProvider;
import com.example.demo.dataproviders.spotify.SpotifyAccessTokenProvider;
import com.example.demo.dataproviders.spotify.SpotifyProvider;
import com.example.demo.usecase.CreateGameUseCase;
import com.example.demo.usecase.MatchUseCase;
import com.example.demo.usecase.MovieListUseCase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;

import java.beans.BeanProperty;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Value("${tmdb.bearerToken}")
    String tmdbBearerToken;
    @Value("${spotify.bearerToken}")
    String spotifyBearerToken;

    @Bean
    MovieProvider movieProvider() {
        return new TMDBMovieProvider(new RestTemplateBuilder(), tmdbBearerToken);
    }

    @Bean
    SpotifyProvider spotifyProvider() {
        return new SpotifyProvider(new RestTemplateBuilder(), spotifyAccessTokenProvider());
    }

    @Bean
    SpotifyAccessTokenProvider spotifyAccessTokenProvider() {
        return new SpotifyAccessTokenProvider(new RestTemplateBuilder());
    }

    @Bean
    MovieListUseCase movieListUseCase() {
        return new MovieListUseCase();
    }

    @Bean
    MatchUseCase matchUseCase() {
        return new MatchUseCase(movieProvider(), spotifyProvider());
    }



    @Bean
    CreateGameUseCase createGameUsecase() {
        return new CreateGameUseCase(movieProvider(), movieListUseCase(), matchUseCase());
    }
}
