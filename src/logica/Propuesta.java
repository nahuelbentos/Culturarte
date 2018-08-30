package logica;

import datatype.DtPropuesta;
import datatype.EstadoPropuesta;
import datatype.TipoRetorno;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Propuesta {
    
   private String titulo;
   private String descripcion;
   private String imagen;
   private float montoNecesario;
   private GregorianCalendar fechaPublicacion;
   private GregorianCalendar fechaEspecatulo;
   private float precioEntrada;
   private String lugar;
   private TipoRetorno tipo;

   // PseudoAtributos
   private Proponente proponenteACargo;
   private Estado estadoActual;
   private ArrayList<Estado> estadoHistorial;
   private Categoria categoria;
   
   public Propuesta(String titulo, String descripcion, String imagen, 
			float montoNecesario, GregorianCalendar fechaPublicacion, GregorianCalendar fechaEspecatulo, 
			float precioEntrada, String lugar, TipoRetorno tipo) {
	    this.titulo = titulo;
	    this.descripcion = descripcion;
	    this.imagen = imagen;
	    this.montoNecesario = montoNecesario;
	    this.fechaPublicacion = fechaPublicacion;
	    this.fechaEspecatulo = fechaEspecatulo;
	    this.precioEntrada = precioEntrada;
	    this.lugar = lugar;
	    this.tipo = tipo;
	}
   
   public Propuesta(DtPropuesta dtP) {
	    this.titulo = dtP.getTitulo();
	    this.descripcion = dtP.getDescripcion();
	    this.imagen = dtP.getImagen();
	    this.montoNecesario = dtP.getMontoNecesario();
	    this.fechaPublicacion = dtP.getFechaPublicacion();
	    this.fechaEspecatulo = dtP.getFechaEspecatulo();
	    this.precioEntrada = dtP.getPrecioEntrada();
	    this.lugar = dtP.getLugar();
	    this.tipo = dtP.getTipo();
	    this.estadoActual = new Estado(EstadoPropuesta.ingresada);
	    this.estadoHistorial = null; // Ya hay que setear el ingresada o al historial pasa al momento del cambio?
	}

    public String getDescripcion() {
        return descripcion;
    }

    public GregorianCalendar getFechaEspecatulo() {
        return fechaEspecatulo;
    }

    public GregorianCalendar getFechaPublicacion() {
        return fechaPublicacion;
    }

    public String getImagen() {
        return imagen;
    }

    public String getLugar() {
        return lugar;
    }

    public float getMontoNecesario() {
        return montoNecesario;
    }

    public float getPrecioEntrada() {
        return precioEntrada;
    }

    public TipoRetorno getTipo() {
        return tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFechaEspecatulo(GregorianCalendar fechaEspecatulo) {
        this.fechaEspecatulo = fechaEspecatulo;
    }

    public void setFechaPublicacion(GregorianCalendar fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public void setMontoNecesario(float montoNecesario) {
        this.montoNecesario = montoNecesario;
    }

    public void setPrecioEntrada(float precioEntrada) {
        this.precioEntrada = precioEntrada;
    }

    public void setTipo(TipoRetorno tipo) {
        this.tipo = tipo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

	public Proponente getProponenteACargo() {
		return proponenteACargo;
	}

	public void setProponenteACargo(Proponente proponenteACargo) {
		this.proponenteACargo = proponenteACargo;
	}
	
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
    
    public boolean isProponenteACargo(String nickname) {
    	if(this.proponenteACargo.getNickname()==nickname)
    		return true;
    	else
    		return false;
    }
    
    public DtPropuesta getInfoPropuesta() {
    	/*DtPropuesta dtp = new DtPropuesta(titulo, descripcion, imagen, montoNecesario, fechaPublicacion,
    									fechaEspecatulo, lugar, precioEntrada, tipo, 0,
    									proponenteACargo, estadoActual, estadoHistorial, categoria);
    	
		*/
    	return null; //dtp;
    	
    }
}   