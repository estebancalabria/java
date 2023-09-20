package org.indra.repository;

public class PeliculaMockRepoFactory extends PeliculaRepositoryFactory {

	@Override
	public IPeliculaRepository createRepository() throws Exception {
		return new PeliculaMockRepository();
	}

}
