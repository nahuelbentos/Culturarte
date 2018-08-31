package datatype;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class DtPerfilColaborador extends DtUsuario {

	private ArrayList<DtPropuestaColaborada> colaboracionesHechas;
	
	public DtPerfilColaborador(String nickname, String nombre, String apellido, String email,
			GregorianCalendar fechaNacimiento, String imagen, ArrayList<DtPropuestaColaborada> colaboracionesHechas) {
		super(nickname, nombre, apellido, email, fechaNacimiento, imagen);
		this.colaboracionesHechas = colaboracionesHechas;
	}

	public ArrayList<DtPropuestaColaborada> getColaboracionesHechas() {
		return colaboracionesHechas;
	}


}
