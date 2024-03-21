package org.indra.claseNueve.persistence;

import org.indra.claseNueve.models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarMockRepository implements Repository<Car> {
    private Map<Integer, Car> carMap;
    private int nextId;

    public CarMockRepository() {
        carMap = new HashMap<>();
        nextId = 1;
    }

    @Override
    public void save(Car car) throws PersistenceException {
        carMap.put(nextId, car);
        car.setId(nextId++);
    }

    @Override
    public void update(Car car) throws PersistenceException {
        carMap.put(car.getId(), car);
    }

    @Override
    public void delete(Car car) throws PersistenceException {
        carMap.remove(car.getId());
    }

    @Override
    public void delete(int id) throws PersistenceException {
        carMap.remove(id);
    }

    @Override
    public Car findById(int id) throws PersistenceException {
        return carMap.get(id);
    }

    @Override
    public List<Car> findAll() throws PersistenceException {
        return new ArrayList<>(carMap.values());
    }
}
