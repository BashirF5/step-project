package az.edu.turing.step_project.model.dto;

import java.time.LocalDate;

public class FlightDto {
    public Long flightId;
    public String destination;
    public LocalDate date;
    public String time;
    public int availableSeats;

    public FlightDto(Long flightId, String destination, LocalDate date, String time, int availableSeats) {
        this.flightId = flightId;
        this.destination = destination;
        this.date = date;
        this.time = time;
        this.availableSeats = availableSeats;
    }

    @Override
    public String toString() {
        return "FlightDto{" +
                "flightId=" + flightId +
                ", destination='" + destination + '\'' +
                ", date=" + date +
                ", time='" + time + '\'' +
                ", availableSeats=" + availableSeats +
                '}';
    }
}

