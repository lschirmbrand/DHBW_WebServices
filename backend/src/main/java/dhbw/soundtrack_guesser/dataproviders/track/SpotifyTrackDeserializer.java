package dhbw.soundtrack_guesser.dataproviders.track;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import dhbw.soundtrack_guesser.models.Track;

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

        JsonNode imagesNode = node.get("album").get("images");
        String coverURL = imagesNode.isEmpty() ? "" : imagesNode.get(0).get("url").asText();

        return new Track(id, name, String.join(", ", artists), previewURL, spotifyURL, albumName, coverURL);
    }
}
