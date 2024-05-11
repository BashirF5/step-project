
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
//    public static void main(String[] args) throws IOException {
//        BookingDaoImpl bookingDaoImpl =new BookingDaoImpl(new ObjectMapper().registerModule(new JavaTimeModule()));
//        BookingServiceImp bookingServiceImp=new BookingServiceImp(bookingDaoImpl);
//        BookingController bookingController=new BookingController(bookingServiceImp);
////        BookingEntity bookingDto2 = new BookingEntity("Aydan", 11L, 1L, 123L, LocalDate.now());
////        BookingEntity bookingDto3 = new BookingEntity("Aydan", 11L, 1L, 123L, LocalDate.now());
////        BookingEntity bookingDto4 = new BookingEntity("Aydan", 11L, 1L, 123L, LocalDate.now());
////        BookingEntity bookingDto5 = new BookingEntity("Aydan", 11L, 1L, 123L, LocalDate.now());
////
//
////        BOOKING_ENTITIES.add(bookingDto2);
////        bookingDaoImpl.saveAllToFile();
////        System.out.println(bookingDaoImpl.getAllBookings());
//        BookingDto bookingDto=new BookingDto("Test",22L,3L,123L,LocalDate.now());
//        BookingDto bookingDto1=new BookingDto("Test",12L,3L,123L,LocalDate.now());
//        BookingDto bookingDto2=new BookingDto("Test",32L,3L,123L,LocalDate.now());
//
////        bookingController.saveAllToFile(bookingDto);
////        bookingController.saveAllToFile(bookingDto1);
////        bookingController.saveAllToFile(bookingDto2);
//        bookingController.saveAllToFile(bookingDto);
//     //   System.out.println(bookingDaoImpl.getAllBookings());
//        System.out.println( bookingController.cancelBookingById(22L));
//        System.out.println( bookingController.getAllBookings());
//
//
//
//
//    }
//}

package az.edu.turing.step_project;

public class Application {
    public static void main(String[] args) {
        boolean flag = false;
        while (!flag) {
            System.out.println("S");
            flag = true;
        }
    }
}

