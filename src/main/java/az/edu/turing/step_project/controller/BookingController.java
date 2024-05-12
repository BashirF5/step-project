package az.edu.turing.step_project.controller;

import az.edu.turing.step_project.model.dto.BookingDto;
import az.edu.turing.step_project.model.entities.BookingEntity;
import az.edu.turing.step_project.service.BookingService;
import az.edu.turing.step_project.exception.BookingException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class BookingController {
    private static BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }


    public void createBooking(BookingDto bookingDto) throws IOException {
        if (bookingDto.getBookingId() == null || bookingDto.getBookingId() < 1) {
            throw new BookingException("Invalid booking ID. Please provide a positive value.");
        }
        if (bookingDto.getCreadationDate().isBefore(LocalDate.now())) {
            throw new BookingException("Creation date cannot be in the past.");
        }
        bookingService.saveAllToFile(bookingDto);
    }

    public List<BookingEntity> getAllBookings() throws IOException {
        return bookingService.getAllBookings();
    }

    public Optional<BookingEntity> getBookingById(Long bookingId) throws IOException {
        if (bookingService != null) {
            return bookingService.getBookingById(bookingId);
        } else {
            throw new BookingException("Booking service is not available");
        }
    }

    public Optional<BookingEntity> getBookingsByFlightId(Long flightId) throws IOException {
        if (bookingService != null) {
            return bookingService.getBookingsByFlightId(flightId);
        } else {
            throw new BookingException("Booking service is not available");
        }
    }

    public static boolean cancelBookingById(Long bookingId) throws IOException {
        if (bookingId > 0) {
            if (bookingService.cancelBookingById(bookingId)) {
                return true;
            } else {
                return false;
            }
        } else {
            throw new BookingException("BookingId cannot be negative");
        }
    }

    public void saveAllToFile(BookingDto bookingDto) {
        bookingService.saveAllToFile(bookingDto);
    }


}


