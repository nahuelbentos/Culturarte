package logica;

import java.util.GregorianCalendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import datatype.DtColaboracion;
import datatype.DtPropuestaColaborada;
import datatype.TipoRetorno;

//@Entity
//@Table(name = "COLABORACION")
public class Colaboracion {

//	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	private float monto;
	private GregorianCalendar fechaAporte;
	private TipoRetorno tipo;
	
	// PseudoAtributos
	private Propuesta propuestaColaborada;
	private Colaborador colaborador;
	
	public Colaboracion(float monto, GregorianCalendar fechaAporte, TipoRetorno tipo) {
		super();
		this.monto = monto;
		this.fechaAporte = fechaAporte;
		this.tipo = tipo;
	}

	public float getMonto() {
		return monto;
	}

	public void setMonto(float monto) {
		this.monto = monto;
	}

	public GregorianCalendar getFechaAporte() {
		return fechaAporte;
	}

	public void setFechaAporte(GregorianCalendar fechaAporte) {
		this.fechaAporte = fechaAporte;
	}

	public TipoRetorno getTipo() {
		return tipo;
	}

	public void setTipo(TipoRetorno tipo) {
		this.tipo = tipo;
	}
	
	public boolean tieneProp(String titulo) {
		return propuestaColaborada.getTitulo().equals(titulo);
	}
	
	public boolean tieneColaborador(String nickname) {
		return colaborador.getNickname().equals(nickname);	
	}
	
	public DtColaboracion getDataColaboracion() {
		return new DtColaboracion(propuestaColaborada.getDtPropuesta(), monto,
				colaborador.getDtColaborador(), fechaAporte, tipo);
	}
	
	public DtPropuestaColaborada getPropuestaFromColaboracion() {
		return propuestaColaborada.getInfoPropuestaColaborada();
	}
	
}
