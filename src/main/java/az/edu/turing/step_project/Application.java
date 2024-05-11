//
//package az.edu.turing.step_project;
//
//import az.edu.turing.step_project.controller.BookingController;
//import az.edu.turing.step_project.dao.BookingDto;
//import az.edu.turing.step_project.dao.iml.BookingDaoImpl;
//import az.edu.turing.step_project.dao.iml.BookingEntity;
//import az.edu.turing.step_project.dao.iml.BookingServiceImp;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//
//import java.io.IOException;
//import java.time.LocalDate;
//import java.util.Optional;
//
////import static az.edu.turing.step_project.dao.iml.BookingDaoImpl.BOOKING_ENTITIES;
//
//public class Application {
//
//    public static void main(String[] args) throws IOException {
//        BookingDaoImpl bookingDaoImpl =new BookingDaoImpl(new ObjectMapper().registerModule(new JavaTimeModule()));
//        BookingServiceImp bookingServiceImp=new BookingServiceImp(bookingDaoImpl);
//        BookingController bookingController=new BookingController(bookingServiceImp);
////        BookingDto bookingDto=new BookingDto("A",87L,9L,LocalDate.now());
////       bookingController.createBooking(bookingDto);
//        BookingDto bookingDto1=new BookingDto("A",88L,99L,LocalDate.of(2024,6,4));
//        bookingController.createBooking(bookingDto1);
//     BookingDto bookingDto2=new BookingDto("A",89L,49L,LocalDate.now());
//      bookingController.createBooking(bookingDto2);
//
//        System.out.println( bookingController.getAllBookings());
//        System.out.println( bookingController.cancelBookingById(88L));
//        System.out.println(bookingController.getAllBookings());
//      //  System.out.println(bookingController.getBookingsByFlightId(9L));
//       // System.out.println(bookingController.getBookingsByFlightId(49L));
//        System.out.println(bookingController.getBookingsByFlightId(9L));
//     //   System.out.println(bookingController.getBookingById(89L));
//        System.out.println(bookingController.getBookingById(89L));
//
//
//
//
//
//
//
//
//
//    }
//}
//
//
//
