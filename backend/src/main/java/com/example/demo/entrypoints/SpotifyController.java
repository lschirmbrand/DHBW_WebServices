package com.example.demo.entrypoints;

import com.example.demo.dataproviders.spotify.SpotifyAccessTokenProvider;
import com.example.demo.dataproviders.spotify.SpotifyProvider;
import com.example.demo.models.SpotifyAuth;
import com.example.demo.models.Track;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/spotify")
public class SpotifyController {

    private final  SpotifyProvider spotifyProvider;
    private final  SpotifyAccessTokenProvider spotifyAccessTokenProvider;

    public SpotifyController(SpotifyProvider spotifyProvider, SpotifyAccessTokenProvider spotifyAccessTokenProvider) {
        this.spotifyProvider = spotifyProvider;
        this.spotifyAccessTokenProvider = spotifyAccessTokenProvider;
    }

    @GetMapping("/auth")
    public SpotifyAuth getSpotifyAuth() {
        return spotifyAccessTokenProvider.getAuth();
    }

    @GetMapping("/{id}")
    public Track getMovieById(@PathVariable String id) {
        return spotifyProvider.getTrack(id);
    }

    @GetMapping("/search/{query}")
    public List<Track> getSearch(@PathVariable String query) {
        return spotifyProvider.searchTrack(query);
    }
}
