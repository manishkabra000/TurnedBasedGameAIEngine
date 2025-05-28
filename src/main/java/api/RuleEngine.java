package api;

import boards.TikTakToeBoard;
import game.Board;
import game.GameResult;

import java.util.function.BiFunction;
import java.util.function.Function;

public class RuleEngine {
    public GameResult getState(Board board) {
        if(board instanceof TikTakToeBoard board1) {
            BiFunction<Integer,Integer, String> getNextRow = board1::getCell;
            BiFunction<Integer,Integer, String> getNextCol = (i, j) -> board1.getCell(j, i);

            Function<Integer, String> getNextDiag = (i) -> board1.getCell(i, i);
            Function<Integer, String> getNextRevDiag = (i) -> board1.getCell(2 - i, i);

            // 1. Row Wise
            GameResult rowResult = outerTraversal(getNextRow);
            if (rowResult.isOver()) return rowResult;

            // 2. Column Wise
            GameResult colResult = outerTraversal(getNextCol);
            if (colResult.isOver()) return colResult;

            // 3. Diagonal
            GameResult diagResult = innerTraversal(getNextDiag);
            if(diagResult.isOver()) return diagResult;

            // 4. Reverse Diagonal
            GameResult revDiagResult = innerTraversal(getNextRevDiag);
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
                return new GameResult(true, "-");
            } else {
                return new GameResult(false, "-");
            }

        } else {
            return new GameResult(false, "-");
        }
    }

    private static GameResult innerTraversal(Function<Integer, String> next) {
        GameResult gameResult = new GameResult(false, "-");
        boolean possibleStreak = true;
        for (int j = 0; j < 3; j++) {
            if (next.apply(j) == null || !next.apply(0).equals(next.apply(j))) {
                possibleStreak = false;
                break;
            }
        }
        if(possibleStreak) {
            gameResult = new GameResult(true, next.apply(0));
        }
        return gameResult;
    }

    private static GameResult outerTraversal(BiFunction<Integer,Integer, String> next) {
        GameResult gameResult = new GameResult(false, "-");
        for(int i = 0; i < 3; i++) {
            final int ii = i;
            gameResult = innerTraversal(j -> next.apply(ii, j));
            if(gameResult.isOver())
                break;
        }
        return gameResult;
    }
}
