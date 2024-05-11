package az.edu.turing.step_project.dao.iml;

import az.edu.turing.step_project.dao.BookingDAO;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.*;

public class BookingDaoImpl implements BookingDAO<BookingEntity> {
    private static final String FILE_PATH = "C:\\Users\\aydan\\IdeaProjects\\step-project\\src\\main\\java\\az\\edu\\turing\\step_project\\resources\\";
    private static final String FILE_PATH_RESOURCES = FILE_PATH.concat("bookingInfo.json");
    //  private static final String FILE_PATH_RESOURCES = FILE_PATH.concat("bookingInfo.txt");
    private static final File file = new File(FILE_PATH_RESOURCES);
    private static ObjectMapper mapper;
    private static  ArrayList<BookingEntity> BOOKING_ENTITIES = new ArrayList<>();

    public BookingDaoImpl(ObjectMapper objectMapper) {
        this.mapper = objectMapper;
    }


    public void saveAllToFile(BookingEntity bookingEntity) {
        BOOKING_ENTITIES.add(bookingEntity);
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH_RESOURCES,true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            mapper.writeValue(bufferedWriter, BOOKING_ENTITIES);
            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println("There is a problem");
            e.printStackTrace();
        }
    }

    public boolean saveBooking(Collection<BookingEntity> bookings) throws IOException {
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH_RESOURCES,true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            mapper.writeValue(bufferedWriter, bookings);
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
        List<BookingEntity> allBookings = getAllBookings();
        return allBookings.stream()
                .filter(booking -> booking.getBookingId().equals(bookingId))
                .findFirst();
    }

    @Override
    public List<BookingEntity> getAllBookings() throws IOException {
      //  BOOKING_ENTITIES.clear();
       // File bookingFile = file;

            try (FileReader fileReader = new FileReader(FILE_PATH_RESOURCES);
                 BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                BookingEntity[] bookings = mapper.readValue(bufferedReader, BookingEntity[].class);
                BOOKING_ENTITIES.addAll(Arrays.asList(bookings));
            }

        return BOOKING_ENTITIES;
    }

    @Override
    public Optional<BookingEntity> getBookingsByPassengerName(String name) throws IOException {
        List<BookingEntity> allBookings = getAllBookings();
        return allBookings.stream()
                .filter(booking -> booking.getPassengerName().equalsIgnoreCase(name))
                .findFirst();
    }

    @Override
    public boolean cancelBookingById(Long bookingId) throws IOException {
        List<BookingEntity> allBookings = getAllBookings();
        Optional<BookingEntity> bookingToDelete = allBookings.stream()
                .filter(booking -> booking.getBookingId().equals(bookingId))
                .findFirst();

        if (bookingToDelete.isPresent()) {
            allBookings.remove(bookingToDelete.get());
            saveBooking(allBookings);
            return true;
        }
//        return bookingToDelete;
        return false;
    }

    @Override
    public Optional<BookingEntity> cancelBookingByName(String bookingName) throws IOException {
        List<BookingEntity> allBookings = getAllBookings();
        Optional<BookingEntity> bookingToDelete = allBookings.stream()
                .filter(booking -> booking.getPassengerName().equalsIgnoreCase(bookingName))
                .findFirst();

        if (bookingToDelete.isPresent()) {
            allBookings.remove(bookingToDelete.get());
            saveBooking(allBookings);
        }
        return bookingToDelete;
    }
}