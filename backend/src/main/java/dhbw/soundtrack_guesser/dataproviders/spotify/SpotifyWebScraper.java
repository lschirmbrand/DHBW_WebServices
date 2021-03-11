package dhbw.soundtrack_guesser.dataproviders.spotify;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class SpotifyWebScraper {
    public String scrapeForPreviewURL(String trackID) {
        String json;
        try {
            String url = "https://open.spotify.com/embed/track/" + trackID;
            Document doc = Jsoup.connect(url).get();
            json = URLDecoder.decode(doc.getElementById("resource").html(), StandardCharsets.UTF_8);

        } catch (IOException e) {
            e.printStackTrace();
            return "null";
        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode node = mapper.readTree(json);
            return node.get("preview_url").asText();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "null";
        }
    }
}
