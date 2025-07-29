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
        int x = position.getX() % rows;
        int y = position.getY() % columns;

        Cell cell = grid[x][y];
        return cell.isObstacle() || cell.getRover() != null;
    }



    public void deployRoverInPosition(Rover currentRover) {
        currentRover.setPosition(getSpherePosition(currentRover));
        Cell cell = grid[currentRover.getX()][currentRover.getY()];
        cell.occupyWithRover(currentRover);
    }

    private Position getSpherePosition(Rover currentRover) {
        return new Position(currentRover.getX() % rows, currentRover.getY() % columns);
    }

    public void removeRoverFromCell(Position currentPosition) {
        currentPosition.setX(currentPosition.getX() % rows);
        currentPosition.setY(currentPosition.getY() % columns);
        Cell cell = grid[currentPosition.getX()][currentPosition.getY()];
        cell.removeRover();
    }


}
