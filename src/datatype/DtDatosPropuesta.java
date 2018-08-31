package datatype;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class DtDatosPropuesta {
   private String titulo;
   private String descripcion;
   private String imagen;
   private float montoNecesario;
   private GregorianCalendar fechaPublicacion;
   private GregorianCalendar fechaEspecatulo;
   private String lugar;
   private float precioEntrada;
   private TipoRetorno tipo;
   private float recaudado;
   
   //Pseudoatributos
   private ArrayList<DtColaborador> colaboradores;
   
	   
	
	public DtDatosPropuesta(String titulo, String descripcion, String imagen, float montoNecesario,
			GregorianCalendar fechaPublicacion, GregorianCalendar fechaEspecatulo, String lugar, float precioEntrada,
			TipoRetorno tipo, float recaudado, ArrayList<DtColaborador> colaboradores) {
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
	
	public ArrayList<DtColaborador> getColaboradores() {
		return colaboradores;
	}
   
}
