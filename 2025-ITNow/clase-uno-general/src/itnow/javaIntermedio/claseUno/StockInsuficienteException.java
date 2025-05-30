package itnow.javaIntermedio.claseUno;

public class StockInsuficienteException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StockInsuficienteException() {
		super("No hay stock para realizar la operacion");
	}

}
