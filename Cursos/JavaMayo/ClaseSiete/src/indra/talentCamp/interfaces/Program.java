package indra.talentCamp.interfaces;

import java.util.*;
import java.util.stream.Collectors;
import indra.talentCamp.generics.*;


public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        List<AreaCalculable> escenario = Arrays.asList(
        		new Circulo(10.0),
        		new Triangulo(12.0, 15.0, 16.36, 16.36),
        		new Rectangulo(10,10),
        		new Planta());
        
        
        System.out.println("El area total de mi dibujo es...");
        System.out.println("Areas: "+escenario.stream().mapToDouble(figura -> figura.calcularArea()).sum());
        
        //System.out.println("El perimetro total de mi dibujo es..");
        //System.out.println("Perimetro: "+ dibujo.stream().mapToDouble(n->n.calcularPerimetro()).sum());
        
        //Bienvenidos al lado oscuro : Casteos
        
        //Casteo "clasico"
        /*double perimetroTotal = 0.0;
        for (AreaCalculable c : escenario) {
        	//if (c instanceof Perimetro) {
        	if (Perimetro.class.isInstance(c)) {
        		//perimetroTotal +=((Perimetro)c).calcularPerimetro();
        		perimetroTotal += Perimetro.class.cast(c).calcularPerimetro();
        	}
        }*/
        
        //Casteo con expresion lambda
        double perimetroTotal = escenario.stream()
        		.filter(Perimetro.class::isInstance)
        		.map(Perimetro.class::cast).mapToDouble(p -> p.calcularPerimetro()).sum();
        
        System.out.println("Perimetro Total: " +  perimetroTotal);
        

        //Ejemplo de uso de interfaz existente
        List<Figura> figuras = escenario.stream()
        		.filter(Figura.class::isInstance)
        		.map(Figura.class::cast).collect(Collectors.toList());
        //System.out.println(figuras.getClass());
        Collections.sort(figuras);
        figuras.forEach(System.out::println);
        
        //Ejemplo de uso interfaz funcional
        OperacionInteger sumaInt = (a,b)->(a+b);
        int resInt = sumaInt.operar(2, 2);
        System.out.println("Sumar 2+2 es "+ resInt);
        
        Operacion<Integer> suma = (a,b)->(a+b);
        Integer res = suma.operar(2,2);
        System.out.println("Sumar 2+2 es "+ res);
        
        //Ejemplo Caja Fuerte
        
        //Una Caja Fuerte que guarde Strings
        CajaFuerte<String, String> claveTexto = new CajaFuerte<String, String>("Abrete Sesamo");
        claveTexto.guardarValor("La clave del home banking");
        String claveOk = claveTexto.leerValor("Abrete Sesamo");
        System.out.println("La clave es: " + claveOk);
        
        System.out.println("Intentando acceder con una clave invalida");
        try {
        	String claveMal = claveTexto.leerValor("dsdsdsds");
        }catch(Exception ex) {
        	System.err.println(ex.getMessage());
        }
        
        //Una caja fuerte que guarde Integers
        CajaFuerte<Integer, String> claveNumero = new CajaFuerte<Integer, String>("Abrete Sesamo");
        claveNumero.guardarValor(566);
        Integer claveNumOk = claveNumero.leerValor("Abrete Sesamo");
        System.out.println("La clave es: " + claveNumOk);
        System.out.println("Intentando acceder con una clave invalida");
        try{
            Integer claveNumMal = claveNumero.leerValor("Aassdfasdfasdf");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
	}

}
