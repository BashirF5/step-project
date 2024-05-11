package az.edu.turing.step_project.dao.iml;


import az.edu.turing.step_project.dao.BookingDto;
import az.edu.turing.step_project.exception.BookingException;
import az.edu.turing.step_project.service.BookingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class BookingServiceImp implements BookingService {
    private final BookingDaoImpl daoBooking;

    public BookingServiceImp(BookingDaoImpl daoBooking) {
        this.daoBooking = daoBooking;
    }

    @Override
    public BookingDto createBooking(BookingDto bookingDto) throws IOException {
        BookingEntity bookingEntity = new BookingEntity(bookingDto.passengerName, bookingDto.bookingId, bookingDto.flightId, bookingDto.NUM_TICKERS, bookingDto.CreadationDate);
        Collection<BookingEntity> bookingsToSave = new ArrayList<>();
        bookingsToSave.add(bookingEntity);
        boolean savedEntity = daoBooking.saveBooking(bookingsToSave);
        return new BookingDto(bookingDto.passengerName, bookingDto.bookingId, bookingDto.flightId, bookingDto.NUM_TICKERS, bookingDto.CreadationDate);
    }



    @Override
    public Optional<BookingEntity> getDetailsBooking(BookingDto bookingDto) {
        try {
            return daoBooking.getBookingById(bookingDto.bookingId);
        } catch (BookingException | IOException e) {
            System.err.println("Booking not found: " + e.getMessage());
            return Optional.empty();
        }
    }
    @Override
    public List<BookingEntity> getAllBookings() throws IOException {
        ObjectMapper mapper = new ObjectMapper(); // Create a new ObjectMapper
        mapper.registerModule(new JavaTimeModule()); // Register JavaTimeModule (optional)
        return daoBooking.getAllBookings();
    }


    public Optional<BookingEntity> getBookingById(Long bookingId) throws IOException {
        return daoBooking.getBookingById(bookingId);
    }

    @Override
    public Optional<BookingEntity> getBookingsByPassengerName(String name) throws IOException {
        return daoBooking.getBookingsByPassengerName(name);
    }

    @Override
    public boolean cancelBookingById(Long bookingId) throws IOException {
        return daoBooking.cancelBookingById(bookingId);
    }

    @Override
    public Optional<BookingEntity> cancelBookingByName(String bookingName) throws IOException {
         return daoBooking.cancelBookingByName(bookingName);
    }

    @Override
    public void saveAllToFile(BookingDto bookingDto) {
        BookingEntity booking = new BookingEntity(bookingDto.passengerName, bookingDto.bookingId, bookingDto.flightId);
        daoBooking.saveAllToFile(booking);
    }


}
