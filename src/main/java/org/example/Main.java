package org.example;

import org.example.controllers.GameController;
import org.example.models.Game;
import org.example.models.Player;
import org.example.models.PlayerType;
import org.example.models.Symbol;

import java.util.ArrayList;
import java.util.List;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Tic Tac Toe!");
        int dimension = 3;
        List<Player> players = new ArrayList<>();

        players.add(new Player("Rakesh", new Symbol("X")));
        players.add(new Player("Blitz", new Symbol("O")));

        GameController gameController = new GameController();

        Game game = gameController.startNewGame(dimension, players);
        game.printBoard();



        System.out.println("Game started with ID: ");

        }
    }
