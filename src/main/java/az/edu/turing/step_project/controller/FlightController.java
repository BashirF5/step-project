package az.edu.turing.step_project.controller;
import az.edu.turing.step_project.model.entities.FlightEntity;
import az.edu.turing.step_project.service.FlightService;

import java.util.List;

public class FlightController {
    private static FlightService flightService; // Assuming you have a FlightService class to handle flight-related operations

    public FlightController() {
        this.flightService = FlightService; // Initialize your FlightService
    }

    // Method to retrieve all flights from Kiev in the next 24 hours
    public List<FlightEntity> getFlightsFromKievNext24Hours() {
        return flightService.getFlightsFromKievNext24Hours();
    }

    // Method to search for flights based on destination, date, and number of people
    public List<FlightEntity> searchFlights(String destination, String date, int numOfPeople) {
        return flightService.searchFlights(destination, date, numOfPeople);
    }

    // Method to book a flight with the provided details
    public boolean bookFlight(FlightEntity flight, String[] passengerNames) {
        return flightService.bookFlight(flight, passengerNames);
    }

    // Method to get flight by ID
    public FlightEntity getFlightById(int flightId) {
        return flightService.getFlightById(flightId);
    }
}

