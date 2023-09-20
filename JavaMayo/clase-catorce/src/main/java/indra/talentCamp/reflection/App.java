package indra.talentCamp.reflection;

import java.lang.reflect.*;
import java.util.*;

import javax.lang.model.type.ArrayType;

public class App {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SecurityException {
		System.out.println("Bienvenidos a la clase de Reflection");
		
		try (Scanner in = new Scanner(System.in)){
			Class<?> clazz = null;
			
			//? (Wildcard) en los genericos
			//List<?> lista = new ArrayList<String>();
			//lista.add() //No lo puedo usar porque tengo que saber el tipo
			//lista.clear(); //Este si porque no importa el tipo

			//Busco una clase a partir del nombre con Reflection
			boolean claseEncontrada = false;
			while (!claseEncontrada) {
				System.out.println("Ingrese el nombre completo de la clase para instanciar un objeto de la misma");
				//indra.talentCamp.reflection.models.SuperHeroe
				String className = in.nextLine();	
				try {
					clazz = Class.forName(className);
					claseEncontrada = true;
				} catch (Exception ex) {
					System.out.println("Clase no encontrada. Vuelva a intentar");
				}
			}
			
			//Listo los campos de una clase con reflection
			System.out.println("Los atributos de la clase son:");
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				System.out.format("    %s %s : %s", 
						Modifier.toString(field.getModifiers()),
						field.getName(),
						field.getType().getName());
				System.out.println("");
			}
			
			//Listo los metodos con reflection
			System.out.println("Los metodos son");
			List<Method> metodos = Arrays.asList(clazz.getDeclaredMethods());
			metodos = metodos.stream()
					.filter(m -> !m.getName().startsWith("set") && !m.getName().startsWith("get"))
					.toList();
			for (Method method : metodos) {
				System.out.format("   %s  %s : %s",
						Modifier.toString(method.getModifiers()),
						method.getName(),
						method.getReturnType().getName());
				System.out.println();
			}
			
			//Para instanciar objetos sin el constructor con Reflection
			System.out.println("Instanciando un objeto de ese tipo");
			Object objeto = clazz.getConstructors()[0].newInstance();
			System.out.println("Objetos instanciados");
			
			//Modificamos un atributo con reflection y rompemos el encapsulamiento
			System.out.println("Desea Modificar algun atributo?");
			String modificarAtributo = in.nextLine();			
			while (modificarAtributo.equalsIgnoreCase("S")) {
				boolean atributoEncontrado = false;
				while (!atributoEncontrado) {
					System.out.println("Que atributo desea modificar?");
					String atributo = in.nextLine();
					try {
			
						Field f = clazz.getDeclaredField(atributo);
						System.out.println("Que valor desea ponerle?");
						String valor = in.nextLine();
						f.setAccessible(true);  //Rompo el encapsulamiento con reflection y seteo atributos privado
						f.set(objeto, valor);
						atributoEncontrado = true;
					}catch (Exception ex) {
						System.out.println("Atributo no encotrado/ no se puede setear vuelva a escribir " 
									+ ex.getMessage());
					}
				}	
				System.out.println("Desea Modificar otro atributo?");
				modificarAtributo = in.nextLine();			
			}	
			
			//Listar los atributos con su valores con Reflection 
			System.out.println("Los atributos quedan asi:");
			for (Field field : fields) {
				field.setAccessible(true); //ROMPO EL ENCAPSULAMIENTO
				System.out.format( " %s : %s",
						field.getName(),
						field.get(objeto));
				System.out.println();
			}
			
			//Invoco un metodo con Reflection
			System.out.println("Desea Invocar un metodo?");
			String modificarMetodo = in.nextLine();
			while (modificarMetodo.equalsIgnoreCase("S")) {

				boolean metodoEncontrado = false;
				while (!metodoEncontrado) {
					System.out.println("Que metodo? (ojo sin parametros)");
					String nombreMetodo = in.nextLine();
					try {
						Method metodo = clazz.getDeclaredMethod(nombreMetodo);
						metodo.invoke(objeto);
						metodoEncontrado = true;
					}catch(Exception ex) {
						System.out.println("Metodo no encontrado vuelva a intentar");
					}
				}
				
				System.out.println("Desea Invocar otro metodo?");
				modificarMetodo = in.nextLine();
			}
		}
	}

}
