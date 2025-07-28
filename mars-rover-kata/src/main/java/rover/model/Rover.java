package rover.model;
import lombok.*;

@Getter
@Setter
@Builder
public class Rover {

    private int id;
    private Position position;
    private Direction direction;
    private String name;
    private boolean running;

    public int getX() {
        return position.getX();
    }
    public int getY() {
        return position.getY();
    }

}
