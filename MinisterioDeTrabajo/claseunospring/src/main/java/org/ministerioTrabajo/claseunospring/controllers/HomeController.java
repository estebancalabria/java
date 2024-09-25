package org.ministerioTrabajo.claseunospring.controllers;

import java.util.*;

import org.ministerioTrabajo.claseunospring.models.Persona;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/*import org.springframework.web.bind.annotation.*;*/

@RestController
public class HomeController {

	@GetMapping("/")
	public String holaMundo() {
		return "Hola Mundo";
	}

	@GetMapping("/html")
	public String getEjemploHtml() {
		// DISCLAIMER : ESTO ES UNA PRUEBA
		// NADIE EN SU SANO JUICIO TRABAJA CON HTML ASI
		return """
				<html>
					<head>
					  <title>Curso Spring Boot</title>
					  <style>
					     h1{
					        text-align:center;
					     }
					  </style>
					</head>
					<body>
						<h1>Hola Mundo HTML</h1>
					</body>
				</html>
				""";
	}

	@GetMapping("/demo-persona")
	public Persona getPersona() {
		Persona persona = new Persona("Esteban", 43);
		return persona;
		// return new Persona("Esteban", 43);
	}

	@GetMapping("/demo-personas")
	public List<Persona> getPersonas() {
		List<Persona> lista = new ArrayList<Persona>();

		Persona esteban = new Persona("Esteban", 43);
		lista.add(esteban);

		Persona diego = new Persona("Diego", 32);
		lista.add(diego);

		Persona ivan = new Persona("Ivan", 29);
		lista.add(ivan);

		return lista;
		// return new Persona("Esteban", 43);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/saludar")
	public String saludarConQueryString(@RequestParam(defaultValue = "Sr.") String prefijo, @RequestParam String nombre,
			@RequestParam(name = "segundo") Optional<String> segundoNombre,
			@RequestParam(required = false) String apellido) {

		// Version 1 : Naive: Concatenando Strings
		/*
		 * String saludo = "Hola " + prefijo + " " + nombre;
		 * 
		 * if (segundoNombre.isPresent()) { saludo += " " + segundoNombre.get(); }
		 * 
		 * if (apellido != null) { saludo += " " + apellido; }
		 * 
		 * return saludo;
		 */

		// Version 2 : Con String Builder
		StringBuilder saludo = new StringBuilder();
		saludo.append("Hola");
		saludo.append(" ").append(prefijo);
		saludo.append(" ").append(nombre);
		segundoNombre.ifPresent(segundo -> saludo.append(" ").append(segundo)); // Expresion Lambda
		Optional.of(apellido).ifPresent(a -> saludo.append(" ").append(a));

		return saludo.toString();
	}
	
	@GetMapping(value="/longitud/{texto}", produces = "text/plain")
	public String longitud(@PathVariable String texto) {
		//Version 1 : Gracias Diego!
		//return String.format("%d", texto.length());
		
		//Version 2: La que conocia el profe
		return String.valueOf(texto.length());
	}
	
	@PostMapping("/echo")
	public String echo(@RequestBody String mensaje) {
		return mensaje;
	}
	
	@PostMapping("/recibir")
	public String recibirDatos(@RequestBody Persona persona) {
		
		System.out.println("Recibi Datos " + persona);
		
		return "Datos Recibidos";
	}
	
	
	
	

}
