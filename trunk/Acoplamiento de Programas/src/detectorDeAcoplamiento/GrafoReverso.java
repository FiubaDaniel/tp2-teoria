package detectorDeAcoplamiento;

import java.util.ArrayList;

import detectorDeAcoplamiento.Grafo;
import detectorDeAcoplamiento.NodoGrafo;

public class GrafoReverso {
	
	private int cantidadDePaquetes;
	private NodoGrafo[] nodos;
	
	public GrafoReverso(int cantidadDePaquetes){
		this.cantidadDePaquetes = cantidadDePaquetes;
	}

	/**
	 * Calcula, dado un grafo, su reverso.
	 * @param grafo
	 */
	public void calcularGrafoReverso(Grafo grafo){
		
		int tamanio = grafo.getCantidadDePaquetes();
		NodoGrafo[] nodos = grafo.componentesDelGrafo();
		GrafoReverso grafoReverso = new GrafoReverso(tamanio);
		NodoGrafo[] nodoReverso = grafoReverso.getNodos();
		
		for(int i = 0; i < tamanio; i++){  //reocorro grafo original y obtengo la lista de adyacencia de cada nodo
			ArrayList listaDeAdyacencia = nodos[i].getListaDeAdyacencia();
			
			if ( !listaDeAdyacencia.isEmpty()){
				
				for(int j = 0; j < listaDeAdyacencia.size(); j++){  //recorro la lista de adyacencia del nodo
					Integer nodoAdyacente = (Integer)listaDeAdyacencia.get(j);
					ArrayList listaDeAdyacenciaReversa = nodoReverso[nodoAdyacente].getListaDeAdyacencia();
					listaDeAdyacenciaReversa.add(j);
				}
			}
		}
	}

	private NodoGrafo[] getNodos() {
		return nodos;
	}
	
}
