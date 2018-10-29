package datatype;

import java.util.GregorianCalendar;

public class DtColaborador extends DtUsuario {

	public DtColaborador(String nickname, String nombre, String apellido, String email, char[] password, 
			GregorianCalendar fechaNacimiento, byte[] imagen) {
		super(nickname, nombre, apellido, email, password, fechaNacimiento, imagen);
	}
	
	public DtColaborador() {
		super();
	}
	
}
