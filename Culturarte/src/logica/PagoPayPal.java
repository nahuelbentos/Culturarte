package logica;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import datatype.DtPagoPayPal;

@Entity
@DiscriminatorValue("P")
public class PagoPayPal extends Pago {
	@Column(name="PP_CUENTA")
	private String numeroCuenta;
	@Column(name="PP_TITULAR")
	private String nombreTitular;
	
	public PagoPayPal() {
		super();
	}

	public PagoPayPal(double montoAPagar, boolean compEmitido, String numeroCuenta, String nombreTitular) {
		super(compEmitido, montoAPagar);
		this.numeroCuenta = numeroCuenta;
		this.nombreTitular = nombreTitular;
	}
	
	public PagoPayPal(DtPagoPayPal pp) {
		super(pp.isCompEmitido(), pp.getMontoAPagar());
		this.numeroCuenta = pp.getNumeroCuenta();
		this.nombreTitular = pp.getNombreTitular();
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getNombreTitular() {
		return nombreTitular;
	}

	public void setNombreTitular(String nombreTitular) {
		this.nombreTitular = nombreTitular;
	}
	
	@Override
	public DtPagoPayPal getDtPago() {
		return new DtPagoPayPal(this.getId(), 
								this.getMontoAPagar(), 
								this.isCompEmitido(), 
								this.numeroCuenta, 
								this.nombreTitular);
	}
}
