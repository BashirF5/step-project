import az.edu.turing.step_project.console.ConsoleInterface;
import az.edu.turing.step_project.dao.iml.BookingServiceImp;
import az.edu.turing.step_project.model.entities.BookingEntity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class ConsoleInterfaceTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Mock
    private BookingServiceImp bookingService;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setIn(originalIn);
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void testStartExitOption() {
        // Prepare test input
        String simulatedUserInput = "6"; // Simulate user choosing to exit

        // Redirect System.in
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));

        // Call the method to be tested
        ConsoleInterface bookingSystem = new ConsoleInterface();
        bookingSystem.start();

        // Verify the output
        String expectedOutput = "Welcome to the Plane Ticket Booking System!\n" +
                "============================================\n" +
                "Exiting the application...\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testDisplayMainMenu() {
        // Call the method to be tested
        ConsoleInterface bookingSystem = new ConsoleInterface();
        bookingSystem.displayMainMenu();

        // Verify the output
        String expectedOutput = "\nMain Menu:\n" +
                "1. Online-board\n" +
                "2. Show Flight Info\n" +
                "3. Search and Book a Flight\n" +
                "4. Cancel Booking\n" +
                "5. My Flights\n" +
                "6. Exit\n" +
                "Please enter your choice: ";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testDisplayOnlineBoard() throws IOException {
        // Mock the behavior of bookingService
        List<BookingEntity> mockFlights = new ArrayList<>();
        // Add some mock flight data
        mockFlights.add(new BookingEntity(1, "Destination1", "2024-05-12", "10:00", 100));
        mockFlights.add(new BookingEntity(2, "Destination2", "2024-05-13", "11:00", 150));
        when(bookingService.getAllBookings()).thenReturn(mockFlights);

        // Call the method to be tested
        ConsoleInterface bookingSystem = new ConsoleInterface();
        bookingSystem.setBookingService(bookingService); // Inject the mocked booking service
        bookingSystem.displayOnlineBoard();

        // Verify the output
        String expectedOutput = "Online Board - Flights from Kiev in the next 24 hours:\n" +
                "Flight ID: 1 | Destination: Destination1 | Date: 2024-05-12 | Time: 10:00 | Available Seats: 100\n" +
                "Flight ID: 2 | Destination: Destination2 | Date: 2024-05-13 | Time: 11:00 | Available Seats: 150\n";
        assertEquals(expectedOutput, outContent.toString());
    }



    // Add more test cases to cover other functionalities
}
