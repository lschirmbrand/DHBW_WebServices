package com.example.demo.models;


import java.util.List;

public class TMDBSearchResponse {
    private List<Movie> results;

    public TMDBSearchResponse() {

    }

    public TMDBSearchResponse(List<Movie> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "Query{" +
                "results=" + results +
                '}';
    }

    public List<Movie> getResults() {
        return results;
    }
}
