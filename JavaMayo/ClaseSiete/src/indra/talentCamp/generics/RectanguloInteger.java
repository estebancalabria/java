package indra.talentCamp.generics;

public class RectanguloInteger extends FiguraGenerica<Integer> {

	private int base;
	private int altura;

	public RectanguloInteger(int base, int altura) {
		super();
		this.base = base;
		this.altura = altura;
	}

	public int getBase() {
		return base;
	}

	public int getAltura() {
		return altura;
	}
	
	@Override
	public Integer calcularArea() {
		return this.base*this.altura;
	}

	@Override
	public Integer calcularPerimetro() {
		// TODO Auto-generated method stub
		return 2* this.base +  2* this.altura;
	}
}
