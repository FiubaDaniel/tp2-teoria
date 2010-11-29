package detectorDeAcoplamiento;

import java.util.ArrayList;

public class NodoGrafo {
	
	public static final int NULL = -1;
	private String ID;
	private int IDinterno;
	private ArrayList<Integer> listaDeAdyacencia;
	private int Pre,Post;
	private boolean visitado;
	private boolean terminado;
	private int distancia;
	private int padre;
	private boolean EnComponenteConexa;
	private int numeroDeComponenteConexa;
	
	public NodoGrafo(String nombrePaquete,int identificacionInterna){
	
		this.setID(nombrePaquete);
		this.setIDinterno(identificacionInterna);
		listaDeAdyacencia = new ArrayList<Integer>();
		this.setVisitado(false);
		this.setPre(NULL);
		this.setPost(NULL);
		this.setEnComponenteConexa(false);
		this.setNumeroDeComponenteConexa(NULL);
		this.setDistancia(-1);
		this.padre = NULL;
		this.terminado = false;
	}

	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}

	public int getDistancia() {
		return distancia;
	}

	public void setPadre(int padre) {
		this.padre = padre;
	}

	public int getPadre() {
		return padre;
	}

	public void setEnComponenteConexa(boolean enComponenteConexa) {
		EnComponenteConexa = enComponenteConexa;
	}

	public boolean isEnComponenteConexa() {
		return EnComponenteConexa;
	}

	public void setNumeroDeComponenteConexa(int numeroDeComponenteConexa) {
		this.numeroDeComponenteConexa = numeroDeComponenteConexa;
	}

	public int getNumeroDeComponenteConexa() {
		return numeroDeComponenteConexa;
	}

	public void setVisitado(boolean visitado) {
		this.visitado = visitado;
	}

	public boolean isVisitado() {
		return visitado;
	}

	public void setPost(int post) {
		Post = post;
	}

	public int getPost() {
		return Post;
	}

	public void setPre(int pre) {
		Pre = pre;
	}

	public int getPre() {
		return Pre;
	}

	public void setIDinterno(int iDinterno) {
		IDinterno = iDinterno;
	}

	public int getIDinterno() {
		return IDinterno;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getID() {
		return ID;
	}

	public void setTerminado(boolean terminado) {
		this.terminado = terminado;
	}

	public boolean isTerminado() {
		return terminado;
	}
	
	
	
}
