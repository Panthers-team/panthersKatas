import org.junit.jupiter.api.Test;
import rover.model.Direction;
import rover.model.Grid;
import rover.model.Position;
import rover.model.Rover;
import rover.service.GridManager;
import rover.service.RoverService;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class GridManagerTest {


    @Test
    public void createGrid_shouldBeCreated() {

        Grid grid = new Grid(List.of());

        assertThat(grid.getGrid()).isNotNull();
    }

    @Test
    public void deployRover_shouldReturnTrue_whenPositionIsValidAndEmpty() {
        GridManager gridManager = new GridManager();
        Rover rover = createTestingRover();

        boolean deployed = gridManager.deployRover(rover);

        assertThat(deployed).isTrue();

    }

    @Test
    public void deployRover_shouldReturnFalse_whenPositionIsInvalid() {
        GridManager gridManager = new GridManager();
        Rover firstRover = createTestingRover();
        firstRover.setPosition(new Position(5, 5));

        assertThatThrownBy(() -> gridManager.deployRover(firstRover))
                .isInstanceOf(IndexOutOfBoundsException.class)
                .hasMessage("Position out of bounds!");
        Rover secondRover = createTestingRover();
        secondRover.setPosition(new Position(2, 2));
        assertThat(gridManager.deployRover(secondRover)).isTrue();
        assertThat(gridManager.deployRover(secondRover)).isFalse();

    }

    @Test
    public void moveForward_shouldMoveNorth_whenDirectionIsNorth() {
        GridManager gridManager = new GridManager();
        Rover rover = createTestingRover();

        gridManager.deployRover(rover);

        String response = gridManager.moveForward(rover);

        assertThat(response).isEqualTo("Rover is not turned on.");

        rover.setRunning(true);
        response = gridManager.moveForward(rover);

        assertThat(response).isEqualTo("Execution success.");

    }

    @Test
    public void moveForward_shouldWrapAroundAndMoveCorrectly_forAllDirections() {
        GridManager gridManager = new GridManager();
        Rover northRover = Rover.builder()
                .position(new Position(2, 0))
                .direction(Direction.NORTH)
                .name("North")
                .running(true)
                .build();

        gridManager.deployRover(northRover);
        String responseNorth = gridManager.moveForward(northRover);
        assertThat(responseNorth).isEqualTo("Execution success.");


        Rover southRover = Rover.builder()
                .position(new Position(2, 4))
                .direction(Direction.SOUTH)
                .name("South")
                .running(true)
                .build();
        gridManager.deployRover(southRover);
        String responseSouth = gridManager.moveForward(southRover);
        assertThat(responseSouth).isEqualTo("Execution success.");


        Rover eastRover = Rover.builder()
                .position(new Position(4, 3))
                .direction(Direction.EAST)
                .name("East")
                .running(true)
                .build();
        gridManager.deployRover(eastRover);
        String responseEast = gridManager.moveForward(eastRover);
        assertThat(responseEast).isEqualTo("Execution success.");


        Rover westRover = Rover.builder()
                .position(new Position(0, 3))
                .direction(Direction.WEST)
                .name("West")
                .running(true)
                .build();
        gridManager.deployRover(westRover);
        String responseWest = gridManager.moveForward(westRover);
        assertThat(responseWest).isEqualTo("Execution success.");

    }

    @Test
    public void moveBackwards_shouldMoveInCorrectDirection_whenDirectionIsAny() {
        GridManager gridManager = new GridManager();

        Rover northRover = createTestingRover(new Position(2, 2), Direction.NORTH);
        gridManager.deployRover(northRover);
        String responseNorth = gridManager.moveBackwards(northRover);
        assertThat(responseNorth).isEqualTo("Execution success.");

        Rover southRover = createTestingRover(new Position(2, 2), Direction.SOUTH);
        gridManager.deployRover(southRover);
        String responseSouth = gridManager.moveBackwards(southRover);
        assertThat(responseSouth).isEqualTo("Execution success.");

        Rover eastRover = createTestingRover(new Position(2, 2), Direction.EAST);
        gridManager.deployRover(eastRover);
        String responseEast = gridManager.moveBackwards(eastRover);
        assertThat(responseEast).isEqualTo("Execution success.");

        Rover westRover = createTestingRover(new Position(2, 2), Direction.WEST);
        gridManager.deployRover(westRover);
        String responseWest = gridManager.moveBackwards(westRover);
        assertThat(responseWest).isEqualTo("Execution success.");
    }


    @Test
    public void turnRight_shouldTurnRight_whenFacingAnyDirection() {
        GridManager gridManager = new GridManager();
        Rover northRover = createTestingRover(new Position(2, 2), Direction.NORTH);
        Rover southRover = createTestingRover(new Position(2, 2), Direction.SOUTH);
        Rover eastRover = createTestingRover(new Position(2, 2), Direction.EAST);
        Rover westRover = createTestingRover(new Position(2, 2), Direction.WEST);

        gridManager.deployRover(northRover);
        gridManager.deployRover(southRover);
        gridManager.deployRover(eastRover);
        gridManager.deployRover(westRover);

        String northResponse = gridManager.turnRoverRight(northRover);
        String southResponse = gridManager.turnRoverRight(southRover);
        String eastResponse = gridManager.turnRoverRight(eastRover);
        String westResponse = gridManager.turnRoverRight(westRover);

        assertThat(northResponse).isEqualTo("Execution success.");
        assertThat(southResponse).isEqualTo("Execution success.");
        assertThat(eastResponse).isEqualTo("Execution success.");
        assertThat(westResponse).isEqualTo("Execution success.");

    }

    @Test
    public void turnRight_shouldNotTurnRight_whenRoverIsOff() {
        GridManager gridManager = new GridManager();
        Rover offRover = createTestingRover(new Position(2, 2), Direction.NORTH);
        offRover.setRunning(false);
        gridManager.deployRover(offRover);
        String offResponse = gridManager.turnRoverRight(offRover);
        assertThat(offResponse).isEqualTo("Rover is not turned on.");
    }


    @Test
    public void turnLeft_shouldTurnLeft_whenFacingAnyDirection() {
        GridManager gridManager = new GridManager();
        Rover northRover = createTestingRover(new Position(2, 2), Direction.NORTH);
        Rover southRover = createTestingRover(new Position(2, 2), Direction.SOUTH);
        Rover eastRover = createTestingRover(new Position(2, 2), Direction.EAST);
        Rover westRover = createTestingRover(new Position(2, 2), Direction.WEST);

        gridManager.deployRover(northRover);
        gridManager.deployRover(southRover);
        gridManager.deployRover(eastRover);
        gridManager.deployRover(westRover);

        String northResponse = gridManager.turnRoverLeft(northRover);
        String southResponse = gridManager.turnRoverLeft(southRover);
        String eastResponse = gridManager.turnRoverLeft(eastRover);
        String westResponse = gridManager.turnRoverLeft(westRover);

        assertThat(northResponse).isEqualTo("Execution success.");
        assertThat(southResponse).isEqualTo("Execution success.");
        assertThat(eastResponse).isEqualTo("Execution success.");
        assertThat(westResponse).isEqualTo("Execution success.");

    }

    @Test
    public void turnLeft_shouldNotTurnLeft_whenRoverIsOff() {
        GridManager gridManager = new GridManager();
        Rover offRover = createTestingRover(new Position(2, 2), Direction.NORTH);
        offRover.setRunning(false);
        gridManager.deployRover(offRover);
        String offResponse = gridManager.turnRoverLeft(offRover);
        assertThat(offResponse).isEqualTo("Rover is not turned on.");
    }


    private Rover createTestingRover(Position position, Direction direction) {
        return Rover.builder()
                .position(position)
                .name("TestRover")
                .direction(direction)
                .running(true)
                .build();
    }

    private Rover createTestingRover() {
       return Rover
                .builder()
                .position(new Position(2,2))
                .name("Rover de Edu")
                .running(Boolean.FALSE)
                .direction(Direction.NORTH)
                .build();
    }




}
