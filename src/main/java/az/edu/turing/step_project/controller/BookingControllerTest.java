package az.edu.turing.step_project.controller;

import az.edu.turing.step_project.exception.BookingException;
import az.edu.turing.step_project.model.dto.BookingDto;
import az.edu.turing.step_project.model.entities.BookingEntity;
import az.edu.turing.step_project.service.BookingService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class BookingControllerTest {

    @Test
    @DisplayName("createBooking - Valid Booking")
    public void testCreateBookingValid() throws IOException {
        BookingService mockBookingService = Mockito.mock(BookingService.class);
        BookingController bookingController = new BookingController(mockBookingService);
        BookingDto bookingDto = new BookingDto("Alice", 100L, 78L, "Paris", LocalDate.now().plusDays(2));
        bookingController.createBooking(bookingDto);
        Mockito.verify(mockBookingService).saveAllToFile(bookingDto);
    }


    @Test
    @DisplayName("getAllBookings - Mocked Service")
    public void testGetAllBookings() throws IOException {
        List<BookingEntity> expectedBookings = Arrays.asList(
                new BookingEntity("Alice", 100L, 78L, "Baku", LocalDate.now().plusDays(2)),
                new BookingEntity("Bob", 102L, 90L, "Norway", LocalDate.now().plusDays(1))
        );
        BookingService mockBookingService = Mockito.mock(BookingService.class);
        Mockito.when(mockBookingService.getAllBookings()).thenReturn(expectedBookings);
        BookingController bookingController = new BookingController(mockBookingService);
        List<BookingEntity> actualBookings = bookingController.getAllBookings();
        assertEquals(expectedBookings, actualBookings);
    }


    @Test
    @DisplayName("getBookingById - Existing Booking (Mocked Service)")
    public void testGetBookingByIdExisting() throws IOException {
        Long existingBookingId = 100L;
        BookingEntity expectedBooking = new BookingEntity("Alice", existingBookingId, 78L, "Baku", LocalDate.now().plusDays(2));
        BookingService mockBookingService = Mockito.mock(BookingService.class);
        Mockito.when(mockBookingService.getBookingById(existingBookingId)).thenReturn(Optional.of(expectedBooking));
        BookingController bookingController = new BookingController(mockBookingService);
        Optional<BookingEntity> actualBooking = bookingController.getBookingById(existingBookingId);
        assertTrue(actualBooking.isPresent());
        assertEquals(expectedBooking, actualBooking.get());
    }

    @Test
    @DisplayName("getBookingById - Non-existent Booking (Mocked Service)")
    public void testGetBookingByIdNonExistent() throws IOException {
        Long nonExistentBookingId = 99L; // Choose an ID that doesn't exist
        BookingService mockBookingService = Mockito.mock(BookingService.class);
        Mockito.when(mockBookingService.getBookingById(nonExistentBookingId)).thenReturn(Optional.empty());
        BookingController bookingController = new BookingController(mockBookingService);
        Optional<BookingEntity> actualBooking = bookingController.getBookingById(nonExistentBookingId);
        assertFalse(actualBooking.isPresent());
    }

    @Test
    @DisplayName("createBooking - Creation Date in Past")
    public void testCreateBookingCreationDatePast() throws IOException {
        BookingController bookingController = new BookingController(null);

        BookingDto bookingDto = new BookingDto("David", 101L, 22L, "Tokyo", LocalDate.now().minusDays(2));

        try {
            bookingController.createBooking(bookingDto);
            fail("Expected BookingException");
        } catch (BookingException e) {
            System.out.println("you cant booking for past!");
        }
    }

    @Test()
    @DisplayName("createBooking - Invalid Booking ID (Zero)")
    public void testCreateBookingInvalidBookingIdZero() throws IOException {
        BookingController bookingController = new BookingController(null);

        BookingDto bookingDto = new BookingDto("Charlie", 0L, 33L, "New York", LocalDate.now().plusDays(7));
        try {
            bookingController.createBooking(bookingDto);
        } catch (BookingException e) {
            System.out.println("Booking Id cannot be 0");
        }

    }

    @Test()
    @DisplayName("createBooking - Invalid Booking ID (Negative)")
    public void testCreateBookingInvalidBookingIdNegative() throws IOException {
        BookingController bookingController = new BookingController(null);
        BookingDto bookingDto = new BookingDto("Bob", -10L, 55L, "London", LocalDate.now().plusDays(5));
        try {
            bookingController.createBooking(bookingDto);
        } catch (BookingException e) {
            System.out.println("Booking Id cannot be negative");
        }

    }


}
