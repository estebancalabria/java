package org.ministerioEconomia.claseTres;

public class Edad {
	public static final int RANGO_MINIMO = 0;
	public static final int RANGO_MAXIMO = 100;
	public static final int MAYORIA_DE_EDAD = 18;
	public static final int JUBILACION = 70;
	
	public static boolean estaDentroDelRango(int edad) {
		//Pecado capital 3 : Uso excesivo de IFs
		/*if ((edad > Edad.RANGO_MINIMO) && (edad < Edad.RANGO_MAXIMO)) {
			return true;
		} else {
			return false;
		} /* if */
		
		return ((edad > Edad.RANGO_MINIMO) && (edad < Edad.RANGO_MAXIMO));
	}
}
