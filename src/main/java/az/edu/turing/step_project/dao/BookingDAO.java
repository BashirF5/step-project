package az.edu.turing.step_project.dao;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public interface BookingDAO<T> {
    boolean saveBooking(Collection<T> t) throws IOException;
    Optional<T> getBookingById(Long bookingId);
    T getAllBookings();
    Optional<T>getBookingsByPassengerName(String name);
    Optional<T> getBookingsById(Long bookId);
    Optional<T>cancelBooking(String bookingId);


    }