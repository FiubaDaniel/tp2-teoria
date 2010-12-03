package pruebas;

import java.io.IOException;
import detectorDeAcoplamiento.Acoplamiento;
import detectorDeAcoplamiento.Grafo;

public class MainPruebas {

	public static void main(String[] args) throws IOException{
		
		System.out.println("Prueba 1 ");
		String ruta = "Prueba1";
		Grafo grafo = new Grafo(ruta);
		Acoplamiento acoplamiento = new Acoplamiento(grafo);
		acoplamiento.CalcularComponentesConexas();
		System.out.println("");
		System.out.println("");
		System.out.println("Prueba 2 ");
		ruta = "Prueba2";
		grafo = new Grafo(ruta);
		acoplamiento = new Acoplamiento(grafo);
		acoplamiento.CalcularComponentesConexas();
	}
		
}

