package detectorDeAcoplamiento;

public class NodoCiclo {
	
	private int numeroPaquete;
	private int peso;
	
	public NodoCiclo(int num,int p){
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
