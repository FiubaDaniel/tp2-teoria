package detectorDeAcoplamiento;

import java.io.File;

public class Grafo {

	/*
	 * La aplicación debe recibir como parámetro la ruta a un directorio donde se encontrán los archivos a procesar
	 */

	public Grafo(String ruta){

		//System.out.println(ruta);
		//String page = "pageRank";
		File CarpetaPaquetes = new File(ruta);
		File listaDePaquetes[] = CarpetaPaquetes.listFiles(); //Aca tengo los paquetes
		File listaDeClases[];
		for(int i=0;i<listaDePaquetes.length;i++){
			String nombrePaquete = listaDePaquetes[i].getName();
			File CarpetaClasesDePaqueteX = new File(ruta+"/"+nombrePaquete);
			listaDeClases = CarpetaClasesDePaqueteX.listFiles(); //Aca tengo las clases de un Paquete
			for(int j = 0; j<listaDeClases.length;i++){
				String nombreClase = listaDeClases[i].getName();
				procesarClase(ruta,nombrePaquete,nombreClase);
			}
			/*System.out.println("nombre: "+listaDeFiles[i]);
			System.out.println(listaDeFiles[i].getName());
			if(page.compareTo((listaDeFiles[i].getName()))==0){
				String ruta2 = ruta+"/"+listaDeFiles[i].getName();
				System.out.println("RUTA: "+ruta2);
				file2 = new File(ruta2);
			}*/
			
			
		}
		/*
		listaDeFiles2 = file2.listFiles();
		if(listaDeFiles2 != null){
			System.out.println("Tamaño lista: "+listaDeFiles2.length);
		}else {
			System.out.println("OUCH");
		}
		for(int i=0;i<listaDeFiles2.length;i++){
			System.out.println("nombre: "+listaDeFiles2[i]);
		}*/
	}

	private void procesarClase(String ruta, String nombrePaquete, String nombreClase) {

		
	}

}
