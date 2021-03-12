package dhbw.soundtrack_guesser.entrypoints;

import dhbw.soundtrack_guesser.models.Game;
import dhbw.soundtrack_guesser.usecase.CreateGameUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
public class GameController {

    private final CreateGameUseCase createGameUsecase;

    public GameController(CreateGameUseCase createGameUsecase) {
        this.createGameUsecase = createGameUsecase;
    }

    @GetMapping("")
    public Game getNewGame(
            @RequestParam(name = "roundCount", defaultValue = "10") int roundCount,
            @RequestParam(name = "moviesPerRound", defaultValue = "3") int moviesPerRound) {
        return createGameUsecase.create(roundCount, moviesPerRound);
    }
}
