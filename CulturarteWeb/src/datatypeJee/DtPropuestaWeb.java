package datatypeJee;

import java.io.UnsupportedEncodingException;

import org.apache.tomcat.util.codec.binary.Base64;

public class DtPropuestaWeb {
	
	private String titulo;
	private byte[] imagen;
	
	public DtPropuestaWeb(String titulo, byte[] imagen) {
		super();
		this.titulo = titulo;
		this.imagen = imagen;
	}
	
	public String getTitulo() {
		return titulo;
	}
	public byte[] getImagen() {
		return imagen;
	}
	
	public String getImagenAsBase64() throws UnsupportedEncodingException {
		byte[] encodeBase64 = Base64.encodeBase64(this.imagen);
        String base64Encoded = new String(encodeBase64, "UTF-8");
        return base64Encoded;
	}

}
