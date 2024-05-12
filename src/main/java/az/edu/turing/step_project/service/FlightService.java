package az.edu.turing.step_project.service;


import az.edu.turing.step_project.dao.FlightDAO;
import az.edu.turing.step_project.model.entities.FlightEntity;
import az.edu.turing.step_project.model.dto.FlightDto;
import az.edu.turing.step_project.model.entities.FlightEntity;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface FlightService {

    FlightDto createFlight(FlightDto flightDto) throws IOException;

    Optional<FlightEntity> getFlightById(Long flightId) throws IOException;

    List<FlightEntity> getAllFlights() throws IOException;

    List<FlightEntity> getFlightsByDestination(String destination) throws IOException;

    boolean updateFlight(FlightDto flightDto) throws IOException;

    boolean deleteFlightById(Long flightId) throws IOException;

    void saveAllToFile(List<FlightDto> flightDtos) throws IOException;
}
