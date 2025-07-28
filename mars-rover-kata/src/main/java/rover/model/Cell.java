package rover.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cell {

    private boolean isObstacle;
    private int roverId;
    private Rover rover;


    public Cell(boolean isObstacle) {
        this.isObstacle = isObstacle;
        this.roverId = -1;
        this.rover = null;
    }


    public void occupyWithRover(Rover rover) {
        this.roverId = rover.getId();
        this.rover = rover;
    }

    public void removeRover() {
        this.rover = null;
    }
}
