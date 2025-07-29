import org.junit.jupiter.api.Test;
import rover.controller.RoverController;
import rover.model.Direction;
import rover.model.Position;
import rover.model.Rover;
import rover.service.RoverService;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class RoverControllerTest {


    @Test
    void listRovers_shouldReturnListOfRovers_whenServiceReturnsRovers() {
        RoverService mockService = mock(RoverService.class);
        RoverController controller = new RoverController(mockService);

        List<Rover> fakeList = List.of(
                Rover.builder().id(1).name("rover_1").build(),
                Rover.builder().id(2).name("rover_2").build()
        );
        when(mockService.listRovers()).thenReturn(fakeList);

        List<Rover> result = controller.listRovers();

        assertEquals(2, result.size());
        assertEquals("rover_1", result.get(0).getName());
        verify(mockService).listRovers();
    }

    @Test
    void deployRover_shouldReturnDeployedRover_whenValidRequest() {
        RoverService mockService = mock(RoverService.class);
        RoverController controller = new RoverController(mockService);

        Rover request = Rover.builder()
                .name("r1")
                .position(new Position(5, 5))
                .direction(Direction.NORTH)
                .running(false)
                .build();

        Rover response = Rover.builder()
                .id(99)
                .name("r2")
                .position(new Position(5, 5))
                .direction(Direction.NORTH)
                .running(false)
                .build();

        when(mockService.deployRover(request)).thenReturn(response);

        Rover result = controller.deployRover(request);

        assertEquals(99, result.getId());
        assertEquals("r2", result.getName());
        verify(mockService).deployRover(request);
    }

    @Test
    void turnOffRover_shouldReturnMessage_whenRoverIsTurnedOff() {
        RoverService mockService = mock(RoverService.class);
        RoverController controller = new RoverController(mockService);

        when(mockService.turnOffRover(1)).thenReturn("rover 1 is now stopped!");

        String result = controller.turnOffRover(1);

        assertEquals("rover 1 is now stopped!", result);
        verify(mockService).turnOffRover(1);
    }

    @Test
    void turnOnRover_shouldReturnMessage_whenRoverIsTurnedOn() {
        RoverService mockService = mock(RoverService.class);
        RoverController controller = new RoverController(mockService);

        when(mockService.turnOnRover(1)).thenReturn("rover 1 is now running!");

        String result = controller.turnOnRover(1);

        assertEquals("rover 1 is now running!", result);
        verify(mockService).turnOnRover(1);
    }

    @Test
    void executeRover_shouldReturnLog_whenCommandsAreExecuted() {
        RoverService mockService = mock(RoverService.class);
        RoverController controller = new RoverController(mockService);

        Map<String, String> request = Map.of("command", "ffr");
        when(mockService.executeCommands(1, "ffr")).thenReturn("Execution log...");

        String result = controller.executeRover(1, request);

        assertEquals("Execution log...", result);
        verify(mockService).executeCommands(1, "ffr");
    }


    @Test
    void findRover_shouldReturnRover_whenRoverExists() {
        RoverService mockService = mock(RoverService.class);
        RoverController controller = new RoverController(mockService);

        Rover expected = Rover.builder()
                .id(1)
                .name("rover 1")
                .position(new Position(3, 7))
                .direction(Direction.EAST)
                .running(true)
                .build();

        when(mockService.findRoverById(1)).thenReturn(expected);

        Rover result = controller.findRover(1);

        assertEquals(1, result.getId());
        assertEquals("rover 1", result.getName());
        assertEquals(3, result.getX());
        assertEquals(7, result.getY());
        assertEquals(Direction.EAST, result.getDirection());
        assertTrue(result.isRunning());

        verify(mockService).findRoverById(1);
    }








}
