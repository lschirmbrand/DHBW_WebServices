package com.example.demo.entrypoints;

import com.example.demo.dataproviders.database.entities.MatchEntity;
import com.example.demo.entrypoints.exceptions.DuplicateTrackException;
import com.example.demo.entrypoints.exceptions.MovieNotFound;
import com.example.demo.entrypoints.exceptions.TracksNotFoundException;
import com.example.demo.models.Match;
import com.example.demo.models.Movie;
import com.example.demo.models.Track;
import com.example.demo.usecase.MatchUseCase;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/match")
public class MatchController {

    private final MatchUseCase matchUseCase;

    public MatchController(MatchUseCase matchUseCase) {
        this.matchUseCase = matchUseCase;
    }

    @GetMapping()
    public List<Match> getAllMatches() {
        return matchUseCase.getAll();
    }

    @GetMapping("/{id}")
    public Match getMatchByID(@PathVariable int id) {
        return matchUseCase.getMatch(id);
    }

    @GetMapping("/movie/{movieID}")
    public List<Track> forMovie(@PathVariable int movieID) {
        List<Track> tracks = matchUseCase.getTracksForMovie(movieID);
        if (tracks.isEmpty()) {
            throw new TracksNotFoundException();
        }
        return tracks;
    }

    @GetMapping("/track/{trackID}")
    public Movie forTrack(@PathVariable String trackID) {
        Movie movie = matchUseCase.getMovieForTrack(trackID);
        if (movie == null) {
            throw new MovieNotFound();
        }
        return movie;
    }

    @PostMapping()
    public Match addMatch(@RequestBody MatchEntity matchEntity) {
        List<String> trackIds = matchUseCase.getAll().stream().map(match -> match.getTrack().getId()).collect(Collectors.toList());
        if (trackIds.contains(matchEntity.getSpotifyID())) {
            throw new DuplicateTrackException();
        }
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
