package datatype;

import java.awt.Image;
import java.util.Date;
import java.util.GregorianCalendar;

public class DtColaborador extends DtUsuario {

	public DtColaborador(String nickname, String nombre, String apellido, String email, GregorianCalendar fechaNacimiento,
			Image imagen) {
		super(nickname, nombre, apellido, email, fechaNacimiento, imagen);
	}

}
