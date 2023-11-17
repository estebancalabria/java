package org.allianz.clasecincospringboot.infraestructura;

public class NewLineStringBuilderDecorator {
	
	private StringBuilder sb;

	public NewLineStringBuilderDecorator(StringBuilder stringBuilder) {
		this.sb = stringBuilder;
	}
	
	public void append(String line) {
		this.sb.append(line + "\n");
	}
	
	@Override
	public String toString() {
		return this.sb.toString();
	}
}
