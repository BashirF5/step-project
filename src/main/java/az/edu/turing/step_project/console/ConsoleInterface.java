package az.edu.turing.step_project.console;

import az.edu.turing.step_project.controller.FlightController;
import az.edu.turing.step_project.controller.BookingController;
import az.edu.turing.step_project.model.entities.BookingEntity;
import az.edu.turing.step_project.model.entities.FlightEntity;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ConsoleInterface {
    private Scanner scanner;
    private FlightController flightController; // Assuming FlightController is your controller class for flights

    public ConsoleInterface() {
        this.scanner = new Scanner(System.in);
        this.flightController = new FlightController(); // Initialize your flight controller
    }

    public void displayMainMenu() {
        System.out.println("Welcome to the Plane Ticket Booking System");
        System.out.println("1. Online-board");
        System.out.println("2. Show the flight info");
        System.out.println("3. Search and book a flight");
        System.out.println("4. Cancel the booking");
        System.out.println("5. My flights");
        System.out.println("6. Exit");
        System.out.print("Please select an option: ");
    }

    public void handleUserInput() throws IOException {
        int option = -1;
        while (option != 0) {
            displayMainMenu();
            option = scanner.nextInt();
            switch (option) {
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
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Implement methods for each menu option based on the requirements

    private void displayOnlineBoard() {
        System.out.println("Online Board - Flights from Kiev in the next 24 hours:");

        // Assuming you have a method in FlightController to get flights from Kiev in the next 24 hours
        List<FlightEntity> flights = flightController.getFlightsFromKievNext24Hours();

        if (flights.isEmpty()) {
            System.out.println("No flights available in the next 24 hours.");
        } else {
            for (FlightEntity flight : flights) {
                System.out.println("Flight ID: " + flight.getId());
                System.out.println("Destination: " + flight.getDestination());
                System.out.println("Date: " + flight.getDate());
                System.out.println("Time: " + flight.getTime());
                System.out.println("Available Seats: " + flight.getAvailableSeats());
                System.out.println("-----------------------------------------");
            }
        }
    }


    private void showFlightInfo() {
        System.out.print("Enter the flight ID: ");
        int flightId = scanner.nextInt();

        // Assuming you have a method in FlightController to get flight info by ID
        FlightEntity flight = flightController.getFlightById(flightId);

        if (flight == null) {
            System.out.println("Flight with ID " + flightId + " not found.");
        } else {
            System.out.println("Flight Information:");
            System.out.println("Flight ID: " + flight.getId());
            System.out.println("Destination: " + flight.getDestination());
            System.out.println("Date: " + flight.getDate());
            System.out.println("Time: " + flight.getTime());
            System.out.println("Available Seats: " + flight.getAvailableSeats());
        }
    }


    private void searchAndBookFlight() {
        System.out.println("Search and Book a Flight:");

        // Prompt user to enter destination, date, and number of people
        System.out.print("Enter destination: ");
        String destination = scanner.nextLine();

        System.out.print("Enter date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        System.out.print("Enter number of people: ");
        int numOfPeople = scanner.nextInt();

        // Assuming you have a method in FlightController to search for flights
        List<FlightEntity> flights = flightController.searchFlights(destination, date, numOfPeople);

        if (flights.isEmpty()) {
            System.out.println("No flights available for the specified criteria.");
            return;
        }

        // Display found flights
        System.out.println("Found Flights:");
        for (int i = 0; i < flights.size(); i++) {
            FlightEntity flight = flights.get(i);
            System.out.println((i + 1) + ". " + flight);
        }

        // Prompt user to choose a flight or return to main menu
        System.out.print("Enter the number of the flight you want to book (0 to return to main menu): ");
        int choice = scanner.nextInt();

        if (choice == 0 || choice > flights.size()) {
            System.out.println("Returning to main menu.");
            return;
        }

        // Book the chosen flight
        FlightEntity chosenFlight = flights.get(choice - 1);
        System.out.println("You have chosen the following flight:");
        System.out.println(chosenFlight);

        // Prompt user to enter passenger names and surnames
        scanner.nextLine(); // Consume newline character
        String[] passengerNames = new String[numOfPeople];
        for (int i = 0; i < numOfPeople; i++) {
            System.out.print("Enter passenger " + (i + 1) + " name and surname: ");
            passengerNames[i] = scanner.nextLine();
        }

        // Assuming you have a method in FlightController to book the flight
        boolean success = flightController.bookFlight(chosenFlight, passengerNames);
        if (success) {
            System.out.println("Flight booked successfully!");
        } else {
            System.out.println("Failed to book the flight.");
        }
    }


    private void cancelBooking() throws IOException {
        System.out.println("Cancel Booking:");

        // Prompt user to enter the booking ID
        System.out.print("Enter the booking ID: ");
        long bookingId = scanner.nextInt();

        // Assuming you have a method in BookingController to cancel a booking
        boolean success = BookingController.cancelBookingById(bookingId);

        if (success) {
            System.out.println("Booking with ID " + bookingId + " has been successfully cancelled.");
        } else {
            System.out.println("Booking with ID " + bookingId + " was not found or could not be cancelled.");
        }
    }


    private void displayMyFlights() throws IOException {
        System.out.println("My Flights:");

        // Prompt user to enter their full name
        scanner.nextLine(); // Consume newline character
        System.out.print("Enter your full name: ");
        String fullName = scanner.nextLine();

        // Assuming you have a method in BookingController to get all bookings by passenger name
        Optional<BookingEntity> bookingsOptional = BookingController.getBookingsByPassengerName(fullName);

        if (bookingsOptional.isEmpty()) {
            System.out.println("No bookings found for passenger: " + fullName);
        } else {
            System.out.println("Bookings for passenger: " + fullName);
            List<BookingEntity> bookings = (List<BookingEntity>) bookingsOptional.get();
            for (BookingEntity booking : bookings) {
                System.out.println(booking);
            }
        }
    }


    // Add more helper methods as needed

    public static void main(String[] args) throws IOException {
        ConsoleInterface console = new ConsoleInterface();
        console.handleUserInput();
    }
}
