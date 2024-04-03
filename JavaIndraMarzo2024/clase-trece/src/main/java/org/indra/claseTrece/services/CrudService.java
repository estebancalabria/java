package org.indra.claseTrece.services;

import java.util.List;

public interface CrudService<ID, T> {
   //Lo completan uds?
	void create(T model);
	void delete(ID id);
	void update(ID id, T model);
	T readById(ID id);
	List<T> readAll();
}
