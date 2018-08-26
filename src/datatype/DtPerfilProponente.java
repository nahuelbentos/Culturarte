package datatype;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class DtPerfilProponente extends DtProponente{

	private ArrayList<DtPropuesta> prPublicadas;
	private ArrayList<DtPropuesta> prCanceladas;
	private ArrayList<DtPropuesta> prEnFinanciacion;
	private ArrayList<DtPropuesta> prFinanciadas;
	private ArrayList<DtPropuesta> prNoFinanciadas;
	
	
	public DtPerfilProponente(String nickname, String nombre, String apellido, String email,
			GregorianCalendar fechaNacimiento, String imagen, String direccion, String biografia, String sitioWeb,
			ArrayList<DtPropuesta> prPublicadas,ArrayList<DtPropuesta> prCanceladas,ArrayList<DtPropuesta> prEnFinanciacion,
			ArrayList<DtPropuesta> prFinanciadas,ArrayList<DtPropuesta> prNoFinanciadas) {
		super(nickname, nombre, apellido, email, fechaNacimiento, imagen, direccion, biografia, sitioWeb);
		// TODO Auto-generated constructor stub
		this.prPublicadas = prPublicadas;
		this.prCanceladas = prCanceladas;
		this.prEnFinanciacion = prEnFinanciacion;
		this.prFinanciadas = prFinanciadas;
		this.prNoFinanciadas = prNoFinanciadas;
	}


	public ArrayList<DtPropuesta> getPrPublicadas() {
		return prPublicadas;
	}


	public ArrayList<DtPropuesta> getPrCanceladas() {
		return prCanceladas;
	}


	public ArrayList<DtPropuesta> getPrEnFinanciacion() {
		return prEnFinanciacion;
	}


	public ArrayList<DtPropuesta> getPrFinanciadas() {
		return prFinanciadas;
	}


	public ArrayList<DtPropuesta> getPrNoFinanciadas() {
		return prNoFinanciadas;
	}	
	
}
