package logica;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Colaborador extends Usuario {

	public Colaborador(String nickname, String nombre, GregorianCalendar fechaNacimiento, String correoElectronico,
			String apellido, String imagen) {
		super(nickname, nombre, fechaNacimiento, correoElectronico, apellido, imagen);
	}

	// PseudoAtributos
	private ArrayList<Colaboracion> colaboracionesHechas;
	
}
