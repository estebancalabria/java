package org.indra.claseTrece.controllers;

import java.util.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaludoController {
	
	@GetMapping("/saludo/holaMundo")
	public String holaMundo() {
		return "Hola mundo";
	}
	
	//http://localhost:5000/saludo/saludarV1?nombre=Jiten
	@GetMapping("/saludo/saludarV1")
	public String saludar(@RequestParam("nombre") String nombre) {
		return "Hola " + nombre;
	}
	
	//http://localhost:5000/saludo/saludarV2?nombre=Jiten
	@GetMapping("/saludo/saludarV2")
	public String saludar2(@RequestParam() String nombre) {
		return "Hola " + nombre;
	}
	
	//http://localhost:5000/saludo/saludarV3?nombre=Jiten
	//http://localhost:5000/saludo/saludarV3
	@GetMapping("/saludo/saludarV3")
	public String saludar3(@RequestParam(required = false) String nombre) {
		
		if (nombre != null) {
			return "Hola " + nombre;
		}
		return "Hola desconocido";
	}
	
	//http://localhost:5000/saludo/saludarV4?nombre=Jiten
	//http://localhost:5000/saludo/saludarV4
	@GetMapping("/saludo/saludarV4")
	public String saludar4(@RequestParam() Optional<String> nombre) {
		return "Hola " + nombre.orElse("desconocido");
	}
	
	//http://localhost:5000/saludo/saludarV5?nombre=Jiten
	//http://localhost:5000/saludo/saludarV5
	@GetMapping("/saludo/saludarV5")
	public String saludar5(@RequestParam(defaultValue = "desconocido") String nombre) {
		return "Hola " + nombre;
	}
	
	//http://localhost:5000/saludo/saludarV6?nombres=Ruben,Jitten,Orejo
	@GetMapping("/saludo/saludarV6")
	public String saludar6(@RequestParam() List<String> nombres) {
		StringBuilder sb = new StringBuilder();
		sb.append("Hola ");
		sb.append("<ol>");
		nombres.forEach(n -> sb.append("<li>" + n + "</li>"));
		sb.append("</ol>");
		
		return sb.toString();
	}
	
	//http://localhost:5000/saludo/saludarV7?nombre=Esteban&apellido=Calabria
	@GetMapping("/saludo/saludarV7")
	public String saludar7(@RequestParam() Map<String,String> query) {
		StringBuilder sb = new StringBuilder();
		sb.append("Mis Parametros ");
		sb.append("<ul>");
		query.keySet().forEach(key -> sb.append("<li>" + key + "=" + query.get(key)+ "</li>"));
		sb.append("</ul>");
		
		return sb.toString();
	}
	
	@GetMapping("/saludo/saludarV8/{nombre}")
	//public String saludar8(@PathVariable() String nombre) {
	public String saludar8(@PathVariable("nombre") String nombre) {
		return "Hola " + nombre;
	}
	
}
