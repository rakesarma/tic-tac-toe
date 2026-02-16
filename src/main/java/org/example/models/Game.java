package org.example.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private GameState gameState;
    private Player winner;
    private int nextPlayerIndex;


    public static Builder builder()
    {
        return new Builder();
    }

    private Game( int dimension, List<Player> players)
    {
        this.board = new Board(dimension);
        this.players = players;
        this.moves = new ArrayList<>();
        this.gameState = GameState.IN_PROGRESS;
        this.nextPlayerIndex = 0;
    }

    public static class Builder{
        private int dimension;
        private List<Player> players;

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Game build() {

            return new Game(dimension, players);
        }

        private void validateBotCount()
        {
            int botCount = 0;
            for(Player player : players)
            {
                if(player.getPlayerType() == PlayerType.BOT)
                {
                    botCount++;
                }
            }

            if(botCount > 1)
            {
                throw new IllegalArgumentException("Only one bot player is allowed in the game.");
            }
        }

    }

    public void printBoard()
    {
        board.printBoard();
    }
}
