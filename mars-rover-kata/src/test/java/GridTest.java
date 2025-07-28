import org.junit.jupiter.api.Test;
import rover.model.Grid;
import rover.model.Position;
import rover.service.GridManager;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class GridTest {


    @Test
    public void createGrid_shouldBeCreated() {

        Grid grid = new Grid();

        assertThat(grid.getGrid()).isNotNull();
    }

    @Test
    public void deployRover_shouldReturnTrue_whenPositionIsValidAndEmpty() {
        GridManager gridManager = new GridManager();

        Position position = new Position(0, 0);
        int roverId = 1;

        boolean deployed = gridManager.deployRover(position, roverId);

        assertThat(deployed).isTrue();

    }

    @Test
    public void deployRover_shouldReturnFalse_whenPositionIsInvalid() {
        GridManager gridManager = new GridManager();
        Position position = new Position(5, 5);
        int roverId = 1;
        assertThatThrownBy(() -> gridManager.deployRover(position, roverId))
                .isInstanceOf(IndexOutOfBoundsException.class)
                .hasMessage("Position out of bounds!");

        Position nextPosition = new Position(2, 2);
        assertThat(gridManager.deployRover(nextPosition, roverId)).isTrue();
        assertThat(gridManager.deployRover(nextPosition, roverId)).isFalse();

    }



}
