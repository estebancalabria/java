package org.indra.clasequince.controllers;

import java.util.*;

import org.springframework.web.bind.annotation.*;

@RestController
public class DemoController extends BaseController {

	private int numero=1;
	
	@GetMapping("/api/contador")
	public int getNumero() {
		this.numero++;
		return this.numero;
	}
	
	@GetMapping("/api/headers")
	public String listarHeaders(@RequestHeader Map<String, String> headers) {
		StringBuilder sb = new StringBuilder();
		sb.append("<ul>");
		
		headers.keySet().forEach( key -> { 
			sb.append(String.format("<li>%s : %s</li>", key, headers.get(key)));
		});
		
		sb.append("</ul>");
		return sb.toString();
	}
	
	@GetMapping("/api/navegador")
	public String readHeader(@RequestHeader("user-agent") String navegador) {
		return navegador;
	}
}
