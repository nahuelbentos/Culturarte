package logica;

import datatype.EstadoPropuesta;

public class Estado {
	
	private EstadoPropuesta estado;

	public Estado(EstadoPropuesta estado) {
		super();
		this.estado = estado;
	}

	public EstadoPropuesta getEstado() {
		return estado;
	}

	public void setEstado(EstadoPropuesta estado) {
		this.estado = estado;
	}
	
	
}
