package org.indra.claseOnce.persistence;

import org.indra.claseOnce.models.ObjetoDeNegocio;

public interface Repository<T extends ObjetoDeNegocio> {
	void save(T obj);
	T findById(int id);
}
