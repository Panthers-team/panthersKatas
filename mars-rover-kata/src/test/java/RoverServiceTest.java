import org.junit.jupiter.api.Test;
import rover.model.Direction;
import rover.model.Grid;
import rover.model.Position;
import rover.model.Rover;
import rover.service.GridManager;
import rover.service.RoverService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class RoverServiceTest {

    @Test
    public void deployRover_shouldCreateRover_whenInputIsValid() {
        RoverService roverService = new RoverService();

        Rover eduRover = Rover.builder()
                .position(new Position(1, 1))
                .name("Rover de Edu")
                .direction(Direction.NORTH)
                .running(false)
                .build();

        Rover created = roverService.deployRover(eduRover);

        assertThat(created.getName()).isEqualTo("Rover de Edu");
        assertThat(created.getDirection()).isEqualTo(Direction.NORTH);
        assertThat(created.isRunning()).isFalse();
        assertThat(created.getPosition().getX()).isEqualTo(1);
        assertThat(created.getPosition().getY()).isEqualTo(1);
    }


    @Test
    public void deployRover_shouldUpdateExistingRover_whenRoverAlreadyDeployed() {
        RoverService roverService = new RoverService();
        List<Rover> rovers;
        Rover eduRover = Rover
                .builder()
                .position(new Position(1,1))
                .name("Rover de Edu")
                .running(Boolean.FALSE)
                .direction(Direction.NORTH)
                .build();

        roverService.deployRover(eduRover);
        eduRover.setPosition(new Position(3,4));
        roverService.deployRover(eduRover);

        rovers = roverService.listRovers();
        assertThat(rovers.size()).isEqualTo(1);
        assertThat(rovers.get(0).getX()).isEqualTo(3);
        assertThat(rovers.get(0).getY()).isEqualTo(4);
    }


    @Test
    public void listRovers_shouldReturnAllRovers_whenMultipleRoversDeployed() {
        RoverService roverService = new RoverService();

        for (int i = 0; i < 5; i++) {
            Rover addingRover = Rover.builder()
                    .position(new Position(1, 1))
                    .name("Rover " + i)
                    .direction(Direction.NORTH)
                    .running(false)
                    .build();

            roverService.deployRover(addingRover);
        }

        List<Rover> roversList = roverService.listRovers();

        assertThat(roversList).hasSize(5);
        assertThat(roversList.get(3).getName()).isEqualTo("Rover 3");
    }


    @Test
    public void turnOnRover_shouldTurnOn_whenRoverIsOff() {
        RoverService roverService = new RoverService();

        Rover engineOffRover = Rover.builder()
                .name("rover 1")
                .position(new Position(1, 1))
                .direction(Direction.NORTH)
                .running(false)
                .build();

        roverService.deployRover(engineOffRover);

        String response = roverService.turnOnRover(engineOffRover.getId());

        assertThat(response).isEqualTo("rover 1 is now running!");
    }


    @Test
    public void turnOnRover_shouldNotTurnOn_whenRoverIsOn() {
        RoverService roverService = new RoverService();

        Rover engineOnRover = Rover.builder()
                .name("rover 1")
                .position(new Position(1, 1))
                .direction(Direction.NORTH)
                .running(true)
                .build();

        roverService.deployRover(engineOnRover);

        String response = roverService.turnOnRover(engineOnRover.getId());

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

        Rover engineOnRover = Rover.builder()
                .name("rover 1")
                .position(new Position(1, 1))
                .direction(Direction.NORTH)
                .running(true)
                .build();

        roverService.deployRover(engineOnRover);

        String response = roverService.turnOffRover(engineOnRover.getId());

        assertThat(response).isEqualTo("rover 1 is now stopped!");
    }


    @Test
    public void turnOffRover_shouldNotTurnOff_whenRoverIsOff() {
        RoverService roverService = new RoverService();

        Rover engineOffRover = Rover.builder()
                .name("rover 1")
                .position(new Position(1, 1))
                .direction(Direction.NORTH)
                .running(false)
                .build();

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

    @Test
    public void turnAny_shouldReturnNotFound_whenIdDoesNotMatchAnyRover() {
        Rover rover1 = Rover.builder()
                .id(1)
                .name("rover 1")
                .position(new Position(0, 0))
                .direction(Direction.NORTH)
                .running(false)
                .build();

        Rover rover2 = Rover.builder()
                .id(2)
                .name("rover 2")
                .position(new Position(1, 1))
                .direction(Direction.EAST)
                .running(true)
                .build();

        RoverService roverService = new RoverService();
        roverService.deployRover(rover1);
        roverService.deployRover(rover2);

        String turnOnResult = roverService.turnOnRover(99);
        String turnOffResult = roverService.turnOffRover(99);

        assertThat(turnOnResult).isEqualTo("Rover with ID 99 is not found.");
        assertThat(turnOffResult).isEqualTo("Rover with ID 99 is not found.");
    }


    @Test
    public void findRover_shouldReturnRover_whenIdExists() {
        RoverService roverService = new RoverService();

        Rover engineOffRover = Rover.builder()
                .name("TestRover")
                .running(false)
                .position(new Position(0, 0))
                .direction(Direction.NORTH)
                .build();

        Rover deployed = roverService.deployRover(engineOffRover);

        Rover found = roverService.findRoverById(deployed.getId());

        assertThat(found).isEqualTo(deployed);
    }



    @Test
    public void findRover_shouldReturnError_whenIdNotExists() {
        Rover existingRover = Rover.builder()
                .id(1)
                .name("Demo")
                .position(new Position(0, 0))
                .direction(Direction.NORTH)
                .running(true)
                .build();

        RoverService roverService = new RoverService();

        roverService.deployRover(existingRover);
        assertThatThrownBy(() -> roverService.findRoverById(-1))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("No rover found with id -1");
    }



    @Test
    public void executeCommands_shouldExecuteAllSuccessfully_whenNoCollisions() {
        GridManager mockedGridManager = mock(GridManager.class);
        RoverService roverService = new RoverService(mockedGridManager);

        Rover executeCommandsRover = createTestingRover(new Position(0, 0));
        roverService.deployRover(executeCommandsRover);

        String commands = "ffrfflbb";

        when(mockedGridManager.moveForward(executeCommandsRover)).thenReturn(
                "Execution success.",
                "Execution success."
        );
        when(mockedGridManager.turnRoverRight(executeCommandsRover)).thenReturn(
                "Execution success."
        );
        when(mockedGridManager.moveForward(executeCommandsRover)).thenReturn(
                "Execution success.",
                "Execution success."
        );
        when(mockedGridManager.turnRoverLeft(executeCommandsRover)).thenReturn(
                "Execution success."
        );
        when(mockedGridManager.moveBackwards(executeCommandsRover)).thenReturn(
                "Execution success.",
                "Execution success."
        );

        String response = roverService.executeCommands(executeCommandsRover.getId(), commands);

        assertThat(response).contains("Execution success.");
    }




    @Test
    public void executeCommands_shouldStopAndLogCollision_whenObstacleEncountered() {
        GridManager mockedGridManager = mock(GridManager.class);
        RoverService roverService = new RoverService(mockedGridManager);
        Rover rover = createTestingRover(new Position(0, 0));

        when(mockedGridManager.moveForward(rover)).thenReturn(
                "Collision! Rock detected at: (0, 1)",
                "Execution success.");

        roverService.deployRover(rover);

        String response = roverService.executeCommands(rover.getId(), "fff");

        assertThat(response).contains("Collision! Rock detected at: (0, 1)");


        assertThat(rover.getPosition().getX()).isEqualTo(0);
        assertThat(rover.getPosition().getY()).isEqualTo(0);
        assertThat(response).doesNotContain("Execution success.");


    }




    @Test
    public void executeCommands_shouldReturnError_whenRoverIdDoesNotExist() {
        RoverService roverService = new RoverService();
        Rover executeCommandsRover = createTestingRover(new Position(0, 0));
        String commands = "ffff";

        assertThatThrownBy(() -> roverService.executeCommands(executeCommandsRover.getId(), commands))
                .hasMessage("No rover found with id 0");
    }

    private Rover createTestingRover(Position position) {
        return Rover.builder()
                .position(position)
                .name("TestRover")
                .direction(Direction.NORTH)
                .running(true)
                .build();
    }








}
