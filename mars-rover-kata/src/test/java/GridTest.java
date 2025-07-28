import org.junit.jupiter.api.Test;
import rover.model.Direction;
import rover.model.Grid;
import rover.model.Position;
import rover.model.Rover;
import rover.service.GridManager;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GridTest {


    @Test
    public void createGrid_shouldBeCreated() {

        Grid grid = new Grid();

        assertThat(grid.getGrid()).isNotNull();
    }

    @Test
    public void deployRover_shouldBeDeployed_whenPositionIsValid() {
        GridManager gridManager = new GridManager();

        Rover rover = new Rover();
        rover.setId(1);
        rover.setName("Rover 1");
        rover.setDirection(Direction.NORTH);
        rover.setPosition(new Position(0,0));

        boolean response = gridManager.deployRover(rover.getPosition(), rover.getId());

        assertThat(response).isTrue();
    }
}
