package logica;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import datatype.DtColaboracion;
import datatype.DtDatosPropuesta;
import datatype.DtEstado;
import datatype.DtPropuesta;
import datatype.DtPropuestaColaborada;
import datatype.EstadoPropuesta;
import datatype.TipoRetorno;

@Entity
@Table(name="PROPUESTAS")
public class Propuesta {
	
	@Id
	@Column(name="TITULO")
	private String titulo;
	
	@Column(name="DESCRIPCION", length=900)
	private String descripcion;
	@Lob
	@Column(name="IMAGEN")
	private byte[] imagen;
	@Column(name="MONTO_NECESARIO")
	private float montoNecesario;
	@Column(name="FECHA_PUBLICACION")
	private GregorianCalendar fechaPublicacion;
	@Column(name="FECHA_ESPECTACULO")
	private GregorianCalendar fechaEspecatulo;
	@Column(name="PRECIO_ENTRADA")
	private float precioEntrada;
	@Column(name="LUGAR")
	private String lugar;
	@Column(name="FECHA_FINALIZACION")
	private GregorianCalendar fechaFinalizacion;
	@Column(name="TIPO_RETORNO")
	@Enumerated(EnumType.STRING)
	private TipoRetorno tipo;
	@Column(name="ESTADO_ACTUAL")
	@Enumerated(EnumType.STRING)
	private EstadoPropuesta estadoActual;
	
	
//	PseudoAtributos
	@ManyToOne
	@JoinColumn(name="NICK_PROPONENTE")
	private Proponente proponenteACargo;
	
	@OneToOne
	@JoinColumn(name="ID_CATEGORIA")
	private Categoria categoria;
	
	@Column(nullable=false, name="ESTAELIMINADA",columnDefinition = "boolean default false")
	private boolean flagElm;
	
	@ManyToMany(mappedBy="propuestasFavoritas")
	private List<Usuario> usuariosFollowers = new ArrayList<>();
	
	public Propuesta() {
		super();
	}
   
	public boolean isFlagElm() {
		return flagElm;
	}

	public void setFlagElm(boolean flagElm) {
		this.flagElm = flagElm;
	}

	public Propuesta(String titulo, String descripcion, byte[] imagen, 
			float d, GregorianCalendar fechaPublicacion, GregorianCalendar fechaEspecatulo, 
			float e, String lugar, TipoRetorno tipo) {
	    this.titulo = titulo;
	    this.descripcion = descripcion;
	    this.imagen = imagen;
	    this.montoNecesario = d;
	    this.fechaPublicacion = fechaPublicacion;
	    this.fechaEspecatulo = fechaEspecatulo;
	    this.precioEntrada = e;
	    this.lugar = lugar;
	    this.fechaFinalizacion = fechaPublicacion;
	    
	    // Calculo la fecha de finalizacion con respecto al día de hoy
	    GregorianCalendar fechaFinalizacion = (GregorianCalendar) GregorianCalendar.getInstance();
		fechaFinalizacion.add(GregorianCalendar.DAY_OF_MONTH, 30);	//	agrego 30 días a la caducidad
	    this.fechaFinalizacion = fechaFinalizacion;
	    
	    this.tipo = tipo;
	    this.estadoActual = EstadoPropuesta.ingresada;
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
	    this.fechaFinalizacion = fechaPublicacion;
	    
	    // Calculo la fecha de finalizacion con respecto al día de hoy
	    GregorianCalendar fechaFinalizacion = (GregorianCalendar) GregorianCalendar.getInstance();
		fechaFinalizacion.add(GregorianCalendar.DAY_OF_MONTH, 30);	//	agrego 30 días a la caducidad
	    this.fechaFinalizacion = fechaFinalizacion;
	    
	    this.tipo = dtP.getTipo();
	    this.estadoActual = EstadoPropuesta.ingresada;
	    //this.estadoHistorial = null; // Ya hay que setear el ingresada o al historial pasa al momento del cambio?
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

    public byte[] getImagen() {
        return imagen;
    }

    public String getLugar() {
        return lugar;
    }
    
    public GregorianCalendar getFechaFinalizacion() {
        return fechaFinalizacion;
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
    
    public EstadoPropuesta getEstadoActual() {
 		return estadoActual;
 	}

 	public void setEstadoActual(EstadoPropuesta estadoActual) {
 		this.estadoActual = estadoActual;
 	}
/*
 	public ArrayList<Estado> getEstadoHistorial() {
 		return estadoHistorial;
 	}

 	public void setEstadoHistorial(ArrayList<Estado> estadoHistorial) {
 		this.estadoHistorial = estadoHistorial;
 	}
*/
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFechaEspecatulo(GregorianCalendar fechaEspecatulo) {
        this.fechaEspecatulo = fechaEspecatulo;
    }

    public void setFechaPublicacion(GregorianCalendar fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }
    
    public void setFechaFinalizacion(GregorianCalendar fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
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
    
    public boolean isProponenteACargo(String nickname) {
    	return (this.proponenteACargo.getNickname().equals(nickname));
    }
    
    public DtPropuesta getInfoPropuesta() {
    	/*DtPropuesta dtp = new DtPropuesta(titulo, descripcion, imagen, montoNecesario, fechaPublicacion,
    									fechaEspecatulo, lugar, precioEntrada, tipo, 0,
    									proponenteACargo, estadoActual, estadoHistorial, categoria);
    	/*
    	return new DtPropuesta(titulo, descripcion, imagen, montoNecesario, fechaPublicacion,
				fechaEspecatulo, lugar, precioEntrada, tipo, 0,
				proponenteACargo.getDtProponente(), estadoActual.getDtEstado(), this.getDtEstadoHistorial(),
				categoria.getDtCategoria(),null);    
    	*/
    	
    	// CAMBIO EL METODO PARA PROBAR PERSISTENCIA
    	return new DtPropuesta(titulo, descripcion, imagen, montoNecesario, fechaPublicacion,
				fechaEspecatulo, lugar, precioEntrada, TipoRetorno.EntradasGratis, 0 /* recaudado*/ ,
				proponenteACargo.getDtProponente(), estadoActual, this.getDtEstadoHistorial(),
				null,null);
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
				fechaEspecatulo, lugar, precioEntrada, tipo, 0,
				proponenteACargo.getDtProponente(), estadoActual, this.getDtEstadoHistorial(),
				categoria.getDtCategoriaSimple(), null);
	}
	
	public DtPropuesta getDtPropuestaLazy() {
		return new DtPropuesta(this.titulo);
	}

	public DtDatosPropuesta getDtDatosPropuesta() {
		/*
		 * 
		 * // CAMBIO EL METODO PARA PROBAR PERSISTENCIA
		
		 
    	return null; //dtp;
    	*/
		
		ArrayList<String> colaboradores = new ArrayList<String>();
		ArrayList<DtColaboracion> colaboraciones = new ArrayList<DtColaboracion>();
    	return new DtDatosPropuesta(titulo, descripcion, imagen, montoNecesario, fechaPublicacion, fechaEspecatulo, lugar, precioEntrada,tipo, 0, colaboradores,proponenteACargo.getDtProponente(),categoria.getNombre(), fechaFinalizacion, estadoActual, colaboraciones);


    	
    }

    public DtPropuestaColaborada getInfoPropuestaColaborada() {
    	return new DtPropuestaColaborada(titulo, descripcion, imagen, 0, proponenteACargo.getDtProponente(), estadoActual);
    	
    }
    
    public ArrayList<DtEstado> getDtEstadoHistorial(){
    	ArrayList<DtEstado> dte = new ArrayList<DtEstado>() ;
//    	for (Estado e : estadoHistorial) {
//    		dte.add(e.getDtEstado());			
//		}
    	return dte;
    }
    
    public boolean tieneCategoria(String nomCat) {
    	return categoria.getNombre().equals(nomCat);
    }
    
}   