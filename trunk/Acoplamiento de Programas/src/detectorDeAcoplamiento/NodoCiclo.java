package detectorDeAcoplamiento;

public class NodoCiclo {
	
	private int numeroPaquete;
	private int peso;
	private String nombre;
	
	public NodoCiclo(int num,String nombre,int p){
		this.numeroPaquete = num;
		this.peso = p;
		this.nombre = nombre;
	}
	
	public int peso(){
		return peso;
	}

	public int numeroPaquete(){
		return numeroPaquete;
	}

	public String getNombre() {
		return nombre;
	}
}
