package org.indra.claseNueve.services;

import org.indra.claseNueve.dto.RegistrationData;
import org.indra.claseNueve.models.Car;
import org.indra.claseNueve.models.Movil;
import org.indra.claseNueve.persistence.RepositoryAbstractFactory_Reflection;

import lombok.SneakyThrows;

public class RegistrationService_IoC_Static_DependencyInjection {

	@SneakyThrows
	public void registerCarAndMobile(RegistrationData data) {
	    // Registrar el auto y el movil
		
		var repoCar = RepositoryAbstractFactory_Reflection.createCarRepository();
		var repoMovil = RepositoryAbstractFactory_Reflection.createMovilRepository();
	    
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
	}
}
