package az.edu.turing.step_project.dao.iml;


import az.edu.turing.step_project.dao.BookingDto;
import az.edu.turing.step_project.service.BookingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class BookingServiceImp implements BookingService {
    private final BookingDaoImpl daoBooking;

    public BookingServiceImp(BookingDaoImpl daoBooking) {
        this.daoBooking = daoBooking;
    }

//    @Override
//    public void saveBooking(Collection<BookingDto> bookingDtos) throws IOException {
//        daoBooking.saveBooking(bookingDtos.stream().
//                map(bookingDto -> new BookingEntity(bookingDto.getPassengerName(),bookingDto.getBookingId(),bookingDto.getFlightId()))
//                .collect(Collectors.toList()));
//    }

    //    @Override
//    public List<BookingDto> getAllBookings() throws IOException {
//       return daoBooking.getAllBookings().stream().
//               map(bookingEntity -> new BookingDto(bookingEntity.getPassengerName(), bookingEntity.getBookingId(), bookingEntity.getFlightId()))
//               .collect(Collectors.toList());
//
//    }
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
        BookingEntity booking = new BookingEntity(bookingDto.getPassengerName(), bookingDto.getBookingId(), bookingDto.getFlightId(), bookingDto.getCreadationDate());
        daoBooking.saveAllToFile(booking);
    }

}
