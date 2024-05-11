package az.edu.turing.step_project.dao;

import az.edu.turing.step_project.model.entities.FlightEntity;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface FlightDAO<T> {
    boolean saveFlight(Collection<T> t) throws IOException;
    Optional<T> getFlightById(Long flightId) throws IOException;
    List<FlightEntity> getAllFlights() throws IOException;
    List<FlightEntity> getFlightsByDestination(String destination) throws IOException;

    boolean cancelFlightById(Long flightId) throws IOException;
    Optional<T> cancelFlightByDestination(String destination) throws IOException;
    void saveAllToFile(FlightEntity flightEntity) throws IOException;
}
