package itnow.javaintermedio.clasecuatro.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//Aunque no lo explicite todos los objetos de java heredan directo o indirectamente de la clase Object
public class Ticket extends ObjetoDeNegocio {

	//private static int ULTIMO_ID = 1;  //Una variable compartida por todas las instancias de Ticket...
	
	//Representacion interna del objeto
	private Integer id = null;    //De Donde Viene ahora este id?
	private LocalDateTime fechaDeCreacion;
	private String descripcion;
	private EstadoTicket estado;
	

	//Otros campos
	//Para creacion de tickets a mano dentro del programa
	public Ticket(String descripcion) {
		super();
		this.descripcion = descripcion;
		
		//ID autoincrementable <<<<
		//Estado con valor nuevo por defecto
		//Fecha de creacion la inicializa con la fecha de ahora
		this.estado = EstadoTicket.NUEVO;
		this.fechaDeCreacion = LocalDateTime.now();
		//this.id = Ticket.ULTIMO_ID;
		//Ticket.ULTIMO_ID++;
	}

	//Vamos a suponer que tenemos que crear tickets que ya estan guardados en la base de datos
	//Que ya tienen id, estado y fechaDeCreacion. 
	//Para eso voy a agregar un constructor nuevo
	//Para crear tickets que ya tengo con todos los datos en la base de datos

	public Ticket(int id, LocalDateTime fechaDeCreacion, String descripcion, EstadoTicket estado) {
		super();
		this.id = id;
		this.fechaDeCreacion = fechaDeCreacion;
		this.descripcion = descripcion;
		this.estado = estado;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void asignarIdDesdeBase(Integer idBase) {
		this.id = idBase;
	}

	public LocalDateTime getFechaDeCreacion() {
		return fechaDeCreacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public EstadoTicket getEstado() {
		return estado;
	}

	public void detallarDescripcion(String masDescripcion) {
		this.descripcion += masDescripcion;
	}
	
	//Polimorfismo. Sobrescribo (reemplazo) un metodo que ya existe y lo extiendo o le doy mi propia implementacion
	@Override
	public String toString() {
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		return this.id + "-" + this.fechaDeCreacion.format(formatter) + "-(" + this.estado  +")-" + this.descripcion;
	}
}
