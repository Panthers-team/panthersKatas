package rover.service;

import org.springframework.stereotype.Service;
import rover.model.Direction;
import rover.model.Position;
import rover.model.Rover;

import java.util.ArrayList;
import java.util.List;
@Service
public class RoverService {

    private final List<Rover> roverList;
    private int id;
    private final GridManager gridManager;

    public RoverService() {
        this.roverList = new ArrayList<>();
        this.id = 1;
        this.gridManager = new GridManager();
    }

    public RoverService(GridManager gridManager) {
        this.gridManager = gridManager;
        this.id = 1;
        this.roverList = new ArrayList<>();
    }
    public Rover deployRover(Rover rover) {
        for (int i = 0; i < roverList.size(); i++) {
            Rover r = roverList.get(i);
            if (r.getId() == rover.getId()) {
                Rover updated = Rover.builder()
                        .id(r.getId())
                        .position(rover.getPosition())
                        .direction(rover.getDirection())
                        .name(r.getName())
                        .running(rover.isRunning())
                        .build();

                roverList.set(i, updated);
                gridManager.deployRover(updated);
                return updated;
            }
        }

        rover.setId(this.id++);
        roverList.add(rover);
        gridManager.deployRover(rover);

        return rover;
    }



    public List<Rover> listRovers() {
        return roverList;
    }

    public Rover findRoverById(int id) {

        return roverList.stream()
                .filter(r -> r.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No rover found with id " + id));


    }

    public String executeCommands(int id, String commands) {
        StringBuilder log = new StringBuilder();
        Rover roverExec = findRoverById(id);
        int stage = 1;


        log.append(getLogHeader());
        for (char command : commands.toCharArray()) {
            String description = "";

            switch (command) {
                case 'f' -> description = gridManager.moveForward(roverExec);
                case 'b' -> description = gridManager.moveBackwards(roverExec);
                case 'l' -> description = gridManager.turnRoverLeft(roverExec);
                case 'r' -> description = gridManager.turnRoverRight(roverExec);

            }

            log.append(formatLogLine(stage++, command, roverExec, description)).append("\n");

            if (description.contains("Collision!")) {
                break;
            }
        }

        return log.toString().trim();
    }


    public String turnOnRover(int id) {

        for( Rover r : roverList) {
            if (r.getId() != id) continue;

            if (r.isRunning()) return r.getName() + " is already turned on.";

            r.setRunning(true);
            return r.getName() + " is now running!";
        }

        return "Rover with ID " + id + " is not found.";
    }


    public String turnOffRover(int id) {
        for( Rover r : roverList) {
            if (r.getId() != id) continue;

            if (!r.isRunning()) return r.getName() + " is already turned off.";

            r.setRunning(false);
            return r.getName() + " is now stopped!";
        }

        return "Rover with ID " + id + " is not found.";
    }

    private String formatLogLine(int stage, char command, Rover rover, String description) {
        String action = switch (command) {
            case 'f' -> "Move Forward";
            case 'b' -> "Move Backward";
            case 'l' -> "Rotate Left";
            case 'r' -> "Rotate Right";
            default -> "Unknown";
        };

        Position pos = rover.getPosition();
        Direction dir = rover.getDirection();

        return String.format("|  %-4d |  <%-1s> %-13s  |    (%-2d,%-2d) %-4s  |  %-25s",
                stage,
                command,
                action,
                pos.getX(),
                pos.getY(),
                dir.name().charAt(0),
                description);

    }

    private String getLogHeader() {

        return "======================================\n" +
                "=== MARS ROVER KATA - EXECUTION LOG===\n" +
                "======================================\n" +
                "------------------------------------------------------------------------------\n" +
                "| Stage |       Action        |     Position     |     Description\n" +
                "------------------------------------------------------------------------------\n";

    }

}
