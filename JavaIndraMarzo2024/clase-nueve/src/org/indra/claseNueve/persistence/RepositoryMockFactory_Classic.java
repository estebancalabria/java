package org.indra.claseNueve.persistence;

import org.indra.claseNueve.models.Car;
import org.indra.claseNueve.models.Movil;

public class RepositoryMockFactory_Classic implements RepositoryAbstractFactory_Classic {

	@Override
	public Repository<Car> createCarRepository() {
		// TODO Auto-generated method stub
		return new MockRepository<Car>();
	}

	@Override
	public Repository<Movil> createMovilRepository() {
		// TODO Auto-generated method stub
		return new MockRepository<Movil>();
	}

}
