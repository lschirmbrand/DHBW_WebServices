package com.example.demo.dataproviders;

import com.example.demo.models.Game;
import com.example.demo.models.Movie;
import com.example.demo.models.Round;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameProvider {
    private final MovieProvider movieProvider;

    public GameProvider(MovieProvider movieProvider) {
        this.movieProvider = movieProvider;
    }


    public Game createGame() {
        List<Movie> movieList = movieProvider.getAllMovies();

        List<Round> rounds = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            List<Movie> movies = choose(3, movieList);
            rounds.add(new Round(movies, 0, ""));
        }

        return new Game(rounds);
    }

    private List<Movie> choose(int n, List<Movie> movies) {
        List<Movie> chosen = new ArrayList<>();
        Random rand = new Random();

        for (int i = 0; i < n; i++) {
            chosen.add(movies.remove(rand.nextInt(movies.size())));
        }
        return chosen;
    }
}
