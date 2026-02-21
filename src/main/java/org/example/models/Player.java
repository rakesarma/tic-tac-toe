package org.example.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Scanner;

@Getter
@Setter
public class Player {

    private String name;
    private Symbol symbol;
    private PlayerType playerType;
    private static Scanner scanner = new Scanner(System.in);

    public Player(String name, Symbol symbol)
    {
        this.name = name;
        this.symbol = symbol;
        this.playerType = PlayerType.HUMAN;
    }

    public Move makeMove() {
        System.out.println("Enter row for next move:");
        int row = scanner.nextInt();
        System.out.println("Enter column for next move:");
        int col = scanner.nextInt();
        return new Move(this, new Cell(row, col));
    }
}
