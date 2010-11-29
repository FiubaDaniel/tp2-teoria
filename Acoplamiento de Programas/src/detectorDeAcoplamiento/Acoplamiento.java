package detectorDeAcoplamiento;

import java.util.Iterator;

public class Acoplamiento {
	
	private static final int INFINITO = -1;
	private static final boolean VISITADO = true;
	private static final boolean TERMINADO = true;
	private static final int NULL = -1;
	
	private Grafo grafo;
	
	public Acoplamiento(Grafo grafo){
		this.grafo = grafo;
	}
	
	public void CalcularComponentesConexas(){
		 /* DFS sobre Grafo Transpuesto */
		 DFS(true);  
		 /*Le doy los valores de pre/post a grafo comun */
		 for(int i = 0; i < this.grafo.ComponentesDelGrafo(false).length;i++){
			 this.grafo.ComponentesDelGrafo(false)[i].setPre(this.grafo.ComponentesDelGrafo(true)[i].getPre());
			 this.grafo.ComponentesDelGrafo(false)[i].setPost(this.grafo.ComponentesDelGrafo(true)[i].getPost());
		 }
		 /* Buscar cada uno de los arboles */
		 
	}
	
	private void DFS(boolean EsInvertido){
		for(int i = 0;i<this.grafo.ComponentesDelGrafo(EsInvertido).length;i++){
			this.grafo.ComponentesDelGrafo(EsInvertido)[i].setDistancia(INFINITO);
			this.grafo.ComponentesDelGrafo(EsInvertido)[i].setPadre(NULL);
		}
		this.grafo.setTiempo(0);
		for(int j = 0; j < this.grafo.ComponentesDelGrafo(EsInvertido).length; j++){
			Iterator<NodoClases> it = this.grafo.getListaClasesYBloques().iterator();
			while(it.hasNext()){
				NodoClases nodo = it.next();
				if(!this.grafo.ComponentesDelGrafo(EsInvertido)[nodo.getIdPaqueteInterno()].isVisitado()){
					DFSVisitar(this.grafo.ComponentesDelGrafo(EsInvertido)[nodo.getIdPaqueteInterno()],EsInvertido);
				}
			}
		}
	}

	private void DFSVisitar(NodoGrafo n,boolean EsInvertido){
		n.setVisitado(VISITADO);
        this.grafo.setTiempo(this.grafo.getTiempo()+1);
        n.setPre(this.grafo.getTiempo());
        Iterator<NodoClases> it = this.grafo.getListaClasesYBloques().iterator();
		while(it.hasNext()){
			NodoClases nodo = it.next();
			if(!this.grafo.ComponentesDelGrafo(EsInvertido)[nodo.getIdPaqueteInterno()].isVisitado()){
				this.grafo.ComponentesDelGrafo(EsInvertido)[nodo.getIdPaqueteInterno()].setPadre(nodo.getIdPaqueteInterno());
	            DFSVisitar(this.grafo.ComponentesDelGrafo(EsInvertido)[nodo.getIdPaqueteInterno()],EsInvertido);
			}
		}
		n.setTerminado(TERMINADO);
		this.grafo.setTiempo(this.grafo.getTiempo()+1);
	    n.setPost(this.grafo.getTiempo());
	}
}
