package com.example.demo;

import com.example.demo.dataproviders.GameProvider;
import com.example.demo.dataproviders.MovieProvider;
import com.example.demo.dataproviders.TMDBMovieProvider;
import com.example.demo.models.Game;
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
    GameProvider gameProvider() {
        return new GameProvider(movieProvider());
    }
}
