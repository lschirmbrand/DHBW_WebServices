package dhbw.soundtrack_guesser.dataproviders.movie;

import dhbw.soundtrack_guesser.models.Movie;

import java.util.List;

public interface MovieProvider {
    List<Movie> getMovies(List<Integer> movieIDs);

    Movie getMovieById(int id);

    List<Movie> search(String title);
}
