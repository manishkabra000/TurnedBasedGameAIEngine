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
            GameResult rowResult = isVictory(getNextRow);
            if (rowResult.isOver()) return rowResult;

            // 2. Column Wise
            GameResult colResult = isVictory(getNextCol);
            if (colResult.isOver()) return colResult;

            // 3. Diagonal
            GameResult diagResult = isDiagStreak(getNextDiag);
            if(diagResult.isOver()) return diagResult;

            // 4. Reverse Diagonal
            GameResult revDiagResult = isDiagStreak(getNextRevDiag);
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

    private static GameResult isDiagStreak(Function<Integer, String> next) {
        boolean isStreak = true;
        for (int i = 0; i < 3; i++) {
            if (next.apply(i) == null || !next.apply(0).equals(next.apply(i))) {
                isStreak = false;
                break;
            }
            if(isStreak) {
                return new GameResult(true, next.apply(0));
            }
        }
        return new GameResult(false, "-");
    }

    private static GameResult isVictory(BiFunction<Integer,Integer, String> next) {
        for(int i = 0; i < 3; i++) {
            boolean possibleStreak = true;
            for (int j = 0; j < 3; j++) {
                if (next.apply(i, j) == null || !next.apply(i, 0).equals(next.apply(i, j))) {
                    possibleStreak = false;
                    break;
                }
            }
            if(possibleStreak) {
                return new GameResult(true, next.apply(i,0));
            }
        }
        return new GameResult(false, "-");
    }
}
