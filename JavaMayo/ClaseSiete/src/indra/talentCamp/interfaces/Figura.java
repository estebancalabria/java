package indra.talentCamp.interfaces;

public abstract class Figura implements AreaCalculable, Perimetro, Comparable<Figura> {
	public abstract double calcularArea();
	public abstract double calcularPerimetro();
	
	@Override
	public int compareTo(Figura o) {
		return (int)this.calcularArea() - (int)o.calcularArea();
	}
}
