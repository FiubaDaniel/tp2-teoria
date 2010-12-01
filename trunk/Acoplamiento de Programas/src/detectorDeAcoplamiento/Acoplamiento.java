package detectorDeAcoplamiento;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Acoplamiento {

	private static final boolean NO_VISITADO = false;
	private static final boolean VISITADO = true;
	private static final int NULL = -1;

	private Grafo grafo;
	private LinkedList<Integer> listaDePost;
	private int index;
	private ArrayList<NodoComponenteConexa> ComponenteConexa;
	private char Componente;

	public Acoplamiento(Grafo grafo){
		this.grafo = grafo;
		this.listaDePost = new LinkedList<Integer>();
		this.ComponenteConexa = new ArrayList<NodoComponenteConexa>();
		this.Componente = 'A';
	}

	/*Utilizo busqueda en profundidad que se encuentra en el libro */
	public void CalcularComponentesConexas(){
		GrafoReverso grafoReverso = new GrafoReverso(this.grafo.getCantidadDePaquetes());
		grafoReverso.calcularGrafoReverso(this.grafo);
		/* DFS sobre Grafo */
		DFS_Grafo();
		/*Le doy los valores de pre/post a grafo reverso */
		for(int i = 0; i < this.grafo.componentesDelGrafo().length;i++){
			grafoReverso.componentesDelGrafoReverso()[i].setPre(this.grafo.componentesDelGrafo()[i].getPre());
			grafoReverso.componentesDelGrafoReverso()[i].setPost(this.grafo.componentesDelGrafo()[i].getPost());
			grafoReverso.componentesDelGrafoReverso()[i].setPadre(this.grafo.componentesDelGrafo()[i].getPadre());
		}
		/* Ahora calculo las componentes conexas mostrando por pantalla el tamaño y el circuito
		 * La idea seria agarra el primer elemento de la lista, encontrar la componente conexa y despues eliminar la cantidad
		 * de elementos de la componente de la lista.
		 */
		this.setIndex(0);
		DFS_GrafoReverso(grafoReverso);
	}

	private void DFS_GrafoReverso(GrafoReverso grafoReverso) {
		for(int i = 0 ; i < this.grafo.componentesDelGrafo().length ; i++){
			grafoReverso.componentesDelGrafoReverso()[i].setVisitado(NO_VISITADO);
		}
		System.out.println("Alumno: Leandro Alessandrello ");
		System.out.println("Padron: 84155 ");
		System.out.println("Alumno: Daniel Mugica ");
		System.out.println("Padron: 87697 ");
		System.out.println("Cantidad de modulos:  "+this.grafo.getCantidadDePaquetes());
		System.out.println("Cantidad de Clases: ");
		
		while(this.getIndex() < this.listaDePost.size()){
			NodoGrafo nodo = grafoReverso.componentesDelGrafoReverso()[this.listaDePost.get(index)];
			if(!nodo.isVisitado()){		
				this.ComponenteConexa.clear();
				DFS_GrafoInversoVisitar(grafoReverso,nodo,0);
				System.out.println("Componente: "+this.getComponente()+" ; Tamaño: "+this.ComponenteConexa.size());
				this.setComponente(this.getComponente());/*Nombre de la componente */
				this.setIndex(this.index+this.ComponenteConexa.size());
				System.out.println("Ciclo: ");
				if(this.ComponenteConexa.size()==1){
					System.out.println("La componente conexa esta formada por un solo elemento "+ this.grafo.componentesDelGrafo()[this.ComponenteConexa.get(0).numeroPaquete()].getID());
				}else{
					Iterator<NodoComponenteConexa> it2 = this.ComponenteConexa.iterator();
					while(it2.hasNext()){
						NodoComponenteConexa inicial = it2.next();
						NodoComponenteConexa finall;
						if(it2.hasNext()){
							finall = it2.next();
							System.out.println(this.grafo.componentesDelGrafo()[inicial.numeroPaquete()].getID() +" >> "+this.grafo.componentesDelGrafo()[finall.numeroPaquete()].getID()+" (peso: "+finall.peso()+")");
						}
					}
				}
			}
		}
	}
	private void DFS_GrafoInversoVisitar(GrafoReverso grafoReverso,	NodoGrafo nodo,int peso) {		
		nodo.setVisitado(VISITADO);
		NodoComponenteConexa nodoA = new NodoComponenteConexa(nodo.getIDinterno(),peso);
		this.ComponenteConexa.add(nodoA);
		Iterator<NodoListaDeAdyacencia> it = nodo.getListaDeAdyacencia().iterator();
		while(it.hasNext()){
			NodoListaDeAdyacencia nodoAux = it.next();
			if(!grafoReverso.componentesDelGrafoReverso()[nodoAux.getNumeroPaquete()].isVisitado()){
				DFS_GrafoInversoVisitar(grafoReverso,grafoReverso.componentesDelGrafoReverso()[nodoAux.getNumeroPaquete()],nodoAux.getPeso());
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

	private void setIndex(int index){
		this.index = index;
	}

	private int getIndex(){
		return this.index;
	}
	public void setComponente(char componente) {
		Componente = (char) (componente+1);
	}
	public char getComponente() {
		return Componente;
	}
}

