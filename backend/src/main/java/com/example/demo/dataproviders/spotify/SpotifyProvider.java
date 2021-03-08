package com.example.demo.dataproviders.spotify;

import com.example.demo.models.SpotifyAuth;
import com.example.demo.models.Track;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

public class SpotifyProvider {
    private final RestTemplate restTemplate;
    private final SpotifyAccessTokenProvider spotifyAccessTokenProvider;
    private final String SPOTIFY_BASE_URL = "https://api.spotify.com/v1/";
    private HttpEntity<Void> httpEntity;
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
            System.out.println(" Unauthorized Exception caught!");
            SpotifyAuth spotifyAuth = spotifyAccessTokenProvider.getAuth();
            System.out.println(spotifyAuth.getAccess_token());
            access_token = spotifyAuth.getAccess_token();

            return getTrack(id);
        }

    }
}
