package com.example.demo.models;

import java.util.List;

public class Round {
    private final List<Movie> movies;
    private final int correctIndex;
    private final String soundTrackUrl;

    public Round(List<Movie> movies, int correctIndex, String soundTrackUrl) {
        this.movies = movies;
        this.correctIndex = correctIndex;
        this.soundTrackUrl = soundTrackUrl;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public int getCorrectIndex() {
        return correctIndex;
    }

    public String getSoundTrackUrl() {
        return soundTrackUrl;
    }
}
