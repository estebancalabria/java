package org.indra.claseNueve.services;

import org.indra.claseNueve.dto.RegistrationData;
import org.indra.claseNueve.models.*;
import org.indra.claseNueve.persistence.Repository;

public class RegistrationService_ConstructorDependencyInjection {
	
	private Repository<Car> repoAuto = null;
	private Repository<Movil> repoMovil = null;
	
	RegistrationService_ConstructorDependencyInjection(Repository<Car> repoAuto,
		      Repository<Movil> repoMovil) {
	    this.repoAuto = repoAuto;
	    this.repoMovil = repoMovil;
	  }
	

	public void registerCarAndMobile(RegistrationData data) {
		//Registra el auto y el movil
		
	}
}
