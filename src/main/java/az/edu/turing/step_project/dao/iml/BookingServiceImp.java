package az.edu.turing.step_project.dao.iml;


import az.edu.turing.step_project.dao.BookingDto;
import az.edu.turing.step_project.exception.BookingException;
import az.edu.turing.step_project.service.BookingService;

import java.io.IOException;
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
            boolean savedEntity = daoBooking.saveBooking((Collection<BookingEntity>) bookingEntity);
           return  new BookingDto(bookingDto.passengerName, bookingDto.bookingId, bookingDto.flightId, bookingDto.NUM_TICKERS, bookingDto.CreadationDate);

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
        }

    }

    @Override
    public List<BookingEntity> getAllBookings(BookingEntity entity) throws IOException {

        BookingEntity newBooking = new BookingEntity(entity.getPassengerName(),entity.getBookingId(),entity.getBookingId(), entity.getNUM_TICKERS(),entity.getCreadationDate());
        daoBooking.saveBooking((Collection<BookingEntity>) newBooking);
        List<BookingEntity> allBookings = (List<BookingEntity>) daoBooking.getAllBookings();
        allBookings.add(newBooking);

        return allBookings;
    }


}
