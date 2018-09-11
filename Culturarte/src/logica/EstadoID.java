package logica;

import java.io.Serializable;

import datatype.EstadoPropuesta;

public class EstadoID implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EstadoPropuesta estado;
	private String propuesta;
	
	public EstadoID() {
		super();
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
	public String getPropuesta() {
		return propuesta;
	}

	/**
	 * @param propuesta the propuesta to set
	 */
	public void setPropuesta(String propuesta) {
		this.propuesta = propuesta;
	}

	/**
	 * @param estado
	 * @param propuesta
	 */
	public EstadoID(EstadoPropuesta estado, String propuesta) {
		super();
		this.estado = estado;
		this.propuesta = propuesta;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((propuesta == null) ? 0 : propuesta.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EstadoID other = (EstadoID) obj;
		if (estado != other.estado)
			return false;
		if (propuesta == null) {
			if (other.propuesta != null)
				return false;
		} else if (!propuesta.equals(other.propuesta))
			return false;
		return true;
	}
	
}
