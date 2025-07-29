import org.junit.jupiter.api.Test;
import rover.controller.RoverController;
import rover.model.Rover;
import rover.service.RoverService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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



}
