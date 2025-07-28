package rover.service;

import rover.model.Grid;
import rover.model.Position;
import rover.model.Rover;

public class GridManager {

    private Grid grid;

    public GridManager() {
        grid = new Grid();
    }


    public boolean deployRover(Position position, int roverId) {

        if(!grid.isPositionAvailable(position)) {
            return false;
        }
        grid.deployRoverInPosition(position, roverId);
        return true;
    }


}
