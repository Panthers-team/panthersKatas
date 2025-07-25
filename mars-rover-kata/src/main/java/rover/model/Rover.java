package rover.model;
import lombok.*;

@Getter
@Setter
public class Rover {

    private int id;
    private Position position;
    private Direction direction;
    private String name;
    private boolean running;
    private int x;
    private int y;

    public Rover() {}


    public Rover(int id, Position position, Direction direction, String name, boolean running, int x, int y) {
        this.id = id;
        this.position = position;
        this.direction = direction;
        this.name = name;
        this.running = running;
        this.x = x;
        this.y = y;
    }
}
