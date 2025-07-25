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


    public Rover() {}


    public Rover(int id, Position position, Direction direction, String name, boolean running) {
        this.id = id;
        this.position = position;
        this.direction = direction;
        this.name = name;
        this.running = running;

    }
}
