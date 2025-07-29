package rover.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Random;

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

            placeRandomObstacles(5);
        }


    }

    private void placeRandomObstacles(int count) {
        Random random = new Random();
        int placed = 0;

        while (placed < count) {
            int x = random.nextInt(rows);
            int y = random.nextInt(columns);

            if (!grid[x][y].isObstacle()) {
                grid[x][y].setObstacle(true);
                placed++;
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
        return !cell.isObstacle() && cell.getRover() == null;
    }


    public void deployRoverInPosition(Rover currentRover) {
        Cell cell = grid[currentRover.getX()][currentRover.getY()];
        cell.occupyWithRover(currentRover);
    }

    public void removeRoverFromCell(Position currentPosition) {
        Cell cell = grid[currentPosition.getX()][currentPosition.getY()];
        cell.removeRover();
    }


}
