package com.example.demo.dataproviders.movies;

import com.example.demo.models.Movie;

import java.util.List;

public interface MovieProvider {
    List<Movie> getMovies(List<Integer> movieIDs);

    Movie getMovieById(int id);

    List<Movie> search(String title);
}
