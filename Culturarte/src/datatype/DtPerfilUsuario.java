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
	
	
	public DtPerfilUsuario(String nickname, String nombre, String apellido, String email, String password,
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
	
	

	
	
	

}
