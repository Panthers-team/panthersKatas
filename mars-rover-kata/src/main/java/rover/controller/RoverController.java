package rover.controller;

import org.springframework.web.bind.annotation.*;
import rover.model.Rover;
import rover.service.RoverService;

import java.util.List;
import java.util.Map;


@RestController
public class RoverController {

    private final RoverService roverService;


    public RoverController(RoverService roverService) {
        this.roverService = roverService;
    }


    @GetMapping("/list-rovers")
    public List<Rover> listRovers() {
        return roverService.listRovers();
    }
    @PostMapping("/deploy_rover")
    public Rover deployRover(@RequestBody Rover request) {
        return roverService.deployRover(request);
    }
    @GetMapping("/rover/{id}")
    public Rover findRover(@PathVariable int id) {
        return roverService.findRoverById(id);
    }
    @PostMapping("/rover/{id}")
    public String executeRover(@PathVariable int id, @RequestBody Map<String, String> request) {
        return roverService.executeCommands(id, request.get("commands"));
    }

    @GetMapping("/turn-on-rover/{id}")
    public String turnOnRover(@PathVariable int id) {
        return roverService.turnOnRover(id);
    }

    @GetMapping("/turn-off-rover/{id}")
    public String turnOffRover(@PathVariable int id) {
        return roverService.turnOffRover(id);
    }



}
