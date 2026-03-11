package indra.talentCamp.lambda.models;

public class Persona {

	private String nombre;
	private int centimetrosAltura;

	public Persona(String nombre, int centimetrosAltura) {
		super();
		this.nombre = nombre;
		this.setCentimetrosAltura(centimetrosAltura);
	}
	
	public int getCentimetrosAltura() {
		return centimetrosAltura;
	}

	public void setCentimetrosAltura(int centimetrosAltura) {
		
		//Uso Error para no estar obligado a capturar la excepcion o poner thows
		if (centimetrosAltura>300) throw new Error("No se puede medir mas de 300 cm") ;
		if (centimetrosAltura<0) throw new Error("No se puede medir menos que cero");
		//assert(centimetrosAltura<300); //Otra alternativa
		//assert(centimetrosAltura>=0); //Otra alternativa
		
		this.centimetrosAltura = centimetrosAltura;
	}

	public String getNombre() {
		return nombre;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getNombre();
	}
	
}
