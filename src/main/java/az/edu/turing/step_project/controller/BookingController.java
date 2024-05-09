package az.edu.turing.step_project.controller;


import az.edu.turing.step_project.dao.BookingDto;
import az.edu.turing.step_project.dao.iml.BookingEntity;
import az.edu.turing.step_project.exception.BookingException;
import az.edu.turing.step_project.service.BookingService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }


    public BookingDto getBookingPasserName(BookingDto bookingDto) {
        try {
            Collections.sort(Arrays.asList(bookingDto.passengerName.split(" ")));
            StringBuilder sortedName = new StringBuilder();
            for (String word : bookingDto.passengerName.split(" ")) {
                sortedName.append(word).append(" ");
            }
            bookingDto.passengerName = sortedName.toString().trim();
            return bookingDto;
        } catch (BookingException e) {
            System.out.println("There are some problems sorter passengers..!");

            return bookingDto;
        }
    }


    public BookingDto createBooking(BookingDto bookingDto) throws IOException {
        final Long bookingId = bookingDto.bookingId;
        if (bookingId == null && bookingId != 6) {
            throw new RuntimeException("Not valid BookingId,please check again! -->" + bookingDto);
        }
        if (bookingDto.CreadationDate.isBefore(LocalDate.now())){
            throw  new BookingException("Creating a book cannot be past!");
        }
        return bookingService.createBooking(bookingDto);

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
    public Optional<BookingEntity> getBookingsByPassengerName(String name) throws IOException {

       Optional<BookingEntity> bookings = bookingService.getBookingsByPassengerName(name);
    if (name==name.toLowerCase().toUpperCase(Locale.ROOT)){
        if (bookings.isPresent()){
            return bookings;
        }
        else {
            throw new BookingException("Name is not found");
        }
    }
        return bookings;
    }
}


