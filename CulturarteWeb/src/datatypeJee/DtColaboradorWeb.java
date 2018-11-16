package datatypeJee;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import org.apache.tomcat.util.codec.binary.Base64;

import publicadores.DtColaborador;

@SuppressWarnings("serial")
public class DtColaboradorWeb extends DtColaborador {

	public DtColaboradorWeb(String nickname, String nombre, String apellido, String email,
			Calendar fechaNacimiento, byte[] imagen) {
		super();
		this.setNickname(nickname);
		this.setNombre(nombre);
		this.setApellido(apellido);
		this.setEmail(email);
		this.setFechaNacimiento(fechaNacimiento);
		this.setImagen(imagen);
	}
	
	public String getImagenAsBase64() throws UnsupportedEncodingException {
		byte[] encodeBase64 = Base64.encodeBase64(super.getImagen());
	    String base64Encoded = new String(encodeBase64, "UTF-8");
        return base64Encoded;
	}
}
