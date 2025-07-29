package rover.model;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Cell {

    private boolean isObstacle;
    private Rover rover;


    public Cell(boolean isObstacle) {
        this.isObstacle = isObstacle;
        this.rover = null;
    }


    public void occupyWithRover(Rover rover) {
        this.rover = rover;
    }

    public void removeRover() {
        this.rover = null;
    }

    public void setObstacle() { isObstacle = true; }
}
