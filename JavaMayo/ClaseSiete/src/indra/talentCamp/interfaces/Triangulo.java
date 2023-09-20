package indra.talentCamp.interfaces;

public class Triangulo extends Figura{

    private double base;
    private double altura;
    private double lado1;
    private double lado2;
    
    public Triangulo(double base, double altura, double lado1, double lado2) {
        this.base = base;
        this.altura = altura;
        this.lado1 = lado1;
        this.lado2 = lado2;
    }
    
    public double getBase() {
        return base;
    }
    
    public double getAltura() {
        return altura;
    }
    
    public double getLado1() {
        return lado1;
    }
    
    public double getLado2() {
        return lado2;
    }
        
    @Override
    public double calcularArea() {
        return (this.getBase() * this.getAltura()) / 2;
    }
    
    @Override
    public double calcularPerimetro() {
        return this.getLado1() + lado2 + base;
    }
	
}
