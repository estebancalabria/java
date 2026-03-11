package indra.talentCamp.banco;

public class Cliente {
	
	private int identificador;
	private String nombre;
    private String apellido;
    
	public Cliente(int identificador, String nombre, String apellido) {
		super();
		this.identificador = identificador;
		this.nombre = nombre;
		this.apellido = apellido;
	}
    
	public int getIdentificador() {
		return identificador;
	}
	public String getNombre() {
		return nombre;
	}
	public String getApellido() {
		return apellido;
	}
}
