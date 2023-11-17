package org.allianz.clasecincospringboot.infraestructura;

import org.springframework.stereotype.Component;

@Component
public class DummyLog implements Log{


	public void info(String mensaje) {
		// No hago nada
		
	}


	public void error(String mensaje) {
		// No hago nada
		
	}

}
