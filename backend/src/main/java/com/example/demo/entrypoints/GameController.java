package com.example.demo.entrypoints;

import com.example.demo.models.Game;

import com.example.demo.usecase.CreateGameUsecase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
public class GameController {

    private final CreateGameUsecase createGameUsecase;

    public GameController(CreateGameUsecase createGameUsecase) {
        this.createGameUsecase = createGameUsecase;
    }

    @GetMapping()
    public Game getNewGame(
            @RequestParam(name = "roundCount", defaultValue = "10") int roundCount,
            @RequestParam(name = "moviesPerRound", defaultValue = "3") int moviesPerRound) {
        return createGameUsecase.create(roundCount, moviesPerRound);
    }
}
