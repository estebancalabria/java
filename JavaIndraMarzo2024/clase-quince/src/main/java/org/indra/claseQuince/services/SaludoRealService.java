package org.indra.claseQuince.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class SaludoRealService implements SaludoService {

	@Override
	public String saludar() {
		// TODO Auto-generated method stub
		return "Hola ambiente productivo";
	}

}
