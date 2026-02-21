package org.example.models;

import lombok.Getter;
import lombok.Setter;
import org.example.exceptions.InvalidMoveException;

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
    private List<WinningSrategy> winningStrategies;


    public static Builder builder()
    {
        return new Builder();
    }

    private Game( int dimension, List<Player> players, List<WinningSrategy> winningStrategies)
    {
        this.board = new Board(dimension);
        this.players = players;
        this.moves = new ArrayList<>();
        this.gameState = GameState.IN_PROGRESS;
        this.nextPlayerIndex = 0;
        this.winningStrategies = winningStrategies;
    }

    public void makeMove() throws InvalidMoveException {
        Player currentPlayer = players.get(nextPlayerIndex);
        System.out.println("Current turn: " + currentPlayer.getName() + " Symbol: " + currentPlayer.getSymbol().getSymbol());
        Move move = currentPlayer.makeMove();

        //validate move
        if(!validateMove(move))
        {
            System.out.println("Invalid move! Try again.");
            throw new InvalidMoveException("Invalid move attempted by player: " + currentPlayer.getName());
        }

        //update board
        int row  = move.getCell().getRow();
        int col = move.getCell().getCol();
        Cell cell = board.getBoardCells().get(row).get(col);
        cell.setPlayer(move.getPlayer());
        cell.setCellStatus(CellStatus.FILLED);

        //update list of moves
        moves.add(move);

        //Update next player
        nextPlayerIndex = (nextPlayerIndex+1) % players.size();

        //Check winner
        if(checkWinner(move))
        {
            this.winner = currentPlayer;
            this.gameState = GameState.WIN;
            System.out.println("Game Over! Congrats to " + winner.getName() + " on the win!");
        }
        else if(moves.size() == board.getDimension()*board.getDimension())
        {
            this.gameState = GameState.DRAW;
            System.out.println("Game Over! It is a draw!");
        }
    }

    private boolean checkWinner(Move move)
    {
        for(WinningSrategy strategy : winningStrategies)
        {
            if(strategy.checkWinner(this.board, move))
            {
                return true;
            }
        }
        return false;
    }

    private boolean validateMove(Move move)
    {
        Cell cell = move.getCell();

        int row = cell.getRow();
        int col = cell.getCol();

        //Check if cell exceeds board dimensions or cell is filled already
        if(row<0 || row>=board.getDimension() ||
           col<0 || col>=board.getDimension() ||
           board.getBoardCells().get(row).get(col).getCellStatus() != CellStatus.EMPTY)
        {
            return false;
        }
        return true;
    }

    public static class Builder{
        private int dimension;
        private List<Player> players;
        private List<WinningSrategy> winningStrategies;

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder setWinningStrategies(List<WinningSrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        public Game build() {

            return new Game(dimension, players, winningStrategies);
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
