package dhbw.soundtrack_guesser.entrypoints;

import dhbw.soundtrack_guesser.dataproviders.movies.MovieProvider;
import dhbw.soundtrack_guesser.models.Movie;
import dhbw.soundtrack_guesser.usecase.MovieListUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/movies")
public class MoviesController {

    private final MovieProvider movieProvider;
    private final MovieListUseCase movieListUsecase;

    public MoviesController(MovieProvider movieProvider, MovieListUseCase movieListUsecase) {
        this.movieProvider = movieProvider;
        this.movieListUsecase = movieListUsecase;
    }

    @GetMapping("")
    public List<Movie> getAllMovies() {
        return movieListUsecase.getAllMovies();
    }

    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable int id) {
        return movieListUsecase.getMovieById(id);
    }

    @GetMapping("/search/{title}")
    public List<Movie> getSearch(@PathVariable String title) {
        return movieProvider.search(title);
    }

}
