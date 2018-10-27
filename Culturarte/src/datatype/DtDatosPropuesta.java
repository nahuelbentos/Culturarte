package datatype;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class DtDatosPropuesta {
	private String titulo;
	private String descripcion;
	private byte[] imagen;
	private double montoNecesario;
	private GregorianCalendar fechaPublicacion;
	private GregorianCalendar fechaEspecatulo;
	private String lugar;
	private double precioEntrada;
	private TipoRetorno tipo;
	private double recaudado;
	private DtProponente proponenteACargo;
	private String categoria;
	private GregorianCalendar fechaFinalizacion;
	private EstadoPropuesta estadoActual;
	
	//Pseudoatributos
	private ArrayList<String> colaboradores;
	
	public DtDatosPropuesta(String titulo, String descripcion, byte[] imagen, double montoNecesario,
			GregorianCalendar fechaPublicacion, GregorianCalendar fechaEspecatulo, String lugar, double precioEntrada,
			TipoRetorno tipo, double recaudado, ArrayList<String> colaboradores, DtProponente proponenteACargo,
			String categoria, GregorianCalendar fechaFinalizacion, EstadoPropuesta estadoActual) {
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
		this.colaboradores = colaboradores;
		this.proponenteACargo = proponenteACargo;
		this.categoria = categoria;
		this.fechaFinalizacion = fechaFinalizacion;
		this.estadoActual = estadoActual;
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
	
	public double getMontoNecesario() {
		return montoNecesario;
	}
	
	public GregorianCalendar getFechaPublicacion() {
		return fechaPublicacion;
	}
	
	public GregorianCalendar getFechaEspecatulo() {
		return fechaEspecatulo;
	}
	
	public GregorianCalendar getFechaFinalizacion() {
		return fechaFinalizacion;
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
	
	public double getRecaudado() {
		return recaudado;
	}
	
	public DtProponente getProponenteACargo() {
		return proponenteACargo;
	}

	public ArrayList<String> getColaboradores() {
		return colaboradores;
	}

	public String getCategoria() {
		return categoria;
	}
	
	public EstadoPropuesta getEstadoActual() {
		return estadoActual;
	}
	
	public void addColaborador(String nickname) {
		colaboradores.add(nickname);
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

	public void setMontoNecesario(double montoNecesario) {
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

	public void setPrecioEntrada(double precioEntrada) {
		this.precioEntrada = precioEntrada;
	}

	public void setTipo(TipoRetorno tipo) {
		this.tipo = tipo;
	}

	public void setProponenteACargo(DtProponente proponenteACargo) {
		this.proponenteACargo = proponenteACargo;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public void setFechaFinalizacion(GregorianCalendar fechaFinalizacion) {
		this.fechaFinalizacion = fechaFinalizacion;
	}

	public void setEstadoActual(EstadoPropuesta estadoActual) {
		this.estadoActual = estadoActual;
	}

	public void setColaboradores(ArrayList<String> colaboradores) {
		this.colaboradores = colaboradores;
	}

	public void setRecaudado(double recaudado) {
		this.recaudado = recaudado;
	}
	
	public DtDatosPropuesta() {
		super();
	}
}
