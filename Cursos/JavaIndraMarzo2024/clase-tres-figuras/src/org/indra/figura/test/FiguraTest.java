package org.indra.figura.test;

import static org.junit.jupiter.api.Assertions.*;

import org.indra.figura.*;
import org.junit.jupiter.api.Test;

class FiguraTest {

	@Test
	void testRectangulo() {
		Rectangulo rectangulo = new Rectangulo(2,2);
		assertEquals(4, rectangulo.calcularArea());
		assertEquals(8, rectangulo.calcularPerimetro());
	}
	
    @Test
    void testTrianguloRectangulo() {
        TrianguloRectangulo trianguloRectangulo = new TrianguloRectangulo(3, 3, 3.0, 3.0);
        assertEquals(4.5, trianguloRectangulo.calcularArea());
        //assertEquals(10.242640687119284, trianguloRectangulo.calcularPerimetro());
        assertEquals(10.24, trianguloRectangulo.calcularPerimetro(),0.01);
    }
    
    @Test
    void testCirculo() {
        Circulo circ = new Circulo(3, 3, 3);
        assertEquals(9 * Math.PI, circ.calcularArea());
        assertEquals(6 * Math.PI, circ.calcularPerimetro());
    }

}
