package org.allianz.hellospringboot.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
	
   //Este va con basic auth
   @GetMapping("/api/login")
   public String GetToken() {
	   return "TOKEN";
   }
}
