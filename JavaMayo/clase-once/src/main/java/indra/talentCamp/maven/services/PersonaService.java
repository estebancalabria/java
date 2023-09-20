package indra.talentCamp.maven.services;

import java.util.List;

import indra.talentCamp.maven.models.Persona;
import indra.talentCamp.maven.persistence.PersonaRepository;

//Este lo hago en spanish para diferenciarlo
public class PersonaService {

	private PersonaRepository repository;
	
	public PersonaService() {
		this.repository = new PersonaRepository();
	}
	
    public List<Persona> recuperarPersonas(){
    	List<Persona> personas = this.repository.findAll();
    	
    	//Aqui programo los requerimientos tecnicos
    	//Puedo eliminar las personas que el usuario actual no tiene tiene permiso de ver
    	//Puedo sacar las personas que no estan activas
    	//Puedo hacer validaciones si tengo permisos para recuperar las personas
    	//Puedo guardar un mensaje de log de que es lo que esta pasando...
    	
    	
    	return personas;    	
    }
    
    public void registrarPersonaNueva(Persona p) {
    	
    	//Valido que la persona cunpla las reglas de negocio
    	//Delego la validacion al modelo o en ultima instancia valido aqui
    	//Ejemplo
    	if (p.getNombre()==null) {
    		//throw new ValidationException("Falta el nombre");
    	}
    	
    	this.repository.save(p);
    }
}
