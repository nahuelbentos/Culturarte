package logica;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import datatype.DtEstado;
import datatype.EstadoPropuesta;

@Entity
@Table(name="ESTADOS")
public class Estado {
	
	
	@Id
	@Column(name="NOMBRE",columnDefinition="VARCHAR(15)")
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
