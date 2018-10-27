package datatype;

import java.util.ArrayList;
import java.util.GregorianCalendar;



public class DtPropuesta {
    
   private String titulo;
   private String descripcion;
   private byte[] imagen;
   private float montoNecesario;
   private GregorianCalendar fechaPublicacion;
   private GregorianCalendar fechaEspecatulo;
   private String lugar;
   private float precioEntrada;
   private TipoRetorno tipo;
   private float recaudado;
   private EstadoPropuesta estadoActual;

   // PseudoAtributos
   private DtProponente proponenteACargo;
   private ArrayList<DtEstado> estadoHistorial;
   private DtCategoria categoria;
   private ArrayList<DtColaboracion> colaboraciones;
   
   public DtPropuesta(String titulo, String descripcion, byte[] imagen, float montoNecesario,
		GregorianCalendar fechaPublicacion, GregorianCalendar fechaEspecatulo, String lugar, float precioEntrada,
		TipoRetorno tipo, float recaudado, DtProponente proponenteACargo, EstadoPropuesta estadoActual,
		ArrayList<DtEstado> estadoHistorial, DtCategoria categoria, ArrayList<DtColaboracion> colaboraciones) {
	super();
	this.titulo = titulo;
	this.descripcion = descripcion;
	this.imagen = imagen;
	this.montoNecesario = montoNecesario;
	this.fechaPublicacion = fechaPublicacion;
	this.fechaEspecatulo = fechaEspecatulo;
	this.lugar = lugar;
	this.precioEntrada = precioEntrada;
	this.tipo = tipo;
	this.recaudado = recaudado;
	this.proponenteACargo = proponenteACargo;
	this.estadoActual = estadoActual;
	this.estadoHistorial = estadoHistorial;
	this.categoria = categoria;
	this.colaboraciones = colaboraciones;
}
   
	public DtPropuesta(String titulo) {
		super();
		this.titulo = titulo;
	}
	
	public String getTitulo() {
		return titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public float getMontoNecesario() {
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

	public float getPrecioEntrada() {
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

	public EstadoPropuesta getEstadoActual() {
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

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public void setMontoNecesario(float montoNecesario) {
		this.montoNecesario = montoNecesario;
	}

	public void setFechaPublicacion(GregorianCalendar fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public void setFechaEspecatulo(GregorianCalendar fechaEspecatulo) {
		this.fechaEspecatulo = fechaEspecatulo;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public void setPrecioEntrada(float precioEntrada) {
		this.precioEntrada = precioEntrada;
	}

	public void setTipo(TipoRetorno tipo) {
		this.tipo = tipo;
	}

	public void setRecaudado(float recaudado) {
		this.recaudado = recaudado;
	}

	public void setEstadoActual(EstadoPropuesta estadoActual) {
		this.estadoActual = estadoActual;
	}

	public void setProponenteACargo(DtProponente proponenteACargo) {
		this.proponenteACargo = proponenteACargo;
	}

	public void setEstadoHistorial(ArrayList<DtEstado> estadoHistorial) {
		this.estadoHistorial = estadoHistorial;
	}

	public void setCategoria(DtCategoria categoria) {
		this.categoria = categoria;
	}

	public DtPropuesta() {
		super();
	}
	
}
