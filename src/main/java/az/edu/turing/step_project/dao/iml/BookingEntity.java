package az.edu.turing.step_project.dao.iml;

import java.io.Serializable;
import java.time.LocalDate;

public class BookingEntity implements Serializable {

    private  static final Long serialUID=1L;
    private String passengerName;
    private Long bookingId;
    private Long flightId;
    private Long NUM_TICKERS;
    private LocalDate CreadationDate;

    public BookingEntity() {
    }

    public BookingEntity(String passengerName, Long bookingId, Long flightId, Long NUM_TICKERS, LocalDate creadationDate) {
        this.passengerName = passengerName;
        this.bookingId = bookingId;
        this.flightId = flightId;
        this.NUM_TICKERS = NUM_TICKERS;
        CreadationDate = creadationDate;
    }
    public BookingEntity(String passengerName, Long bookingId, Long flightId) {
        this.passengerName = passengerName;
        this.bookingId = bookingId;
        this.flightId = flightId;

    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public Long getNUM_TICKERS() {
        return NUM_TICKERS;
    }

    public void setNUM_TICKERS(Long NUM_TICKERS) {
        this.NUM_TICKERS = NUM_TICKERS;
    }

    public LocalDate getCreadationDate() {
        return CreadationDate;
    }

    public void setCreadationDate(LocalDate creadationDate) {
        CreadationDate = creadationDate;
    }

    @Override
    public String toString() {
        return "BookingEntity{" +
                "passengerName='" + passengerName + '\'' +
                ", bookingId=" + bookingId +
                ", flightId=" + flightId +
                ", NUM_TICKERS=" + NUM_TICKERS +
                ", CreadationDate=" + CreadationDate +
                '}';
    }

}
