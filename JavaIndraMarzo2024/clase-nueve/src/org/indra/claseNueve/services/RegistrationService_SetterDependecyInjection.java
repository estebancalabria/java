package org.indra.claseNueve.services;

import org.indra.claseNueve.dto.RegistrationData;
import org.indra.claseNueve.models.Car;
import org.indra.claseNueve.models.Movil;
import org.indra.claseNueve.persistence.Repository;

import lombok.SneakyThrows;

public class RegistrationService_SetterDependecyInjection {
	
	//Desventaja : Si agrego otro repositorio (si necesito otro repositorio en el futuro)
	//tiene que cambiar el codigo de tiempo de configuracion que se tea el repositorio nuevo
	//tiene que cambiar el codigo de afuera
	
	private Repository<Car> repoAuto = null;
	private Repository<Movil> repoMovil = null;

	public void setRepoAuto(Repository<Car> repoAuto) {
		this.repoAuto = repoAuto;
	}

	public void setRepoMovil(Repository<Movil> repoMovil) {
		this.repoMovil = repoMovil;
	}

	@SneakyThrows
	//Podria llamarse registerAll()
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
