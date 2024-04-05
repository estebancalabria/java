package com.example.profileDemo.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class HelloProductionService implements HelloService {

	@Override
	public String saludar() {
		// TODO Auto-generated method stub
		return "Hola ambiente productivo";
	}

}
