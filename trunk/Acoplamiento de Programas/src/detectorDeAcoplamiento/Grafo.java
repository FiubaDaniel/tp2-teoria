package detectorDeAcoplamiento;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


import java.io.IOException;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

public class Grafo {

	private NodoGrafo [] representacionGrafo;
	private ArrayList<NodoClases> ListaClasesYBloques;

	private Hashtable<String, Integer> Paquete;
	private Hashtable<String, Integer> Clase;

	private int cantidadDePaquetes;

	int tiempo = 0;

	/* Constantes necesarias para el parseo del los archivos */
	public static final int numeroClase = 0;
	public static final int numeroImport = 1;
	public static final int numeroPackage = 2;
	public static final int numeroNew = 3;
	public static final String Package = "package";
	public static final String Import = "import";
	public static final String New = "new";
	public static final String Class = "class"; 

	/*atributos especiales de parseo*/
	private String nombrePaqueteActual; /* De los import */
	private String nombrePaquete;
	private String nombreClase;
	private boolean esClass;


	/**
	 * La aplicacion debe recibir como parametro la ruta a un directorio donde se encontran los archivos a procesar,
	 * o sea los archivos correspondientes a cada una de las clases.
	 * ruta de los archivos
	 */
	public Grafo(String ruta){

		/*Creacion del HashTable key/value*/
		Paquete = new Hashtable<String, Integer>(); /*Nombre Paquete/Numero Paqute. Identifica el numero de paquete segun su nombre */
		Clase = new Hashtable<String,Integer>();  /*Nombre Clase/Numero Paqute. Identifica el numero de paquete segun el numero de clase*/

		File CarpetaClases = new File(ruta);
		File listaDeClases[]= CarpetaClases.listFiles(); /* Lista de clases dentro de la carpeta pasada como ruta */
		/*
		 * Obtengo todos los paquetes que hay en el grafo, para crear el vector, o sea solo analizo packaged, 
		 * import y class para armar la hashtable paquete / identificacion interna y la hashtable 
		 * paquete(identificacion interna) / clase.
		 * Ademas se obtiene la cantidad de paquetes existentes que permite crear el vector que representara al
		 * grafo.
		 */
		for(int j=0;j<listaDeClases.length;j++){
			File archivoClase = new File(ruta+"/"+listaDeClases[j].getName());
			BufferedReader Clase ;
			try {
				Clase = new BufferedReader( new FileReader( archivoClase ) );
				this.nombrePaqueteActual = ""; /* De los import */
				this.nombrePaquete ="";
				this.nombreClase = ""; /* De los import */
				this.setEsClass(false);
				String lineaEnProceso;
				boolean encontrado = false;
				while(Clase.ready() && !encontrado){
					/* Primero obtengo paquete de la clase que examino*/
					lineaEnProceso = Clase.readLine();
					encontrado = this.obtenerPatronEnLineaX(lineaEnProceso, numeroPackage, Package, nombrePaqueteActual, nombreClase, esClass);
					agregarPaquete(encontrado);
				}
				encontrado = false;
				this.setEsClass(false);
				while(Clase.ready() && !encontrado){
					lineaEnProceso = Clase.readLine();
					encontrado = this.obtenerPatronEnLineaX(lineaEnProceso, numeroImport, Import, nombrePaquete, nombreClase, esClass);
					encontrado = agregarClaseOImport(encontrado);
				}
				Clase.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		/*Ahora creo el grafo usando los datos recolectados mas el parseo total de los archivos */
		this.representacionGrafo = new NodoGrafo[this.cantidadDePaquetes];
		for(int j=0;j<listaDeClases.length;j++){
			File archivoClase = new File(ruta+"/"+listaDeClases[j].getName());
			BufferedReader Clase ;
			try {
				Clase = new BufferedReader( new FileReader( archivoClase ) );
				this.nombreClase = "";
				String lineaEnProceso;
				boolean encontrado = false;
				/*
				 * Primero setea los valores del nodo y los paquetes de la lista de adyacencia.
				 * 1-Creo nodo con numero de paquete (obtengo de hastable) y nombre de paquete (obtengo del archivo)
				 */
				while(Clase.ready() && !encontrado){
					this.nombreClase = "";
					lineaEnProceso = Clase.readLine();
					encontrado = this.obtenerPatronEnLineaX(lineaEnProceso, numeroPackage, Package, nombrePaqueteActual, nombreClase, esClass);
					this.agregarNodoAlGrafo(encontrado);
				}
				encontrado = false;
				/*Ahora vienen los import, pq lo que me determinan la lista de adyacencia.*/
				this.setEsClass(false);
				while(Clase.ready() && !encontrado){
					this.nombreClase = "";
					lineaEnProceso = Clase.readLine();
					encontrado = this.obtenerPatronEnLineaX(lineaEnProceso, numeroImport, Import, nombrePaquete, nombreClase, esClass);
					agregarPaqueteImport(encontrado); /*Esto me garantiza agregar los paquetes q son de java, los cuales sus listas sera vacias */
					encontrado = agregarElementoAListaDeAdyacencia(encontrado);
				}
				encontrado = false;
				while(Clase.ready()){
					this.nombreClase = "";
					lineaEnProceso = Clase.readLine();
					encontrado = this.obtenerPatronEnLineaX(lineaEnProceso, numeroNew, New, nombrePaquete, nombreClase, esClass);
					agregarPeso(encontrado);
				}
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Cantidad de paquetes :"+this.cantidadDePaquetes);
		System.out.println("Length: "+this.representacionGrafo.length);
		for(int i =0 ; i<this.representacionGrafo.length;i++){
			NodoGrafo nodo = this.representacionGrafo[i];
			System.out.println("Nombre Paquete :"+ nodo.getID());
			System.out.println("Numero Paquete :"+ nodo.getIDinterno());
			if(this.representacionGrafo[i].getListaDeAdyacencia().isEmpty()){
				System.out.println("Lista de adyacencia Vacia");
				System.out.println("/**************************/  ");
			}else{
				System.out.println("Lista de adyacencia: ");
				Iterator<NodoListaDeAdyacencia> it = nodo.getListaDeAdyacencia().iterator();
				while(it.hasNext()){
					NodoListaDeAdyacencia nodo2 = it.next();
					System.out.println("Nombre Paquete: "+nodo2.getNombrePaquete());
					System.out.println("Numero Paquete: "+nodo2.getNumeroPaquete());
					System.out.println("Peso Paquete: "+nodo2.getPeso());
					System.out.println("/************************/  ");
				}
			}
		}
	}

	private void agregarPaqueteImport(boolean encontrado) {
		if(encontrado&& !esClass){
			int numeroPaquete = this.Paquete.get(this.nombrePaquete);
			if(this.representacionGrafo[numeroPaquete] == null){
				NodoGrafo nodo = new NodoGrafo(this.nombrePaquete,numeroPaquete);
				this.representacionGrafo[numeroPaquete] = nodo;
			}
		}	
	}

	private void agregarPeso(boolean encontrado) {
		if(encontrado){
			int paqueteReferenciado = this.Clase.get(nombreClase);
			int paqueteActual = this.Paquete.get(nombrePaqueteActual);
			Iterator<NodoListaDeAdyacencia> it = this.representacionGrafo[paqueteActual].getListaDeAdyacencia().iterator();
			boolean encontrado2 = false;
			while(it.hasNext() && !encontrado2){
				NodoListaDeAdyacencia nodo = it.next();
				if(nodo.getNumeroPaquete() == paqueteReferenciado){
					encontrado2 = true;
					nodo.setPeso(nodo.getPeso()+1);
				}
			}
		}
	}

	private boolean agregarElementoAListaDeAdyacencia(boolean encontrado) {
		if(encontrado && !esClass){
			int numeroPaquete = this.Paquete.get(this.nombrePaquete);
			int numeroPaqueteActual = this.Paquete.get(this.nombrePaqueteActual);
			Iterator<NodoListaDeAdyacencia> it = this.representacionGrafo[numeroPaqueteActual].getListaDeAdyacencia().iterator();
			boolean encontro = false;
			while(it.hasNext() && !encontro){
				NodoListaDeAdyacencia nodo = it.next();
				if(nodo.getNumeroPaquete()==numeroPaquete){
					encontro = true;
				}
			}
			if(!encontro){
				NodoListaDeAdyacencia nodo = new NodoListaDeAdyacencia(numeroPaquete,this.nombrePaquete);
				this.representacionGrafo[numeroPaqueteActual].getListaDeAdyacencia().add(nodo);
			}
			return false;
		}else if(esClass && encontrado){			
			return true;
		}
		return false;
	}

	private void agregarNodoAlGrafo(boolean encontrado) {
		if(encontrado){
			int identificadorDePaquete = this.Paquete.get(this.nombrePaqueteActual);
			if(this.representacionGrafo[identificadorDePaquete] == null){
				NodoGrafo nodo = new NodoGrafo(this.nombrePaqueteActual,identificadorDePaquete);
				this.representacionGrafo[identificadorDePaquete] = nodo;
			}	
		}
	}

	void agregarPaquete(boolean encontrado){
		if(encontrado){
			if(this.Paquete.get(nombrePaqueteActual)==null){
				this.Paquete.put(nombrePaqueteActual, this.cantidadDePaquetes);
				this.cantidadDePaquetes++;
			}
		}
	}

	boolean agregarClaseOImport(boolean encontrado){
		if(encontrado && !esClass){
			if(this.Paquete.get(nombrePaquete)==null){
				this.Paquete.put(nombrePaquete, this.cantidadDePaquetes);
				this.cantidadDePaquetes++;
			}
			if(this.Clase.get(nombreClase)==null){
				int numero = this.Paquete.get(nombrePaquete);
				this.Clase.put(nombreClase, numero);
				this.setNombreClase("");
			}
			return false;
		}else if(esClass && encontrado){/*Tengo el nombre de la clase*/
			int numero = this.Paquete.get(nombrePaqueteActual);
			this.Clase.put(nombreClase, numero);
			this.setEsClass(false);
			return true;
		}
		return false;
	}

	/*
	 * Solo busca el patron sobre un linea (que llega por parametro) no sobre todo el archivo.
	 * Si encuentra el patron devuelve true, y dependiendo que patron halla buscado devolvera los datos
	 * correspondientes a la informacion que brinda ese patron en los parametros nombreClase, nombrePatron.
	 * Si hubo algun error es devuelto en error que pasa como parametro.
	 */
	@SuppressWarnings("static-access")
	private boolean obtenerPatronEnLineaX(String lineaLeida,int numeroPatron, String patron,String nombrePaquete,String nombreClase,boolean esClass){
		String ventana = "";
		boolean encontrePatron = false;
		int inicial = 0;
		int finall = patron.length();
		/*Se supone error igual 0, si ocurre un erro se modificará, sino no*/
		this.setEsClass(false);
		/*Busca el patron en la linea */
		if(!lineaLeida.isEmpty()){
			while(!encontrePatron && finall < lineaLeida.length()){
				ventana = lineaLeida.substring(inicial, finall);
				if(ventana.compareTo(patron)==0){
					encontrePatron = true;
				}else{
					inicial++;
					finall++;
				}
			}
		}
		if (encontrePatron){
			String aux = lineaLeida.substring(finall+1);
			eliminarCaracteresEnBlanco(aux);
			StringCharacterIterator iteradorDeLinea = new StringCharacterIterator (aux);
			Character caracter = new Character(iteradorDeLinea.first());
			if(numeroPatron == numeroImport){
				String lineaAuxiliar1="";
				String lineaAuxiliar2="";
				int cantidadDePuntos = 0;
				boolean terminado = false;
				while(caracter != iteradorDeLinea.DONE && !terminado){
					if(caracter != iteradorDeLinea.DONE && caracter != ';'&& caracter != '.'){
						if(cantidadDePuntos==0){
							lineaAuxiliar1 = lineaAuxiliar1+caracter;
						}else if(cantidadDePuntos == 1){
							lineaAuxiliar2 = lineaAuxiliar2+caracter;
						}else if(cantidadDePuntos==2){
							this.setNombreClase(nombreClase = nombreClase + caracter);
						}
					}else if( caracter=='.'){	
						cantidadDePuntos++;
					}
					caracter = iteradorDeLinea.next();	
				}
				if(cantidadDePuntos==1){
					this.setNombreClase(lineaAuxiliar2); /*LineaAxuliar2 es el nombre de la clase y lineaAuxiliar1 el del paquete*/
					this.setNombrePaquete(lineaAuxiliar1);
				}else if(cantidadDePuntos==2){
					this.setNombrePaquete(lineaAuxiliar1+'.'+lineaAuxiliar2); 
					/*El paquete es la suma de las dos lineas auxiliares y el nombre de la clase esta en nombreClase*/
				}
			}else if(numeroPatron == numeroClase){
				this.setEsClass(true);
				boolean terminado = false;
				while(caracter != iteradorDeLinea.DONE && !terminado){
					if(caracter == '{' || caracter == ' ' || caracter == iteradorDeLinea.DONE){
						terminado = true;
					}else{
						this.setNombreClase(nombreClase = nombreClase + caracter);
					}
					caracter = iteradorDeLinea.next();
				}
			}else if( numeroPatron == numeroPackage){
				String lineaAuxiliar="";
				while(caracter != iteradorDeLinea.DONE){
					if(caracter != iteradorDeLinea.DONE&&caracter != ';'){
						lineaAuxiliar = lineaAuxiliar + caracter.toString();
					}
					caracter = iteradorDeLinea.next();
				}
				this.setNombrePaqueteActual(lineaAuxiliar);
			}else if(numeroPatron == numeroNew){
				boolean terminado = false;
				while(caracter != iteradorDeLinea.DONE && !terminado){
					if(caracter == ' ' || caracter == '(' || caracter == iteradorDeLinea.DONE){
						terminado = true;
					}else{
						this.setNombreClase(this.nombreClase + caracter);
					}
					caracter = iteradorDeLinea.next();
				}
			}
		}if(!encontrePatron && numeroPatron==numeroImport){
			encontrePatron = obtenerPatronEnLineaX(lineaLeida,numeroClase,Class,nombrePaquete,nombreClase,esClass);
		}
		return encontrePatron;
	}

	@SuppressWarnings("static-access")
	private void eliminarCaracteresEnBlanco(String linea) {
		StringCharacterIterator iteradorDeLinea = new StringCharacterIterator(linea);
		Character caracter = new Character(iteradorDeLinea.first());
		while (caracter==' '&& caracter != iteradorDeLinea.DONE){
			caracter = iteradorDeLinea.next();
		}
		String lineaAuxiliar="";
		while(caracter != iteradorDeLinea.DONE){
			if(caracter != iteradorDeLinea.DONE){
				lineaAuxiliar = lineaAuxiliar + caracter.toString();
			}
			caracter = iteradorDeLinea.next();
		}
		linea = lineaAuxiliar;
	}

	void setNombrePaqueteActual(String parametro){
		this.nombrePaqueteActual = parametro;
	}
	void setNombreClase(String parametro){
		this.nombreClase = parametro;
	}
	void setNombrePaquete(String parametro){
		this.nombrePaquete = parametro;
	}

	void setEsClass(boolean parametro){
		this.esClass = parametro;
	}

	/**
	 * Constructor que me genera un Grafo con un tamanio especifico
	 * @param tamanio 
	 */
	public Grafo(int tamanio){
		this.representacionGrafo = new NodoGrafo[tamanio];
		for(int i = 0; i < tamanio; i++)
			this.representacionGrafo [i] = new NodoGrafo("unNombre", 0);
	}

	/**
	 *Se arma la lista de clases, donde tengo la informacion de a que paquete pertenece y las clases de las q depende 
	 *@param ruta ruta de la clase
	 *@param nombreClase nombre de la clase a procesar
	 */

	public NodoGrafo [] componentesDelGrafo(){
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

	public int getCantidadDePaquetes(){
		return this.cantidadDePaquetes;
	}
}
