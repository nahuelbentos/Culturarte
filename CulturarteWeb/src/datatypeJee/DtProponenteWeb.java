package datatypeJee;

import java.io.UnsupportedEncodingException;
import java.util.GregorianCalendar;

import org.apache.tomcat.util.codec.binary.Base64;

import datatype.DtProponente;

public class DtProponenteWeb extends DtProponente {

	public DtProponenteWeb(String nickname, String nombre, String apellido, String email, char[] password,
			GregorianCalendar fechaNacimiento, byte[] imagen, String direccion, String biografia, String sitioWeb) {
		super(nickname, nombre, apellido, email, password, fechaNacimiento, imagen, direccion, biografia, sitioWeb);
		// TODO Auto-generated constructor stub
	}

	public String getImagenAsBase64() throws UnsupportedEncodingException {
		byte[] encodeBase64 = Base64.encodeBase64(super.getImagen());
        String base64Encoded = new String(encodeBase64, "UTF-8");
        return base64Encoded;
	}
}