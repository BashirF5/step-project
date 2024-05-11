package az.edu.turing.step_project.dao.iml;
import az.edu.turing.step_project.dao.FlightDAO;
import az.edu.turing.step_project.model.dto.FlightDto;
import az.edu.turing.step_project.model.entities.FlightEntity;
import az.edu.turing.step_project.service.FlightService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class FlightServiceImp implements FlightService {
    private final FlightDAO<FlightEntity> flightDao;

    public FlightServiceImp(FlightDAO<FlightEntity> flightDao) {
        this.flightDao = flightDao;
    }

    @Override
    public FlightDto createFlight(FlightDto flightDto) throws IOException {
        FlightEntity flightEntity = new FlightEntity(
                flightDto.flightId,
                flightDto.destination,
                flightDto.date,
                flightDto.time,
                flightDto.availableSeats
        );
        Collection<FlightEntity> flightsToSave = new ArrayList<>();
        flightsToSave.add(flightEntity);
        boolean savedEntity = flightDao.saveFlight(flightsToSave);
        return new FlightDto(
                flightDto.flightId,
                flightDto.destination,
                flightDto.date,
                flightDto.time,
                flightDto.availableSeats
        );
    }

    @Override
    public Optional<FlightEntity> getFlightById(Long flightId) throws IOException {
        return flightDao.getFlightById(flightId);
    }

    @Override
    public List<FlightEntity> getAllFlights() throws IOException {
        ObjectMapper mapper = new ObjectMapper(); // Create a new ObjectMapper
        mapper.registerModule(new JavaTimeModule()); // Register JavaTimeModule (optional)
        return flightDao.getAllFlights();
    }

    @Override
    public List<FlightEntity> getFlightsByDestination(String destination) throws IOException {
        return flightDao.getFlightsByDestination(destination);
    }

    @Override
    public boolean updateFlight(FlightDto flightDto) throws IOException {
        // Implement logic to update flight
        return false;
    }

    @Override
    public boolean deleteFlightById(Long flightId) throws IOException {
        return flightDao.cancelFlightById(flightId);
    }

    @Override
    public void saveAllToFile(List<FlightDto> flightDtos) throws IOException {
        // Implement logic to save flights to file
    }
}

