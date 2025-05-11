import api.GameEngine;
import game.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);

        GameEngine gameEngine = new GameEngine();
        Board board = gameEngine.start("TikTakToe");

        // Human vs AI
        Player human = new Player("X");
        Player ai = new Player("0");

        while (!gameEngine.isComplete(board).isOver()) {
            System.out.println("Make you move!");
            // For human -> get input from the user i.e. my human
            int row = myObj.nextInt();
            int col = myObj.nextInt();

            gameEngine.move(board, human, new Move(new Cell(row, col)));
            System.out.println(board.toString());

            // For AI -> suggest input
            if(!gameEngine.isComplete(board).isOver()) {
                Move aiMove = gameEngine.suggestMove(board);
                gameEngine.move(board, ai, aiMove);
                System.out.println(board.toString());
            }
        }
        System.out.println("GameResult: " + gameEngine.isComplete(board).getWinner());
        System.out.println(board.toString());
    }
}
