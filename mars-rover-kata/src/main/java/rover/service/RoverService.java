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
        rover.setId(this.id++);
        rovers.add(rover);
        return rover;
    }

    public List<Rover> listRovers() {
        return rovers;
    }

    public Rover findRoverById(int id) {
        //TODO: Implementar despes

        return null;
    }

    public String executeCommands(int id, String commands) {
        //TODO: Implementar despes

        return null;
    }

    public String turnOnRover(int id) {
        //TODO: Implementar despes

        return null;
    }

    public String turnOffRover(int id) {
        return null;
    }
}
