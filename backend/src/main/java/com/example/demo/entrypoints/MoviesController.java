package com.example.demo.entrypoints;

import com.example.demo.dataproviders.movies.MovieProvider;
import com.example.demo.models.Movie;
import com.example.demo.usecase.MovieListUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/movies")
public class MoviesController {

    MovieProvider movieProvider;
    MovieListUseCase movieListUsecase;

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
