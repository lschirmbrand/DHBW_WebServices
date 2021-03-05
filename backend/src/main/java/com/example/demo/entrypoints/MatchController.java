package com.example.demo.entrypoints;

import com.example.demo.entities.MovieSpotifyDBEntity;
import com.example.demo.usecase.MatchUseCase;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/match")
public class MatchController {

    private final MatchUseCase matchUseCase;

    public MatchController(MatchUseCase matchUseCase) {
        this.matchUseCase = matchUseCase;
    }

    @GetMapping()
    public List<MovieSpotifyDBEntity> getAllMatches() {
        return matchUseCase.getAll();
    }

    @PostMapping()
    public MovieSpotifyDBEntity addMatch(@RequestBody MovieSpotifyDBEntity match) {
        return matchUseCase.addMatch(match);
    }

    @PatchMapping()
    public MovieSpotifyDBEntity updateMatch(@RequestBody MovieSpotifyDBEntity match) {
        return matchUseCase.editMatch(match);
    }

    @DeleteMapping()
    public void deleteMatch(@RequestBody MovieSpotifyDBEntity match) {
        matchUseCase.deleteMatch(match);
    }
}
