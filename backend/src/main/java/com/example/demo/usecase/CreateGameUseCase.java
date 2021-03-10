package com.example.demo.usecase;

import com.example.demo.dataproviders.movies.MovieProvider;
import com.example.demo.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class CreateGameUseCase {
    MovieProvider movieProvider;
    MovieListUseCase movieListUsecase;
    MatchUseCase matchUseCase;


    public CreateGameUseCase(MovieProvider movieProvider, MovieListUseCase movieListUsecase, MatchUseCase matchUseCase) {
        this.movieProvider = movieProvider;
        this.movieListUsecase = movieListUsecase;
        this.matchUseCase = matchUseCase;
    }

    public Game create(int roundCount, int moviesPerRound) {
        List<Match> matches = matchUseCase.getAll();

        List<Movie> movieList = matches.stream().map(Match::getMovie).collect(Collectors.toList());

        List<Round> rounds = new ArrayList<>();

        for (int i = 0; i < roundCount; i++) {
            List<Movie> movies = choose(moviesPerRound, movieList);
            int correctIndex = ThreadLocalRandom.current().nextInt(moviesPerRound);
            Track track = null;

            for (Match match : matches) {
                if (match.getMovie() == movies.get(correctIndex)) {
                    track = match.getTrack();
                }
            }

            rounds.add(new Round(movies, correctIndex, track));
        }

        return new Game(rounds);
    }

    private List<Movie> choose(int n, List<Movie> movies) {
        List<Movie> chosen = new ArrayList<>();
        Random rand = ThreadLocalRandom.current();

        for (int i = 0; i < n; i++) {

            if (!movies.isEmpty()) {
                chosen.add(movies.remove(rand.nextInt(movies.size())));
            }
        }
        return chosen;
    }
}
