package az.edu.turing.step_project.dao.iml;
import az.edu.turing.step_project.dao.FlightDAO;
import az.edu.turing.step_project.model.entities.FlightEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class FlightDaoImpl implements FlightDAO<FlightEntity> {
    private static final String FILE_PATH = "C:\\Users\\Admin\\IdeaProjects\\turing\\turing-projects\\step-project2\\src\\main\\java\\az\\edu\\turing\\step_project\\resources\\";
    private static final String FILE_PATH_RESOURCES = FILE_PATH.concat("flightInfo.json");
    private static final File file = new File(FILE_PATH_RESOURCES);
    private static ObjectMapper mapper;
    private static ArrayList<FlightEntity> FLIGHT_ENTITIES = new ArrayList<>();

    public FlightDaoImpl(ObjectMapper objectMapper) {
        this.mapper = objectMapper;
    }

    public void saveAllToFile(FlightEntity flightEntity) {
        FLIGHT_ENTITIES.add(flightEntity);
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH_RESOURCES, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            mapper.writeValue(bufferedWriter, FLIGHT_ENTITIES);
            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println("There is a problem");
            e.printStackTrace();
        }
    }

    public boolean saveFlight(Collection<FlightEntity> flights) throws IOException {
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH_RESOURCES, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            mapper.writeValue(bufferedWriter, flights);
            bufferedWriter.close();
            return true;
        } catch (Exception e) {
            System.out.println("There is a problem");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Optional<FlightEntity> getFlightById(Long flightId) throws IOException {
        List<FlightEntity> allFlights = getAllFlights();
        return allFlights.stream()
                .filter(flight -> flight.getFlightId().equals(flightId))
                .findFirst();
    }

    @Override
    public List<FlightEntity> getAllFlights() throws IOException {
        try (FileReader fileReader = new FileReader(FILE_PATH_RESOURCES);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            FlightEntity[] flights = mapper.readValue(bufferedReader, FlightEntity[].class);
            FLIGHT_ENTITIES.addAll(Arrays.asList(flights));
        }

        return FLIGHT_ENTITIES;
    }

    @Override
    public List<FlightEntity> getFlightsByDestination(String destination) throws IOException {
        List<FlightEntity> allFlights = getAllFlights();
        List<FlightEntity> filteredFlights = new ArrayList<>();
        for (FlightEntity flight : allFlights) {
            if (flight.getDestination().equalsIgnoreCase(destination)) {
                filteredFlights.add(flight);
            }
        }
        return filteredFlights;
    }

    @Override
    public boolean cancelFlightById(Long flightId) throws IOException {
        List<FlightEntity> allFlights = getAllFlights();
        Optional<FlightEntity> flightToDelete = allFlights.stream()
                .filter(flight -> flight.getFlightId().equals(flightId))
                .findFirst();

        if (flightToDelete.isPresent()) {
            allFlights.remove(flightToDelete.get());
            saveFlight(allFlights);
            return true;
        }
        return false;
    }

    @Override
    public Optional<FlightEntity> cancelFlightByDestination(String destination) throws IOException {
        List<FlightEntity> allFlights = getAllFlights();
        Optional<FlightEntity> flightToDelete = allFlights.stream()
                .filter(flight -> flight.getDestination().equalsIgnoreCase(destination))
                .findFirst();

        if (flightToDelete.isPresent()) {
            allFlights.remove(flightToDelete.get());
            saveFlight(allFlights);
        }
        return flightToDelete;
    }
}

