package pruebas;

import java.io.IOException;
import detectorDeAcoplamiento.Acoplamiento;
import detectorDeAcoplamiento.Grafo;

public class MainPruebas {

	public static void main(String[] args) throws IOException{
		
		String ruta = "/home/daniel/Caso1Grafo1Borrar";
		Grafo grafo = new Grafo(ruta);
		Acoplamiento acoplamiento = new Acoplamiento(grafo);
		acoplamiento.CalcularComponentesConexas();
	}
		
}

