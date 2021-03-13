package dhbw.soundtrack_guesser;

import dhbw.soundtrack_guesser.dataproviders.movie.MovieProvider;
import dhbw.soundtrack_guesser.dataproviders.movie.TMDBMovieProvider;
import dhbw.soundtrack_guesser.dataproviders.track.SpotifyAccessTokenProvider;
import dhbw.soundtrack_guesser.dataproviders.track.SpotifyProvider;
import dhbw.soundtrack_guesser.dataproviders.track.TrackProvider;
import dhbw.soundtrack_guesser.service.*;
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
    MovieService movieListUseCase() {
        return new MovieService(movieProvider());
    }

    @Bean
    MatchService matchUseCase() {
        return new MatchService(movieProvider(), trackProvider());
    }


    @Bean
    GameService createGameUsecase() {
        return new GameService(movieProvider(), movieListUseCase(), matchUseCase());
    }

    @Bean
    TrackService trackService() {
        return new TrackService(trackProvider());
    }
}
