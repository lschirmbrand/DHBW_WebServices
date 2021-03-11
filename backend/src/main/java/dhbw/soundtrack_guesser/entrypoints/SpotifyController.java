package dhbw.soundtrack_guesser.entrypoints;

import dhbw.soundtrack_guesser.dataproviders.spotify.SpotifyAccessTokenProvider;
import dhbw.soundtrack_guesser.dataproviders.spotify.SpotifyProvider;
import dhbw.soundtrack_guesser.models.SpotifyAuth;
import dhbw.soundtrack_guesser.models.Track;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/spotify")
public class SpotifyController {

    private final SpotifyProvider spotifyProvider;
    private final SpotifyAccessTokenProvider spotifyAccessTokenProvider;

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
