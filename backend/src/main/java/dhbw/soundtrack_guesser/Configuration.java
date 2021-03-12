package dhbw.soundtrack_guesser;

import dhbw.soundtrack_guesser.dataproviders.movies.MovieProvider;
import dhbw.soundtrack_guesser.dataproviders.movies.TMDBMovieProvider;
import dhbw.soundtrack_guesser.dataproviders.spotify.SpotifyAccessTokenProvider;
import dhbw.soundtrack_guesser.dataproviders.spotify.SpotifyProvider;
import dhbw.soundtrack_guesser.usecase.CreateGameUseCase;
import dhbw.soundtrack_guesser.usecase.MatchUseCase;
import dhbw.soundtrack_guesser.usecase.MovieListUseCase;
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
    SpotifyProvider spotifyProvider() {
        return new SpotifyProvider(new RestTemplateBuilder(), spotifyAccessTokenProvider());
    }

    @Bean
    SpotifyAccessTokenProvider spotifyAccessTokenProvider() {
        return new SpotifyAccessTokenProvider(new RestTemplateBuilder(), spotifyBasicToken);
    }

    @Bean
    MovieListUseCase movieListUseCase() {
        return new MovieListUseCase();
    }

    @Bean
    MatchUseCase matchUseCase() {
        return new MatchUseCase(movieProvider(), spotifyProvider());
    }


    @Bean
    CreateGameUseCase createGameUsecase() {
        return new CreateGameUseCase(movieProvider(), movieListUseCase(), matchUseCase());
    }
}