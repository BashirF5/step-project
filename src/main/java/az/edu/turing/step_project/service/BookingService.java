package az.edu.turing.step_project.service;

import az.edu.turing.step_project.model.dto.BookingDto;
import az.edu.turing.step_project.model.entities.BookingEntity;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface BookingService {

    List<BookingEntity> getAllBookings() throws IOException;
    Optional<BookingEntity> getBookingById(Long bookingId) throws IOException;
    Optional<BookingEntity> getBookingsByFlightId(Long flightId) throws IOException;

     boolean cancelBookingById(Long bookingId) throws IOException;

    public void saveAllToFile(BookingDto bookingDto);

}
