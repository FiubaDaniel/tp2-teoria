package detectorDeAcoplamiento;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;


public class Grafo {

	private NodoGrafo [] representacionGrafo;
	private NodoGrafo [] representacionGrafoTranspuesto;

	private ArrayList<NodoClases> ListaClasesYBloques;

	private Hashtable<String, Integer> Paquete;
	private Hashtable<String, Integer> Clase;

	private int cantidadaDeBloques;

	int tiempo = 0;

	/**
	 * La aplicacion debe recibir como parametro la ruta a un directorio donde se encontran los archivos a procesar,
	 * o sea los archivos correspondientes a cada una de las clases.
	 * @param ruta ruta de los archivos
	 */
	public Grafo(String ruta){

		/*Creacion del HashTable key/value*/
		Paquete = new Hashtable<String, Integer>(); /*Nombre Paquete/Numero Paqute. Identifica el numero de paquete segun su nombre */
		Clase = new Hashtable<String,Integer>();  /*Nombre Clase/Numero Paqute. Identifica el numero de paquete segun el numero de clase*/

		File CarpetaClases = new File(ruta);
		File listaDeClases[]= CarpetaClases.listFiles();
		/*Obtengo todos los paquetes que habrá en el grafo, para crear el vector */
		for(int j=0;j<listaDeClases.length;j++){
			File archivoClase = new File(listaDeClases[j].getName());
			BufferedReader Clase ;
			try {
				String Package = "package";
				String Import = "import";
				Clase = new BufferedReader( new FileReader( archivoClase ) );
				boolean paquete = false;
				String linea;
				while(!paquete){
					linea = Clase.readLine();
					if(!linea.isEmpty()){
						paquete = true;
					}
				}
				
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		/*Ahora parseo todo los archivo */
		for(int i=0;i<listaDeClases.length;i++){
			this.procesarClase(ruta, listaDeClases[i].getName());	
		}
		/*Ahora armo el grafo y el grafo invertido*/
		this.representacionGrafo = new NodoGrafo[this.cantidadaDeBloques];
		for (int i=0;i<this.cantidadaDeBloques;i++){
			this.representacionGrafo[i] = new NodoGrafo("cambiar",0);
		}
	}
	
	/**
	*Se arma la lista de clases, donde tengo la informacion de a que paquete pertenece y las clases de las q depende 
	*@param ruta ruta de la clase
	*@param nombreClase nombre de la clase a procesar
	*/
	private void procesarClase(String ruta,String nombreClase) {
		String Import = "import";
		String Class = "class";
		String New = "new";
		String Package = "package";
		File archivoClase = new File(ruta+"/"+nombreClase);
		BufferedReader clase ;
		try {
			clase = new BufferedReader( new FileReader( archivoClase ) );
			String linea;
			while(clase.ready()){
				linea = clase.readLine();

			}
		}catch (IOException e) {
			e.printStackTrace();
		}	
	}

	public NodoGrafo [] ComponentesDelGrafo(boolean EsInvertido){
		if(EsInvertido){
			return this.representacionGrafoTranspuesto;
		}
		return this.representacionGrafo;
	}

	public ArrayList<NodoClases> getListaClasesYBloques(){
		return this.ListaClasesYBloques;
	}

	public int getTiempo(){
		return this.tiempo;
	}

	public void setTiempo(int tiempo){
		this.tiempo = tiempo;
	}

}
