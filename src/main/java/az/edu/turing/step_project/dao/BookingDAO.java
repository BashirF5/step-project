package az.edu.turing.step_project.dao;

import az.edu.turing.step_project.dao.iml.BookingEntity;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface BookingDAO<T> {
    boolean saveBooking(Collection<T> t) throws IOException;
    Optional<T> getBookingById(Long bookingId) throws IOException;
    public List<BookingEntity> getAllBookings() throws IOException;

    Optional<T> getBookingsByFlightId(Long flightId) throws IOException;

    boolean cancelBookingById(Long bookingId) throws IOException;

    public void saveAllToFile(BookingEntity bookingEntity);



    }