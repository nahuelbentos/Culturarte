package logica;

import datatype.DtEstado;
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
	
	public DtEstado getDtEstado() {
		DtEstado dte = new DtEstado(estado);
		return dte;
	}
}
