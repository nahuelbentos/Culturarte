package logica;

import datatype.DtColaborador;
import datatype.DtDatosPropuesta;
import datatype.DtEstado;
import datatype.DtPropuesta;
import datatype.DtPropuestaColaborada;
import datatype.TipoRetorno;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="PROPUESTAS")
public class Propuesta {
	@Id
	@Column(name="TITULO")
	private String titulo;
	@Column(name="DESCRIPCION")
	private String descripcion;
	@Column(name="IMAGEN")
	private String imagen;
	@Column(name="MONTO_NECESARIO")
	private double montoNecesario;
	@Column(name="FECHA_PUBLICACION")
	private GregorianCalendar fechaPublicacion;
	@Column(name="FECHA_ESPECTACULO")
	private GregorianCalendar fechaEspecatulo;
	@Column(name="PRECIO_ENTRADA")
	private double precioEntrada;
	@Column(name="LUGAR")
	private String lugar;
	//@Column(name="TIPO_RETORNO")
	//private TipoRetorno tipo;

   // PseudoAtributos
	@ManyToOne
	@JoinColumn(name="NICK_PROPONENTE")
	private Proponente proponenteACargo;
	
	@OneToOne
	@JoinColumn(name="ID_CATEGORIA")
	private Categoria categoria;   
   
	public Propuesta() {
		super();
	}

   //private Estado estadoActual;
   //private ArrayList<Estado> estadoHistorial;
   
   public Propuesta(String titulo, String descripcion, String imagen, 
			double d, GregorianCalendar fechaPublicacion, GregorianCalendar fechaEspecatulo, 
			double e, String lugar, TipoRetorno tipo) {
	    this.titulo = titulo;
	    this.descripcion = descripcion;
	    this.imagen = imagen;
	    this.montoNecesario = d;
	    this.fechaPublicacion = fechaPublicacion;
	    this.fechaEspecatulo = fechaEspecatulo;
	    this.precioEntrada = e;
	    this.lugar = lugar;
	    //this.tipo = tipo;
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

    public double getMontoNecesario() {
        return montoNecesario;
    }

    public double getPrecioEntrada() {
        return precioEntrada;
    }

    //public TipoRetorno getTipo() {
    //    return tipo;
    //}

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

    //public void setTipo(TipoRetorno tipo) {
    //    this.tipo = tipo;
    //}

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

	public Proponente getProponenteACargo() {
		return proponenteACargo;
	}

	public void setProponenteACargo(Proponente proponenteACargo) {
		this.proponenteACargo = proponenteACargo;
	}
    
    public boolean isProponenteACargo(String nickname) {
    	return (this.proponenteACargo.getNickname() == nickname);
    }
    
    public DtPropuesta getInfoPropuesta() {
    	
    	/*
    	return new DtPropuesta(titulo, descripcion, imagen, montoNecesario, fechaPublicacion,
				fechaEspecatulo, lugar, precioEntrada, tipo, 0,
				proponenteACargo.getDtProponente(), estadoActual.getDtEstado(), this.getDtEstadoHistorial(),
				categoria.getDtCategoria(),null);    
    	*/
    	
    	// CAMBIO EL METODO PARA PROBAR PERSISTENCIA
    	return new DtPropuesta(titulo, descripcion, imagen, montoNecesario, fechaPublicacion,
				fechaEspecatulo, lugar, precioEntrada, TipoRetorno.entradasGratis, 0,
				proponenteACargo.getDtProponente(), null, this.getDtEstadoHistorial(),
				null,null);
    }
    
    public DtPropuestaColaborada getInfoPropuestaColaborada() {
    	
    	//return new DtPropuestaColaborada(titulo, descripcion, imagen, 0, proponenteACargo.getDtProponente(), estadoActual.getDtEstado());
    	// CAMBIO EL METODO PARA PROBAR PERSISTENCIA
    	return new DtPropuestaColaborada(titulo, descripcion, imagen, 0, proponenteACargo.getDtProponente(), null);
    }
    
    public ArrayList<DtEstado> getDtEstadoHistorial(){
    	/*
    	 * // CAMBIO EL METODO PARA PROBAR PERSISTENCIA
    	ArrayList<DtEstado> dte = new ArrayList<DtEstado>() ;
    	for (Estado e : estadoHistorial) {
    		dte.add(e.getDtEstado());			
		}
    	return dte;
    	*/
    	return null;
    }
    
    /*
    public Estado getEstadoActual() {
		return estadoActual;
	}

	public void setEstadoActual(Estado estadoActual) {
		this.estadoActual = estadoActual;
	}
	
	public ArrayList<Estado> getEstadoHistorial() {
		return estadoHistorial;
	}

	public void setEstadoHistorial(ArrayList<Estado> estadoHistorial) {
		this.estadoHistorial = estadoHistorial;
	}
    */
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public DtPropuesta getDtPropuesta() {
    	/*
    	 * // CAMBIO EL METODO PARA PROBAR PERSISTENCIA
    	 * return new DtPropuesta(titulo, descripcion, imagen, montoNecesario, fechaPublicacion,
				fechaEspecatulo, lugar, precioEntrada, tipo, 0,
				proponenteACargo.getDtProponente(), estadoActual.getDtEstado(), this.getDtEstadoHistorial(),
				categoria.getDtCategoria(),null);
    	*/
    	return new DtPropuesta(titulo, descripcion, imagen, montoNecesario, fechaPublicacion,
				fechaEspecatulo, lugar, precioEntrada, TipoRetorno.entradasGratis, 0,
				proponenteACargo.getDtProponente(), null, this.getDtEstadoHistorial(),
				null,null);
    }

	public DtDatosPropuesta getDtDatosPropuesta() {
		/*
		 * 
		 * // CAMBIO EL METODO PARA PROBAR PERSISTENCIA
		 
		ArrayList<DtColaborador> colaboradores = new ArrayList<DtColaborador>();
				
    	return new DtDatosPropuesta(titulo, descripcion, imagen, 
				montoNecesario, fechaPublicacion, fechaEspecatulo, lugar, precioEntrada,
				tipo, 0, colaboradores);
    	*/
		return null;
    }
}   