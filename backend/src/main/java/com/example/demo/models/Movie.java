package com.example.demo.models;

public class Movie {
    private String title;
    private String posterURL;

    public Movie(String title, String posterURL) {
        this.title = title;
        this.posterURL = posterURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterURL() {
        return posterURL;
    }

    public void setPosterURL(String posterURL) {
        this.posterURL = posterURL;
    }
}
