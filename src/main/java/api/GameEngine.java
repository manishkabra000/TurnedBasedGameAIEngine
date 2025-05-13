package api;

import game.*;
import boards.TikTakToeBoard;

// Game Engine's responsibility should be only to start & make a move

public class GameEngine {
    public Board start(String type) {
        if(type.equals("TikTakToe")) {
            return new TikTakToeBoard();
        } else {
            throw new IllegalArgumentException();
        }
    }
    public void move(Board board, Move move) {
        if(board instanceof TikTakToeBoard board1){
            board1.move(move);
        } else {
            throw new IllegalArgumentException();
        }
    }
}

