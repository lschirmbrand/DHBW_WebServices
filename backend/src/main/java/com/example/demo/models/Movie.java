package com.example.demo.models;

import com.example.demo.dataproviders.TMDBMovieDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

@JsonDeserialize(using = TMDBMovieDeserializer.class)
public class Movie {
    private final String posterURLPrefix = "https://image.tmdb.org/t/p/w1280";

    private int id;
    private String title;
    private String poster_path;
    private List<String> genres;
    private String overview;

    public Movie() {

    }

    public Movie(int id, String title, String posterPath, List<String> genres, String overview) {
        this.id = id;
        this.title = title;
        this.poster_path = posterPath;
        this.genres = genres;
        this.overview = overview;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPoster_path() {
        return posterURLPrefix + poster_path;
    }


    public List<String> getGenres() {
        return genres;
    }

    public String getOverview() {
        return overview;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "posterURLPrefix='" + posterURLPrefix + '\'' +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", posterURL='" + poster_path + '\'' +
                ", genres=" + genres +
                ", overview='" + overview + '\'' +
                '}';
    }
}
