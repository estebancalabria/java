package indra.talentCamp.reflection.models;

public class SuperHeroe {
	
	private String nombre;
    private String habilidad;
	private int alturaCm;
	private double peso;
	
	public SuperHeroe() {  //NEcesito el constructor vacio
		super();
	}
	
	public SuperHeroe(String nombre, String habilidad, int alturaCm, double peso) {
		super();
		this.nombre = nombre;
		this.habilidad = habilidad;
		this.alturaCm = alturaCm;
		this.peso = peso;
	}

	public String getHabilidad() {
		return habilidad;
	}

	public void setHabilidad(String habilidad) {
		this.habilidad = habilidad;
	}

	public int getAlturaCm() {
		return alturaCm;
	}

	public void setAlturaCm(int alturaCm) {
		this.alturaCm = alturaCm;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public String getNombre() {
		return nombre;
	}
	
	public void decirSuFrase() {
		System.out.println("El bien vencera siempre!");
	}
	
	public void hacerObrasDeBien() {
		System.out.println("Ayudando a los desposeidos");
	}
	
	public void salvarElMundo() {
		System.out.println("Un dia de trabajo mas salvando el mundo....");
	}
	
}
