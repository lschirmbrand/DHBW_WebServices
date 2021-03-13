package dhbw.soundtrack_guesser.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import dhbw.soundtrack_guesser.dataproviders.track.SpotifyTrackDeserializer;

@JsonDeserialize(using = SpotifyTrackDeserializer.class)
public class Track {
    private String id;

    private String name;
    private String artistName;
    private String previewURL;
    private String url;
    private String albumName;
    private String coverURL;

    public Track(String id, String name, String artistName, String previewURL, String url, String albumName, String coverURL) {
        this.id = id;
        this.name = name;
        this.artistName = artistName;
        this.previewURL = previewURL;
        this.url = url;
        this.albumName = albumName;
        this.coverURL = coverURL;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getPreviewURL() {
        return previewURL;
    }

    public void setPreviewURL(String previewURL) {
        this.previewURL = previewURL;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getCoverURL() {
        return coverURL;
    }

    public void setCoverURL(String coverURL) {
        this.coverURL = coverURL;
    }
}
