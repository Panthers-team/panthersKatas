import org.junit.jupiter.api.Test;
import rover.model.Grid;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GridTest {


    @Test
    public void createGrid_shouldBeCreated() {
        int rows = 10;
        int cols = 10;

        Grid grid = new Grid(rows, cols);

        assertThat(grid.getGrid()).isNotNull();
    }
}
