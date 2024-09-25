package org.ministerioTrabajo.claseunospring.repositories;

import java.util.List;
import java.util.Optional;

//Vamos a definir la interfaz como generica
public interface GenericRepository<T, ID> {
	T save(T entity);
	Optional<T> findById(ID id);
	void deleteById(ID id);
	List<T> findAll();
}
