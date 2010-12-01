package detectorDeAcoplamiento;

import java.util.ArrayList;
import java.util.Iterator;

import detectorDeAcoplamiento.Grafo;
import detectorDeAcoplamiento.NodoGrafo;

public class GrafoReverso {
	
	private int cantidadDePaquetes;
	private NodoGrafo[] nodos;
	
	public GrafoReverso(int cantidadDePaquetes){
		this.cantidadDePaquetes = cantidadDePaquetes;
		this.nodos = new NodoGrafo[cantidadDePaquetes];
		for(int i = 0; i < this.cantidadDePaquetes; i++){
			NodoGrafo nodo = new NodoGrafo("Nombre",i);
			this.nodos[i] = nodo;
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
		
		for(int i = 0; i < grafo.componentesDelGrafo().length; i++){  //reocorro grafo original y obtengo la lista de adyacencia de cada nodo
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
			this.nodos[i].setID(grafo.componentesDelGrafo()[i].getID());
			/*Ahora invierto el grafo, o sea sus listas de adyacencia*/
			if(!grafo.componentesDelGrafo()[i].getListaDeAdyacencia().isEmpty()){
				Iterator<NodoListaDeAdyacencia> it = this.nodos[i].getListaDeAdyacencia().iterator();
				while(it.hasNext()){
					NodoListaDeAdyacencia nodo = it.next();
					/*A la lista de nodo.getNumeroPaquete le llegará grafo.componenteDelGrafo.getNumeroPaquete */
					NodoListaDeAdyacencia nodoAux = new NodoListaDeAdyacencia(grafo.componentesDelGrafo()[i].getIDinterno(),grafo.componentesDelGrafo()[i].getID());
					nodoAux.setPeso(nodo.getPeso());
					this.nodos[nodo.getNumeroPaquete()].getListaDeAdyacencia().add(nodoAux);
				}
			}
		}
	}

	private NodoGrafo[] getNodos() {
		return nodos;
	}
	
}
