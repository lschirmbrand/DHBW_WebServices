package com.example.demo.dataproviders;

import com.example.demo.models.Movie;
import org.springframework.context.annotation.Bean;

import java.util.List;

public class MockMovieProvider implements MovieProvider {


    public MockMovieProvider() {

    }


    @Override
    public List<Movie> getAllMovies() {
        return List.of(
                new Movie("Star Wars", ""),
                new Movie("Harry Potter", ""),
                new Movie("Herr der Ringe", "")
        );
    }
}
