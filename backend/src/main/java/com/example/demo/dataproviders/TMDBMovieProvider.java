package com.example.demo.dataproviders;

import com.example.demo.models.Movie;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

public class TMDBMovieProvider implements MovieProvider {

    private final RestTemplate restTemplate ;

    public TMDBMovieProvider(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public List<Movie> getAllMovies() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyN2NmZTg4YzU5YTc3NzdhYmVhMGFmYmEyZWNhNWU2OCIsInN1YiI6IjYwMjNhNGZmNDU4MTk5MDAzZmFjZjcwMiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.Tn3gy3VlkVBw87KlnTEpzuMrGb1I50WaKoWBph3SPqc");

        HttpEntity<String> entity = new HttpEntity<>(headers);
        Movie res = this.restTemplate.exchange(URI.create("https://api.themoviedb.org/3/movie/16869"), HttpMethod.GET, entity, Movie.class).getBody();

        System.out.println(res);

        return List.of();
    }

    @Override
    public Movie getMovieById(int id){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyN2NmZTg4YzU5YTc3NzdhYmVhMGFmYmEyZWNhNWU2OCIsInN1YiI6IjYwMjNhNGZmNDU4MTk5MDAzZmFjZjcwMiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.Tn3gy3VlkVBw87KlnTEpzuMrGb1I50WaKoWBph3SPqc");
        String uri = "https://api.themoviedb.org/3/movie/" + id;
        HttpEntity<String> entity = new HttpEntity<>(headers);
        Movie res = this.restTemplate.exchange(URI.create(uri), HttpMethod.GET, entity, Movie.class).getBody();

        System.out.println(res);

        return res;
    }
}
