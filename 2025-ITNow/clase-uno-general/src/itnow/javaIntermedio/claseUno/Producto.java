package itnow.javaIntermedio.claseUno;

public class Producto {

	private String nombre;
	private int stock;

	public Producto(int stockInicial, String nombre) {
		if (stockInicial < 0) {
			throw new IllegalArgumentException("El stock inicial no puede ser negativo");
		}

		this.stock = stockInicial;
		this.nombre = nombre;
	}

	public void agregarStock(int cantidad) {
		if (cantidad < 0) {
			throw new IllegalArgumentException("Cantidad a agregar no puede ser negativa");
		}
		this.stock += cantidad;
	}

	public void vender(int cantidad) throws StockInsuficienteException {
		if ((cantidad > this.stock)) {
			throw new StockInsuficienteException();
		}

		if (cantidad < 0) {
			throw new IllegalArgumentException("Cantidad a agregar no puede ser negativa");
		}

		this.stock -= cantidad;
	}

	public String getNombre() {
		return nombre;
	}

	public int getStock() {
		return stock;
	}

}
