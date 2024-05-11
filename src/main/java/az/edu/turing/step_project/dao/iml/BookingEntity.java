package az.edu.turing.step_project.dao.iml;

import java.io.Serializable;
import java.time.LocalDate;

public class BookingEntity implements Serializable {

    private static final Long serialUID = 1L;
    private String passengerName;
    private Long bookingId;
    private Long flightId;
    private LocalDate creadationDate;

    public BookingEntity() {
    }

    public BookingEntity(String passengerName, Long bookingId, Long flightId, LocalDate creadationDate) {
        this.passengerName = passengerName;
        this.bookingId = bookingId;
        this.flightId = flightId;
        creadationDate = creadationDate;
    }

//    public BookingEntity(String passengerName, Long bookingId, Long flightId) {
//        this.passengerName = passengerName;
//        this.bookingId = bookingId;
//        this.flightId = flightId;
//
//    }

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

    public LocalDate getCreadationDate() {
        return creadationDate;
    }

    public void setCreadationDate(LocalDate creadationDate) {
        this.creadationDate = creadationDate;
    }

    @Override
    public String toString() {
        return "BookingEntity{" +
                "passengerName='" + passengerName + '\'' +
                ", bookingId=" + bookingId +
                ", flightId=" + flightId +
                ", creadationDate=" + creadationDate +
                '}';
    }
}
