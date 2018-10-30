package datatypeJee;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.tomcat.util.codec.binary.Base64;

import datatype.DtPropuestaMinificado;
import publicadores.EstadoPropuesta;

public class DtPropuestaWeb extends DtPropuestaMinificado {
	
	private GregorianCalendar fechaPublicacion;
	private GregorianCalendar fechaEspectaculo;
	private GregorianCalendar fechaFinalizacion;
	private EstadoPropuesta estadoActual;
	
	public DtPropuestaWeb(String titulo, String proponente, byte[] imagen, 
			GregorianCalendar fechaPublicacion, GregorianCalendar fechaEspectaculo, GregorianCalendar fechaFinalizacion, EstadoPropuesta estadoActual) {
		super(titulo,proponente,imagen);
		this.fechaEspectaculo = fechaEspectaculo;
		this.fechaPublicacion = fechaPublicacion;
		this.fechaFinalizacion = fechaFinalizacion;
		this.estadoActual = estadoActual;
	}
	
	public DtPropuestaWeb(String titulo, String nickname, byte[] imagen, 
			Calendar fechaPublicacion, Calendar fechaEspectaculo, Calendar fechaFinalizacion, publicadores.EstadoPropuesta estadoActual) {
		super(titulo,nickname,imagen);
		this.fechaEspectaculo = (GregorianCalendar) fechaEspectaculo;
		this.fechaPublicacion = (GregorianCalendar) fechaPublicacion;
		this.fechaFinalizacion = (GregorianCalendar) fechaFinalizacion;
		this.estadoActual = estadoActual;
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
	
	public GregorianCalendar getFechaFinalizacion() {
		return this.fechaFinalizacion;
	}
	
	public String getFechaFinalizacionAsString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy hh:mm");
		
		return sdf.format(this.fechaFinalizacion.getTime());
	}
	
	public EstadoPropuesta getEstadoPropuesta() {
		return estadoActual;
	}
	
}
