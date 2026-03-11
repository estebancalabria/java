package itnow.javaIntermedio.claseDos;

public class FichaDeLuz implements Operable {

	@Override
	public void encender() {
		System.out.println("La luz esta encendida");
	}

	@Override
	public void apagar() {
		System.out.println("La luz esta apagada");
	}

}
