package com.example.demo.models;

import java.util.List;

public class Game {
    private final List<Round> rounds;

    public Game(List<Round> rounds) {
        this.rounds = rounds;
    }

    public List<Round> getRounds() {
        return rounds;
    }
}
