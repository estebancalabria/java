package indra.talentCamp.banco;

public class Cliente {
	
	//Imaginemos que tiene muchos mas atributos para el caso...
	private int identificador;
	private String nombre;
    private String apellido;
    
	/*public Cliente(int identificador, String nombre, String apellido) {
		super();
		this.identificador = identificador;
		this.nombre = nombre;
		this.apellido = apellido;
	}*/
    
    //Hice el constructor privado por lo que solo el builder puede crear ahora un cliente 
    private Cliente() {
    	super();
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
	
	//InnerClass
	public static class ClienteBuilder{
		private int identificador = 0;
		private String nombre = null;
	    private String apellido = null;
	    
	    //Setters con Fluent Interfaces
		public ClienteBuilder setIdentificador(int identificador) {
			this.identificador = identificador;
			return this;
		}
		public ClienteBuilder setNombre(String nombre) {
			this.nombre = nombre;
			return this;
		}
		public ClienteBuilder setApellido(String apellido) {
			this.apellido = apellido;
			return this;
		}
		
		public Cliente build() {
			//Que tengo que asegurarme para construir un objeto cliente consistente
			//Deffensive Programing
			assert this.nombre != null;
			assert this.apellido != null;
			assert this.identificador>0; //Si no se cumple la condicion se hace un throw de AssertionError  
			//Me gusto la validacion que no se repita el identificador! Esa queda pendiente!
			
			Cliente c = new Cliente();
			c.identificador = this.identificador;
			c.nombre = this.nombre;
			c.apellido = this.apellido;
			return c;
		}
	}

	
	//Dentro del cliente
	public static ClienteBuilder builder() {
		return new ClienteBuilder();
	}
}
