 package org.allianz.javaAvanzado.claseUno.infraestructura;

public class ConsoleLog implements Log {

	@Override
	public void info(String mensaje) {
		System.out.println(mensaje);
		
	}
	
	@Override
	public void error(String mensaje) {
		System.err.println(mensaje);	
	}

}
