package rover.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Grid {

    private Cell[][] grid;
    private int rows;
    private int columns;
    public Grid() {
        rows = 5;
        columns = 5;
        grid = new Cell[rows][columns];
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < columns; y++) {
                grid[x][y] = new Cell(false);
            }
        }
    }

    public boolean isPositionAvailable(Position position) {
        int x = position.getX();
        int y = position.getY();
        boolean positionInBounds = x >= 0 && x < rows && y >= 0 && y < columns;

        if (!positionInBounds) {
            throw new IndexOutOfBoundsException("Position out of bounds!");
        }

        Cell cell = grid[x][y];
        return !cell.isObstacle() && cell.getRoverId() == -1;
    }


    public void deployRoverInPosition(Position position, int roverId) {
        Cell cell = grid[position.getX()][position.getY()];
        cell.setRoverId(roverId);
    }
}
