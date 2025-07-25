package rover.service;

import org.springframework.stereotype.Service;
import rover.model.Rover;

import java.util.ArrayList;
import java.util.List;
@Service
public class RoverService {

    private List<Rover> rovers;
    private int id;
    public RoverService() {
        this.rovers = new ArrayList<>();
        this.id = 1;
    }
    public Rover deployRover(Rover rover) {
        for (Rover r : rovers) {
            if (r.getId() == rover.getId()) {
                r.setPosition(rover.getPosition());
                r.setDirection(rover.getDirection());
                r.setRunning(rover.isRunning());
                return new Rover(r.getId(), r.getPosition(), r.getDirection(), r.getName(), r.isRunning());
            }
        }

        rover.setId(this.id++);
        rovers.add(rover);
        return rover;
    }


    public List<Rover> listRovers() {
        return rovers;
    }

    public Rover findRoverById(int id) {

        return rovers.stream()
                .filter(r -> r.getId() == id)
                .findFirst()
                .orElse(new Rover());


    }

    public String executeCommands(int id, String commands) {
        //TODO: Implementar despes

        return null;
    }

    public String turnOnRover(int id) {

        for( Rover r : rovers ) {
            if (r.getId() != id) continue;

            if (r.isRunning()) return r.getName() + " is already turned on.";

            r.setRunning(true);
            return r.getName() + " is now running!";
        }

        return "Rover with ID " + id + " is not found.";
    }


    public String turnOffRover(int id) {
        for( Rover r : rovers ) {
            if (r.getId() != id) continue;

            if (!r.isRunning()) return r.getName() + " is already turned off.";

            r.setRunning(false);
            return r.getName() + " is now stopped!";
        }

        return "Rover with ID " + id + " is not found.";
    }
}
