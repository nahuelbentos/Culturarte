package datatype;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class DtDatosPropuesta {
   private String titulo;
   private String descripcion;
   private String imagen;
   private double montoNecesario;
   private GregorianCalendar fechaPublicacion;
   private GregorianCalendar fechaEspecatulo;
   private String lugar;
   private double precioEntrada;
   private TipoRetorno tipo;
   private double recaudado;
   
   //Pseudoatributos
   private ArrayList<String> colaboradores;
   
	   
	
	public DtDatosPropuesta(String titulo, String descripcion, String imagen, double montoNecesario,
			GregorianCalendar fechaPublicacion, GregorianCalendar fechaEspecatulo, String lugar, double precioEntrada,
			TipoRetorno tipo, double recaudado, ArrayList<String> colaboradores) {
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
	
	public DtDatosPropuesta() {
		
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
	
	public double getRecaudado() {
		return recaudado;
	}
	
	public ArrayList<String> getColaboradores() {
		return colaboradores;
	}
   
}
