package com.example.ProfileDemo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.profileDemo.services.HelloService;

@SpringBootTest
@ActiveProfiles("test")
public class ServiceTests {
	@Autowired
	private HelloService service;
	
	@Test
	void contextLoads() {
		assertEquals("Hola ambiente testing", service.saludar());
	}
}
