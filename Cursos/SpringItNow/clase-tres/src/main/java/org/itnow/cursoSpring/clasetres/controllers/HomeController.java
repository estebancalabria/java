package org.itnow.cursoSpring.clasetres.controllers;

import java.io.IOException;
import java.util.*;

import javax.net.ssl.SSLEngineResult.Status;

import org.apache.juli.logging.*;
import org.itnow.cursoSpring.clasetres.models.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.*;



@RestController
public class HomeController {

	private Log logger = LogFactory.getLog(HomeController.class);

	@GetMapping("/")
	public String index() {
		logger.info("Se llama al index");
		return "Hola Mundo";
	}

	// @GetMapping("/html")
	@RequestMapping(value = "/html", method = RequestMethod.GET)
	public String html() {
		return "<h1>Hola Mundo con Html</h1>";
	}

	@GetMapping("/json")
	public Object jsonDemo() {
		Automovil auto = new Automovil();
		auto.setMarca("Fiat");
		auto.setModelo("600");
		auto.setAño(1959);
		return auto;
	}

	//
	// Request Params
	//

	@RequestMapping(value = "/saludo", method = RequestMethod.GET)
	public String saludo(@RequestParam("nombre") String nombre) {
		return "Hola " + nombre;
	}

	@RequestMapping(value = "/saludito", method = RequestMethod.GET)
	public String saludito(@RequestParam() String nombre) {
		return "Hola " + nombre;
	}

	@RequestMapping(value = "/saludar", method = RequestMethod.GET)
	public String saludar(@RequestParam(name = "nombre", defaultValue = "Mundo") String nombre) {
		return "Hola " + nombre;
	}

	@RequestMapping(value = "/saludaso", method = RequestMethod.GET)
	public String saludaso(@RequestParam() Optional<String> nombre) {
		return "Hola " + nombre.orElse("Mundo");
	}

	@GetMapping("/parametros")
	public String parametros(@RequestParam() Map<String, String> params) {
		StringBuilder sb = new StringBuilder();
		sb.append("<h3>Mis parametros son:</h3>");
		sb.append("<ul>");
		params.forEach((clave, valor) -> {
			sb.append(String.format("<li> %s = %s</li>", clave, valor));
		});
		sb.append("</ul>");
		return sb.toString();
	}

	@GetMapping("/nombres")
	public String nombres(@RequestParam("nombre") List<String> params) {
		StringBuilder sb = new StringBuilder();
		sb.append("<h3>Mis nombres son:</h3>");
		sb.append("<ul>");
		params.forEach((valor) -> {
			sb.append(String.format("<li> %s </li>", valor));
		});
		sb.append("</ul>");
		return sb.toString();
	}

	//
	// PathVariable
	//

	@RequestMapping(value = "/saludon/{nombre}", method = RequestMethod.GET)
	public String saludon(@PathVariable("nombre") String nombre) {
		return "<h1>Hola " + nombre.toUpperCase() + "</h1>";
	}

	// Headers

	@GetMapping("/headers")
	public String headers(@RequestHeader() Map<String, String> headers) {
		StringBuilder sb = new StringBuilder();
		sb.append("<h3>Mis parametros son:</h3>");
		sb.append("<ul>");
		headers.forEach((clave, valor) -> {
			sb.append(String.format("<li> %s = %s</li>", clave, valor));
		});
		sb.append("</ul>");
		return sb.toString();
	}

	//
	// Post
	//

	@PostMapping("/saludoPost")
	public String saludoPost(@RequestBody() String nombre) {
		return "Hola " + nombre;
	}

	// curl "http://localhost:3000/json" --header "Content-Type: application/json"
	// --data "{ \"marca\":\"fiat\", \"modelo\":\"500\", \"año\": 1965}"
	@RequestMapping(value = "/json", method = RequestMethod.POST)
	public String jsonPost(@RequestBody() Automovil automovil) {
		return "Manejo un " + automovil;
	}

	//
	// Respuestas del Controller
	//
	@GetMapping("/secret")
	public ResponseEntity<String> secret(@RequestParam("password") String password) {
		if (password.equals("sesamo")) {
			return new ResponseEntity<>("Acceso Concedido", HttpStatus.OK);
		}

		return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
	}

	@GetMapping("/404")
	public ResponseEntity<String> cuatroCeroCuatro() {
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/dividir")
	public ResponseEntity<String> dividir(
			@RequestParam() double numerador,
			@RequestParam() double denominador){
		/*
		if (denominador==0) {
			return ResponseEntity.internalServerError().body("No se puede dividir por 0");
		}
		
		Double resultado = numerador/denominador;
		return new ResponseEntity<String>(resultado.toString(), HttpStatus.OK);*/
		try {
			Double resultado = numerador/denominador;
			return new ResponseEntity<String>(resultado.toString(), HttpStatus.OK);
			
		}catch(Error ex){
			return ResponseEntity.internalServerError().body(ex.getMessage());
		}
		
	}
	
	@GetMapping("/servlet")
	public void servletResponse(HttpServletRequest request, HttpServletResponse response) throws IOException {
		logger.info("Request a " + request.getRequestURI());
		response.setHeader("Mi-Header", "valor");
		response.setStatus(200);
		response.getWriter().println("<h1>Asi se trabajaba con los servlets originalmente</h1>");
	}
	
	//Manejo de errores
	@GetMapping("/errorsin")
	public ResponseEntity<String> error(){
		throw new Error("Este es un error adrede");
	}
	
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Error.class)
	public ResponseEntity<String> erroresController(){
		return ResponseEntity
				.internalServerError()
				.body("Ups... ha ocurrido un error inesperado");
	}

}
