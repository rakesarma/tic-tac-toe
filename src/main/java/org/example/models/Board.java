package org.example.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Board {

    private int dimension;
    private List<List<Cell>> boardCells;

    public Board(int dimension) {
        this.dimension = dimension;
        this.boardCells = new ArrayList<>();

        for(int row=0; row<dimension; row++)
        {
            boardCells.add(new ArrayList<>());
            for(int col=0; col<dimension; col++)
            {
                boardCells.get(row).add(new Cell(row,col));
            }
        }
    }

    public void printBoard()
    {
        for(List<Cell> cells : boardCells)
        {
            for(Cell cell : cells)
            {
                if(cell.getCellStatus() == CellStatus.EMPTY)
                {
                    System.out.print("|   |");
                }
                else
                {
                    System.out.print("| " + cell.getPlayer().getSymbol().getSymbol() + " |");
                }
            }
            System.out.println();
        }
    }

}
