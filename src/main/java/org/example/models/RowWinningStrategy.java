package org.example.models;

import java.util.HashMap;
import java.util.Map;

public class RowWinningStrategy implements WinningSrategy
{
    private Map<Integer, Map<Symbol, Integer>> rowCountMap = new HashMap<>();

    @Override
    public boolean checkWinner(Board board, Move move)
    {
        int row = move.getCell().getRow();
        Symbol symbol = move.getPlayer().getSymbol();

        //If that row is not present in the map, i.e, no move has been made in that row,
        // then we will create a new map for that row and add the symbol with count 1.
        if(!rowCountMap.containsKey(row))
        {
            rowCountMap.put(row, new HashMap<>());
        }

        //If the symbol is not present in the row at all,
        //then add the symbol with count 0
        Map<Symbol, Integer> symbolCountMap = rowCountMap.get(row);
        if(!symbolCountMap.containsKey(symbol))
        {
            symbolCountMap.put(symbol, 0);
        }

        //Increment the count of symbol in that row by 1
        rowCountMap.get(row).put(symbol, symbolCountMap.get(symbol)+1);

        //If count of symbols in the row matches dimension of the board, then player wins
        if(rowCountMap.get(row).get(symbol) == board.getDimension()) {
            return true;
        }
        return false;
    }
}
