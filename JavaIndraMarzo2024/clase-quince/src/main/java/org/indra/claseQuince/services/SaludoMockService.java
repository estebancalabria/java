package org.indra.claseQuince.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("test")
public class SaludoMockService implements SaludoService {

	@Override
	public String saludar() {
		return "Hola ambiente test";
	}
}
