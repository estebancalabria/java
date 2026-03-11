package indra.talentCamp.interfaces;

public class Rectangulo extends Figura{
	
	   private double base;
	   private double altura;
	    
	    public Rectangulo(double base, double altura) {
	        this.base = base;
	        this.altura = altura;
	    }
	    
	    public double getBase() {
	        return base;
	    }
	    
	    public double getAltura() {
	        return altura;
	    }
	    
	    @Override
	    public double calcularArea() {
	        return this.getAltura()*this.getBase();
	    }
	    
	    @Override
	    public double calcularPerimetro() {
	        return 2*(this.getAltura() + this.getBase());
	    }
}
