package az.edu.turing.step_project.dao.iml;


import az.edu.turing.step_project.dao.BookingDto;
import az.edu.turing.step_project.exception.BookingException;
import az.edu.turing.step_project.service.BookingService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class BookingServiceImp implements BookingService {
    private final BookingDaoIml daoBooking;

    public BookingServiceImp(BookingDaoIml daoBooking) {
        this.daoBooking = daoBooking;
    }

    @Override
    public BookingDto createBooking(BookingDto bookingDto) throws IOException {
        BookingEntity bookingEntity = new BookingEntity(bookingDto.passengerName, bookingDto.bookingId, bookingDto.flightId, bookingDto.NUM_TICKERS, bookingDto.CreadationDate);
        Collection<BookingEntity> bookingsToSave = new ArrayList<>(); // Create a collection
        bookingsToSave.add(bookingEntity); // Add the created entity
        boolean savedEntity = daoBooking.saveBooking(bookingsToSave); // Pass the collection
        return new BookingDto(bookingDto.passengerName, bookingDto.bookingId, bookingDto.flightId, bookingDto.NUM_TICKERS, bookingDto.CreadationDate);
    }


    @Override
    public Optional<BookingEntity> getDetailsBooking(BookingDto bookingDto) {
        try {
            Optional<BookingEntity> getInfo = daoBooking.getBookingById(bookingDto.bookingId);
            return getInfo;
        } catch (BookingException e) {
            System.out.println("Please try again..");
            e.getMessage();
            return null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<BookingEntity> getAllBookings() throws IOException {
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
    public Optional<BookingEntity> cancelBookingById(Long bookingId) throws IOException {
        return daoBooking.cancelBookingById(bookingId);
    }

    @Override
    public Optional<BookingEntity> cancelBookingByName(String bookingName) throws IOException {
         return daoBooking.cancelBookingByName(bookingName);
    }


}
