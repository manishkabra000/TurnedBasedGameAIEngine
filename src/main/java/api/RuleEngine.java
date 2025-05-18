package api;

import boards.TikTakToeBoard;
import game.Board;
import game.GameResult;

import java.util.function.BiFunction;
import java.util.function.Function;

public class RuleEngine {
    public GameResult getState(Board board) {
        if(board instanceof TikTakToeBoard board1) {
            Function<Integer, String> getRow = (i) -> board1.getCell(i,0);
            Function<Integer, String> getCol = (i) -> board1.getCell(0,i);

            BiFunction<Integer,Integer, String> getNextRow = board1::getCell;
            BiFunction<Integer,Integer, String> getNextCol = (i, j) -> board1.getCell(j, i);
            // 1. Row Wise
            GameResult rowResult = isVictory(getRow, getNextRow);
            if (rowResult.isOver()) return rowResult;

            // 2. Column Wise
            GameResult colResult = isVictory(getCol, getNextCol);
            if (colResult.isOver()) return colResult;

            // 3. Diagonal
            String firstChar;
            firstChar = board1.getCell(0,0);
            boolean diagComplete = firstChar != null;
            if(firstChar != null) {
                for (int i = 0; i < 3; i++) {
                    if (!firstChar.equals(board1.getCell(i, i))) {
                        diagComplete = false;
                        break;
                    }
                }
            }
            if(diagComplete) {
                return new GameResult(true, firstChar);
            }
            // 4. Reverse Diagonal
            firstChar = board1.getCell(2,0);
            boolean revDiagComplete = firstChar != null;
            if(firstChar != null) {
                for(int i = 0; i < 3; i++) {
                    if (!firstChar.equals(board1.getCell(2 - i,i))) {
                        revDiagComplete = false;
                        break;
                    }
                }
            }
            if(revDiagComplete) {
                return new GameResult(true, firstChar);
            }

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

    private static GameResult isVictory(Function<Integer, String> getVal, BiFunction<Integer,Integer, String> getNextVal) {
        for(int i = 0; i < 3; i++) {
            String whoWon = getVal.apply(i);
            boolean possibleStreak = whoWon != null;
            if(whoWon != null) {
                for (int j = 1; j < 3; j++) {
                    if (!whoWon.equals(getNextVal.apply(i, j))) {
                        possibleStreak = false;
                        break;
                    }
                }
            }
            if(possibleStreak) {
                return new GameResult(true, whoWon);
            }
        }
        return new GameResult(false, "-");
    }
}
