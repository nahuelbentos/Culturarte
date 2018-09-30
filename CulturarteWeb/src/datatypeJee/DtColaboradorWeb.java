package datatypeJee;

import java.io.UnsupportedEncodingException;
import java.util.GregorianCalendar;

import org.apache.tomcat.util.codec.binary.Base64;

import datatype.DtColaborador;

public class DtColaboradorWeb extends DtColaborador {

	public DtColaboradorWeb(String nickname, String nombre, String apellido, String email, String password,
			GregorianCalendar fechaNacimiento, byte[] imagen) {
		super(nickname, nombre, apellido, email, password, fechaNacimiento, imagen);
	}
	
	public String getImagenAsBase64() throws UnsupportedEncodingException {
		byte[] encodeBase64 = Base64.encodeBase64(super.getImagen());
	    String base64Encoded = new String(encodeBase64, "UTF-8");
        return base64Encoded;
	}
}
