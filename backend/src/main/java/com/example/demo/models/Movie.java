package com.example.demo.models;

import java.util.List;

public class Movie {
    private final String posterURLPrefix = "https://image.tmdb.org/t/p/w1280";

    private final int id;
    private final String title;
    private final String posterURL;
    private final List<String> genres;
    private final String overview;

    public Movie(int id, String title, String posterPath, List<String> genres, String overview) {
        this.id = id;
        this.title = title;
        this.posterURL = posterURLPrefix + posterPath;
        this.genres = genres;
        this.overview = overview;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPosterURL() {
        return posterURL;
    }

    public List<String> getGenres() {
        return genres;
    }

    public String getOverview() {
        return overview;
    }
}
