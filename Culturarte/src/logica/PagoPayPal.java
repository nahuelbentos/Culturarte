package logica;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("P")
public class PagoPayPal extends Pago {
	@Column(name="PP_CUENTA")
	private String numeroCuenta;
	@Column(name="PP_TITULAR")
	private String nombreTitular;
	
	public PagoPayPal(double montoAPagar, String numeroCuenta, String nombreTitular) {
		super(montoAPagar);
		this.numeroCuenta = numeroCuenta;
		this.nombreTitular = nombreTitular;
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
}
