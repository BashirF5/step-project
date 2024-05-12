//package az.edu.turing.step_project.controller;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//        import java.io.File;
//        import java.io.IOException;
//        import java.util.ArrayList;
//        import java.util.List;
//        import java.util.Optional;
//
//        import org.junit.jupiter.api.AfterAll;
//        import org.junit.jupiter.api.BeforeAll;
//        import org.junit.jupiter.api.BeforeEach;
//        import org.junit.jupiter.api.Test;
//
//        import com.fasterxml.jackson.databind.ObjectMapper;
//
//        import az.edu.turing.step_project.dao.iml.BookingDaoImpl;
//        import az.edu.turing.step_project.dao.BookingEntity;
//
//public class BookingDaoImplTest {
//
//    private static BookingDaoImpl bookingDao;
//    private static ObjectMapper mapper;
//    private static final String TEST_FILE_PATH = "test_booking_info.json";
//
//    @BeforeAll
//    public static void beforeAll() throws IOException {
//        mapper = new ObjectMapper();
//        bookingDao = new BookingDaoImpl(mapper);
//
//        // Create a temporary file for testing
//        File testFile = new File(TEST_FILE_PATH);
//        if (testFile.exists()) {
//            testFile.delete();
//        }
//        testFile.createNewFile();
//    }
//
//    @AfterAll
//    public static void afterAll() throws IOException {
//        // Delete the temporary file after tests
//        File testFile = new File(TEST_FILE_PATH);
//        if (testFile.exists()) {
//            testFile.delete();
//        }
//    }
//
//    @BeforeEach
//    public void beforeEach() throws IOException {
//        // Clear the in-memory list of bookings before each test
//        bookingDao.BOOKING_ENTITIES.clear();
//
//        // Clear the test file content
//        File testFile = new File(TEST_FILE_PATH);
//        testFile.delete();
//        testFile.createNewFile();
//    }
//
//    @Test
//    public void testSaveAllToFile() throws IOException {
//        BookingEntity booking1 = new BookingEntity("Alice", 100L, 789L, LocalDate.of(2024, 5, 12));
//        BookingEntity booking2 = new BookingEntity("Bob", 101L, 456L, LocalDate.of(2024, 5, 11));
//
//        bookingDao.saveAllToFile(booking1);
//        bookingDao.saveAllToFile(booking2);
//
//        // Read bookings from the file
//        List<BookingEntity> allBookings = bookingDao.getAllBookings();
//
//        assertEquals(2, allBookings.size());
//        assertTrue(allBookings.contains(booking1));
//        assertTrue(allBookings.contains(booking2));
//    }
//
//    @Test
//    public void testGetBookingById_ExistingBooking() throws IOException {
//        BookingEntity booking = new BookingEntity("Charlie", 102L, 123L, LocalDate.of(2024, 5, 10));
//        bookingDao.saveAllToFile(booking);
//
//        Optional<BookingEntity> retrievedBooking = bookingDao.getBookingById(booking.getBookingId());
//
//        assertTrue(retrievedBooking.isPresent());
//        assertEquals(booking, retrievedBooking.get());
//    }
//
//    @Test
//    public void testGetBookingById_NonexistentBooking() throws IOException {
//        Optional<BookingEntity> retrievedBooking = bookingDao.getBookingById(1000L); // Nonexistent ID
//
//        assertFalse(retrievedBooking.isPresent());
//    }
//
//    @Test
//    public void testGetAllBookings_EmptyList() throws IOException {
//        List<BookingEntity> allBookings = bookingDao.getAllBookings();
//
//        assertTrue(allBookings.isEmpty());
//    }
//
//    @Test
//    public void testGetAllBookings_WithData() throws IOException {
//        BookingEntity booking1 = new BookingEntity("David", 103L, 345L, LocalDate.of(2024, 5, 9));
//        BookingEntity booking2 = new BookingEntity("Eve", 104L, 678L, LocalDate.of(2024, 5, 8));
//
//        bookingDao.saveAllToFile(booking1);
//        bookingDao.saveAllToFile(booking2);
//
//        List<BookingEntity> allBookings = bookingDao.getAllBookings();
//
//        assertEquals(2, allBookings.size());
//        assertTrue(allBookings.contains(booking1));
//        assertTrue(allBookings.contains
//{
//}
