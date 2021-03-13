package dhbw.soundtrack_guesser;

import dhbw.soundtrack_guesser.dataproviders.movie.MovieProvider;
import dhbw.soundtrack_guesser.dataproviders.movie.TMDBMovieProvider;
import dhbw.soundtrack_guesser.dataproviders.track.SpotifyAccessTokenProvider;
import dhbw.soundtrack_guesser.dataproviders.track.SpotifyProvider;
import dhbw.soundtrack_guesser.dataproviders.track.TrackProvider;
import dhbw.soundtrack_guesser.service.GameService;
import dhbw.soundtrack_guesser.service.MatchService;
import dhbw.soundtrack_guesser.service.MovieService;
import dhbw.soundtrack_guesser.service.TrackService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Value("${tmdb.bearerToken}")
    String tmdbBearerToken;
    @Value("${spotify.basicToken}")
    String spotifyBasicToken;

    @Bean
    MovieProvider movieProvider() {
        return new TMDBMovieProvider(new RestTemplateBuilder(), tmdbBearerToken);
    }

    @Bean
    TrackProvider trackProvider() {
        return new SpotifyProvider(new RestTemplateBuilder(), spotifyAccessTokenProvider());
    }

    @Bean
    SpotifyAccessTokenProvider spotifyAccessTokenProvider() {
        return new SpotifyAccessTokenProvider(new RestTemplateBuilder(), spotifyBasicToken);
    }

    @Bean
    MovieService movieService() {
        return new MovieService(movieProvider());
    }

    @Bean
    MatchService matchService() {
        return new MatchService(movieProvider(), trackProvider());
    }

    @Bean
    GameService gameService() {
        return new GameService(matchService());
    }

    @Bean
    TrackService trackService() {
        return new TrackService(trackProvider());
    }
}
