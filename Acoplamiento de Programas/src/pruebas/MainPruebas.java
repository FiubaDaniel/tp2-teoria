package pruebas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.StringCharacterIterator;

import detectorDeAcoplamiento.Acoplamiento;
import detectorDeAcoplamiento.Grafo;

public class MainPruebas {

	public static void main(String[] args) throws IOException{
		
		String ruta = "/home/daniel/ZPruebaBorrar";
		Grafo grafo = new Grafo(ruta);
		Acoplamiento acoplamiento = new Acoplamiento(grafo);
		acoplamiento.CalcularComponentesConexas();
	}

		/*Implementar Una ventana de busqueda de patrones*/

		/*int numeroClase = 0;
		int numeroImport = 1;
		int numeroPackage = 2;
		int numeroNew = 3;
		String Package = "package";
		String Import = "import";
		String New = "new";
		String Class = "class";
		String patron;
		String ventana;




		patron = New;
		int numeroPatron = 3;
		boolean encontrePatron = false;

		String ruta = "/home/daniel/ZPruebaBorrar";
		File CarpetaClases = new File(ruta);
		File listaDeClases[]= CarpetaClases.listFiles();
		for(int i=0;i<listaDeClases.length;i++){

			File archivoClase = new File(ruta+"/"+listaDeClases[i].getName());
			BufferedReader Clase ;
			Clase = new BufferedReader( new FileReader( archivoClase ) );
			ventana = Clase.readLine();
			int inicial = 0;
			int finall = patron.length();
			while(!encontrePatron && Clase.ready()){
				if(!ventana.isEmpty()){
					while(!encontrePatron && finall < ventana.length()){
						String lineaAux = ventana.substring(inicial, finall);
						if(lineaAux.compareTo(patron)==0){
							encontrePatron = true;
						}else{
							inicial++;
							finall++;
						}
					}

				}
				if(!encontrePatron){
					inicial = 0;
					finall = patron.length();
					ventana = Clase.readLine();
				}
			}
			if(encontrePatron){
				String aux = ventana.substring(finall+1);
				eliminarCaracteresEnBlanco(aux);
				StringCharacterIterator iteradorDeLinea = new StringCharacterIterator (aux);
				Character caracter = new Character(iteradorDeLinea.first());
				if(numeroPatron == numeroImport){
					String lineaAuxiliar1="";
					String lineaAuxiliar2="";
					String nombreClase="";
					int cantidadDePuntos = 0;
					boolean terminado = false;
					while(caracter != iteradorDeLinea.DONE && !terminado){
						if(caracter != iteradorDeLinea.DONE && caracter != ';'&& caracter != '.'){
							if(cantidadDePuntos==0){
								lineaAuxiliar1 = lineaAuxiliar1+caracter;
							}else if(cantidadDePuntos == 1){
								lineaAuxiliar2 = lineaAuxiliar2+caracter;
							}else if(cantidadDePuntos==2){
								nombreClase = nombreClase + caracter;
							}
						}else if( caracter=='.'){	
							cantidadDePuntos++;
						}
						caracter = iteradorDeLinea.next();	
					}
					if(cantidadDePuntos==1){
						nombreClase = lineaAuxiliar2; 
					}else if(cantidadDePuntos==2){
						lineaAuxiliar1 = lineaAuxiliar1+'.'+lineaAuxiliar2; 
						
					}else{
						
					}
					System.out.println("Paquete: "+lineaAuxiliar1);
					System.out.println("Clase: "+nombreClase);
				}else if(numeroPatron == numeroClase){
					boolean terminado = false;
					String nombreClase = "";
					while(caracter != iteradorDeLinea.DONE && !terminado){
						if(caracter == '{' || caracter == ' ' || caracter == iteradorDeLinea.DONE){
							terminado = true;
						}else{
							nombreClase = nombreClase + caracter;
						}
						caracter = iteradorDeLinea.next();
					}
					System.out.println("Clase: "+nombreClase);
				}else if( numeroPatron == numeroPackage){
					String lineaAuxiliar="";
					while(caracter != iteradorDeLinea.DONE){
						if(caracter != iteradorDeLinea.DONE&&caracter != ';'){
							lineaAuxiliar = lineaAuxiliar + caracter.toString();
						}
						caracter = iteradorDeLinea.next();
					}
					System.out.println("Paquete quedo como: "+lineaAuxiliar );
				}else if(numeroPatron == numeroNew){
					boolean terminado = false;
					String nombreClase = "";
					while(caracter != iteradorDeLinea.DONE && !terminado){
						if(caracter == ' ' || caracter == '(' || caracter == iteradorDeLinea.DONE){
							terminado = true;
						}else{
							nombreClase = nombreClase + caracter;
						}
						caracter = iteradorDeLinea.next();
					}
					System.out.println("Clase despues de new: "+nombreClase);
				}
			}
		}
	}			

	private static	void eliminarCaracteresEnBlanco(String linea) {
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
	/*pruebas con string */ 
	/*
		String lineaPrueba = "package NodoGrafo";
		StringCharacterIterator iteradorDeLinea = new StringCharacterIterator(lineaPrueba);
		String nombre = "";
		Character caracter = new Character(iteradorDeLinea.first());
		while(caracter!= 'e' && caracter != iteradorDeLinea.DONE){
			caracter = iteradorDeLinea.next();
		void eliminarCaracteresEnBlanco(String linea) {
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
		}
		linea = lineaAuxiliar;
	}	//System.out.println((int)caracter);
		}*/
	/*tengo el index en el caracter e*/
	//caracter = iteradorDeLinea.next();/*El siguiente a e*/
	/*while(caracter== ' ' && caracter != iteradorDeLinea.DONE ){
			caracter = iteradorDeLinea.next();
		}	*/
	/*Ahora agrego los caracteres al String */
	/*System.out.println("caracter "+ caracter.toString());
		nombre = nombre+(caracter.toString());
		//nombre.concat(caracter.toString());
		while(caracter != iteradorDeLinea.DONE){
			caracter = iteradorDeLinea.next();
			if(caracter!= iteradorDeLinea.DONE){
				nombre = nombre+(caracter.toString());
				System.out.println("nombre paquete parcial "+ nombre);
			}
		}
		System.out.println("Nombre paquete: "+nombre);
		/*ProbarObtenerArchivos prueba1 = new ProbarObtenerArchivos();*/
	/*String ruta = "/home/daniel/ZPruebaBorrar";
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
		}*/
	//Grafo grafo = new Grafo(ruta);
}

