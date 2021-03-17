package dhbw.soundtrack_guesser.service;

import dhbw.soundtrack_guesser.entrypoints.exceptions.NotEnoughMatches;
import dhbw.soundtrack_guesser.models.Game;
import dhbw.soundtrack_guesser.models.Match;
import dhbw.soundtrack_guesser.models.Movie;
import dhbw.soundtrack_guesser.models.Track;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class GameService {
    private final MatchService matchService;

    public GameService(MatchService matchService) {
        this.matchService = matchService;
    }

    public Game create(int roundCount, int moviesPerRound) {
        List<Match> matches = matchService.getAll();

        if (matches.size() < roundCount * moviesPerRound) {
            throw new NotEnoughMatches();
        }

        List<Movie> movieList = matches.stream().map(Match::getMovie).collect(Collectors.toList());

        List<Game.Round> rounds = new ArrayList<>();

        for (int i = 0; i < roundCount; i++) {
            List<Movie> movies = choose(moviesPerRound, movieList);
            int correctIndex = ThreadLocalRandom.current().nextInt(moviesPerRound);
            Track track = null;

            for (Match match : matches) {
                if (match.getMovie() == movies.get(correctIndex)) {
                    track = match.getTrack();
                }
            }

            rounds.add(new Game.Round(movies, correctIndex, track));
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
