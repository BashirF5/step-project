package az.edu.turing.step_project.dao;

import java.time.LocalDate;

public class BookingDto {
    public String passengerName;
    public Long bookingId;
    public Long flightId;
    public Long NUM_TICKERS;
    public LocalDate CreadationDate;

    public BookingDto(String passengerName, Long bookingId, Long flightId, Long NUM_TICKERS, LocalDate creadationDate) {
        this.passengerName = passengerName;
        this.bookingId = bookingId;
        this.flightId = flightId;
        this.NUM_TICKERS = NUM_TICKERS;
        CreadationDate = creadationDate;
    }
}
