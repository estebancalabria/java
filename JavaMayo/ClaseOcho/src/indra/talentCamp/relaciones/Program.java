package indra.talentCamp.relaciones;

import indra.talentCamp.banco.*;
import indra.talentCamp.relaciones.composite.*;
import org.mozilla.javascript.*;

public class Program {

	public static void main(String[] args) {
	
		
		//Vamos a realizar el siguiente Calculo
		// 2+2*3
		Operacion calculo = new Suma(
				new Valor(2),
				new Multiplicacion(new Valor(2), new Valor(3)));
		
		System.out.println("2+2*3=" + calculo.calcular());
		
		// 6*5+3*8
        Operacion calculo2 = new Suma(
                new Multiplicacion(new Valor(6), new Valor(5)),
                new Multiplicacion(new Valor(3), new Valor(8)));
        System.out.println("6*5+3*8=" + calculo2.calcular());
        
        //5+3*8+4*3
        var calculo3 = new Suma(5,
                new Suma(
                    new Multiplicacion(3, 8),
                    new Multiplicacion(4, 3)
                ));
		System.out.println("5+3*8+4*3=" + calculo3.calcular());
        
		String expresion = "5+3*8+4*3";
		
		//Esto no funciona mas en la version actual de java
		//El profesor esta viejito...
		/*ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("Javascript");
		System.out.println(engine);
		try {
			var result = engine.eval(expresion);
			System.out.println("El resultado es"+ result);
		}catch(Exception ex) {
			System.out.println("Error al ejecutar la expresion " + expresion + ex.getMessage());		
		}*/
		
		//Ahora si...
		Context jsContext = Context.enter();
		try {
			var res = jsContext.evaluateString( jsContext.initSafeStandardObjects(), 
					expresion, 
					"Calculo", 
					1, null);
			System.out.println("El resultado es");
			System.out.println(expresion +"="+ res);
		} finally {
			Context.exit();
		}
		
		//Ejemplo de como usar colecciones que dejen de poder modificarse en algun momento
		/*List<String> nombres = new ArrayList<>(); 
				//Arrays.asList("Juan","Pedro","Ramiro");
		nombres.add("Juan");
		nombres.add("Esteban");
		
		nombres = Collections.unmodifiableList(nombres);
		nombres.add("Otro mas");*/
		
		/*CuentaBancaria c = new CajaDeAhorro(new Cliente());
		c.getMovimientos().add(new Movimiento(-1000)); //Estoy permitiendo modificar la coleccion de movimientos desde afuera
		*/
		
	}

}
