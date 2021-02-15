package com.example.demo.entrypoints;

import com.example.demo.dataproviders.GameProvider;
import com.example.demo.models.Game;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
public class GameController {

    private GameProvider gameProvider;

    public GameController(GameProvider gameProvider) {
        this.gameProvider = gameProvider;
    }

    @GetMapping()
    public Game getNewGame() {
        return gameProvider.createGame();
    }
}
