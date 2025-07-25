import org.junit.jupiter.api.Test;
import rover.model.Direction;
import rover.model.Position;
import rover.model.Rover;
import rover.service.RoverService;

import java.util.List;

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
    @Test
    public void alreadyDeployedRover() {
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



        //Assert que la posicion q tiene y la nueva son la misma, la de response

    }




}
