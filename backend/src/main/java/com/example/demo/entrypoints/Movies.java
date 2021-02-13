package com.example.demo.entrypoints;

import com.example.demo.dataproviders.MovieProvider;
import com.example.demo.models.Movie;
import com.example.demo.models.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/movies")
public class Movies {

    MovieProvider movieProvider;

    public Movies(MovieProvider movieProvider) {
        this.movieProvider = movieProvider;
    }

    @GetMapping("")
    public List<Movie> getAllMovies() {
        return movieProvider.getAllMovies();
    }

    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable int id) {
        return movieProvider.getMovieById(id);
    }

    @GetMapping("/search/{title}")
    public Query getSearch(@PathVariable String title) {
        return movieProvider.search(title);
    }

}
