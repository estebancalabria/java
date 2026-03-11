 package org.allianz.javaAvanzado.claseCuatro.infraestructura;

public class ConsoleLog implements Log {


	public void info(String mensaje) {
		System.out.println(mensaje);
		
	}
	
	public void error(String mensaje) {
		System.err.println(mensaje);	
	}

}
