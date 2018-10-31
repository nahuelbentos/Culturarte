package datatypeJee;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.tomcat.util.codec.binary.Base64;

import publicadores.DtPropuestaMinificado;
import publicadores.EstadoPropuesta;

@SuppressWarnings("serial")
public class DtPropuestaWeb extends DtPropuestaMinificado {
	
	private GregorianCalendar fechaPublicacion;
	private GregorianCalendar fechaEspectaculo;
	private GregorianCalendar fechaFinalizacion;
	private EstadoPropuesta estadoActual;
	private Boolean esFavoritaDelUsuarioActual;
	
	public DtPropuestaWeb(String titulo, String proponente, byte[] imagen, 
			Calendar fechaPublicacion, Calendar fechaEspectaculo, Calendar fechaFinalizacion, EstadoPropuesta estadoActual) {
		super.setTitulo(titulo);
		super.setProponente(proponente);
		super.setImagen(imagen);
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

	public Boolean getEsFavoritaDelUsuarioActual() {
		return esFavoritaDelUsuarioActual;
	}

	public void setEsFavoritaDelUsuarioActual(Boolean esFavoritaDelUsuarioActual) {
		this.esFavoritaDelUsuarioActual = esFavoritaDelUsuarioActual;
	}
	
}
