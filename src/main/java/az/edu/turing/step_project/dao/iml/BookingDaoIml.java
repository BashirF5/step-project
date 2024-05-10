package az.edu.turing.step_project.dao.iml;

import az.edu.turing.step_project.dao.BookingDAO;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.*;

public class BookingDaoIml implements BookingDAO<BookingEntity> {
    private static final String FILE_PATH = "C:\\Users\\aydan\\IdeaProjects\\step-project\\src\\main\\java\\az\\edu\\turing\\step_project\\resources";
    private static final String FILE_PATH_RESOURCES = FILE_PATH.concat("bookingInfo.ser");
  //  private static final String FILE_PATH_RESOURCES = FILE_PATH.concat("bookingInfo.txt");
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final ArrayList<BookingEntity> BOOKING_ENTITIES = new ArrayList<>();

    public boolean saveBooking(Collection<BookingEntity> t) throws IOException {
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH_RESOURCES);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(mapper.writeValueAsString(BOOKING_ENTITIES));
            bufferedWriter.close();
            return true;
        } catch (Exception e) {
            System.out.println("There is a problem");
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public Optional<BookingEntity> getBookingById(Long bookingId) throws IOException {
        if (getAllBookings().contains(bookingId)) {
            return Optional.of(BOOKING_ENTITIES.get(Math.toIntExact(bookingId)));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<BookingEntity> getAllBookings() throws IOException {
        List<BookingEntity> bookingList = new ArrayList<>();
        File bookingFile = new File(FILE_PATH_RESOURCES);
        if (bookingFile.exists()) {
            BookingEntity[] bookings = mapper.readValue(bookingFile, BookingEntity[].class);
            for (BookingEntity booking : bookings) {
                bookingList.add(booking);
            }
        }
        return bookingList;
    }


    @Override
    public Optional<BookingEntity> getBookingsByPassengerName(String name) throws IOException {
        List<BookingEntity> allBookings = getAllBookings();
        for (BookingEntity booking : allBookings) {
            if (booking.getPassengerName().equalsIgnoreCase(name)) {
                return Optional.of(booking);
            }
        }
        return Optional.empty();
    }


    @Override
    public Optional<BookingEntity> cancelBookingById(Long bookingId) throws IOException {
        int bookingIndex = -1;
        for (int i = 0; i < BOOKING_ENTITIES.size(); i++) {
            if (BOOKING_ENTITIES.get(i).getBookingId().equals(bookingId)) {
                bookingIndex = i;
                break;
            }
        }
        if (bookingIndex != -1) {
            BookingEntity bookingDelete = BOOKING_ENTITIES.remove(bookingIndex);
            saveBooking(BOOKING_ENTITIES);
            return Optional.of(bookingDelete);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<BookingEntity> cancelBookingByName(String bookingName) throws IOException {
        BookingEntity bookingToDelete = null;
        for (int i = 0; i < BOOKING_ENTITIES.size(); i++) {
            if (BOOKING_ENTITIES.get(i).getPassengerName().equalsIgnoreCase(bookingName)) {
                bookingToDelete = BOOKING_ENTITIES.remove(i);
                break;
            }
        }
        if (bookingToDelete != null) {
            saveBooking(BOOKING_ENTITIES);
            return Optional.of(bookingToDelete);
        } else {
            return Optional.empty();
        }
    }


}
