package datatype;

import java.util.ArrayList;
import java.util.GregorianCalendar;



public class DtPropuesta {
    
   private String titulo;
   private String descripcion;
   private String imagen;
   private double montoNecesario;
   private GregorianCalendar fechaPublicacion;
   private GregorianCalendar fechaEspecatulo;
   private String lugar;
   private double precioEntrada;
   private TipoRetorno tipo;
   private float recaudado;

   // PseudoAtributos
   private DtProponente proponenteACargo;
   private DtEstado estadoActual;
   private ArrayList<DtEstado> estadoHistorial;
   private DtCategoria categoria;
   private ArrayList<DtColaboracion> colaboraciones;
   
	

	public DtPropuesta(String titulo, String descripcion, String imagen, double montoNecesario2,
		GregorianCalendar fechaPublicacion, GregorianCalendar fechaEspecatulo, String lugar, double precioEntrada2,
		TipoRetorno tipo, float recaudado, DtProponente proponenteACargo, DtEstado estadoActual,
		ArrayList<DtEstado> estadoHistorial, DtCategoria categoria, ArrayList<DtColaboracion> colaboraciones) {
	super();
	this.titulo = titulo;
	this.descripcion = descripcion;
	this.imagen = imagen;
	this.montoNecesario = montoNecesario2;
	this.fechaPublicacion = fechaPublicacion;
	this.fechaEspecatulo = fechaEspecatulo;
	this.lugar = lugar;
	this.precioEntrada = precioEntrada2;
	this.tipo = tipo;
	this.recaudado = recaudado;
	this.proponenteACargo = proponenteACargo;
	this.estadoActual = estadoActual;
	this.estadoHistorial = estadoHistorial;
	this.categoria = categoria;
	this.colaboraciones = colaboraciones;
}

	public String getTitulo() {
		return titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getImagen() {
		return imagen;
	}

	public double getMontoNecesario() {
		return montoNecesario;
	}

	public GregorianCalendar getFechaPublicacion() {
		return fechaPublicacion;
	}

	public GregorianCalendar getFechaEspecatulo() {
		return fechaEspecatulo;
	}

	public String getLugar() {
		return lugar;
	}

	public double getPrecioEntrada() {
		return precioEntrada;
	}

	public TipoRetorno getTipo() {
		return tipo;
	}

	public float getRecaudado() {
		return recaudado;
	}

	public DtProponente getProponenteACargo() {
		return proponenteACargo;
	}

	public DtEstado getEstadoActual() {
		return estadoActual;
	}

	public ArrayList<DtEstado> getEstadoHistorial() {
		return estadoHistorial;
	}

	public DtCategoria getCategoria() {
		return categoria;
	}

	public ArrayList<DtColaboracion> getColaboraciones() {
		return colaboraciones;
	}

	public void setColaboraciones(ArrayList<DtColaboracion> colaboraciones) {
		this.colaboraciones = colaboraciones;
	}
	
	
	
	
}

