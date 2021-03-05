package com.example.demo.usecase;

import com.example.demo.dataproviders.movies.MovieProvider;
import com.example.demo.models.Game;
import com.example.demo.models.Movie;
import com.example.demo.models.Round;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreateGameUseCase {
    MovieProvider movieProvider;
    MovieListUseCase movieListUsecase;

    public CreateGameUseCase(MovieProvider movieProvider, MovieListUseCase movieListUsecase) {
        this.movieProvider = movieProvider;
        this.movieListUsecase = movieListUsecase;
    }

    public Game create(int roundCount, int moviesPerRound) {
        List<Movie> movieList = movieListUsecase.getAllMovies();

        List<Round> rounds = new ArrayList<>();

        for (int i = 0; i < roundCount; i++) {
            List<Movie> movies = choose(moviesPerRound, movieList);
            rounds.add(new Round(movies, 0, ""));
        }

        return new Game(rounds);
    }

    private List<Movie> choose(int n, List<Movie> movies) {
        List<Movie> chosen = new ArrayList<>();
        Random rand = new Random();

        for (int i = 0; i < n; i++) {

            if(!movies.isEmpty()) {
                chosen.add(movies.remove(rand.nextInt(movies.size())));
            }
        }
        return chosen;
    }
}
