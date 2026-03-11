package indra.talentCamp.maven;

import java.util.Properties;
import com.google.gson.Gson;

import indra.talentCamp.maven.models.Persona;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hola Mundo Maven");
		
		String json = "{\"nombre\":\"pepe\",\"edad\":23}"; //{"nombre":"pepe","edad":23}
		
		//Mi primer ejemplo usando una libreria externa
		Gson gson = new Gson();
		Properties p = gson.fromJson(json, Properties.class);
		System.out.println("El nombre es :" + p.getProperty("nombre"));
		System.out.println("La edad es :" + p.getProperty("edad"));
		
		//A ver si esto funciona
		System.out.println("-----------------------------------------");
		Persona persona = gson.fromJson(json, Persona.class);
		System.out.println("El nombre es :" + persona.getNombre());
		System.out.println("La edad es :" + persona.getEdad());
	}
	
}
