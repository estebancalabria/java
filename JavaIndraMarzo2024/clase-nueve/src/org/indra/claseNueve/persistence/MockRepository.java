package org.indra.claseNueve.persistence;

import org.indra.claseNueve.models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockRepository<T extends BusinessObject> implements Repository<T> {
    private Map<Integer, T> carMap;
    private int nextId;

    public MockRepository() {
        carMap = new HashMap<>();
        nextId = 1;
    }

    @Override
    public void save(T car) throws PersistenceException {
        carMap.put(nextId, car);
        car.setId(nextId++);
    }

    @Override
    public void update(T car) throws PersistenceException {
        carMap.put(car.getId(), car);
    }

    @Override
    public void delete(T car) throws PersistenceException {
        carMap.remove(car.getId());
    }

    @Override
    public void delete(int id) throws PersistenceException {
        carMap.remove(id);
    }

    @Override
    public T findById(int id) throws PersistenceException {
        return carMap.get(id);
    }

    @Override
    public List<T> findAll() throws PersistenceException {
        return new ArrayList<>(carMap.values());
    }
}
