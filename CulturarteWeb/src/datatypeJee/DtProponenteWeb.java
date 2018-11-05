package datatypeJee;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.tomcat.util.codec.binary.Base64;

import publicadores.DtProponente;

public class DtProponenteWeb extends DtProponente {

	public DtProponenteWeb(String nickname, String nombre, String apellido, String email,
			Calendar fechaNacimiento, byte[] imagen, String direccion, String biografia, String sitioWeb) {
		super();
		super.setNickname(nickname);
		super.setNombre(nombre);
		super.setApellido(apellido);
		super.setEmail(email);
		super.setFechaNacimiento((GregorianCalendar) fechaNacimiento);
		super.setImagen(imagen);
		super.setDireccion(direccion);
		super.setBiografia(biografia);
		super.setSitioWeb(sitioWeb);

		// TODO Auto-generated constructor stub
	}

	public String getImagenAsBase64() throws UnsupportedEncodingException {
		byte[] encodeBase64 = Base64.encodeBase64(super.getImagen());
        String base64Encoded = new String(encodeBase64, "UTF-8");
        return base64Encoded;
	}
}
