package org.indra.ClaseCatorce.controllers;

import java.util.*;

import org.springframework.web.bind.annotation.*;

@RestController
public class HolaMundoController {
	
	@RequestMapping(value="/api/HolaMundo", method = RequestMethod.GET)
	public String miPrimeraAccion() {
		return "Hola Mundo Rest";
	}
	
	@RequestMapping(value="/api/Saludo/{nombre}", method = RequestMethod.GET)
	public String saludar(@PathVariable("nombre")String nombre) {
		return "Hola " + nombre;
	}
	
	@RequestMapping(value="/api/SaludoConQuery", method = RequestMethod.GET)
	public String saludarConQuery(@RequestParam("nombre")String nombre, @RequestParam("apellido")String apellido) {
		return "<h1>"+"Hola " + nombre+ " " + apellido+"</h1>";
	}
	
	@RequestMapping(value="/api/SaludoOpcional", method= RequestMethod.GET)
	public String saludarOpcional(@RequestParam(name = "nombre", required=false)String nombre) {
		if (nombre==null) {
			return "Hola, como te llamaS?";
		}
		return "Hola " + nombre;
	}
	
	@GetMapping("/api/OtroSaludo")
	public String otroSaludoMas(@RequestParam() String nombre) {
		return "Hola te saludo de nuevo "+nombre;
	}
	
	@GetMapping("/api/OtroSaludoOpcional")
	public String otroSaludoOpcional(@RequestParam() Optional<String> nombreOpcional) {
		//return "Hola "+nombreOpcional.orElse("Sin Nombre");
		return "Hola "+nombreOpcional.orElseGet(()-> "Sin Nombre");
	}
	
	@GetMapping("/api/SaludoDefault")
	public String saludoPorDefecto(@RequestParam(defaultValue = "Sin Nombre")String nombre) {
		return String.format("<h1>Hola %s</h1>", nombre);
	}
	
	@RequestMapping(value="/api/sumar", method = RequestMethod.GET)
	public int sumar(@RequestParam("num1")int a, @RequestParam("num2")int b) {
		return a+b;
	}
	
	@GetMapping("/api/ListarQuery")
	public String listarQueryString(@RequestParam()Map<String, String> query) {
		//Ejemplo de invocacion: http://localhost:8080/api/ListarQuery?a=1&b=2&nombre=Esteban&leguaje=java
		StringBuilder sb = new StringBuilder();
		sb.append("<ul>");
		sb.append("<li>Mis Parametros del QueryString son:</li>");
		query.keySet().forEach( key -> { sb.append("<li>" + key + ":" + query.get(key)+"</li>");});
		sb.append("</ul>");
		return sb.toString();
	}
	
	@GetMapping("/api/MultiParam")
	public String parametrosMultiples(@RequestParam(name="p")List<String> params) {
		//http://localhost:8080/api/MultiParam?p=1&p=4&p=hola&p=5
		//http://localhost:8080/api/MultiParam?p=1,2,3,4,5,6
		StringBuilder sb = new StringBuilder();
		sb.append("<ol>");
		params.forEach(param ->{ sb.append("<li>"+ param +"</li>");});
		sb.append("</ol>");
		return sb.toString();
	}
	
}
