package org.indra.claseNueve.services;

import org.indra.claseNueve.dto.RegistrationData;
import org.indra.claseNueve.models.*;
import org.indra.claseNueve.persistence.*;

import lombok.SneakyThrows;

//IoC Inversion of Control Container es una fabrica que crea objetos
//para la inyeccion de dependencia,
public class RegistrationService_IoC_DependencyInjection {
	
	private RepositoryAbstractFactory_Classic repoFactory ;

	//Ventajas: 
	// -- No tengo que modificar lo de afuera si en el futuro necesito otro repositorio
	// -- Puedo crear todos los repositorios que quiera
	public RegistrationService_IoC_DependencyInjection(RepositoryAbstractFactory_Classic repoFactory) {
		this.repoFactory = repoFactory;
	}
	
	private Car testCar = null;
	private Movil testMovil = null;
	
	@SneakyThrows
	public void registerCarAndMobile(RegistrationData data) {
	    // Registrar el auto y el movil
		
		var repoCar = this.repoFactory.createCarRepository();
		var repoMovil = this.repoFactory.createMovilRepository();
	    
		Car car = new Car();
	    car.setModel(data.getModel());
	    car.setYear(data.getYear());
	    car.setColor(data.getColor());
	    car.validate();
	    
	    Movil movil = new Movil();
	    movil.setMarca(data.getMarca());
	    movil.setTamaño(data.getTamaño());
	    movil.validate();
	    
	    repoCar.save(car);
	    repoMovil.save(movil);		
	    
	    this.testCar = car;
	    this.testMovil = movil;
	}
	
	//Metodos para testear y que funcione el test
	public Car getRegisteredCar_ForTesting() {
		return this.testCar;
	}
	
	public Movil getRegisteredMovil_ForTesting() {
		return this.testMovil;
	}

}
