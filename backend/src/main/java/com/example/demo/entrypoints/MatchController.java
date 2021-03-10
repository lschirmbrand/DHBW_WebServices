package com.example.demo.entrypoints;

import com.example.demo.dataproviders.movies.MovieProvider;
import com.example.demo.dataproviders.spotify.SpotifyProvider;
import com.example.demo.entities.MatchEntity;
import com.example.demo.models.Match;
import com.example.demo.usecase.MatchUseCase;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/match")
public class MatchController {

    private final MatchUseCase matchUseCase;
    private final MovieProvider movieProvider;
    private final SpotifyProvider spotifyProvider;

    public MatchController(MatchUseCase matchUseCase, MovieProvider movieProvider, SpotifyProvider spotifyProvider) {
        this.matchUseCase = matchUseCase;
        this.movieProvider = movieProvider;
        this.spotifyProvider = spotifyProvider;
    }

    @GetMapping()
    public List<Match> getAllMatches() {
        return matchUseCase.getAll();
    }

    @PostMapping()
    public Match addMatch(@RequestBody MatchEntity matchEntity) {
        return matchUseCase.addMatch(matchEntity);
    }

    @PatchMapping()
    public MatchEntity updateMatch(@RequestBody MatchEntity matchEntity) {
        return matchUseCase.editMatch(matchEntity);
    }

    @DeleteMapping("/{id}")
    public void deleteMatch(@PathVariable int id) {
        matchUseCase.deleteMatch(id);
    }
}
