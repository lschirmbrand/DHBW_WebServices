package dhbw.soundtrack_guesser.dataproviders.movies;

import dhbw.soundtrack_guesser.models.Movie;
import dhbw.soundtrack_guesser.models.TMDBSearchResponse;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TMDBMovieProvider implements MovieProvider {

    private final RestTemplate restTemplate;
    private final HttpEntity<Void> httpEntity;
    private final String TMDB_BASE_URL = "https://api.themoviedb.org/3";

    public TMDBMovieProvider(RestTemplateBuilder restTemplateBuilder, String tmdbBearerToken) {
        this.restTemplate = restTemplateBuilder.build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + tmdbBearerToken);
        httpEntity = new HttpEntity<>(headers);
    }

    @Override
    public List<Movie> getMovies(List<Integer> movieIDs) {

        // perform async request for each ID
        return movieIDs.parallelStream().map(this::getMovieById).collect(Collectors.toList());
    }

    @Override
    @Cacheable("movie")
    public Movie getMovieById(int id) {
        String uri = TMDB_BASE_URL + "/movie/" + id;
        return this.restTemplate.exchange(URI.create(uri), HttpMethod.GET, httpEntity, Movie.class).getBody();
    }

    @Override
    public List<Movie> search(String title) {
        String uri = TMDB_BASE_URL + "/search/movie?query=" + URLEncoder.encode(title, StandardCharsets.UTF_8);
        TMDBSearchResponse res = this.restTemplate.exchange(URI.create(uri), HttpMethod.GET, httpEntity, TMDBSearchResponse.class).getBody();
        return Objects.requireNonNull(res).getResults();
    }
}
