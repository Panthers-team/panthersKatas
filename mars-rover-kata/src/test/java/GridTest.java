import org.junit.jupiter.api.Test;
import rover.model.Cell;
import rover.model.Grid;
import rover.model.Position;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GridTest {


    @Test
    public void isPositionOccupied_shouldHandleAllBoundaries() {
        Grid grid = new Grid(List.of());

        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                grid.getGrid()[x][y] = new Cell(false);
            }
        }

        assertDoesNotThrow(() -> grid.isPositionOccupied(new Position(3330, 0)));
        assertDoesNotThrow(() -> grid.isPositionOccupied(new Position(4, 4444)));
        assertDoesNotThrow(() -> grid.isPositionOccupied(new Position(2, 3)));


    }

}
