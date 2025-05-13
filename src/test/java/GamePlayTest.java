import api.*;
import game.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GamePlayTest {
    GameEngine gameEngine;
    AIPlayer aiPlayer;
    RuleEngine ruleEngine;

    @Before
    public void setup() {
        gameEngine = new GameEngine();
        aiPlayer = new AIPlayer();
        ruleEngine = new RuleEngine();
    }
    @Test
    public void checkForRowWin() {
        Board board;
        board = gamePlay(new int[][] {{1,0},{1,1},{1,2}});
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals("X", ruleEngine.getState(board).getWinner());
    }

    @Test
    public void checkForColWin() {
        Board board;
        board = gamePlay(new int[][] {{0,0},{1,0},{2,0}});
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals("X", ruleEngine.getState(board).getWinner());
    }

    @Test
    public void checkForDiagWin() {
        Board board;
        board = gamePlay(new int[][] {{0,0},{1,1},{2,2}});
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals("X", ruleEngine.getState(board).getWinner());
    }

    @Test
    public void checkForRevDiagWin() {
        Board board;
        board = gamePlay(new int[][] {{2,0},{1,1},{0,2}});
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals("X", ruleEngine.getState(board).getWinner());
    }

    @Test
    public void checkForComputerWin() {
        Board board;
        board = gamePlay(new int[][] {{1,0},{1,1},{2,0}});
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals("O", ruleEngine.getState(board).getWinner());
    }

    private Board gamePlay(int[][] moves) {
        Board board = gameEngine.start("TikTakToe");

        // Human vs AI
        Player human = new Player("X");
        Player ai = new Player("O");

        int next = 0;

        while (!ruleEngine.getState(board).isOver()) {
            System.out.println("Make you move!");
            // For human -> get input from the user i.e. my human
            int row = moves[next][0];
            int col = moves[next][1];
            next++;
            gameEngine.move(board, new Move(new Cell(row, col), human));
            System.out.println(board.toString());

            // For AI -> suggest input
            if(!ruleEngine.getState(board).isOver()) {
                Move aiMove = aiPlayer.suggestMove(ai, board);
                gameEngine.move(board, aiMove);
                System.out.println(board);
            }
        }

        return board;
    }
}
