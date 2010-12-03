package detectorDeAcoplamiento;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class CalculadorAcoplamiento {

	private static final boolean NO_VISITADO = false;
	private static final boolean VISITADO = true;
	private static final int NULL = -1;

	private Grafo grafo;
	private LinkedList<Integer> listaDePost;
	private ArrayList<NodoCiclo> Ciclo;
	private char Componente;
	private ArrayList<Integer> componentesConexas;
	private int cantidadDeCiclos;


	public CalculadorAcoplamiento(Grafo grafo){
		this.grafo = grafo;
		this.listaDePost = new LinkedList<Integer>();
		this.Ciclo = new ArrayList<NodoCiclo>();
		this.componentesConexas = new ArrayList<Integer>();
		this.Componente = 'A';
		this.cantidadDeCiclos = 1;
	}

	/*Utilizo busqueda en profundidad que se encuentra en el libro */
	public void CalcularComponentesConexas(){
		GrafoReverso grafoReverso = new GrafoReverso(this.grafo.getCantidadDePaquetes());
		grafoReverso.calcularGrafoReverso(this.grafo);
		/* DFS sobre Grafo */
		DFS_Grafo();
		/*Le doy los valores de pre/post a grafo reverso */
		for(int i = 0; i < this.grafo.componentesDelGrafo().length;i++){
			grafoReverso.representacionGrafoReverso()[i].setPre(this.grafo.componentesDelGrafo()[i].getPre());
			grafoReverso.representacionGrafoReverso()[i].setPost(this.grafo.componentesDelGrafo()[i].getPost());
			grafoReverso.representacionGrafoReverso()[i].setPadre(this.grafo.componentesDelGrafo()[i].getPadre());
		}
		/* Ahora calculo las componentes conexas mostrando por pantalla el tamaño y el circuito
		 * La idea seria agarra el primer elemento de la lista, encontrar la componente conexa y despues eliminar la cantidad
		 * de elementos de la componente de la lista.
		 */
		/******************Sacar**********************************/
		/*for(int i = 0; i < this.listaDePost.size() ; i++){
			System.out.println("Nodo numero: "+this.listaDePost.get(i));
		}*/
		/*********************************************************/

		System.out.println("");
		System.out.println("");

		System.out.println("Alumno: Leandro Alessandrello ");
		System.out.println("Padron: 84155 ");
		System.out.println("Alumno: Daniel Mugica ");
		System.out.println("Padron: 87697 ");
		System.out.println("");
		System.out.println("");
		System.out.println("Cantidad de modulos:  "+this.grafo.getCantidadDePaquetes());
		System.out.println("Cantidad de Clases: "+grafo.getCantidadDeClases());
		System.out.println("");
		System.out.println("");
		DFS_ComponentesConexas(grafoReverso);
		calcularCiclos(grafoReverso); 

	}
	/*
	 * Calcula los ciclos dentro de una compnente conexa, por eso utiliza el grafoReverso para 
	 * poder obtener componentes conexas.
	 */
	private void calcularCiclos(GrafoReverso grafoReverso) {
		for(int i = 0 ; i < this.grafo.componentesDelGrafo().length ; i++){
			grafoReverso.representacionGrafoReverso()[i].setVisitado(NO_VISITADO);
		}	
		while(this.listaDePost.size() > 0){
			if(!grafoReverso.representacionGrafoReverso()[this.listaDePost.getFirst()].isVisitado()){
				this.componentesConexas.clear();
				this.Ciclo.clear();
				this.Ciclo.add(new NodoCiclo(grafoReverso.representacionGrafoReverso()[this.listaDePost.getFirst()].getIDinterno(), 0));
				calcularCiclosRecursivo(grafoReverso,grafoReverso.representacionGrafoReverso()[this.listaDePost.getFirst()]);
				actualizarVisitados(grafoReverso);
				actualizarListaPost(grafoReverso);				
			}
		}

	}

	private void calcularCiclosRecursivo(GrafoReverso grafoReverso,NodoGrafo nodo) {
		this.componentesConexas.add(nodo.getIDinterno());
		Iterator<NodoListaDeAdyacencia> it = nodo.getListaDeAdyacencia().iterator();
		while(it.hasNext()){
			NodoListaDeAdyacencia nodoAux = it.next();
			if(!grafoReverso.representacionGrafoReverso()[nodoAux.getNumeroPaquete()].isVisitado()){
				boolean encontrado = formaCiclo(nodoAux.getNumeroPaquete(),nodoAux.getPeso());
				if(!encontrado){
					/*******************Sacar********************************************/
					//System.out.println(nodoAux.getPeso());
					/********************************************************************/
					Ciclo.add(new NodoCiclo(nodoAux.getNumeroPaquete(),nodoAux.getPeso()));
					calcularCiclosRecursivo(grafoReverso,grafoReverso.representacionGrafoReverso()[nodoAux.getNumeroPaquete()]);
				}
			}
		}
	}

	private boolean formaCiclo(int numeroPaquete,int peso) {
		boolean encontrado = false;
		/********************Sacar*******************************/
		/*for(int j = 0; j < this.Ciclo.size() ; j++){
			System.out.println(Ciclo.get(j).numeroPaquete());
		}*/
		/********************************************************/
		for(int i = 0; i< this.Ciclo.size() && !encontrado ; i++){
			if(Ciclo.get(i).numeroPaquete() == numeroPaquete){
				encontrado = true;
				imprimirCiclo(i,peso);
				this.cantidadDeCiclos++;
			}
		}
		return encontrado;		
	}

	private void imprimirCiclo(int i,int peso) {
		int finall = this.Ciclo.size()-1;
		System.out.println("");
		System.out.println("Ciclo "+this.cantidadDeCiclos);
		while(finall > i){
			System.out.println(this.Ciclo.get(finall).numeroPaquete()+" >> "+this.Ciclo.get(finall-1).numeroPaquete()+" (Peso: "+this.Ciclo.get(finall).peso()+")");
			finall--;
		}	
		finall = this.Ciclo.size()-1;
		System.out.println(this.Ciclo.get(i).numeroPaquete()+" >> "+this.Ciclo.get(finall).numeroPaquete()+" (Peso: "+peso+")");
	}

	private void actualizarVisitados(GrafoReverso grafoReverso) {
		for (int i = 0 ; i < this.componentesConexas.size() ; i++){
			grafoReverso.representacionGrafoReverso()[this.componentesConexas.get(i)].setVisitado(VISITADO);
		}	
	}

	private void DFS_ComponentesConexas(GrafoReverso grafoReverso) {
		for(int i = 0 ; i < this.grafo.componentesDelGrafo().length ; i++){
			grafoReverso.representacionGrafoReverso()[i].setVisitado(NO_VISITADO);
		}	
		LinkedList<Integer> listaAux = (LinkedList<Integer>) this.listaDePost.clone();
		while(this.listaDePost.size() > 0){
			if(!grafoReverso.representacionGrafoReverso()[this.listaDePost.getFirst()].isVisitado()){
				this.componentesConexas.clear();			
				DFS_ComponentesConexasVisitar(grafoReverso,grafoReverso.representacionGrafoReverso()[this.listaDePost.getFirst()]);
				System.out.print("Componente: "+this.Componente+"; Tamanio: "+this.componentesConexas.size());
				System.out.print(";  Sus componentes son: ");
				for(int j=0 ; j < this.componentesConexas.size() ; j++){
					System.out.print(this.grafo.componentesDelGrafo()[this.componentesConexas.get(j)].getID()+" ; ");
				}
				System.out.println("");
				actualizarListaPost(grafoReverso);
			}
			this.setComponente(this.getComponente());				
		}
		this.listaDePost = listaAux;
	}

	void actualizarListaPost(GrafoReverso grafoReverso){
		for (int i = 0, cant = 0; cant < this.componentesConexas.size() && i < this.listaDePost.size(); i++ ){
			if(grafoReverso.representacionGrafoReverso()[listaDePost.get(i)].isVisitado()){
				listaDePost.remove(i);
				i--;
				cant++; 
				/*Lista de post tiene |v| elementos como maximo si la componente conexa es todo el grafo es todo el grafo*/
			}
		}
	}

	private void DFS_ComponentesConexasVisitar(GrafoReverso grafoReverso,NodoGrafo nodo) {
		nodo.setVisitado(VISITADO);
		this.componentesConexas.add(nodo.getIDinterno());
		Iterator<NodoListaDeAdyacencia> it = nodo.getListaDeAdyacencia().iterator();
		while(it.hasNext()){
			NodoListaDeAdyacencia nodoAux = it.next();
			if(!grafoReverso.representacionGrafoReverso()[nodoAux.getNumeroPaquete()].isVisitado()){
				DFS_ComponentesConexasVisitar(grafoReverso,grafoReverso.representacionGrafoReverso()[nodoAux.getNumeroPaquete()]);
			}
		}
	}
	/* 
	 DFS(grafo G)
     PARA CADA vertice u ∈ V[G] HACER
             estado[u] ← NO_VISITADO
             padre[u] ← NULO
     tiempo ← 0
     PARA CADA vertice u ∈ V[G] HACER
             SI estado[u] = NO_VISITADO ENTONCES
                     DFS_Visitar(u)
	 */
	private void DFS_Grafo() {
		for(int j = 0 ; j < this.grafo.componentesDelGrafo().length ; j++){
			this.grafo.componentesDelGrafo()[j].setVisitado(NO_VISITADO);
			this.grafo.componentesDelGrafo()[j].setPadre(NULL);
		}
		this.grafo.setTiempo(0);
		for(int i = 0 ; i < this.grafo.componentesDelGrafo().length ; i++ ){
			if(!this.grafo.componentesDelGrafo()[i].isVisitado()){
				DFS_GrafoVisitar(this.grafo.componentesDelGrafo()[i]);
			}
		}

	}
	/*
	 DFS-Visitar(nodo u)
     estado[u] ← VISITADO
     tiempo ← tiempo + 1
     d[u] ← tiempo
     PARA CADA v ∈ Vecinos[u] HACER
             SI estado[v] = NO_VISITADO ENTONCES
                     padre[v] ← u
                     DFS_Visitar(v)
     estado[u] ← TERMINADO
     tiempo ← tiempo + 1
     f[u] ← tiempo
	 */
	private void DFS_GrafoVisitar(NodoGrafo nodoGrafo) {
		nodoGrafo.setVisitado(VISITADO);
		this.grafo.setTiempo(this.grafo.getTiempo()+1);
		nodoGrafo.setPre(this.grafo.getTiempo());
		Iterator<NodoListaDeAdyacencia> it = nodoGrafo.getListaDeAdyacencia().iterator();
		while(it.hasNext()){
			NodoListaDeAdyacencia nodo = it.next();
			if(!this.grafo.componentesDelGrafo()[nodo.getNumeroPaquete()].isVisitado()){
				this.grafo.componentesDelGrafo()[nodo.getNumeroPaquete()].setPadre(nodoGrafo.getIDinterno());
				DFS_GrafoVisitar(this.grafo.componentesDelGrafo()[nodo.getNumeroPaquete()]);
			}
		}
		this.grafo.setTiempo(this.grafo.getTiempo()+1);
		nodoGrafo.setPost(this.grafo.getTiempo());
		this.listaDePost.addFirst(nodoGrafo.getIDinterno());
	}

	public void setComponente(char componente) {
		Componente = (char) (componente+1);
	}
	public char getComponente() {
		return Componente;
	}
	/************Sacar**********************************/
	private void imprimirGrafoReverso(GrafoReverso grafoReverso){
		System.out.println("");
		System.out.println("GrafReverso");
		for(int i =0 ; i<grafoReverso.representacionGrafoReverso().length;i++){
			NodoGrafo nodo = grafoReverso.representacionGrafoReverso()[i];
			System.out.println("Nombre Paquete :"+ nodo.getID());
			System.out.println("Numero Paquete :"+ nodo.getIDinterno());
			if(grafoReverso.representacionGrafoReverso()[i].getListaDeAdyacencia().isEmpty()){
				System.out.println("Lista de adyacencia Vacia");
				System.out.println("/----------------------------/  ");
				System.out.println("");
			}else{
				System.out.println("Lista de adyacencia: ");
				System.out.println("");
				Iterator<NodoListaDeAdyacencia> it = nodo.getListaDeAdyacencia().iterator();
				while(it.hasNext()){
					NodoListaDeAdyacencia nodo2 = it.next();
					System.out.println("Nombre Paquete: "+nodo2.getNombrePaquete());
					System.out.println("Numero Paquete: "+nodo2.getNumeroPaquete());
					System.out.println("Peso Paquete: "+nodo2.getPeso());
					System.out.println(" ");
				}
				System.out.println("/----------------------------/  ");
				System.out.println("");
			}
		}
	}
	/***************************************************/
}

