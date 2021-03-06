package com.example.demo.dataproviders.spotify;

import com.example.demo.Configuration;
import com.example.demo.models.SpotifyAuth;
import com.example.demo.models.Track;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

public class SpotifyProvider {
    private final RestTemplate restTemplate;
    private final SpotifyAccessTokenProvider spotifyAccessTokenProvider;
    private final HttpEntity<Void> httpEntity;
    private final String SPOTIFY_BASE_URL = "https://api.spotify.com/v1/";
    private String access_token;
    private HttpHeaders headers;

    public SpotifyProvider(RestTemplateBuilder restTemplateBuilder, SpotifyAccessTokenProvider spotifyAccessTokenProvider) {
        this.restTemplate = restTemplateBuilder.build();
        this.spotifyAccessTokenProvider = spotifyAccessTokenProvider;
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + access_token);
        httpEntity = new HttpEntity<>(headers);
    }



    public Track getTrack(String id) {
        String uri = SPOTIFY_BASE_URL + "tracks/" + id;

        try{
            ResponseEntity<Track> response = this.restTemplate.exchange(URI.create(uri), HttpMethod.GET, httpEntity, Track.class);
            return response.getBody();
        }catch (HttpClientErrorException e){
            System.out.println(" Unauthorized Exception caught!");
            SpotifyAuth spotifyAuth = spotifyAccessTokenProvider.getAuth();
            System.out.println(spotifyAuth.getAccess_token());
            access_token = spotifyAuth.getAccess_token();
            HttpHeaders newHeaders = new HttpHeaders();

            newHeaders.setContentType(MediaType.APPLICATION_JSON);
            newHeaders.set("Authorization", "Bearer " + spotifyAuth.getAccess_token());
            HttpEntity newHttpEntity = new HttpEntity<>(newHeaders);
            return this.restTemplate.exchange(URI.create(uri), HttpMethod.GET, newHttpEntity, Track.class).getBody();

        }

    }
}
