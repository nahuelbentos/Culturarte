package logica;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import datatype.DtPagoTrfBancaria;

@Entity
@DiscriminatorValue("B")
public class PagoTrfBancaria extends Pago {
	@Column(name="PB_NOMBREBANCO")
	private String nombreBanco;
	@Column(name="PB_NUMCUENTA")
	private String numCuenta;
	@Column(name="PB_NOMBRETITULAR")
	private String nombreTitular;
	
	public PagoTrfBancaria(boolean compEmitido, Calendar fechaEmitido, double montoAPagar, String nombreBanco, String numCuenta, String nombreTitular) {
		super(compEmitido, fechaEmitido, montoAPagar);
		this.nombreBanco = nombreBanco;
		this.numCuenta = numCuenta;
		this.nombreTitular = nombreTitular;
	}
	
	public PagoTrfBancaria(DtPagoTrfBancaria pb) {
		super(pb.isCompEmitido(), pb.getFechaEmitido(), pb.getMontoAPagar());
		this.nombreBanco = pb.getNombreBanco();
		this.numCuenta = pb.getNumCuenta();
		this.nombreTitular = pb.getNombreTitular();
	}

	public String getNombreBanco() {
		return nombreBanco;
	}

	public PagoTrfBancaria() {
		super();
	}

	public void setNombreBanco(String nombreBanco) {
		this.nombreBanco = nombreBanco;
	}

	public String getNumCuenta() {
		return numCuenta;
	}

	public void setNumCuenta(String numCuenta) {
		this.numCuenta = numCuenta;
	}

	public String getNombreTitular() {
		return nombreTitular;
	}

	public void setNombreTitular(String nombreTitular) {
		this.nombreTitular = nombreTitular;
	}
	
	@Override
	public DtPagoTrfBancaria getDtPago() {
		return new DtPagoTrfBancaria(this.getId(), 
									this.getMontoAPagar(), 
									this.getFechaEmitido(), 
									this.isCompEmitido(), 
									nombreBanco, 
									numCuenta, 
									nombreTitular);
	}
}
