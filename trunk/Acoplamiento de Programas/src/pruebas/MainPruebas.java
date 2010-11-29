package pruebas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import detectorDeAcoplamiento.Grafo;

public class MainPruebas {
	
	public static void main(String[] args) throws IOException{
		
		/*ProbarObtenerArchivos prueba1 = new ProbarObtenerArchivos();*/
		String ruta = "/home/daniel/ZPruebaBorrar";
		File CarpetaClases = new File(ruta);
		File listaDeClases[]= CarpetaClases.listFiles();
		for(int i=0;i<listaDeClases.length;i++){
			 System.out.println(listaDeClases[i].getName());
			 File archivoClase = new File(ruta+"/"+listaDeClases[i].getName());
				BufferedReader Clase ;
				Clase = new BufferedReader( new FileReader( archivoClase ) );
				String linea;
				int cont =0;
				do{
					cont++;
					linea = Clase.readLine();
					System.out.println("Linea numero "+cont+linea);
					System.out.println("Esta vacia: "+linea.isEmpty());
				}while(Clase.ready());
		}
		//Grafo grafo = new Grafo(ruta);
	}

}
