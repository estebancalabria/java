package org.indra.claseNueve.services;

import org.indra.claseNueve.dto.RegistrationData;
import org.indra.claseNueve.models.*;
import org.indra.claseNueve.persistence.Repository;

import lombok.SneakyThrows;

public class RegistrationService_ConstructorDependencyInjection {
	
	private Repository<Car> repoAuto = null;
	private Repository<Movil> repoMovil = null;
	
	//Contra. Que si el servicio necesita usar otro repositorio tengo que agregar otro parametro al constructor
	//Constructores telescopicos
	public RegistrationService_ConstructorDependencyInjection(Repository<Car> repoAuto,
		      Repository<Movil> repoMovil) {
	    this.repoAuto = repoAuto;
	    this.repoMovil = repoMovil;
	  }
	

	@SneakyThrows
	public void registerCarAndMobile(RegistrationData data) {
		//Registra el auto y el movil
        Car car = new Car();
        car.setModel(data.getModel());
        car.setYear(data.getYear());
        car.setColor(data.getColor());
        car.validate();

        Movil movil = new Movil();
        movil.setMarca(data.getMarca());
        movil.setTamaño(data.getTamaño());
        movil.validate();
        
        this.repoAuto.save(car);
        this.repoMovil.save(movil);
		
	}
}
