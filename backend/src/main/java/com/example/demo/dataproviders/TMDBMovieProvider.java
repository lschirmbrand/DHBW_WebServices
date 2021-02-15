package com.example.demo.dataproviders;

import com.example.demo.models.Movie;
import com.example.demo.models.Query;
import org.springframework.boot.web.client.RestTemplateBuilder;
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

    public TMDBMovieProvider(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    private HttpEntity<String> httpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyN2NmZTg4YzU5YTc3NzdhYmVhMGFmYmEyZWNhNWU2OCIsInN1YiI6IjYwMjNhNGZmNDU4MTk5MDAzZmFjZjcwMiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.Tn3gy3VlkVBw87KlnTEpzuMrGb1I50WaKoWBph3SPqc");
        return new HttpEntity<>(headers);
    }

    @Override
    public List<Movie> getAllMovies() {

        // hard coded for now, should be stored in db
        List<Integer> movieIDs = List.of(120, 278, 680, 13, 16869);

        // perform request for each ID, maybe async?
        return movieIDs.stream().map(movieID -> {
            String uri = "https://api.themoviedb.org/3/movie/" + movieID;
            return this.restTemplate.exchange(URI.create(uri), HttpMethod.GET, httpEntity(), Movie.class).getBody();
        }).collect(Collectors.toList());
    }

    @Override
    public Movie getMovieById(int id) {
        String uri = "https://api.themoviedb.org/3/movie/" + id;
        Movie res = this.restTemplate.exchange(URI.create(uri), HttpMethod.GET, httpEntity(), Movie.class).getBody();
        System.out.println(res);

        return res;
    }

    @Override
    public Query search(String title) {
        String uri = "https://api.themoviedb.org/3/search/movie?query=" + URLEncoder.encode(title, StandardCharsets.UTF_8);
        Query res = this.restTemplate.exchange(URI.create(uri), HttpMethod.GET, httpEntity(), Query.class).getBody();

        System.out.println(res);

        return res;
    }
}
