package com.example.demo.entrypoints;

import com.example.demo.dataproviders.MovieProvider;
import com.example.demo.models.Movie;
import org.springframework.web.bind.annotation.GetMapping;
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
}
