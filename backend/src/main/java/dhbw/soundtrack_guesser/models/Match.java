package dhbw.soundtrack_guesser.models;

public class Match {
    private final int id;
    private final Movie movie;
    private final Track track;

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
