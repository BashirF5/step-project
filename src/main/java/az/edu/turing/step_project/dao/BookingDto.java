package az.edu.turing.step_project.dao;

import java.time.LocalDate;

public class BookingDto {
    private static final Long serialUID = 1L;
    private String passengerName;
    private Long bookingId;
    private Long flightId;
    private String destination;
    private LocalDate CreadationDate;


    public BookingDto(String passengerName, Long bookingId, Long flightId, String destination, LocalDate creadationDate) {
        this.passengerName = passengerName;
        this.bookingId = bookingId;
        this.flightId = flightId;
        this.destination = destination;
        CreadationDate = creadationDate;
        
    }

    public String getDestination() {
        return destination;
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
                ", CreadationDate=" + CreadationDate +
                '}';
    }

}
