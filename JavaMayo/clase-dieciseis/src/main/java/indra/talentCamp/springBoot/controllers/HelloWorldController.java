package indra.talentCamp.springBoot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import indra.talentCamp.springBoot.models.Automovil;
import indra.talentCamp.springBoot.services.AutomovilService;

@RestController
public class HelloWorldController {
	
	@Autowired
	private AutomovilService service;

	@RequestMapping(value="/api/hello", method = RequestMethod.GET)
	public String Hello() {
		return "Hola Mundo";
	}
	
	@RequestMapping(value="/api/nicehello", method = RequestMethod.GET)
	public String NiceHello() {
		StringBuilder sb = new StringBuilder();
		sb.append("<html>");
		sb.append("<body>");
		sb.append("<h1>Hola Mundo Spring Boot!</h1>");
		sb.append("</body>");
		sb.append("</html>");
		return sb.toString();
	}
	
	@RequestMapping(value="/api/coche", method = RequestMethod.GET)
	public Automovil GetAuto() {
		return this.service.autoDeElonMuk();
	}
}
