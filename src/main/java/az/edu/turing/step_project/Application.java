package az.edu.turing.step_project;

import az.edu.turing.step_project.console.ConsoleInterface;
import az.edu.turing.step_project.controller.BookingController;
import az.edu.turing.step_project.controller.FlightsController;
import az.edu.turing.step_project.dao.BookingDAO;
import az.edu.turing.step_project.dao.FlightsDao;
import az.edu.turing.step_project.dao.FlightsFileDao;
import az.edu.turing.step_project.dao.iml.BookingDaoImpl;
import az.edu.turing.step_project.dao.iml.BookingServiceImp;
import az.edu.turing.step_project.dao.iml.FlightsServiceImpl;
import az.edu.turing.step_project.exception.FlightNotFoundException;
import az.edu.turing.step_project.model.dto.FlightsDto;
import az.edu.turing.step_project.model.entities.BookingEntity;
import az.edu.turing.step_project.model.entities.FlightsEntity;
import az.edu.turing.step_project.service.BookingService;
import az.edu.turing.step_project.service.FlightsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        // Initialize necessary components
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        BookingDAO bookingDAO = new BookingDaoImpl(objectMapper);
        FlightsDao flightDAO = new FlightsFileDao(objectMapper);

        BookingService bookingService = new BookingServiceImp((BookingDaoImpl) bookingDAO);
        FlightsService flightService = new FlightsServiceImpl(flightDAO);

        BookingController bookingController = new BookingController(bookingService);
        FlightsController flightController = new FlightsController(flightService);

        ConsoleInterface consoleInterface = new ConsoleInterface(bookingService);
        consoleInterface.setFlightService(flightService);

        LocalDateTime localDateTime = LocalDateTime.of(2024, 05, 13, 01, 15);
        FlightsEntity flightsEntity1 = new FlightsEntity(localDateTime, "Baku", "Salyan", 23);
        FlightsDto flightsDto = new FlightsDto(flightsEntity1.getFlightId(), flightsEntity1.getDepartureDateTime(), flightsEntity1.getDestination(), flightsEntity1.getLocation(), flightsEntity1.getSeats());
        flightController.createFlights(flightsDto);

        // Start the application
        consoleInterface.start();


    }
}
