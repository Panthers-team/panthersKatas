package rover.service;

import rover.model.Direction;
import rover.model.Grid;
import rover.model.Position;
import rover.model.Rover;

public class GridManager {

    private Grid grid;

    public GridManager() {
        grid = new Grid();
    }


    public boolean deployRover(Rover currentRover) {

        if(!grid.isPositionAvailable(currentRover.getPosition())) {
            return false;
        }

        grid.deployRoverInPosition(currentRover);
        return true;
    }


    public String moveForward(Rover currentRover) {

        if (!currentRover.isRunning()) return "Rover is not turned on.";

        Position currentPosition = currentRover.getPosition();
        Position nextPosition = calculateNextPosition(currentPosition, currentRover.getDirection());

        if(!grid.isPositionAvailable(nextPosition)) return "Next position is already occupied.";

        grid.removeRoverFromCell(currentPosition);
        currentRover.setPosition(nextPosition);
        grid.deployRoverInPosition(currentRover);
        return "Execution success.";
    }



    public String moveBackwards(Rover currentRover) {
        if (!currentRover.isRunning()) return "Rover is not turned on.";

        Position currentPosition = currentRover.getPosition();
        Direction oppositeDirection = currentRover.getDirection().opposite();
        Position nextPosition = calculateNextPosition(currentPosition, oppositeDirection);

        if (!grid.isPositionAvailable(nextPosition)) return "Next position is already occupied.";

        grid.removeRoverFromCell(currentPosition);
        currentRover.setPosition(nextPosition);
        grid.deployRoverInPosition(currentRover);

        return "Execution success.";
    }


    private Position calculateNextPosition(Position current, Direction direction) {
        int x = current.getX();
        int y = current.getY();

        switch (direction) {
            case NORTH:
                y = (y + 1) % grid.getRows();
                break;
            case SOUTH:
                y = (y - 1 + grid.getRows()) % grid.getRows();
                break;
            case EAST:
                x = (x + 1) % grid.getColumns();
                break;
            case WEST:
                x = (x - 1 + grid.getColumns()) % grid.getColumns();
                break;
        }
        return new Position(x, y);
    }

    public String turnRoverRight(Rover currentRover) {
        if (!currentRover.isRunning()) {
            return "Rover is not turned on.";
        }

        currentRover.turnRight();
        return "Execution success.";
    }

    public String turnRoverLeft(Rover currentRover) {
        if (!currentRover.isRunning()) {
            return "Rover is not turned on.";
        }

        currentRover.turnLeft();
        return "Execution success.";
    }




}
