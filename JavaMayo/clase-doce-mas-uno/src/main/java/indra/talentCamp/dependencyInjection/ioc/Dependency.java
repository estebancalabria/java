package indra.talentCamp.dependencyInjection.ioc;

public class Dependency {

	private Class<?> source;
	private String fieldName;
	private Class<?> destination;
	
	public Dependency(Class<?> source, String fieldName, Class<?> destination) {
		super();
		this.source = source;
		this.fieldName = fieldName;
		this.destination = destination;
	}
	
	public Class<?> getSource() {
		return source;
	}
	public void setSource(Class<?> source) {
		this.source = source;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public Class<?> getDestination() {
		return destination;
	}
	public void setDestination(Class<?> destination) {
		this.destination = destination;
	}
}
