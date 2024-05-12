package az.edu.turing.step_project.dao;
import java.io.*;
import java.util.*;
import java.util.function.Predicate;

import az.edu.turing.step_project.model.entities.FlightsEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FlightsFileDao extends FlightsDao {
    private static final String RESOURCE_PATH = "src/main/java/az/edu/turing/step_project/resources/";
    private static final String FLIGHTS_FILE_PATH = RESOURCE_PATH.concat("flights.json");
    private final ObjectMapper objectMapper;
    private static final File file = new File(FLIGHTS_FILE_PATH);

    public FlightsFileDao(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean save(Collection<FlightsEntity> flights) {
        try {
            FileWriter fw = new FileWriter(FLIGHTS_FILE_PATH);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(objectMapper.writeValueAsString(flights));
            bw.close();
            return true;
        } catch (IOException e) {
            System.err.println("Error while adding new flight: " + e.getMessage());
        }
        return false;
    }

    @Override
    public Collection<FlightsEntity> getAll() {
        try {
            FileReader fr = new FileReader(file);
            BufferedReader x = new BufferedReader(fr);
            String jsonData = x.readLine();
            if (jsonData != null && !jsonData.isBlank()) {
                FlightsEntity[] flights = objectMapper.readValue(jsonData, FlightsEntity[].class);
                x.close();
                var tempList = Arrays.asList(flights);
                return new ArrayList<>(tempList);
            }
            x.close();
        } catch (IOException e) {
            System.out.println("Error while reading flights from file: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public void delete(long flightId) {
        Collection<FlightsEntity> bookings = new ArrayList<>(getAll());
        bookings.removeIf(flights -> flights.getFlightId() == flightId);
        save(bookings);
    }

    @Override
    public Optional<FlightsEntity> findOneBy(Predicate<FlightsEntity> predicate) {
        return getAll().stream().filter(predicate).findFirst();
    }

    @Override
    public Collection<FlightsEntity> findAllBy(Predicate<FlightsEntity> predicate) {
        return getAll().stream().filter(predicate).toList();
    }
}
