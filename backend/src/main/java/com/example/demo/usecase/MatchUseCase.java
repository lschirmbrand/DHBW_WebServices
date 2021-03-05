package com.example.demo.usecase;

import com.example.demo.dataproviders.database.MovieSpotifyRepository;
import com.example.demo.entities.MovieSpotifyDBEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MatchUseCase {
    private MovieSpotifyRepository movieSpotifyRepository;

    @Autowired
    public MatchUseCase(MovieSpotifyRepository movieSpotifyRepository) {
        this.movieSpotifyRepository = movieSpotifyRepository;
    }

    public List<MovieSpotifyDBEntity> getAll() {
        return StreamSupport.stream(movieSpotifyRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public MovieSpotifyDBEntity addMatch(MovieSpotifyDBEntity match) {
        return this.movieSpotifyRepository.save(match);
    }

    public MovieSpotifyDBEntity editMatch(MovieSpotifyDBEntity match) {
        Optional<MovieSpotifyDBEntity> entityOptional = this.movieSpotifyRepository.findById(match.getId());
        if (entityOptional.isPresent()) {
            MovieSpotifyDBEntity entity = entityOptional.get();
            entity.setTmdbID(match.getTmdbID());
            entity.setSpotifyURI(match.getSpotifyURI());
            movieSpotifyRepository.save(entity);

            return entity;
        }
        throw new RuntimeException("Could not find entity with id " + match.getId());
    }

    public void deleteMatch(MovieSpotifyDBEntity match) {
        Optional<MovieSpotifyDBEntity> entityOptional = this.movieSpotifyRepository.findById(match.getId());

        if (entityOptional.isPresent()) {
            movieSpotifyRepository.delete(entityOptional.get());
            return;
        }
        throw new RuntimeException("Could not find entity with id " + match.getId());
    }
}
