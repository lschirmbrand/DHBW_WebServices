package dhbw.soundtrack_guesser.models;

import java.util.List;

public class Game {
    private final List<Round> rounds;

    public Game(List<Round> rounds) {
        this.rounds = rounds;
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public static class Round {
        private final List<Movie> movies;
        private final int correctIndex;
        private final Track soundTrack;

        public Round(List<Movie> movies, int correctIndex, Track soundTrack) {
            this.movies = movies;
            this.correctIndex = correctIndex;
            this.soundTrack = soundTrack;
        }

        public List<Movie> getMovies() {
            return movies;
        }

        public int getCorrectIndex() {
            return correctIndex;
        }

        public Track getSoundTrack() {
            return soundTrack;
        }
    }
}
