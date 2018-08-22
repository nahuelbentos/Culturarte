package logica;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Colaborador extends Usuario {

	public Colaborador(String nickname, String nombre, GregorianCalendar fechaNacimiento, String correoElectronico,
			String apellido) {
		super(nickname, nombre, fechaNacimiento, correoElectronico, apellido);
	}

	// PseudoAtributos
	private ArrayList<Colaboracion> colaboracionesHechas;
	
}
