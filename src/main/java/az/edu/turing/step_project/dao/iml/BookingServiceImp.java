package az.edu.turing.step_project.dao.iml;


import az.edu.turing.step_project.dao.BookingDto;
import az.edu.turing.step_project.service.BookingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;



public class BookingServiceImp implements BookingService {
    private final BookingDaoImpl daoBooking;

    public BookingServiceImp(BookingDaoImpl daoBooking) {
        this.daoBooking = daoBooking;
    }

    @Override
    public List<BookingEntity> getAllBookings() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return daoBooking.getAllBookings();
    }

    @Override
    public Optional<BookingEntity> getBookingById(Long bookingId) throws IOException {
        return daoBooking.getBookingById(bookingId);
    }

    @Override
    public Optional<BookingEntity> getBookingsByFlightId(Long flightId) throws IOException {
        return daoBooking.getBookingsByFlightId(flightId);
    }

    @Override
    public boolean cancelBookingById(Long bookingId) throws IOException {
        return daoBooking.cancelBookingById(bookingId);
    }


    @Override
    public void saveAllToFile(BookingDto bookingDto) {
        BookingEntity booking = new BookingEntity(bookingDto.getPassengerName(), bookingDto.getBookingId(), bookingDto.getFlightId(),bookingDto.getDestination(), bookingDto.getCreadationDate());
        daoBooking.saveAllToFile(booking);
    }

}
