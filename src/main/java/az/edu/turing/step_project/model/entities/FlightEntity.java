package az.edu.turing.step_project.model.entities;
import java.io.Serializable;
import java.time.LocalDate;

public class FlightEntity implements Serializable {

    private static final Long serialVersionUID = 1L;
    private Long flightId;
    private String destination;
    private LocalDate date;
    private String time;
    private int availableSeats;

    public FlightEntity() {
    }

    public FlightEntity(Long flightId, String destination, LocalDate date, String time, int availableSeats) {
        this.flightId = flightId;
        this.destination = destination;
        this.date = date;
        this.time = time;
        this.availableSeats = availableSeats;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    @Override
    public String toString() {
        return "FlightEntity{" +
                "flightId=" + flightId +
                ", destination='" + destination + '\'' +
                ", date=" + date +
                ", time='" + time + '\'' +
                ", availableSeats=" + availableSeats +
                '}';
    }
}
