package org.indra.claseNueve.persistence;

import org.indra.claseNueve.models.*;

import lombok.SneakyThrows;

public class RepositoryAbstractFactory_Reflection {

	private static Class<Repository<Car>> carRepositoryClass = null;
	private static Class<Repository<Movil>> movilRepositoryClass = null;
	
	//Metodo para configurar que clase usar en tiempo de configuracion 
	//y evitar las subclases
	public static void registerCarRepositoryClass(Class<Repository<Car>> repoClass) {
		carRepositoryClass = repoClass;
	}
	

	public static void registerMovilRepositoryClass(Class<Repository<Movil>> repoClass) {
		movilRepositoryClass = repoClass;
	}

	@SuppressWarnings("unchecked")
	@SneakyThrows
    public static Repository<Car> createCarRepository(){
		assert carRepositoryClass != null;
    	return ((Repository<Car>) carRepositoryClass.getDeclaredConstructors()[0].newInstance());
    }
    
	@SneakyThrows
	@SuppressWarnings("unchecked")
    public static Repository<Movil> createMovilRepository(){
		assert movilRepositoryClass != null;
    	return (Repository<Movil>) movilRepositoryClass.getDeclaredConstructors()[0].newInstance();
    }
}
