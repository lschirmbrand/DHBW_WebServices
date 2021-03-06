package com.example.demo.dataproviders.spotify;

import com.example.demo.models.SpotifyAuth;
import com.example.demo.models.Track;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class SpotifyAccessTokenProvider {
    private final RestTemplate restTemplate;
    private final HttpEntity<MultiValueMap<String, String>> httpEntity;
    private final String SPOTIFY_AUTH_URL = "https://accounts.spotify.com/api/token";

    public SpotifyAccessTokenProvider(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "Basic YzM4YjRhZTZlOTA0NGU5NTkwODdhYjlkNDY4ZmRjZGI6OWVhNjI0OTBkNGYyNDFkZjlkYWUwNGJhYjEyOGI2MTg=");

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("grant_type", "client_credentials");

        httpEntity = new HttpEntity<>(map, headers);

    }



    public SpotifyAuth getAuth() {
        String uri = SPOTIFY_AUTH_URL;
        return this.restTemplate.postForEntity(URI.create(uri), httpEntity, SpotifyAuth.class).getBody();
    }
}
