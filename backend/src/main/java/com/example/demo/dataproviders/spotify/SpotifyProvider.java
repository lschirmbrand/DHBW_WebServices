package com.example.demo.dataproviders.spotify;

import com.example.demo.models.Movie;
import com.example.demo.models.SpotifyAuth;
import com.example.demo.models.SpotifySearchResponse;
import com.example.demo.models.Track;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

public class SpotifyProvider {
    private final RestTemplate restTemplate;
    private final SpotifyAccessTokenProvider spotifyAccessTokenProvider;
    private final String SPOTIFY_BASE_URL = "https://api.spotify.com/v1/";
    private String access_token;

    public SpotifyProvider(RestTemplateBuilder restTemplateBuilder, SpotifyAccessTokenProvider spotifyAccessTokenProvider) {
        this.restTemplate = restTemplateBuilder.build();
        this.spotifyAccessTokenProvider = spotifyAccessTokenProvider;
    }

    private HttpEntity httpEntity(String bearerToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(bearerToken);
        return new HttpEntity(headers);
    }

    public Track getTrack(String id) {
        String uri = SPOTIFY_BASE_URL + "tracks/" + id;
        try {
            HttpEntity httpEntity = httpEntity(access_token);
            ResponseEntity<Track> response = this.restTemplate.exchange(URI.create(uri), HttpMethod.GET, httpEntity, Track.class);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            SpotifyAuth spotifyAuth = spotifyAccessTokenProvider.getAuth();
            access_token = spotifyAuth.getAccess_token();
            return getTrack(id);
        }
    }

    public List<Track> searchTrack(String query) {
        String uri = SPOTIFY_BASE_URL + "search?type=track&q=" + query;
        try {
            HttpEntity httpEntity = httpEntity(access_token);
            ResponseEntity<SpotifySearchResponse> response =  this.restTemplate.exchange(URI.create(uri), HttpMethod.GET, httpEntity, SpotifySearchResponse.class);
            return response.getBody().getTracks().getItems();
        } catch (HttpClientErrorException e) {
            SpotifyAuth spotifyAuth = spotifyAccessTokenProvider.getAuth();
            access_token = spotifyAuth.getAccess_token();
            return searchTrack(query);
        }
    }
}
