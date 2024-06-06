package org.minsterioEconomia.claseTres.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class CondicionalesTests {

	@Test
	void testAnd() {
		int edad = 50;
		boolean estaDentroDelRango; 

		if ((edad > 0) && (edad < 100)) {
			estaDentroDelRango = true;
		} else {
			estaDentroDelRango = false;
		} /* if */	
		
		assertEquals(true, estaDentroDelRango);
		assertTrue(estaDentroDelRango);
		
		edad = 1232323;
		if ((edad > 0) && (edad < 100)) {
			estaDentroDelRango = true;
		} else {
			estaDentroDelRango = false;
		} /* if */	

		assertEquals(false, estaDentroDelRango);
		assertFalse(estaDentroDelRango);
		
	}/* metodo testAnd */
} /* class */
