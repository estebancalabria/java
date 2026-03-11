package org.ministerioTrabajo.claseunospring.controllers;

import java.net.URI;
import java.util.List;

import org.ministerioTrabajo.claseunospring.dto.AutomovilDto;
import org.ministerioTrabajo.claseunospring.services.AutomovilService;
import org.ministerioTrabajo.claseunospring.services.ElementoDuplicadoException;
import org.ministerioTrabajo.claseunospring.services.NoEncontradoException;
import org.ministerioTrabajo.claseunospring.services.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AutomovilController {
    @Autowired
    private AutomovilService service;
    
    @GetMapping("/automoviles")
    public ResponseEntity<List<AutomovilDto>> getAll() {
        return ResponseEntity.ok(this.service.findAll());
    }
    
    @GetMapping("/automoviles/{id}")
    public ResponseEntity<AutomovilDto> getById(@PathVariable int id) {
        return new ResponseEntity<>(this.service.findById(id), HttpStatus.OK);
    }
    
    @PostMapping("/automoviles")
    public ResponseEntity<Void> agregar(@RequestBody AutomovilDto automovil) {
        this.service.agregar(automovil);
        return ResponseEntity.created(URI.create("/automoviles/" + automovil.getId())).build();
    }

    @DeleteMapping("/automoviles/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        this.service.deleteById(id);
        return ResponseEntity.ok("Automóvil Eliminado Satisfactoriamente");
    }
    
    /*@ExceptionHandler(NoEncontradoException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNoEncontradoException(NoEncontradoException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(ElementoDuplicadoException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ResponseEntity<String> handleElementoDuplicadoException(ElementoDuplicadoException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleValidationException(ValidationException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }*/
}
