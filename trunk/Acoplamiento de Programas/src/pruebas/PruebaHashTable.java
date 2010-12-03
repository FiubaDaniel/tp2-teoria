package pruebas;

import java.util.Hashtable;

public class PruebaHashTable {
	
	public PruebaHashTable (){
		super();
	}
	public void probarPut(int cant){
		Hashtable<Integer, Integer> tabla = new Hashtable<Integer,Integer>();
		for(int i = 0; i < cant ; i++){
			tabla.put(i, i);
		}
	}
	public void probarGet(int cant){
		Hashtable<Integer, Integer> tabla = new Hashtable<Integer,Integer>();
		for(int i = 0; i < cant ; i++){
			tabla.get(i);
		}
	}
}
