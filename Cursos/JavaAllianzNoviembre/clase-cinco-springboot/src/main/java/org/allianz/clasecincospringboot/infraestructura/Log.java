package org.allianz.clasecincospringboot.infraestructura;

import org.springframework.stereotype.Component;

@Component
public interface Log {
	void info(String mensaje);
	void error(String mensaje);
}
