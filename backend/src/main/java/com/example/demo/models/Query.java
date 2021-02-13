package com.example.demo.models;


import java.util.List;

public class Query {
    private List<Movie> results;

    public Query() {

    }

    public Query(List<Movie> results) {
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
