package api;

import boards.TikTakToeBoard;
import game.*;

public class AIPlayer {
    public Move suggestMove(Player ai, Board board) {
        if(board instanceof TikTakToeBoard board1) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if(board1.getCell(i,j) == null) {
                        return new Move(new Cell(i,j), ai);
                    }
                }
            }
            throw new IllegalStateException();
        } else {
            throw new IllegalArgumentException();
        }
    }

}
