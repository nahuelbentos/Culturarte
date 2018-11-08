package datatypeJee;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import org.apache.tomcat.util.codec.binary.Base64;

import publicadores.DtProponente;

public class DtProponenteWeb extends DtProponente {

	public DtProponenteWeb(String nickname, String nombre, String apellido, String email,
			Calendar fechaNacimiento, byte[] imagen, String direccion, String biografia, String sitioWeb) {
//		super(nickname, nombre, apellido, email, null, (GregorianCalendar) fechaNacimiento, imagen, direccion, biografia, sitioWeb);
		super();
		this.setNickname(nickname);
		this.setNombre(nombre);
		this.setApellido(apellido);
		this.setEmail(email);
		this.setFechaNacimiento(fechaNacimiento);
		this.setImagen(imagen);
		this.setDireccion(direccion);
		this.setBiografia(biografia);
		this.setSitioWeb(sitioWeb);
	}

	public String getImagenAsBase64() throws UnsupportedEncodingException {
		byte[] encodeBase64 = Base64.encodeBase64(super.getImagen());
        String base64Encoded = new String(encodeBase64, "UTF-8");
        return base64Encoded;
	}
}
