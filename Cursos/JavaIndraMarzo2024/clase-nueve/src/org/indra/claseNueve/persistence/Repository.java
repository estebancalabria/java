package org.indra.claseNueve.persistence;

import java.util.*;

import org.indra.claseNueve.models.*;

//Es un generico con constraints/restricciones
public interface Repository<T extends BusinessObject> {
	void save(T model) throws PersistenceException;
	void update(T model) throws PersistenceException;
	void delete(T model) throws PersistenceException;
	void delete(int id) throws PersistenceException;
	T findById(int id) throws PersistenceException;
	List<T> findAll() throws PersistenceException;
}
