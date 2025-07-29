package rover.model;

import lombok.Getter;

import java.util.List;

@Getter
public class Grid {

    private final Cell[][] grid;
    private final int rows;
    private final int columns;


    public Grid(List<Position> obstaclePositions) {
        rows = 5;
        columns = 5;
        grid = new Cell[rows][columns];

        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < columns; y++) {
                grid[x][y] = new Cell(false);
            }
        }

        for (Position pos : obstaclePositions) {
            grid[pos.getX()][pos.getY()].setObstacle();
        }
    }



    public boolean isPositionOccupied(Position position) {
        int x = position.getX();
        int y = position.getY();
        boolean positionInBounds = x >= 0 && x < rows && y >= 0 && y < columns;

        if (!positionInBounds) {
            throw new IndexOutOfBoundsException("Position out of bounds!");
        }

        Cell cell = grid[x][y];
        return cell.isObstacle() || cell.getRover() != null;
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
