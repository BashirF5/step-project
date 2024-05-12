package az.edu.turing.step_project.controller;

import az.edu.turing.step_project.model.dto.FlightsDto;
import az.edu.turing.step_project.service.FlightsService;

import java.time.LocalDateTime;
import java.util.*;

public class FlightsController {
    private final FlightsService flightService;

    public FlightsController(FlightsService flightService) {
        this.flightService = flightService;
    }

    public void createFlights(FlightsDto flightsDto) {
        flightService.createFlights(flightsDto);
    }

    public Collection<FlightsDto> getAllFlights() {
        return flightService.getAllFlights();
    }

    public List<FlightsDto> getAllFlightsByLocation(String location) {
        return flightService.getAllFlightsByLocation(location);
    }
    public List<FlightsDto> getFlightInfoByFlightId(long flightId) {
        return flightService.getFlightInfoByFlightId(flightId);
    }
    public List<FlightsDto> getOnlineBoard(String location, LocalDateTime dateTime) {
        return flightService.flightsInNext24Hours(location, dateTime);
    }
}