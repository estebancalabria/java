package com.example.profileDemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.profileDemo.services.HelloService;

@RestController
public class HelloController {
	
	@Autowired
	private HelloService service;
	
	@GetMapping("/")
	public String hello() {
		return this.service.saludar();
	}

}
