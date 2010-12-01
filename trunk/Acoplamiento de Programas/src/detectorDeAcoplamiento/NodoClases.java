package detectorDeAcoplamiento;


/***********************************************************************************************/
/*Esta clase no va habria q eliminarla */
/***********************************************************************************************/
import java.util.ArrayList;

public class NodoClases {

	private String IdPaquete;
	private String IdClase;
	private int IdPaqueteInterno;
	private ArrayList<String> DependienteDeClases;
	
	public NodoClases(String IdPaquete,String IdClase,int IdInterno){
		this.setIdClase(IdClase);
		this.setIdPaquete(IdPaquete);
		this.setIdPaqueteInterno(IdInterno);
		this.setDependienteDeClases(new ArrayList<String>());
	}

	public void setIdPaquete(String idPaquete) {
		IdPaquete = idPaquete;
	}

	public String getIdPaquete() {
		return IdPaquete;
	}

	public void setIdClase(String idClase) {
		IdClase = idClase;
	}

	public String getIdClase() {
		return IdClase;
	}

	public void setIdPaqueteInterno(int idPaqueteInterno) {
		IdPaqueteInterno = idPaqueteInterno;
	}

	public int getIdPaqueteInterno() {
		return IdPaqueteInterno;
	}

	public void setDependienteDeClases(ArrayList<String> dependienteDeClases) {
		DependienteDeClases = dependienteDeClases;
	}

	public ArrayList<String> getDependienteDeClases() {
		return DependienteDeClases;
	}

}
