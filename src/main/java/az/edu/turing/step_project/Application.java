package az.edu.turing.step_project;

import az.edu.turing.step_project.controller.BookingController;
import az.edu.turing.step_project.dao.BookingDto;
import az.edu.turing.step_project.dao.iml.BookingDaoIml;
import az.edu.turing.step_project.dao.iml.BookingEntity;
import az.edu.turing.step_project.dao.iml.BookingServiceImp;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

public class Application {
    public static void main(String[] args) throws IOException {
        BookingDaoIml bookingDaoIml=new BookingDaoIml();
        BookingServiceImp bookingServiceImp=new BookingServiceImp(bookingDaoIml);
        BookingController bookingController=new BookingController(bookingServiceImp);
        BookingDto bookingDto=bookingController.createBooking(new BookingDto("Aydan", 11L, 1L, 123L, LocalDate.now()));
        System.out.println(bookingDto);
        BookingDto bookingDto1=bookingController.createBooking(new BookingDto("Aydan1", 12L, 1L, 123L, LocalDate.now()));
        System.out.println(bookingDto1);
        BookingDto bookingDto2=bookingController.createBooking(new BookingDto("Aydan2", 10L, 1L, 123L, LocalDate.now()));
        System.out.println(bookingDto2);
      Optional<BookingEntity> bookingDto3=bookingController.cancelBookingById(10L);
        System.out.println(bookingDto3);
        Optional<BookingEntity> bookingDto4=bookingController.cancelBookingByName("Aydan");
        System.out.println(bookingDto4);
        System.out.println( bookingController.getAllBookings());
        System.out.println( bookingController.cancelBookingById(10L));
        System.out.println(bookingController.getAllBookings());
    }
}
