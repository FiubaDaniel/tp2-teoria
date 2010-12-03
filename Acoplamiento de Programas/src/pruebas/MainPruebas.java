package pruebas;

import java.io.IOException;
import java.util.Calendar;

import detectorDeAcoplamiento.CalculadorAcoplamiento;
import detectorDeAcoplamiento.Grafo;

public class MainPruebas {

	public static void main(String[] args) throws IOException{
		
		System.out.println("Prueba 1 ");
		String ruta = "Prueba1";
		Grafo grafo = new Grafo(ruta);
		CalculadorAcoplamiento acoplamiento = new CalculadorAcoplamiento(grafo);
		acoplamiento.CalcularComponentesConexas();
		System.out.println("");
		System.out.println("");
		System.out.println("Prueba 2 ");
		ruta = "Prueba3";
		grafo = new Grafo(ruta);
		acoplamiento = new CalculadorAcoplamiento(grafo);
		acoplamiento.CalcularComponentesConexas();
		System.out.println("");
		System.out.println("");
		System.out.println("Prueba 3 ");
		ruta = "Prueba4";
		grafo = new Grafo(ruta);
		acoplamiento = new CalculadorAcoplamiento(grafo);
		acoplamiento.CalcularComponentesConexas();
		System.out.println("");
		System.out.println("");
		System.out.println("Prueba 4 ");
		ruta = "Prueba2";
		grafo = new Grafo(ruta);
		acoplamiento = new CalculadorAcoplamiento(grafo);
		acoplamiento.CalcularComponentesConexas();
		
		
	}
		
}

