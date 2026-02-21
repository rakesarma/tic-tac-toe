package org.example;

import org.example.controllers.GameController;
import org.example.exceptions.InvalidMoveException;
import org.example.models.*;

import java.util.ArrayList;
import java.util.List;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InvalidMoveException {
        System.out.println("Welcome to Tic Tac Toe!");
        int dimension = 3;

        //Add players
        List<Player> players = new ArrayList<>();
        players.add(new Player("Rakesh", new Symbol("X")));
        players.add(new Player("Blitz", new Symbol("O")));

        //Add winning strategies
        List<WinningSrategy> strategies = new ArrayList<>();
        strategies.add(new ColWinningStrategy());
        strategies.add(new RowWinningStrategy());

        GameController gameController = new GameController();

        //Start a new game
        Game game = gameController.startNewGame(dimension, players, strategies);
        game.printBoard();



        while(game.getGameState() == GameState.IN_PROGRESS)
        {
            gameController.makeMove(game);
            game.printBoard();
        }

        }
    }
