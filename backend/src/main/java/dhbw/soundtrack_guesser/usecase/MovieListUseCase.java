package dhbw.soundtrack_guesser.usecase;

import dhbw.soundtrack_guesser.dataproviders.database.MatchRepository;
import dhbw.soundtrack_guesser.dataproviders.database.entities.MatchEntity;
import dhbw.soundtrack_guesser.dataproviders.movies.MovieProvider;
import dhbw.soundtrack_guesser.models.Movie;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class MovieListUseCase {

    @Autowired
    MatchRepository matchRepository;

    @Autowired
    MovieProvider movieProvider;

    public List<Movie> getAllMovies() {

        // get movieIDs from DB

        Iterable<MatchEntity> movieSpotifyDBEntities = matchRepository.findAll();

        // fetch all Movies form tmdb

        return StreamSupport.stream(movieSpotifyDBEntities.spliterator(), true)
                .map(movieSpotifyDBEntity -> movieProvider.getMovieById(movieSpotifyDBEntity.getTmdbID()))
                .collect(Collectors.toList());
    }

    public Movie getMovieById(int id) {
        return movieProvider.getMovieById(id);
    }
}
