package az.edu.turing.step_project.service;

import az.edu.turing.step_project.dao.BookingDto;
import az.edu.turing.step_project.dao.iml.BookingEntity;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface BookingService {

    public BookingDto createBooking(BookingDto bookingDto) throws IOException;

    public Optional<BookingEntity> getDetailsBooking(BookingDto bookingDto);

    public List<BookingEntity> getAllBookings() throws IOException;

    public Optional<BookingEntity> getBookingById(Long bookingId) throws IOException;
    Optional<BookingEntity>getBookingsByPassengerName(String name) throws IOException;
    boolean cancelBookingById(Long bookingId) throws IOException;
    Optional<BookingEntity>cancelBookingByName(String bookingName) throws IOException;
    public void saveAllToFile(BookingDto bookingDto);

}
