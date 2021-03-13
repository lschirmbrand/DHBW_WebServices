package dhbw.soundtrack_guesser.service;

import dhbw.soundtrack_guesser.dataproviders.match.MatchEntity;
import dhbw.soundtrack_guesser.dataproviders.match.MatchRepository;
import dhbw.soundtrack_guesser.dataproviders.movie.MovieProvider;
import dhbw.soundtrack_guesser.dataproviders.track.TrackProvider;
import dhbw.soundtrack_guesser.models.Match;
import dhbw.soundtrack_guesser.models.Movie;
import dhbw.soundtrack_guesser.models.Track;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


public class MatchService {
    private final MovieProvider movieProvider;
    private final TrackProvider trackProvider;
    @Autowired
    private MatchRepository matchRepository;


    public MatchService(MovieProvider movieProvider, TrackProvider trackProvider) {
        this.trackProvider = trackProvider;
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

    public Match addMatch(MatchEntity matchEntity) {
        MatchEntity entity = this.matchRepository.save(matchEntity);
        Movie movie = movieProvider.getMovieById(entity.getTmdbID());
        Track track = trackProvider.getTrack(entity.getSpotifyID());

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
        Track track = trackProvider.getTrack(entity.getSpotifyID());

        return new Match(entity.getId(), movie, track);
    }
}
