package org.example.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bot extends Player {

    private BotDifficultyLevel botDifficultyLevel;

    public Bot(String name, Symbol symbol, BotDifficultyLevel botDifficultyLevel) {
        super(name, symbol);
        this.botDifficultyLevel = botDifficultyLevel;
        setPlayerType(PlayerType.BOT);
    }
}
