package dhbw.soundtrack_guesser.entrypoints.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Not enough Matches to create a Game")
public class NotEnoughMatchesException extends RuntimeException{
}
