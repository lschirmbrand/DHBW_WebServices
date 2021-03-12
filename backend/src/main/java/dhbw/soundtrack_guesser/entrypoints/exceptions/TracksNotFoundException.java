package dhbw.soundtrack_guesser.entrypoints.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No tracks found for this movie")
public class TracksNotFoundException extends RuntimeException {
}
