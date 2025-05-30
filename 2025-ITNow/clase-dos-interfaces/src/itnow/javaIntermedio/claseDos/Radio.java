package itnow.javaIntermedio.claseDos;

public class Radio implements Operable {

	@Override
	public void encender() {
		System.out.println("La radio esta prendida");
	}

	@Override
	public void apagar() {
		System.out.println("La radio esta apagada");		
	}
	
	//Un metodo que no esta en la interfaz
	public void cambiarEstacion() {
		System.out.println("Se cambia de estacion");
	}

}
