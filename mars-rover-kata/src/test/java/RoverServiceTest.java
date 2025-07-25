import org.junit.jupiter.api.Test;
import rover.model.Direction;
import rover.model.Position;
import rover.model.Rover;
import rover.service.RoverService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class RoverServiceTest {

    @Test
    public void deployRover_shouldCreateRover_whenInputIsValid() {
        RoverService roverService = new RoverService();

        Rover eduRover = new Rover();
        eduRover.setPosition(new Position(1,1));
        eduRover.setName("Rover de Edu");
        eduRover.setDirection(Direction.NORTH);
        eduRover.setRunning(Boolean.FALSE);

        Rover created = roverService.deployRover(eduRover);

        assertThat(created.getName()).isEqualTo("Rover de Edu");
        assertThat(created.getDirection()).isEqualTo(Direction.NORTH);
        assertThat(created.isRunning()).isEqualTo(Boolean.FALSE);
        assertThat(created.getPosition().getX()).isEqualTo(1);
    }

    @Test
    public void deployRover_shouldUpdateExistingRover_whenRoverAlreadyDeployed() {
        RoverService roverService = new RoverService();
        List<Rover> rovers = roverService.listRovers();
        Rover eduRover = new Rover();

        eduRover.setPosition(new Position(1,1));
        eduRover.setName("Rover de Edu");
        eduRover.setDirection(Direction.NORTH);
        eduRover.setRunning(Boolean.FALSE);

        roverService.deployRover(eduRover);
        eduRover.setPosition(new Position(5,4));
        roverService.deployRover(eduRover);

        rovers = roverService.listRovers();
        assertThat(rovers.size()).isEqualTo(1);
        assertThat(rovers.get(0).getX()).isEqualTo(5);
        assertThat(rovers.get(0).getY()).isEqualTo(4);
    }


    @Test
    public void listRovers_shouldReturnAllRovers_whenMultipleRoversDeployed() {
        RoverService roverService = new RoverService();
        for (int i = 0; i < 5; i++) {
            Rover addingRover = new Rover();
            addingRover.setPosition(new Position(1,1));
            addingRover.setName("Rover " + i);
            addingRover.setDirection(Direction.NORTH);
            addingRover.setRunning(Boolean.FALSE);

            roverService.deployRover(addingRover);
        }

        List<Rover> roversList = roverService.listRovers();

        assertThat(roversList.size()).isEqualTo(5);
        assertThat(roversList.get(3).getName()).isEqualTo("Rover 3");
    }

    @Test
    public void turnOnRover_shouldTurnOn_whenRoverIsOff() {
        RoverService roverService = new RoverService();

        Rover engineOffRover = new Rover();
        engineOffRover.setRunning(Boolean.FALSE);
        engineOffRover.setName("rover 1");
        roverService.deployRover(engineOffRover);

        String response = roverService.turnOnRover(engineOffRover.getId());

        assertThat(response).isEqualTo("rover 1 is now running!");
    }

    @Test
    public void turnOnRover_shouldNotTurnOn_whenRoverIsOn() {
        RoverService roverService = new RoverService();

        Rover engineOffRover = new Rover();
        engineOffRover.setRunning(Boolean.TRUE);
        engineOffRover.setName("rover 1");
        roverService.deployRover(engineOffRover);

        String response = roverService.turnOnRover(engineOffRover.getId());

        assertThat(response).isEqualTo("rover 1 is already turned on.");
    }
    @Test
    public void turnOnRover_shouldReturnError_whenIdNotExists(){
        RoverService roverService = new RoverService();

        String response = roverService.turnOnRover(9);
        assertThat(response).isEqualTo("Rover with ID 9 is not found.");
    }

    @Test
    public void turnOffRover_shouldTurnOff_whenRoverIsOn() {
        RoverService roverService = new RoverService();

        Rover engineOffRover = new Rover();
        engineOffRover.setRunning(Boolean.TRUE);
        engineOffRover.setName("rover 1");
        roverService.deployRover(engineOffRover);

        String response = roverService.turnOffRover(engineOffRover.getId());

        assertThat(response).isEqualTo("rover 1 is now stopped!");
    }

    @Test
    public void turnOffRover_shouldNotTurnOff_whenRoverIsOff() {
        RoverService roverService = new RoverService();

        Rover engineOffRover = new Rover();
        engineOffRover.setRunning(Boolean.FALSE);
        engineOffRover.setName("rover 1");
        roverService.deployRover(engineOffRover);

        String response = roverService.turnOffRover(engineOffRover.getId());

        assertThat(response).isEqualTo("rover 1 is already turned off.");
    }
    @Test
    public void turnOffRover_shouldReturnError_whenIdNotExists(){
        RoverService roverService = new RoverService();

        String response = roverService.turnOffRover(9);
        assertThat(response).isEqualTo("Rover with ID 9 is not found.");
    }




}
