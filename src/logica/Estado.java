package logica;

import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import datatype.DtEstado;
import datatype.EstadoPropuesta;

@Entity
@IdClass(EstadoID.class)
@Table(name="ESTADOS_PROPUESTA")
public class Estado {
	
	@Id
	@Column(name="ESTADO")
	@Enumerated(EnumType.ORDINAL)
	private EstadoPropuesta estado;
	
	@Id
	@ManyToOne
	@JoinColumn(name="PROPUESTA")
	private Propuesta propuesta;
	
	@Column(name="FECHA_CAMBIO")
	private GregorianCalendar fechaCambio;
	
	public Estado() {
		super();
	}
	
	/**
	 * @param estado
	 * @param propuesta
	 * @param fechaCambio
	 */
	public Estado(EstadoPropuesta estado, Propuesta propuesta, GregorianCalendar fechaCambio) {
		super();
		this.estado = estado;
		this.propuesta = propuesta;
		this.fechaCambio = fechaCambio;
	}

	/**
	 * @return the estado
	 */
	public EstadoPropuesta getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(EstadoPropuesta estado) {
		this.estado = estado;
	}

	/**
	 * @return the propuesta
	 */
	public Propuesta getPropuesta() {
		return propuesta;
	}

	/**
	 * @param propuesta the propuesta to set
	 */
	public void setPropuesta(Propuesta propuesta) {
		this.propuesta = propuesta;
	}

	/**
	 * @return the fechaCambio
	 */
	public GregorianCalendar getFechaCambio() {
		return fechaCambio;
	}

	/**
	 * @param fechaCambio the fechaCambio to set
	 */
	public void setFechaCambio(GregorianCalendar fechaCambio) {
		this.fechaCambio = fechaCambio;
	}



	public DtEstado getDtEstado() {
		DtEstado dte = new DtEstado(estado);
		return dte;
	}
}
