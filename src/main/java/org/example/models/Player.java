package org.example.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player {

    private String name;
    private Symbol symbol;
    private PlayerType playerType;

    public Player(String name, Symbol symbol)
    {
        this.name = name;
        this.symbol = symbol;
        this.playerType = PlayerType.HUMAN;
    }
}
