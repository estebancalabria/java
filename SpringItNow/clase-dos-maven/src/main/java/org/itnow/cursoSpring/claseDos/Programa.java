package org.itnow.cursoSpring.claseDos;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Programa {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context =  new ClassPathXmlApplicationContext("SpringConfig.xml");
	}

}
