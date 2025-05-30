package itnow.javaIntermedio.claseUno;

public class Persona {
	private String nombre;
	private String apellido;
	
	public Persona(String nombre, String apellido) {
		this.nombre = nombre;
		this.apellido = apellido;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String getApellido() {
		return this.apellido;
	}
	
	//Si le comento el setter no puedo cambiar el nombre una vez que el objeto esta creado
	public void setNombre(String nombre) throws Exception{
		
		if ((nombre==null) || (nombre.length()<3)) {
			//Excepcion no tipada
			//throw new Error("Nombre demasiado corto...");
			
			//Excepciones tipadas, obligo a atraparla en caso de que ocurra
			throw new Exception("Nombre demasiado corto...");
		}
		
		this.nombre = nombre;
	}
	
	/*public void setApellido(String apellido) {
		this.apellido = apellido;
	}*/

	public String getNombreCompleto( ) {
		return this.apellido.toUpperCase() + ", " + this.getNombre();
	}
}