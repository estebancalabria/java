package allianz.claseTres.models;
import java.time.LocalDate;

public class Persona {	
	private int documento;
	private String nombre;
	//public int edad;
	private LocalDate fechaDeNacimiento;
	private Sexo sexo;
	

	//Aca tenemos al constructor
	public Persona(int documento, String nombre, LocalDate nacimiento, Sexo sexo) {
		
		//El this es importante para reconocer cual es "atributo" y cual es el "parametro"
		//this.documento = documento;
		//this.nombre = nombre;
		//this.fechaDeNacimiento = nacimiento;
		
		//Otra opcion que algunos conocen como "doble encapsulamiento"
		//this.setDocumento(documento);
		//this.setNombre(nombre);
		//this.setFechaDeNacimiento(nacimiento);
		
		//Otra opcion que algunos conocen como "doble encapsulamiento
		this.documento = documento;
		this.setNombre(nombre);
		this.setFechaDeNacimiento(nacimiento);
		this.setSexo(sexo);
	}
	
	public Persona(int documento, String nombre, LocalDate nacimiento) {
		this(documento, nombre, nacimiento, Sexo.NO_DEFINIDO);
	}
			
	//Una sobrecarga del constructor que pome la fecha actual como nacimiento por defecto
	public Persona(int documento, String nombre) {
		this(documento, nombre, LocalDate.now());  //Con el this llamo al otro
	}
	
	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
	
	//setter
	//Me pregunto. Es posible que una persona cambie de nombre ==> Decidimos que es posible, no es descabellado
	public void setNombre(String value) {
		//Por ejemplo validamos los datos para protegernos
		if (value.length()==1) {
			throw new Error("El nombre de la persona es demasiado corto");
		}
		
		//this.nombre = value
		//Sacar los espacios al principio y al final
		this.nombre = value.trim();
	}
	
	//getter 
	public String getNombre() {
		//Transformamos los datos a como los queremos presentar
		
		//return this.nombre.toUpperCase();
		return this.nombre.substring(0, 1).toUpperCase() + this.nombre.substring(1).toLowerCase();
	}

	
	public int getDocumento() {
		return documento;
	}

	//Puede cambiar de documento? ===>  Decidimos que no, no se cambia
	//Comentamos entonces el setter, 
	//para no darle la posibilidad al que usa esta clase para que lo cambie porque estaria mal
	/*public void setDocumento(int documento) {
		this.documento = documento;
	}*/

	//Tiene solo un getter
	public LocalDate getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}

	public void setFechaDeNacimiento(LocalDate fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}
	
	//Campos calculados. 
	//Los getters booleanos en general en vez de llamarse get...  se pueden llamar is...
	public boolean isBirthdayToday() {
		return (LocalDate.now().getMonth() == this.getFechaDeNacimiento().getMonth()) 
				&& (LocalDate.now().getDayOfMonth()== this.getFechaDeNacimiento().getDayOfMonth());
	}

}


//
//El camino al encapsulamiento
//1. Convertimos los atributos en privado
//2. Agregamos getters y setters

// (Pseudo)Definiciones
//   Todo lo publico se corresponde a la "interfaz" del objeto
//   Todo lo privado se corresponde con el "estado interno/representacion interna del objeto"
//   atributo(private) + setter + getter = property
//   atributo que solo tiene getter y no tiene setter = readonly property
//   atributo que solo tiene setter y no tiene getter = writeonly property (no es tan comun)

// Encapsulamiento
// Objetivos/Ventajas
//     1. Proteger los datos para que sean consistente (Almacenar solo y de la forma que queremos)
//        (Por ejemplo Validar los datos con setters)
//     2. Controlar la manera en que le presentamos los datos al usuario
//     3. Busca que se pueda modificar libremebte el "esta interno" del objeto y siempre y cuando
//        no cambiemos la interfaz, el programa siga funcionando igual
//     4. Productividad: se pueden genera automaticamente con el eclipse
// Desventajas
//     1.Me queda codigo boilerplate (bastante repetitivo) cuando no hago nada de lo anterior
//
// Constructores
// Objetivos/Ventajas
//     1.Permite crear objetos consistentes (con todos los datos y sin datos invalidos)
//     2.Documenta al que usa la clase cuales son los datos que debe proporcionar para crear un objeto
//     3. Productividad: se pueden genera automaticamente con el eclipse
// Desventajas
//     1.Cuando tenemos muchos atributos se hace un poco largo (constructores telescopicos)
//     2.Se dificulta en algunas casos crear objetos de forma generica delegando los datos necesarios
//     3.Se nos puede olvidar repetir validaciones (por eso conviene llamar al seeter (doble encapsulamiento))