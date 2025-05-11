package api;

import game.*;
import boards.TikTakToeBoard;

public class GameEngine {
    public Board start(String type) {
        if(type.equals("TikTakToe")) {
            return new TikTakToeBoard();
        } else {
            throw new IllegalArgumentException();
        }
    }
    public void move(Board board, Player player, Move move) {
        if(board instanceof TikTakToeBoard board1){
            board1.setCell(player.getSymbol(), move.getCell());
        } else {
            throw new IllegalArgumentException();
        }
    }
    public GameResult isComplete(Board board) {
        if(board instanceof TikTakToeBoard board1) {
            // 1. Row Wise
            String firstChar = "-";
            boolean rowComplete = true;
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
            }
            if(rowComplete) {
                return new GameResult(true, firstChar);
            }
            // 2. Column Wise
            boolean colComplete = true;
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
            }
            if(colComplete) {
                return new GameResult(true, firstChar);
            }
            // 3. Diagonal
            boolean diagComplete = true;
            firstChar = board1.getCell(0,0);
            diagComplete = firstChar != null;
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
            boolean revDiagComplete = true;
            firstChar = board1.getCell(2,0);
            revDiagComplete = firstChar != null;
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
    public Move suggestMove(Board board) {
        if(board instanceof TikTakToeBoard board1) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if(board1.getCell(i,j) == null) {
                        return new Move(new Cell(i,j));
                    }
                }
            }
            throw new IllegalStateException();
        } else {
            throw new IllegalArgumentException();
        }
    }
}

