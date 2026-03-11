package indra.talentCamp.dependencyInjection.ioc;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class BeanFactoryV2 {
	
	List<Dependency> dependencies = new ArrayList<Dependency>();
	
	public void registerDependency(Class<?> source, String fielfName, Object destination) {
		throw new Error("Not Implemented YET");
	}

	
	public void registerDependency(Class<?> source, String fieldName, Class<?> destination) {
		this.dependencies.add(new Dependency(source, fieldName, destination));
	}
	
	public Object createBean(Class<?> source) {
		try {
			Object result = source.getConstructor().newInstance();
			
			List<Dependency> sourceDepedencies = this.dependencies.stream()
					.filter( d -> d.getSource()==source )
					.toList();
			
			for (Dependency dependency : sourceDepedencies) {
				Object destinationObject = dependency.getDestination().getConstructor().newInstance();
				Field field = source.getDeclaredField(dependency.getFieldName());
				field.setAccessible(true);
				field.set(result, destinationObject);
			}
			
			return result;
		} catch (Exception e) {
			throw new Error(e.getMessage());
		} 
 	}

}
