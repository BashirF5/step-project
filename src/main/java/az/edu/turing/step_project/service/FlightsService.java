package az.edu.turing.step_project.service;
import az.edu.turing.step_project.model.dto.FlightsDto;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface FlightsService {
    void createFlights(FlightsDto flightsDto);
    Collection<FlightsDto> getAllFlights();

    List<FlightsDto> getAllFlightsByLocation(String location);
    List<FlightsDto> getFlightInfoByFlightId(long flightId);
    List<FlightsDto> flightsInNext24Hours(String location,LocalDateTime dateTime);
}
