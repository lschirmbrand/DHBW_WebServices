package com.example.demo.dataproviders.spotify;

import com.example.demo.models.Track;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

public class SpotifyProvider {
    private final RestTemplate restTemplate;
    private final HttpEntity<Void> httpEntity;
    private final String SPOTIFY_BASE_URL = "https://api.spotify.com/v1/";

    public SpotifyProvider(RestTemplateBuilder restTemplateBuilder, String spotifyBearerToken) {
        this.restTemplate = restTemplateBuilder.build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + spotifyBearerToken);
        httpEntity = new HttpEntity<>(headers);
    }

    public Track getTrack(String id) {
        String uri = SPOTIFY_BASE_URL + "tracks/" + id;
        return this.restTemplate.exchange(URI.create(uri), HttpMethod.GET, httpEntity, Track.class).getBody();
    }
}
