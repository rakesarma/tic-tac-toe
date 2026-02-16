package org.example.controllers;

import org.example.models.Game;
import org.example.models.Player;

import java.util.List;

public class GameController {

    public Game startNewGame(int dimension, List<Player> players)
    {
        return Game.builder().setDimension(dimension).setPlayers(players).build();
    }
}
