package detectorDeAcoplamiento;

import java.util.Iterator;

public class GrafoReverso {
	
	private int cantidadDePaquetes;
	private NodoGrafo[] representacionGrafoReverso;
	private int tiempo;
		
	public GrafoReverso(int cantidadDePaquetes){
		this.setTiempo(0);
		this.cantidadDePaquetes = cantidadDePaquetes;
		this.representacionGrafoReverso = new NodoGrafo[cantidadDePaquetes];
		for(int i = 0; i < this.cantidadDePaquetes; i++){
			NodoGrafo nodo = new NodoGrafo("Nombre",i);
			this.representacionGrafoReverso[i] = nodo;
		}
	}

	public void calcularGrafoReverso(Grafo grafo){
		
		for(int i = 0; i < grafo.componentesDelGrafo().length; i++){  
			/*Seteo el nombre del paquete */
			this.representacionGrafoReverso[i].setID(grafo.componentesDelGrafo()[i].getID());
			/*Ahora invierto el grafo, o sea sus listas de adyacencia*/
			if(!grafo.componentesDelGrafo()[i].getListaDeAdyacencia().isEmpty()){
				Iterator<NodoListaDeAdyacencia> it = grafo.componentesDelGrafo()[i].getListaDeAdyacencia().iterator();
				while(it.hasNext()){
					NodoListaDeAdyacencia nodo = it.next();
					/*A la lista de nodo.getNumeroPaquete le llegará grafo.componenteDelGrafo.getNumeroPaquete */
					NodoListaDeAdyacencia nodoAux = new NodoListaDeAdyacencia(grafo.componentesDelGrafo()[i].getIDinterno(),grafo.componentesDelGrafo()[i].getID());
					nodoAux.setPeso(nodo.getPeso());
					this.representacionGrafoReverso[nodo.getNumeroPaquete()].getListaDeAdyacencia().add(nodoAux);
				}
			}
		}
	}

	public NodoGrafo[] representacionGrafoReverso() {
		return representacionGrafoReverso;
	}
	
	public int getCantidadDePaquetes(){
		return this.cantidadDePaquetes;
	}

	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}

	public int getTiempo() {
		return tiempo;
	}	
}
