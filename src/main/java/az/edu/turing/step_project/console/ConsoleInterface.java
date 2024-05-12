package az.edu.turing.step_project.console;
import az.edu.turing.step_project.controller.FlightsController;
import az.edu.turing.step_project.dao.FlightsDao;
import az.edu.turing.step_project.dao.FlightsFileDao;
import az.edu.turing.step_project.dao.iml.FlightsServiceImpl;
import az.edu.turing.step_project.exception.BookingException;
import az.edu.turing.step_project.model.dto.BookingDto;
import az.edu.turing.step_project.model.dto.FlightsDto;
import az.edu.turing.step_project.model.entities.BookingEntity;
import az.edu.turing.step_project.service.BookingService;
import az.edu.turing.step_project.service.FlightsService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


public class ConsoleInterface {
    private BookingService bookingService;
    private BufferedReader reader;
    private FlightsService flightService;
    FlightsDao flightsDao=new FlightsFileDao(new ObjectMapper());
    FlightsService flightsService=new FlightsServiceImpl(flightsDao);
    FlightsController flightsController=new FlightsController(flightService);

    public ConsoleInterface(BookingService bookingService) {
        this.bookingService = bookingService;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public ConsoleInterface() {

    }

    public void start() {
        System.out.println("Welcome to the Plane Ticket Booking System!");
        System.out.println("============================================");
        try {
            while (true) {
                displayMainMenu();
                int choice = Integer.parseInt(reader.readLine());
                switch (choice) {
                    case 1:
                        displayOnlineBoard();
                        break;
                    case 2:
                        showFlightInfo();
                        break;
                    case 3:
                        searchAndBookFlight();
                        break;
                    case 4:
                        cancelBooking();
                        break;
                    case 5:
                        displayMyFlights();
                        break;
                    case 6:
                        System.out.println("Exiting the application...");
                        return;
                    default:
                        throw new BookingException("Invalid choice! Please enter a valid option.");
                }
            }
        } catch (IOException | NumberFormatException | BookingException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public void displayMainMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("1. Online-board");
        System.out.println("2. Show Flight Info");
        System.out.println("3. Search and Book a Flight");
        System.out.println("4. Cancel Booking");
        System.out.println("5. My Flights");
        System.out.println("6. Exit");
        System.out.print("Please enter your choice: ");
    }

    public void displayOnlineBoard() {
        System.out.println("Online Board - Flights from Kiev in the next 24 hours:");
        Collection<FlightsDto> flights = flightsController.getAllFlights();
        if (flights.isEmpty()) {
            System.out.println("No flights available.");
        } else {
            for (FlightsDto flight : flights) {
                // Assuming the format of flight information is: Flight ID, Destination, Date, Time, Available Seats
                System.out.printf("Flight ID: %d | Destination: %s | Date: %s | Time: %s%n",
                        flight.getFlightId(), getDestinationFromFlightId(flight.getFlightId()),
                        flight.getDepartureDateTime().toLocalDate(), flight.getDepartureDateTime().toLocalTime());
            }
        }
    }

    private String getDestinationFromFlightId(Long flightId) {
        try {
            // Assuming you have a FlightService instance available
            String destination = flightService.getFlightInfoByFlightId(flightId).toString();
            if (destination != null) {
                return destination;
            } else {
                return "Unknown"; // If destination is not found, return a default value
            }
        } catch (Exception e) {
            System.out.println("An error occurred while fetching destination information: " + e.getMessage());
            return "Error";
        }
    }


    private void showFlightInfo() throws IOException {
        try {
            System.out.print("Enter the Flight ID: ");
            Long flightId = Long.parseLong(reader.readLine());

            Optional<BookingEntity> optionalBooking = bookingService.getBookingById(flightId);
            if (optionalBooking.isPresent()) {
                BookingEntity flight = optionalBooking.get();
                System.out.println("Flight Information:");
                System.out.println("Flight ID: " + flight.getFlightId());
                System.out.println("Destination: " + getDestinationFromFlightId(flight.getFlightId()));
                System.out.println("Creation Date: " + flight.getCreadationDate());
            } else {
                System.out.println("Flight with ID " + flightId + " not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid Flight ID! Please enter a valid number.");
        }
    }


    private void searchAndBookFlight() throws IOException {
        try {
            System.out.println("Search and Book a Flight");
            System.out.print("Enter Destination: ");
            String destination = reader.readLine();

            // Assuming you have a mechanism to validate date input
            System.out.print("Enter Date (YYYY-MM-DD): ");
            LocalDate date = LocalDate.parse(reader.readLine());

            System.out.print("Enter Number of People: ");
            int numberOfPeople = Integer.parseInt(reader.readLine());

            List<BookingEntity> availableFlights = findAvailableFlights(destination, date, numberOfPeople);
            if (availableFlights.isEmpty()) {
                System.out.println("No available flights for the specified criteria.");
            } else {
                System.out.println("Available Flights:");
                for (int i = 0; i < availableFlights.size(); i++) {
                    BookingEntity flight = availableFlights.get(i);
                    System.out.printf("%d. Flight ID: %d | Destination: %s | Date: %s %n",
                            i + 1, flight.getFlightId(), destination, date);
                }

                System.out.print("Enter the number of the flight you want to book (0 to cancel): ");
                int choice = Integer.parseInt(reader.readLine());
                if (choice > 0 && choice <= availableFlights.size()) {
                    // Book the selected flight
                    BookingEntity selectedFlight = availableFlights.get(choice - 1);
                    bookFlight(selectedFlight, numberOfPeople);
                } else {
                    System.out.println("Booking canceled.");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a valid number.");
        }
    }

    private List<BookingEntity> findAvailableFlights(String destination, LocalDate date, int numberOfPeople) throws IOException {
        List<BookingEntity> availableFlights = new ArrayList<>();

        // Retrieve all flights for the given destination and date
        List<BookingEntity> allFlights = bookingService.getAllBookings();

        // Assuming you have a method to check if a flight meets the criteria
        for (BookingEntity flight : allFlights) {
            if (flightMatchesCriteria(flight, destination, date, numberOfPeople)) {
                availableFlights.add(flight);
            }
        }

        return availableFlights;
    }

    private boolean flightMatchesCriteria(BookingEntity flight, String destination, LocalDate date, int numberOfPeople) {
        // Implement logic to check if a flight meets the specified criteria:
        // 1. Destination matches
        // 2. Date matches
        // 3. Sufficient available seats for the specified number of people

        // For example:
        return flight.getCreadationDate().isEqual(date);
    }

    private Long generateBookingId() {
        // Generate a random UUID
        UUID uuid = UUID.randomUUID();
        // Convert UUID to a long value
        long id = uuid.getMostSignificantBits() & Long.MAX_VALUE;
        // Ensure the generated ID is positive
        return id > 0 ? id : -id;
    }

    private void bookFlight(BookingEntity flight, int numberOfPeople) throws IOException {
        try {
            // Check if there are sufficient available seats for booking
            int availableSeats = 50000;
            if (availableSeats >= numberOfPeople) {
                // Create booking records for each passenger
                for (int i = 0; i < numberOfPeople; i++) {
                    // Assuming you prompt the user to enter passenger names
                    System.out.print("Enter passenger name for seat " + (i + 1) + ": ");
                    String passengerName = reader.readLine();
                    // Assuming you generate a unique booking ID for each booking
                    Long bookingId = generateBookingId();
                    // Create a BookingDto object to store booking information
                    BookingDto bookingDto = new BookingDto(passengerName, bookingId, flight.getFlightId(),LocalDate.now());
                    // Save the booking to the database or file
                    bookingService.saveAllToFile(bookingDto);
                }
                System.out.println("Booking successful!");
            } else {
                System.out.println("Insufficient available seats. Booking failed.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a valid number.");
        }
    }

    private void cancelBooking() throws IOException {
        try {
            System.out.print("Enter Booking ID to cancel: ");
            Long bookingId = Long.parseLong(reader.readLine());

            // Attempt to cancel the booking
            boolean canceled = cancelBookingById(bookingId);
            if (canceled) {
                System.out.println("Booking with ID " + bookingId + " has been canceled successfully.");
            } else {
                System.out.println("Booking with ID " + bookingId + " not found or already canceled.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a valid number.");
        }
    }

    // Method to cancel booking by ID
    private boolean cancelBookingById(Long bookingId) throws IOException {
        // Implement logic to cancel booking by ID using the bookingService or any other service
        return bookingService.cancelBookingById(bookingId);
    }

    public void setFlightService(FlightsService flightService) {
        this.flightService = flightService;
    }

    private void displayMyFlights() throws IOException {
        try {
            System.out.print("Enter your full name: ");
            String fullName = reader.readLine();

            // Retrieve bookings associated with the user's full name
            List<BookingEntity> myBookings = getBookingsByPassengerName(fullName);
            if (myBookings.isEmpty()) {
                System.out.println("You have no bookings.");
            } else {
                System.out.println("Your Bookings:");
                for (BookingEntity booking : myBookings) {
                    System.out.println("Booking ID: " + booking.getBookingId() +
                            " | Flight ID: " + booking.getFlightId() +
                            " | Destination: " + getDestinationFromFlightId(booking.getFlightId()) +
                            " | Date: " + booking.getCreadationDate());
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    // Method to retrieve bookings by passenger name
    private List<BookingEntity> getBookingsByPassengerName(String fullName) throws IOException {
        // Implement logic to retrieve bookings by passenger name using the bookingService or any other service
        // Return a list of bookings associated with the given passenger name
        List<BookingEntity> allBookings = bookingService.getAllBookings();
        return allBookings.stream()
                .filter(booking -> booking.getPassengerName().equalsIgnoreCase(fullName))
                .collect(Collectors.toList());
    }
    public void setBookingService(BookingService bookingService) {
        this.bookingService = bookingService;
    }
}
