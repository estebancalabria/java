package indra.talentCamp.maven.controllers;

import java.util.List;

import indra.talentCamp.maven.models.Persona;
import indra.talentCamp.maven.services.*;

public class PersonaController {
     private PersonaService service;
     
     public PersonaController() {
    	 this.service = new PersonaService();
     }
     
     public String mostarTodas() {
    	 List<Persona> personas = this.service.recuperarPersonas();
    	 //Arma una tabla html con la lista de personas 
    	 StringBuilder builder = new StringBuilder();
    	 builder.append("<table>");
    	 //voy creando la tabla
    	 //personas.forEach( ()->  /*...*/ );
    	 builder.append("</table>");
    	 return builder.toString();
     }
}
