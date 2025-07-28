package rover.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cell {

    private boolean isObstacle;
    private int roverId;


    public Cell(boolean isObstacle) {
        this.isObstacle = isObstacle;
        this.roverId = -1;
    }


    public void occupyWithRover(int roverId) {}
}
