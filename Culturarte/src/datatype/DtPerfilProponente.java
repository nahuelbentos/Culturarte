package datatype;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class DtPerfilProponente extends DtProponente{

	private ArrayList<DtPropuesta> prPublicadas;
	private ArrayList<DtPropuesta> prCanceladas;
	private ArrayList<DtPropuesta> prEnFinanciacion;
	private ArrayList<DtPropuesta> prFinanciadas;
	private ArrayList<DtPropuesta> prNoFinanciadas;
	private ArrayList<DtPropuesta> prIngresadas;

	public DtPerfilProponente(String nickname, String nombre, String apellido, String email, char[] password,
			GregorianCalendar fechaNacimiento, byte[] imagen, String direccion, String biografia, String sitioWeb,
			ArrayList<DtPropuesta> prIngresadas,ArrayList<DtPropuesta> prPublicadas,ArrayList<DtPropuesta> prCanceladas,
			ArrayList<DtPropuesta> prEnFinanciacion,ArrayList<DtPropuesta> prFinanciadas,ArrayList<DtPropuesta> prNoFinanciadas) {
		super(nickname, nombre, apellido, email, password, fechaNacimiento, imagen, direccion, biografia, sitioWeb);
		// TODO Auto-generated constructor stub
		this.prPublicadas = prPublicadas;
		this.prCanceladas = prCanceladas;
		this.prEnFinanciacion = prEnFinanciacion;
		this.prFinanciadas = prFinanciadas;
		this.prNoFinanciadas = prNoFinanciadas;
		this.prIngresadas = prIngresadas;
	}

	public ArrayList<DtPropuesta> getPrIngresadas() {
		return prIngresadas;
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

	public void setPrPublicadas(ArrayList<DtPropuesta> prPublicadas) {
		this.prPublicadas = prPublicadas;
	}

	public void setPrCanceladas(ArrayList<DtPropuesta> prCanceladas) {
		this.prCanceladas = prCanceladas;
	}

	public void setPrEnFinanciacion(ArrayList<DtPropuesta> prEnFinanciacion) {
		this.prEnFinanciacion = prEnFinanciacion;
	}

	public void setPrFinanciadas(ArrayList<DtPropuesta> prFinanciadas) {
		this.prFinanciadas = prFinanciadas;
	}

	public void setPrNoFinanciadas(ArrayList<DtPropuesta> prNoFinanciadas) {
		this.prNoFinanciadas = prNoFinanciadas;
	}

	public void setPrIngresadas(ArrayList<DtPropuesta> prIngresadas) {
		this.prIngresadas = prIngresadas;
	}

	public DtPerfilProponente(String nickname, String nombre, String apellido, String email, char[] password,
			GregorianCalendar fechaNacimiento, byte[] imagen, String direccion, String biografia, String sitioWeb) {
		super();
	}	
	
}
