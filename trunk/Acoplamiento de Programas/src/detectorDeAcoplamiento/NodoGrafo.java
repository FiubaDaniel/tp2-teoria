package detectorDeAcoplamiento;

import java.util.ArrayList;

public class NodoGrafo {
	
	public static final int NULL = -1;
	private String ID;
	private int IDinterno;
	private ArrayList<NodoListaDeAdyacencia> listaDeAdyacencia;
	private boolean visitado;
	private int Pre,Post;
	private int padre;


	public NodoGrafo(String nombrePaquete,int identificacionInterna){
	
		this.setID(nombrePaquete);
		this.setIDinterno(identificacionInterna);
		setListaDeAdyacencia(new ArrayList<NodoListaDeAdyacencia>());
		this.setVisitado(false);
		this.setPre(NULL);
		this.setPost(NULL);
		this.padre = NULL;

	}
	
	public void setPadre(int padre) {
		this.padre = padre;
	}

	public int getPadre() {
		return padre;
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

	public void setListaDeAdyacencia(ArrayList<NodoListaDeAdyacencia> arrayList) {
		this.listaDeAdyacencia = arrayList;
	}

	public ArrayList<NodoListaDeAdyacencia> getListaDeAdyacencia() {
		return listaDeAdyacencia;
	}

}
