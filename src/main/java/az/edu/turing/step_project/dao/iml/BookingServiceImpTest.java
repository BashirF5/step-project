package az.edu.turing.step_project.dao.iml;

import az.edu.turing.step_project.model.entities.BookingEntity;
import az.edu.turing.step_project.service.BookingService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BookingServiceImpTest {
    @Test
    @DisplayName("getAllBookings - Mocked DAO")
    public void testGetAllBookings() throws IOException {
        List<BookingEntity> expectedBookings = Arrays.asList(
                new BookingEntity("Alice", 100L, 78L, "Paris", LocalDate.now().plusDays(2)),
                new BookingEntity("Bob", 102L, 90L, "London", LocalDate.now().plusDays(1))
        );
        BookingDaoImpl mockBookingDao = Mockito.mock(BookingDaoImpl.class);
        Mockito.when(mockBookingDao.getAllBookings()).thenReturn(expectedBookings);
        BookingService bookingService = new BookingServiceImp(mockBookingDao);
        List<BookingEntity> actualBookings = bookingService.getAllBookings();
        assertEquals(expectedBookings, actualBookings);
    }
    @Test
    @DisplayName("getBookingById - Existing Booking (Mocked DAO)")
    public void testGetBookingByIdExisting() throws IOException {
        Long existingBookingId = 100L;
        BookingEntity expectedBooking = new BookingEntity("Alice", existingBookingId, 78L, "Paris", LocalDate.now().plusDays(2));
        BookingDaoImpl mockBookingDao = Mockito.mock(BookingDaoImpl.class);
        Mockito.when(mockBookingDao.getBookingById(existingBookingId)).thenReturn(Optional.of(expectedBooking));
        BookingService bookingService = new BookingServiceImp(mockBookingDao);
        Optional<BookingEntity> actualBooking = bookingService.getBookingById(existingBookingId);
        assertTrue(actualBooking.isPresent());
        assertEquals(expectedBooking, actualBooking.get());
    }
    @Test
    @DisplayName("getBookingById - Non-existent Booking (Mocked DAO)")
    public void testGetBookingByIdNonExistent() throws IOException {
        Long nonExistentBookingId = 99L;
        BookingDaoImpl mockBookingDao = Mockito.mock(BookingDaoImpl.class);
        Mockito.when(mockBookingDao.getBookingById(nonExistentBookingId)).thenReturn(Optional.empty());
        BookingService bookingService = new BookingServiceImp(mockBookingDao);
        Optional<BookingEntity> actualBooking = bookingService.getBookingById(nonExistentBookingId);
        assertFalse(actualBooking.isPresent());
    }
    @Test
    @DisplayName("getBookingsByFlightId - Existing Booking (Mocked DAO)")
    public void testGetBookingsByFlightIdExisting() throws IOException {
        Long existingFlightId = 78L;
        BookingEntity expectedBooking = new BookingEntity("Alice", 100L, existingFlightId, "Paris", LocalDate.now().plusDays(2));
        BookingDaoImpl mockBookingDao = Mockito.mock(BookingDaoImpl.class);
        Mockito.when(mockBookingDao.getBookingsByFlightId(existingFlightId)).thenReturn(Optional.of(expectedBooking));
        BookingService bookingService = new BookingServiceImp(mockBookingDao);
        Optional<BookingEntity> actualBooking = bookingService.getBookingsByFlightId(existingFlightId);
        assertTrue(actualBooking.isPresent());
        assertEquals(expectedBooking, actualBooking.get());
    }
    @Test
    @DisplayName("getBookingsByFlightId - Non-existent Booking (Mocked DAO)")
    public void testGetBookingsByFlightIdNonExistent() throws IOException {
        Long nonExistentFlightId = 45L;
        BookingDaoImpl mockBookingDao = Mockito.mock(BookingDaoImpl.class);
        Mockito.when(mockBookingDao.getBookingsByFlightId(nonExistentFlightId)).thenReturn(Optional.empty());
        BookingService bookingService = new BookingServiceImp(mockBookingDao);
        Optional<BookingEntity> actualBooking = bookingService.getBookingsByFlightId(nonExistentFlightId);
        assertFalse(actualBooking.isPresent());
    }



}