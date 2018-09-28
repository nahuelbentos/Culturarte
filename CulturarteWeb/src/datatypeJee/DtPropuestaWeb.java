package datatypeJee;

import java.io.UnsupportedEncodingException;

import org.apache.tomcat.util.codec.binary.Base64;

import datatype.DtPropuestaMinificado;

public class DtPropuestaWeb extends DtPropuestaMinificado {
	
	public DtPropuestaWeb(String titulo, String proponente, byte[] imagen) {
		super(titulo,proponente,imagen);
	}
	
	public String getImagenAsBase64() throws UnsupportedEncodingException {
		byte[] encodeBase64 = Base64.encodeBase64(super.getImagen());
        String base64Encoded = new String(encodeBase64, "UTF-8");
        return base64Encoded;
	}

}
