package az.edu.turing.step_project.dao;

import az.edu.turing.step_project.model.entities.FlightsEntity;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;

public abstract class FlightsDao {
    public abstract boolean save(Collection<FlightsEntity> flights);

    public abstract Collection<FlightsEntity> getAll();

    public abstract void delete(long flightId);

    public abstract Optional<FlightsEntity> findOneBy(Predicate<FlightsEntity> predicate);

    public abstract Collection<FlightsEntity> findAllBy(Predicate<FlightsEntity> predicate);
}