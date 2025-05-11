package game;

public class Move {
    private Player player;
    private final Cell cell;
    public Move(Cell cell, Player player) {
        this.cell = cell;
        this.player = player;
    }
    public Cell getCell() {
        return cell;
    }
    public Player getPlayer() {
        return this.player;
    }
}

