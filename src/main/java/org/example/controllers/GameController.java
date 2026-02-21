package org.example.controllers;

import org.example.exceptions.InvalidMoveException;
import org.example.models.Game;
import org.example.models.Player;
import org.example.models.WinningSrategy;

import java.util.List;

public class GameController {

    public Game startNewGame(int dimension, List<Player> players, List<WinningSrategy> winningStrategies)
    {
        return Game.builder().setDimension(dimension).setPlayers(players).setWinningStrategies(winningStrategies).build();
    }

    public void makeMove(Game game) throws InvalidMoveException {
        game.makeMove();
    }

}
