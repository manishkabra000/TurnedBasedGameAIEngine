package api;

import game.*;
import boards.TicTakToeBoard;

// Game Engine's responsibility should be only to start & make a move

public class GameEngine {
    public Board start(String type) {
        if(type.equals("TikTakToe")) {
            return new TicTakToeBoard();
        } else {
            throw new IllegalArgumentException();
        }
    }
    public void move(Board board, Move move) {
        if(board instanceof TicTakToeBoard board1){
            board1.move(move);
        } else {
            throw new IllegalArgumentException();
        }
    }
}

