package detectorDeAcoplamiento;

public class NodoListaDeAdyacencia {
	
	private int numeroPaquete;
	private String nombrePaquete;
	private int peso;
	
	public NodoListaDeAdyacencia(int num, String nom){
		this.setPeso(0);
		this.numeroPaquete = num;
		this.nombrePaquete = nom;
	}

	public int getNumeroPaquete() {
		return numeroPaquete;
	}
	
	public String getNombrePaquete(){
		return nombrePaquete;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public int getPeso() {
		return peso;
	}
	

}
