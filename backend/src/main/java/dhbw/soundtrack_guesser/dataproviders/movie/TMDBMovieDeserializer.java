package dhbw.soundtrack_guesser.dataproviders.movie;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import dhbw.soundtrack_guesser.models.Movie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TMDBMovieDeserializer extends StdDeserializer<Movie> {

    public TMDBMovieDeserializer() {
        this(null);
    }

    public TMDBMovieDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Movie deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);

        int id = node.get("id").asInt();
        String title = node.get("title").asText();
        String posterPath = node.get("poster_path").asText();


        List<String> genres = new ArrayList<>();
        JsonNode genresList = node.get("genres");
        // Search-Endpoint from TMDB only provides Genre IDs so "genres" is empty
        if (genresList != null) {
            genresList.forEach(genreNode -> genres.add(genreNode.get("name").asText()));
        }

        String overview = node.get("overview").asText();

        return new Movie(id, title, posterPath, genres, overview);
    }
}
