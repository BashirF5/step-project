package az.edu.turing.step_project.dao.iml;
import az.edu.turing.step_project.dao.FlightsDao;
import az.edu.turing.step_project.model.dto.FlightsDto;
import az.edu.turing.step_project.model.entities.FlightsEntity;
import az.edu.turing.step_project.service.FlightsService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class FlightsServiceImpl implements FlightsService {

    private final FlightsDao flightsDao;

    public FlightsServiceImpl(FlightsDao flightsDao) {
        this.flightsDao = flightsDao;
    }

    @Override
    public void createFlights(FlightsDto flightsDto) {
        FlightsEntity flightsEntity = new FlightsEntity(
                flightsDto.getDepartureDateTime(),
                flightsDto.getDestination(),
                flightsDto.getSeats());
        Collection<FlightsEntity> flights = flightsDao.getAll();
        flights.add(flightsEntity);
        flightsDao.save(flights);
    }

    @Override
    public Collection<FlightsDto> getAllFlights() {
        Collection<FlightsEntity> flights = flightsDao.getAll();
        ArrayList<FlightsDto> flightsDto = new ArrayList<>();
        flights.forEach(flightsEntity -> flightsDto.add(new FlightsDto(flightsEntity.getFlightId(), flightsEntity.getDepartureDateTime(), flightsEntity.getDestination(), flightsEntity.getLocation(), flightsEntity.getSeats())));
        return flightsDto;
    }

    @Override
    public List<FlightsDto> getAllFlightsByLocation(String location) {
        return getAllFlights().stream().filter(flightsDto -> flightsDto.getLocation().equalsIgnoreCase(location)).toList();
    }
    @Override
    public List<FlightsDto> getFlightInfoByFlightId(long flightId) {
        Collection<FlightsDto> flightsEntities=getAllFlights();
        return getAllFlights().stream().filter(flightsDto -> flightsDto.getFlightId() == flightId).toList();
    }
    @Override
    public List<FlightsDto> flightsInNext24Hours(String location, LocalDateTime dateTime) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime next24Hours = now.plusHours(24);
        Collection<FlightsDto> allFlights = getAllFlights();
        return allFlights.stream()
                .filter(flightsDto -> flightsDto.getLocation().equalsIgnoreCase(location)
                        && flightsDto.getDepartureDateTime().isAfter(now)
                        && flightsDto.getDepartureDateTime().isBefore(next24Hours))
                .toList();
    }
}
