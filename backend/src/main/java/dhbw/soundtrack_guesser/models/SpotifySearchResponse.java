package dhbw.soundtrack_guesser.models;

import java.util.List;

public class SpotifySearchResponse {
    private Tracks tracks;

    public SpotifySearchResponse() {
    }

    public Tracks getTracks() {
        return tracks;
    }

    public void setTracks(Tracks tracks) {
        this.tracks = tracks;
    }

    public static class Tracks {
        private List<Track> items;

        public Tracks() {

        }

        public List<Track> getItems() {
            return items;
        }

        public void setItems(List<Track> items) {
            this.items = items;
        }
    }
}
