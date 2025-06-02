package api;

import boards.TicTakToeBoard;
import game.Board;
import game.GameInfo;
import game.GameState;
import game.Player;

import java.util.function.BiFunction;
import java.util.function.Function;

public class RuleEngine {
    /*
        Game State: Current State of the game,
        Game Info: Any other relevant information about the game.
    */
    public GameInfo getInfo(Board board) {
        if(board instanceof TicTakToeBoard) {
            GameState gameState = getState(board);
        } else {
            throw new IllegalArgumentException();
        }
        return new GameInfo(new GameState(false, "-"), false, new Player("-"));
    }
    public GameState getState(Board board) {
        if(board instanceof TicTakToeBoard board1) {
            BiFunction<Integer,Integer, String> getNextRow = board1::getCell;
            BiFunction<Integer,Integer, String> getNextCol = (i, j) -> board1.getCell(j, i);

            Function<Integer, String> getNextDiag = (i) -> board1.getCell(i, i);
            Function<Integer, String> getNextRevDiag = (i) -> board1.getCell(2 - i, i);

            // 1. Row Wise
            GameState rowResult = outerTraversal(getNextRow);
            if (rowResult.isOver()) return rowResult;

            // 2. Column Wise
            GameState colResult = outerTraversal(getNextCol);
            if (colResult.isOver()) return colResult;

            // 3. Diagonal
            GameState diagResult = innerTraversal(getNextDiag);
            if(diagResult.isOver()) return diagResult;

            // 4. Reverse Diagonal
            GameState revDiagResult = innerTraversal(getNextRevDiag);
            if(revDiagResult.isOver()) return revDiagResult;

            int countOfFilledCells = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if(board1.getCell(i,j) != null) {
                        countOfFilledCells++;
                    }
                }
            }
            if(countOfFilledCells == 9) {
                return new GameState(true, "-");
            } else {
                return new GameState(false, "-");
            }

        } else {
            return new GameState(false, "-");
        }
    }

    private static GameState innerTraversal(Function<Integer, String> next) {
        GameState gameState = new GameState(false, "-");
        boolean possibleStreak = true;
        for (int j = 0; j < 3; j++) {
            if (next.apply(j) == null || !next.apply(0).equals(next.apply(j))) {
                possibleStreak = false;
                break;
            }
        }
        if(possibleStreak) {
            gameState = new GameState(true, next.apply(0));
        }
        return gameState;
    }

    private static GameState outerTraversal(BiFunction<Integer,Integer, String> next) {
        GameState gameState = new GameState(false, "-");
        for(int i = 0; i < 3; i++) {
            final int ii = i;
            gameState = innerTraversal(j -> next.apply(ii, j));
            if(gameState.isOver())
                break;
        }
        return gameState;
    }
}

