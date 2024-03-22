package org.indra.claseNueve.persistence;

import org.indra.claseNueve.models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockRepository<T extends BusinessObject> implements Repository<T> {
    private Map<Integer, T> modelMap;
    private int nextId;

    public MockRepository() {
        modelMap = new HashMap<>();
        nextId = 1;
    }

    @Override
    public void save(T model) throws PersistenceException {
        modelMap.put(nextId, model);
        model.setId(nextId++);
    }

    @Override
    public void update(T model) throws PersistenceException {
        modelMap.put(model.getId(), model);
    }

    @Override
    public void delete(T model) throws PersistenceException {
        modelMap.remove(model.getId());
    }

    @Override
    public void delete(int id) throws PersistenceException {
        modelMap.remove(id);
    }

    @Override
    public T findById(int id) throws PersistenceException {
        return modelMap.get(id);
    }

    @Override
    public List<T> findAll() throws PersistenceException {
        return new ArrayList<>(modelMap.values());
    }
}
