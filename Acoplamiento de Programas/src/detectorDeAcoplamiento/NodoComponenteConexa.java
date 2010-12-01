package detectorDeAcoplamiento;

public class NodoComponenteConexa {
	
	private int numeroPaquete;
	private int peso;
	
	public NodoComponenteConexa(int num,int p){
		this.numeroPaquete = num;
		this.peso = p;
	}
	
	public int peso(){
		return peso;
	}

	public int numeroPaquete(){
		return numeroPaquete;
	}
}
