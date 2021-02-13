package com.example.demo.dataproviders;

import com.example.demo.models.Movie;
import com.example.demo.models.Query;

import java.util.List;

public interface MovieProvider {
    List<Movie> getAllMovies();

    Movie getMovieById(int id);

    Query search(String title);
}
