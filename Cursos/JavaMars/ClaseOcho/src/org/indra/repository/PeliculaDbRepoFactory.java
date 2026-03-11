package org.indra.repository;

public class PeliculaDbRepoFactory extends PeliculaRepositoryFactory {
	
    @Override
    public IPeliculaRepository createRepository() throws Exception {
        return new PeliculaDbRepository();
    }
}
