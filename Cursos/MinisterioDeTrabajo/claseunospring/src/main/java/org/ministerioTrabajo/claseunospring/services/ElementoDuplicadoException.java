package org.ministerioTrabajo.claseunospring.services;

//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(value = HttpStatus.CONFLICT)
public class ElementoDuplicadoException extends RuntimeException{
 
      private static final long serialVersionUID = 1L;
 
      public ElementoDuplicadoException(String message) {
             super(message);
      }
}