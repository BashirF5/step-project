package az.edu.turing.step_project.dao.iml;

import az.edu.turing.step_project.dao.BookingDAO;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class BookingDaoIml implements BookingDAO<BookingEntity> {
    private static final String FILE_PATH = "C:\\Users\\aydan\\IdeaProjects\\step-project\\src\\main\\java\\az\\edu\\turing\\step_project\\resources";
    private static final String FILE_PATH_RESOURCES = FILE_PATH.concat("bookingInfo.ser");
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final    ArrayList<BookingEntity> BOOKING_ENTITIES = new ArrayList<>();

    public boolean saveBooking(Collection<BookingEntity> t) throws IOException {
        try {
            FileWriter fileWriter=new FileWriter(FILE_PATH_RESOURCES);
            BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
            bufferedWriter.write(mapper.writeValueAsString(BOOKING_ENTITIES));
            bufferedWriter.close();
            return true;
        }
        catch (Exception e){
            System.out.println("There is a problem");
            e.printStackTrace();
            return false;
        }
    }




    @Override
    public Optional<BookingEntity> getBookingById(Long bookingId) {


        return Optional.empty();
    }

    @Override
    public List<BookingEntity> getAllBookings() throws IOException {
        List<BookingEntity> bookingList = new ArrayList<>();
        BookingEntity[] bookings = mapper.readValue(new File(FILE_PATH_RESOURCES), BookingEntity[].class);
        Collections.addAll(bookingList, bookings);
        return bookingList;
    }

    @Override
    public Optional<BookingEntity> getBookingsByPassengerName(String name) {
        return Optional.empty();
    }

    @Override
    public Optional<BookingEntity> getBookingsById(Long bookId) {
        return Optional.empty();
    }

    @Override
    public Optional<BookingEntity> cancelBooking(String bookingId) {
        return Optional.empty();
    }


}
