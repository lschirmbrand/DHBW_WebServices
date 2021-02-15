package com.example.demo.dataproviders;

import com.example.demo.models.Movie;
import com.example.demo.models.Query;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class TMDBMovieProvider implements MovieProvider {

    private final RestTemplate restTemplate;
    private final HttpEntity<Void> httpEntity;

    public TMDBMovieProvider(RestTemplateBuilder restTemplateBuilder, String tmdbBearerToken) {
        this.restTemplate = restTemplateBuilder.build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + tmdbBearerToken);
        httpEntity = new HttpEntity<>(headers);
    }

    @Override
    public List<Movie> getAllMovies() {

        // hard coded for now, should be stored in db
        List<Integer> movieIDs = List.of(120, 278, 680, 13, 16869).subList(0,3);

        // perform request for each ID, maybe async?
        return movieIDs.stream().map(movieID -> {
            String uri = "https://api.themoviedb.org/3/movie/" + movieID;
            return this.restTemplate.exchange(URI.create(uri), HttpMethod.GET, httpEntity, Movie.class).getBody();
        }).collect(Collectors.toList());
    }

    @Override
    public Movie getMovieById(int id) {
        String uri = "https://api.themoviedb.org/3/movie/" + id;
        Movie res = this.restTemplate.exchange(URI.create(uri), HttpMethod.GET, httpEntity, Movie.class).getBody();
        System.out.println(res);

        return res;
    }

    @Override
    public Query search(String title) {
        String uri = "https://api.themoviedb.org/3/search/movie?query=" + URLEncoder.encode(title, StandardCharsets.UTF_8);
        Query res = this.restTemplate.exchange(URI.create(uri), HttpMethod.GET, httpEntity, Query.class).getBody();

        System.out.println(res);

        return res;
    }
}
