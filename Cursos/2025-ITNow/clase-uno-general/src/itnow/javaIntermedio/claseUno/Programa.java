package itnow.javaIntermedio.claseUno;

public class Programa {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Repaso total");
		
		//Crear una clase producto con los metodos agregarStock y vender....
		//El producto tiene las propiedades int stock y la nombre
		//Que lanze las excepcion tipada StockInsuficienteException si se trata de vender mas de lo que hay en stock
		//Que lanze la excepcion IllegalArgumentException si estan mal las parametros (cantidad<0)
		
		//Quiero que cree un producto, pregunte al usuario cuanto stock ingresar y luego cuanto vender
		//Que muestre por pantalla cuanto queda del producto...
		try {
			Producto p = new Producto(0, "Celular Nokia 1100");
			p.agregarStock( Consola.readInteger("Ingrese stock a agregar" ) );
			p.vender( Consola.readInteger("Ingrese cuanto quiere vender") );
			System.out.println("Al final quedo  " + p.getStock());
			
		}catch(StockInsuficienteException ex) {
			System.out.println(ex.getMessage());
		}
	}

}
