package com.indra.clasediecideis.controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.indra.clasediecideis.models.*;
import com.indra.clasediecideis.services.*;

import java.util.*;

@RestController
public class CancionController {

	@Autowired
	private ICancionService service;
	
	@GetMapping("/api/cancion")
	public ResponseEntity<List<Cancion>> getAll(){
		return new ResponseEntity<List<Cancion>>(this.service.getAll(), HttpStatus.OK);
	}
	
	@GetMapping("/api/cancion/{id}")
	public ResponseEntity<Object> getById(@PathVariable(name="id")int id){
		try {
			return new ResponseEntity<Object>(this.service.getById(id), HttpStatus.OK);
		}catch(Exception exception) {
			return new ResponseEntity<Object>(exception.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/api/cancion")
	public ResponseEntity<Object> add(@RequestBody() Cancion cancion){
		try {
			return new ResponseEntity<Object>(this.service.add(cancion), HttpStatus.OK);
		}catch(Exception exception) {
			return new ResponseEntity<Object>(exception.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	
}
