package com.example.demo;

import com.example.demo.dataproviders.movies.MovieProvider;
import com.example.demo.dataproviders.movies.TMDBMovieProvider;
import com.example.demo.usecase.CreateGameUsecase;
import com.example.demo.usecase.MovieListUsecase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Value("${tmdb.bearerToken}")
    String tmdbBearerToken;

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**");
            }
        };
    }

    @Bean
    MovieProvider movieProvider() {
        return new TMDBMovieProvider(new RestTemplateBuilder(), tmdbBearerToken);
    }

    @Bean
    MovieListUsecase movieListUseCase() {
        return new MovieListUsecase();
    }


    @Bean
    CreateGameUsecase createGameUsecase() {
        return new CreateGameUsecase(movieProvider(), movieListUseCase());
    }
}
