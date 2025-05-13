package api;

import boards.TikTakToeBoard;
import game.Board;
import game.GameResult;

public class RuleEngine {
    public GameResult getState(Board board) {
        if(board instanceof TikTakToeBoard board1) {
            // 1. Row Wise
            String firstChar;
            boolean rowComplete;
            for(int i = 0; i < 3; i++) {
                firstChar = board1.getCell(i,0);
                rowComplete = firstChar != null;
                if(firstChar != null) {
                    for (int j = 1; j < 3; j++) {
                        if (!firstChar.equals(board1.getCell(i, j))) {
                            rowComplete = false;
                            break;
                        }
                    }
                }
                if(rowComplete) {
                    return new GameResult(true, firstChar);
                }
            }
            // 2. Column Wise
            boolean colComplete;
            for(int i = 0; i < 3; i++) {
                firstChar = board1.getCell(0,i);
                colComplete = firstChar != null;
                if(firstChar != null) {
                    for (int j = 1; j < 3; j++) {
                        if (!firstChar.equals(board1.getCell(j, i))) {
                            colComplete = false;
                            break;
                        }
                    }
                }
                if(colComplete) {
                    return new GameResult(true, firstChar);
                }
            }
            // 3. Diagonal
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

}
