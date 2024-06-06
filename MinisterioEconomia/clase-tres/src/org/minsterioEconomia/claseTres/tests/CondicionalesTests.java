package org.minsterioEconomia.claseTres.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.ministerioEconomia.claseTres.Edad;

class CondicionalesTests {

	@Test
	void testAnd() {
		int edad = 50;
		
		//Pecado capital 3 : Exceso de if
		/*boolean estaDentroDelRango; 

		
		if ((edad > Edad.RANGO_MINIMO) && (edad < Edad.RANGO_MAXIMO)) {
			estaDentroDelRango = true;
		} else {
			estaDentroDelRango = false;
		} /* if */	
		
		assertEquals(true, Edad.estaDentroDelRango(edad));
		assertTrue(Edad.estaDentroDelRango(edad)); //Mejor escribirlo asi
		
		edad = 1232323;
		//Pecado capital 3 : Exceso de if
		/*if ((edad > Edad.RANGO_MINIMO) && (edad < Edad.RANGO_MAXIMO)) {
			estaDentroDelRango = true;
		} else {
			estaDentroDelRango = false;
		} /* if */	

		assertEquals(false, Edad.estaDentroDelRango(edad));
		assertFalse(Edad.estaDentroDelRango(edad));
		
	}/* metodo testAnd */
} /* class */
