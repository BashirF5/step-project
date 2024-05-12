//package az.edu.turing.step_project.controller;
//
//import az.edu.turing.step_project.model.dto.BookingDto;
//import az.edu.turing.step_project.service.BookingService;
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDate;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class BookingControllerTest {
//
//    @Test
//    void createBooking() {
//        BookingService mockBookingService = Mockito.mock(BookingService.class);
//        BookingController bookingController = new BookingController(mockBookingService);
//
//        BookingDto bookingDto = new BookingDto("Alice", 100L, 78L, "Paris", LocalDate.now().plusDays(2));
//
//        bookingController.createBooking(bookingDto);
//
//
//        Mockito.verify(mockBookingService).saveAllToFile(bookingDto);
//    }
//
//    @Test
//    void getAllBookings() {
//    }
//
//    @Test
//    void getBookingById() {
//    }
//
//    @Test
//    void getBookingsByFlightId() {
//    }
//
//    @Test
//    void cancelBookingById() {
//    }
//
//    @Test
//    void saveAllToFile() {
//    }
//}