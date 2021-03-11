package com.example.demo.usecase;

import com.example.demo.dataproviders.database.MatchRepository;
import com.example.demo.dataproviders.database.entities.MatchEntity;
import com.example.demo.dataproviders.movies.MovieProvider;
import com.example.demo.dataproviders.spotify.SpotifyProvider;
import com.example.demo.models.Match;
import com.example.demo.models.Movie;
import com.example.demo.models.Track;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


public class MatchUseCase {
    private final MovieProvider movieProvider;
    private final SpotifyProvider spotifyProvider;
    @Autowired
    private MatchRepository matchRepository;


    public MatchUseCase(MovieProvider movieProvider, SpotifyProvider spotifyProvider) {
        this.spotifyProvider = spotifyProvider;
        this.movieProvider = movieProvider;
    }

    public Match getMatch(int id) {
        Optional<MatchEntity> entityOptional = matchRepository.findById(id);
        return entityOptional.map(this::entityToMatch).orElse(null);
    }

    public List<Match> getAll() {
        return StreamSupport.stream(matchRepository.findAll().spliterator(), false)
                .map(this::entityToMatch)
                .collect(Collectors.toList());
    }

    public List<Track> getTracksForMovie(int movieID) {
        return StreamSupport.stream(matchRepository.findAll().spliterator(), false)
                .filter(entity -> entity.getTmdbID() == movieID)
                .map(entity -> spotifyProvider.getTrack(entity.getSpotifyID()))
                .collect(Collectors.toList());
    }

    public Movie getMovieForTrack(String trackID) {
        return StreamSupport.stream(matchRepository.findAll().spliterator(), false)
                .filter(entity -> entity.getSpotifyID().equals(trackID))
                .map(entity -> movieProvider.getMovieById(entity.getTmdbID()))
                .findFirst().orElse(null);
    }

    public Match addMatch(MatchEntity matchEntity) {
        MatchEntity entity = this.matchRepository.save(matchEntity);
        Movie movie = movieProvider.getMovieById(entity.getTmdbID());
        Track track = spotifyProvider.getTrack(entity.getSpotifyID());

        return new Match(entity.getId(), movie, track);
    }

    public MatchEntity editMatch(MatchEntity matchEntity) {
        Optional<MatchEntity> entityOptional = this.matchRepository.findById(matchEntity.getId());
        if (entityOptional.isPresent()) {
            MatchEntity entity = entityOptional.get();
            entity.setTmdbID(matchEntity.getTmdbID());
            entity.setspotifyID(matchEntity.getSpotifyID());
            matchRepository.save(entity);

            return entity;
        }
        throw new RuntimeException("Could not find entity with id " + matchEntity.getId());
    }

    public void deleteMatch(int id) {
        Optional<MatchEntity> entityOptional = this.matchRepository.findById(id);

        if (entityOptional.isPresent()) {
            matchRepository.delete(entityOptional.get());
            return;
        }
        throw new RuntimeException("Could not find entity with id " + id);
    }

    private Match entityToMatch(MatchEntity entity) {
        Movie movie = movieProvider.getMovieById(entity.getTmdbID());
        Track track = spotifyProvider.getTrack(entity.getSpotifyID());

        return new Match(entity.getId(), movie, track);
    }

}
