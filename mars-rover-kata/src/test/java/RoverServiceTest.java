import org.junit.jupiter.api.Test;
import rover.model.Direction;
import rover.model.Position;
import rover.model.Rover;
import rover.service.RoverService;

import static org.assertj.core.api.Assertions.assertThat;


public class RoverServiceTest {

    @Test
    public void shouldCreateRover_HappyPath() {
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


}
