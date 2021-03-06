package datatype;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class DtPerfilUsuario extends DtUsuario{
	private ArrayList<DtProponente> seguidoresProponentes;
	private ArrayList<DtColaborador> seguidoresColaboradores;
	private ArrayList<DtProponente> seguidosProponentes;
	private ArrayList<DtColaborador> seguidosColaboradores;
	private ArrayList<DtPropuesta> propuestasFavoritas;
	
	private ArrayList<DtPropuesta> propuestasPublicadas;
	private ArrayList<DtPropuesta> propuestasCreadas;
	private ArrayList<DtPropuestaColaborada> propuestasColaboradas;
	private ArrayList<DtColaboracion> colaboracionesHechas;
	
	
	public DtPerfilUsuario(String nickname, String nombre, String apellido, String email, char[] password,
			GregorianCalendar fechaNacimiento, byte[] imagen, ArrayList<DtProponente> seguidoresProponentes,
			ArrayList<DtColaborador> seguidoresColaboradores, ArrayList<DtProponente> seguidosProponentes,
			ArrayList<DtColaborador> seguidosColaboradores, ArrayList<DtPropuesta> propuestasFavoritas,
			ArrayList<DtPropuesta> propuestasPublicadas, ArrayList<DtPropuesta> propuestasCreadas,
			ArrayList<DtPropuestaColaborada> propuestasColaboradas, ArrayList<DtColaboracion> colaboracionesHechas) {
		super(nickname, nombre, apellido, email, password, fechaNacimiento, imagen);
		this.seguidoresProponentes = seguidoresProponentes;
		this.seguidoresColaboradores = seguidoresColaboradores;
		this.seguidosProponentes = seguidosProponentes;
		this.seguidosColaboradores = seguidosColaboradores;
		this.propuestasFavoritas = propuestasFavoritas;
		this.propuestasPublicadas = propuestasPublicadas;
		this.propuestasCreadas = propuestasCreadas;
		this.propuestasColaboradas = propuestasColaboradas;
		this.colaboracionesHechas = colaboracionesHechas;
	}
	
	public DtPerfilUsuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArrayList<DtProponente> getSeguidoresProponentes() {
		return seguidoresProponentes;
	}
	public ArrayList<DtColaborador> getSeguidoresColaboradores() {
		return seguidoresColaboradores;
	}
	public ArrayList<DtProponente> getSeguidosProponentes() {
		return seguidosProponentes;
	}
	public ArrayList<DtColaborador> getSeguidosColaboradores() {
		return seguidosColaboradores;
	}
	public ArrayList<DtPropuesta> getPropuestasFavoritas() {
		return propuestasFavoritas;
	}
	public ArrayList<DtPropuesta> getPropuestasPublicadas() {
		return propuestasPublicadas;
	}
	public ArrayList<DtPropuesta> getPropuestasCreadas() {
		return propuestasCreadas;
	}
	public ArrayList<DtPropuestaColaborada> getPropuestasColaboradas() {
		return propuestasColaboradas;
	}
	public ArrayList<DtColaboracion> getColaboracionesHechas() {
		return colaboracionesHechas;
	}

	public void setSeguidoresProponentes(ArrayList<DtProponente> seguidoresProponentes) {
		this.seguidoresProponentes = seguidoresProponentes;
	}

	public void setSeguidoresColaboradores(ArrayList<DtColaborador> seguidoresColaboradores) {
		this.seguidoresColaboradores = seguidoresColaboradores;
	}

	public void setSeguidosProponentes(ArrayList<DtProponente> seguidosProponentes) {
		this.seguidosProponentes = seguidosProponentes;
	}

	public void setSeguidosColaboradores(ArrayList<DtColaborador> seguidosColaboradores) {
		this.seguidosColaboradores = seguidosColaboradores;
	}

	public void setPropuestasFavoritas(ArrayList<DtPropuesta> propuestasFavoritas) {
		this.propuestasFavoritas = propuestasFavoritas;
	}

	public void setPropuestasPublicadas(ArrayList<DtPropuesta> propuestasPublicadas) {
		this.propuestasPublicadas = propuestasPublicadas;
	}

	public void setPropuestasCreadas(ArrayList<DtPropuesta> propuestasCreadas) {
		this.propuestasCreadas = propuestasCreadas;
	}

	public void setPropuestasColaboradas(ArrayList<DtPropuestaColaborada> propuestasColaboradas) {
		this.propuestasColaboradas = propuestasColaboradas;
	}

	public void setColaboracionesHechas(ArrayList<DtColaboracion> colaboracionesHechas) {
		this.colaboracionesHechas = colaboracionesHechas;
	}

	public DtPerfilUsuario(String nickname, String nombre, String apellido, String email, char[] password,
			GregorianCalendar fechaNacimiento, byte[] imagen) {
		super(nickname, nombre, apellido, email, password, fechaNacimiento, imagen);
	}
	
	

}
