package rover.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
        return null;
    }
    @GetMapping("/rover/{id}")
    public Rover findRover(@PathVariable int id) {
        return null;
    }
    @PostMapping("/rover/{id}")
    public String executeRover(@PathVariable int id, @RequestBody Map<String, String> request) {
        return null;
    }
    @GetMapping("/turn-on-rover/{id}")
    public String turnOnRover(@PathVariable int id) {
        return null;
    }

    @GetMapping("/turn-off-rover/{id}")
    public String turnOffRover(@PathVariable int id) {
        return null;
    }



}
