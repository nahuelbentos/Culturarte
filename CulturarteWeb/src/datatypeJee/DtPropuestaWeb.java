package datatypeJee;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import org.apache.tomcat.util.codec.binary.Base64;

import datatype.DtPropuestaMinificado;

public class DtPropuestaWeb extends DtPropuestaMinificado {
	
	private GregorianCalendar fechaPublicacion;
	private GregorianCalendar fechaEspectaculo;
	
	public DtPropuestaWeb(String titulo, String proponente, byte[] imagen, 
			GregorianCalendar fechaPublicacion, GregorianCalendar fechaEspectaculo) {
		super(titulo,proponente,imagen);
		this.fechaEspectaculo = fechaEspectaculo;
		this.fechaPublicacion = fechaPublicacion;
	}
	
	public String getImagenAsBase64() throws UnsupportedEncodingException {
		byte[] encodeBase64 = Base64.encodeBase64(super.getImagen());
        String base64Encoded = new String(encodeBase64, "UTF-8");
        return base64Encoded;
	}

	public String getFechaPublicacionAsString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
		
		return sdf.format(this.fechaPublicacion.getTime());
	}
	
	public String getFechaEspectaculoAsString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy hh:mm");
		
		return sdf.format(this.fechaEspectaculo.getTime());
	}
	
}