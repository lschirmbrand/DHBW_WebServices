package com.example.demo.models;

public class Match {
    private int id;
    private Movie movie;
    private Track track;

    public Match(int id, Movie movie, Track track) {
        this.id = id;
        this.movie = movie;
        this.track = track;
    }

    public int getId() {
        return id;
    }

    public Movie getMovie() {
        return movie;
    }

    public Track getTrack() {
        return track;
    }
}
