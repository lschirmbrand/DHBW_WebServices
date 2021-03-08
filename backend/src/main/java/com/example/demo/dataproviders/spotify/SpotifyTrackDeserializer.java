package com.example.demo.dataproviders.spotify;

import com.example.demo.models.Track;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SpotifyTrackDeserializer extends StdDeserializer<Track> {

    public SpotifyTrackDeserializer() {
        this(null);
    }

    public SpotifyTrackDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Track deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);

        String id = node.get("id").asText();
        String name = node.get("name").asText();

        List<String> artists = new ArrayList<>();
        JsonNode artistsList = node.get("artists");
        if (artistsList != null) {
            artistsList.forEach(artistNode -> artists.add(artistNode.get("name").asText()));
        }

        String previewURL = node.get("preview_url").asText();
        String spotifyURL = node.get("external_urls").get("spotify").asText();
        String albumName = node.get("album").get("name").asText();
        String coverURL = node.get("album").get("images").get(0).get("url").asText();

        return new Track(id, name, String.join(", ", artists), previewURL, spotifyURL, albumName, coverURL);
    }
}
