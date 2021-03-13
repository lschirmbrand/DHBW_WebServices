package dhbw.soundtrack_guesser.service;

import dhbw.soundtrack_guesser.dataproviders.match.MatchEntity;
import dhbw.soundtrack_guesser.dataproviders.match.MatchRepository;
import dhbw.soundtrack_guesser.dataproviders.movie.MovieProvider;
import dhbw.soundtrack_guesser.models.Movie;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class MovieService {

    @Autowired
    private MatchRepository matchRepository;

    private final MovieProvider movieProvider;

    public MovieService(MovieProvider movieProvider) {
        this.movieProvider = movieProvider;
    }


    public List<Movie> getAllMovies() {
        // get movieIDs from DB
        Iterable<MatchEntity> matches = matchRepository.findAll();

        // fetch all Movies from tmdb
        return StreamSupport.stream(matches.spliterator(), true)
                .map(MatchEntity::getMovieID)
                .map(movieProvider::getMovieById)
                .collect(Collectors.toList());
    }

    public Movie getMovieById(int id) {
        return movieProvider.getMovieById(id);
    }

    public Optional<Movie> findForTrack(String trackID) {
        return StreamSupport.stream(matchRepository.findAllByTrack(trackID).spliterator(), false)
                .map(MatchEntity::getMovieID)
                .map(movieProvider::getMovieById)
                .findFirst();
    }

    public List<Movie> search(String query) {
        return movieProvider.search(query);
    }
}
