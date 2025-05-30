package itnow.javaIntermedio.claseDos;

public class ControRemotoUniversal {
	
	private Operable operable;
	
	public ControRemotoUniversal(Operable operable) {
		this.operable = operable;
	}
	
	public void probarDispositivo() {
		this.operable.encender();
		this.operable.apagar();
		this.operable.encender();
		this.operable.apagar();
	}
}
