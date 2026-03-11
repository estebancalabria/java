package org.indra.claseNueve.persistence;

import org.indra.claseNueve.models.Car;
import org.indra.claseNueve.models.Movil;

import lombok.SneakyThrows;

public class RepositorySqliteFactory_Classic implements RepositoryAbstractFactory_Classic{

	@Override @SneakyThrows
	public Repository<Car> createCarRepository() {
		return new CarSqliteRepository();
	}

	@Override @SneakyThrows
	public Repository<Movil> createMovilRepository() {
		// TODO Auto-generated method stub
		return new MovilSqliteRepository();
	}

}
