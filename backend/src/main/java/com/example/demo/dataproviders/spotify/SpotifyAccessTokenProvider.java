package com.example.demo.dataproviders.spotify;

import com.example.demo.models.SpotifyAuth;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

public class SpotifyAccessTokenProvider {
    private final RestTemplate restTemplate;
    private final HttpEntity<MultiValueMap<String, String>> httpEntity;

    public SpotifyAccessTokenProvider(RestTemplateBuilder restTemplateBuilder, String spotifyBasicToken) {
        this.restTemplate = restTemplateBuilder.build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "Basic " + spotifyBasicToken);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "client_credentials");

        httpEntity = new HttpEntity<>(map, headers);

    }

    public SpotifyAuth getAuth() {
        String SPOTIFY_AUTH_URL = "https://accounts.spotify.com/api/token";
        return this.restTemplate.postForEntity(URI.create(SPOTIFY_AUTH_URL), httpEntity, SpotifyAuth.class).getBody();
    }
}
