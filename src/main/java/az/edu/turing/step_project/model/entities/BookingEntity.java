package az.edu.turing.step_project.model.entities;

import java.io.Serializable;
import java.time.LocalDate;

public class BookingEntity implements Serializable {

    private static final Long serialUID = 1L;
    private String passengerName;
    private Long bookingId;
    private Long flightId;
      private String destination;
    private LocalDate creadationDate;

    public BookingEntity() {
    }

    public BookingEntity(String passengerName, Long bookingId, Long flightId, String destination, LocalDate creadationDate) {
        this.passengerName = passengerName;
        this.bookingId = bookingId;
        this.flightId = flightId;
        this.destination = destination;
        this.creadationDate = creadationDate;
    }

    public String getDestination() {
        return destination;
    }

    public BookingEntity(int i, String destination1, String date, String time, int i1) {

    }


    public void setDestination(String destination) {
        this.destination = destination;
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
