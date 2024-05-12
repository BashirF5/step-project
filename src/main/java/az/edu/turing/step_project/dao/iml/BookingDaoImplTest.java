package az.edu.turing.step_project.dao.iml;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import az.edu.turing.step_project.controller.BookingController;
import az.edu.turing.step_project.model.dto.BookingDto;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import az.edu.turing.step_project.dao.iml.BookingDaoImpl;
import az.edu.turing.step_project.model.entities.BookingEntity;

public class BookingDaoImplTest {
    private static final String FILE_PATH = "C:\\Users\\Admin\\IdeaProjects\\turing\\turing-projects\\step-project2\\src\\main\\java\\az\\edu\\turing\\step_project\\resources\\";
    private static final String FILE_PATH_RESOURCES = FILE_PATH.concat("bookingInfo.json");
    //  private static final String FILE_PATH_RESOURCES = FILE_PATH.concat("bookingInfo.txt");
    private static final File file = new File(FILE_PATH_RESOURCES);
    private static ObjectMapper mapper;

    @Test
    @DisplayName("Crete")

    void create() throws IOException {
        BookingDaoImpl bookingDaoImpl = new BookingDaoImpl(new ObjectMapper().registerModule(new JavaTimeModule()));
        BookingServiceImp bookingServiceImp = new BookingServiceImp(bookingDaoImpl);
        BookingController bookingController = new BookingController(bookingServiceImp);
        BookingDto bookingDto1 = new BookingDto("A", 88L, 99L, LocalDate.of(2024, 6, 4));
        bookingController.createBooking(bookingDto1);
        BookingDto bookingDto2 = new BookingDto("A", 89L, 49L, LocalDate.now());
        bookingController.createBooking(bookingDto2);
    }


    @Test
    @DisplayName("getAll")
    void getAll() throws IOException {
        BookingDaoImpl bookingDaoImpl = new BookingDaoImpl(new ObjectMapper().registerModule(new JavaTimeModule()));
        BookingServiceImp bookingServiceImp = new BookingServiceImp(bookingDaoImpl);
        BookingController bookingController = new BookingController(bookingServiceImp);
        BookingDto bookingDto1 = new BookingDto("A", 88L, 99L,  LocalDate.of(2024, 6, 4));
        List<BookingEntity> actualBookings = bookingController.getAllBookings();
        assertEquals(1, actualBookings.size());
        BookingEntity booking = actualBookings.get(0);
        assertEquals("A", booking.getPassengerName());
        assertEquals(88L, booking.getBookingId());
        assertEquals(99L, booking.getFlightId());
        assertEquals(LocalDate.of(2024, 6, 4), booking.getCreadationDate());
    }

    @Test
    @DisplayName("getByBookingId")
    void getByBookingId() throws IOException {
        BookingDaoImpl bookingDaoImpl = new BookingDaoImpl(new ObjectMapper().registerModule(new JavaTimeModule()));
        BookingServiceImp bookingServiceImp = new BookingServiceImp(bookingDaoImpl);
        BookingController bookingController = new BookingController(bookingServiceImp);
        BookingDto bookingDto1 = new BookingDto("A", 88L, 99L,  LocalDate.of(2024, 6, 4));
        BookingDto bookingDto2 = new BookingDto("A", 89L, 49L,  LocalDate.of(2024, 12, 12));
        bookingController.createBooking(bookingDto1);
        bookingController.createBooking(bookingDto2);
        Optional<BookingEntity> optionalBooking = bookingController.getBookingById(89L);
        assertTrue(optionalBooking.isPresent());
        BookingEntity booking = optionalBooking.get();
        assertEquals("A", booking.getPassengerName());
        assertEquals(89L, booking.getBookingId());
        assertEquals(49L, booking.getFlightId());
        assertEquals(LocalDate.of(2024, 12, 12), booking.getCreadationDate());
    }
    @Test
    @DisplayName("getByFlightId")
    void getByFlightId() throws IOException {
        BookingDaoImpl bookingDaoImpl = new BookingDaoImpl(new ObjectMapper().registerModule(new JavaTimeModule()));
        BookingServiceImp bookingServiceImp = new BookingServiceImp(bookingDaoImpl);
        BookingController bookingController = new BookingController(bookingServiceImp);
        BookingDto bookingDto1 = new BookingDto("A", 88L, 99L,  LocalDate.of(2024, 6, 4));
        BookingDto bookingDto2 = new BookingDto("A", 89L, 49L,  LocalDate.of(2024, 12, 12));
        bookingController.createBooking(bookingDto1);
        bookingController.createBooking(bookingDto2);
        Optional<BookingEntity> optionalBooking = bookingController.getBookingsByFlightId(99L);
        assertTrue(optionalBooking.isPresent());
        BookingEntity booking = optionalBooking.get();
        assertEquals("A", booking.getPassengerName());
        assertEquals(88L, booking.getBookingId());
        assertEquals(99L, booking.getFlightId());
        assertEquals(LocalDate.of(2024, 6, 4), booking.getCreadationDate());
    }
    @Test
    @DisplayName("cancelByBookingId")
    void cancelBookingById() throws IOException {
        BookingDaoImpl bookingDaoImpl = new BookingDaoImpl(new ObjectMapper().registerModule(new JavaTimeModule()));
        BookingServiceImp bookingServiceImp = new BookingServiceImp(bookingDaoImpl);
        BookingController bookingController = new BookingController(bookingServiceImp);
        BookingDto bookingDto1 = new BookingDto("A", 88L, 99L,  LocalDate.of(2024, 6, 4));
        BookingDto bookingDto2 = new BookingDto("A", 89L, 49L,  LocalDate.of(2024, 12, 12));
        bookingController.createBooking(bookingDto1);
        bookingController.createBooking(bookingDto2);
        boolean optionalBooking = bookingController.cancelBookingById(88L);
        assertTrue(optionalBooking);

    }

}