package logica;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import datatype.DtPago;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TIPOPAGO")
@Table(name="PAGO")
public abstract class Pago {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long id;
	
	@Column(name="COMP_EMITIDO")
	private boolean compEmitido;
	
	
	private double montoAPagar;

	public Pago() {
		super();
	}
	
	public Pago(boolean compEmitido, double montoAPagar) {
		super();
		this.compEmitido = compEmitido;
		this.montoAPagar = montoAPagar;
	}
	
	public Long getId() {
		return id;
	}

	public double getMontoAPagar() {
		return montoAPagar;
	}
	
	public boolean isCompEmitido() {
		return compEmitido;
	}
	
	public void setCompEmitido(boolean compEmitido) {
		this.compEmitido = compEmitido;
	}
	
	public abstract DtPago getDtPago();
}
