package dhbw.soundtrack_guesser.models;

import dhbw.soundtrack_guesser.dataproviders.movie.TMDBMovieDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

@JsonDeserialize(using = TMDBMovieDeserializer.class)
public class Movie {
    private final String posterURLPrefix = "https://image.tmdb.org/t/p/w1280";

    private int id;
    private String title;
    private String posterURL;
    private List<String> genres;
    private String overview;

    public Movie() {

    }

    public Movie(int id, String title, String posterPath, List<String> genres, String overview) {
        this.id = id;
        this.title = title;
        this.posterURL = posterPath.equals("null") ? "" : posterURLPrefix + posterPath;
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

    @Override
    public String toString() {
        return "Movie{" +
                "posterURLPrefix='" + posterURLPrefix + '\'' +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", posterURL='" + posterURL + '\'' +
                ", genres=" + genres +
                ", overview='" + overview + '\'' +
                '}';
    }
}
