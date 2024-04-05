package com.example.profileDemo.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("test")
public class HelloMockService implements HelloService {
	
	public String saludar() {
		// TODO Auto-generated method stub
		return "Hola ambiente testing";
	}
}
