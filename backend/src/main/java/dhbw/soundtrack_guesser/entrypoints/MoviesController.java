package dhbw.soundtrack_guesser.entrypoints;

import dhbw.soundtrack_guesser.entrypoints.exceptions.MovieNotFoundException;
import dhbw.soundtrack_guesser.models.Movie;
import dhbw.soundtrack_guesser.service.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/movies")
public class MoviesController {

    private final MovieService movieService;

    public MoviesController( MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("")
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable int id) {
        return movieService.getMovieById(id);
    }

    @GetMapping("/for/{trackID}")
    public Movie getMovieForTrack(@PathVariable String trackID) {
        return movieService.findForTrack(trackID).orElseThrow(MovieNotFoundException::new);
    }

    @GetMapping("/search/{title}")
    public List<Movie> getSearch(@PathVariable String title) {
        return movieService.search(title);
    }

}
