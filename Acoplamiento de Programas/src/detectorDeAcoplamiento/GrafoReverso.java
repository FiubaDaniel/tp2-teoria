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

	/**
	 * Calcula, dado un grafo, su reverso.
	 * @param grafo
	 */
	public void calcularGrafoReverso(Grafo grafo){
		
		/*int tamanio = grafo.getCantidadDePaquetes();
		NodoGrafo[] nodos = grafo.componentesDelGrafo();
		GrafoReverso grafoReverso = new GrafoReverso(tamanio);
		NodoGrafo[] nodoReverso = grafoReverso.getNodos();*/
		
		for(int i = 0; i < grafo.componentesDelGrafo().length; i++){  
			/*ArrayList listaDeAdyacencia = nodos[i].getListaDeAdyacencia();
			
			if ( !listaDeAdyacencia.isEmpty()){
				
				for(int j = 0; j < listaDeAdyacencia.size(); j++){  //recorro la lista de adyacencia del nodo
					Integer nodoAdyacente = (Integer)listaDeAdyacencia.get(j);
					ArrayList listaDeAdyacenciaReversa = nodoReverso[nodoAdyacente].getListaDeAdyacencia();
					listaDeAdyacenciaReversa.add(j);
				}
			}
		}*/
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

	public NodoGrafo[] componentesDelGrafoReverso() {
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
