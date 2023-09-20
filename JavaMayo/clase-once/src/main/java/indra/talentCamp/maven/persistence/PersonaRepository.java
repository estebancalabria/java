package indra.talentCamp.maven.persistence;

import java.util.*;
import indra.talentCamp.maven.models.*;

public class PersonaRepository {
	
     public List<Persona> findAll(){
    	 //Recupera todas las personas de la base de datos
    	 //return new ArrayList<>();  >>> Me tira error por la version de JAVA!
    	 return new ArrayList<Persona>(); 
     }
     
     //Varios metodos find segun el criterio
     
     public void save(Persona p) {
    	 //Graba la persona en la persistencia, la base de datos, un archivo, lo que sea
    	 //INSERT INTO
     }
     
     public void update(Persona p) {
    	 //Actualizo la persona que ya existe
    	 //UPDATE...
     }
     
     public void delete(Persona p) {
    	 //Borro a la persona
    	 //DELETE FROM
     }
     
     
}
