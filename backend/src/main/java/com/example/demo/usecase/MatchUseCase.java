package com.example.demo.usecase;

import com.example.demo.dataproviders.database.MatchRepository;
import com.example.demo.dataproviders.movies.MovieProvider;
import com.example.demo.dataproviders.spotify.SpotifyProvider;
import com.example.demo.entities.MatchEntity;
import com.example.demo.models.Match;
import com.example.demo.models.Movie;
import com.example.demo.models.Track;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


public class MatchUseCase {
    @Autowired
    private MatchRepository matchRepository;
    private MovieProvider movieProvider;
    private SpotifyProvider spotifyProvider;


    public MatchUseCase(MovieProvider movieProvider, SpotifyProvider spotifyProvider) {
        this.spotifyProvider = spotifyProvider;
        this.movieProvider = movieProvider;
    }

    public List<Match> getAll(boolean forcePreviewURL) {
        return StreamSupport.stream(matchRepository.findAll().spliterator(), false)
                .map(matchEntity -> {
                    Movie movie = movieProvider.getMovieById(matchEntity.getTmdbID());
                    Track track = spotifyProvider.getTrack(matchEntity.getSpotifyID(), forcePreviewURL);

                    return new Match(matchEntity.getId(), movie, track);
                }).collect(Collectors.toList());
    }

    public Match addMatch(MatchEntity matchEntity) {
        MatchEntity entity = this.matchRepository.save(matchEntity);
        Movie movie = movieProvider.getMovieById(entity.getTmdbID());
        Track track = spotifyProvider.getTrack(entity.getSpotifyID(), false);

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
}
