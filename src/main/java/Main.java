import api.AIPlayer;
import api.GameEngine;
import api.RuleEngine;
import game.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);

        GameEngine gameEngine = new GameEngine();
        AIPlayer aiPlayer = new AIPlayer();
        RuleEngine ruleEngine = new RuleEngine();

        Board board = gameEngine.start("TikTakToe");

        // Human vs AI
        Player human = new Player("X");
        Player ai = new Player("0");

        while (!ruleEngine.getState(board).isOver()) {
            System.out.println("Make you move!");
            // For human -> get input from the user i.e. my human
            int row = myObj.nextInt();
            int col = myObj.nextInt();

            gameEngine.move(board, new Move(new Cell(row, col), human));
            System.out.println(board.toString());

            // For AI -> suggest input
            if(!ruleEngine.getState(board).isOver()) {
                Move aiMove = aiPlayer.suggestMove(ai, board);
                gameEngine.move(board, aiMove);
                System.out.println(board);
            }
        }
        System.out.println("GameResult: " + ruleEngine.getState(board).getWinner());
        System.out.println(board.toString());
    }
}
