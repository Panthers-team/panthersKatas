package rover.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
public class Grid {

    private Cell[][] grid;

    public Grid() {
        grid = new Cell[5][5];
    }
}
