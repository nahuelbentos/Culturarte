package datatype;

import java.util.GregorianCalendar;

public class DtColaborador extends DtUsuario {

	public DtColaborador(String nickname, String nombre, String apellido, String email, GregorianCalendar fechaNacimiento,
			String imagen) {
		super(nickname, nombre, apellido, email, fechaNacimiento, imagen);
	}

}
