package logica;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")
public class PagoTrfBancaria extends Pago {
	@Column(name="PB_NOMBREBANCO")
	private String nombreBanco;
	@Column(name="PB_NUMCUENTA")
	private String numCuenta;
	@Column(name="PB_NOMBRETITULAR")
	private String nombreTitular;
	
	public PagoTrfBancaria(double montoAPagar, String nombreBanco, String numCuenta, String nombreTitular) {
		super(montoAPagar);
		this.nombreBanco = nombreBanco;
		this.numCuenta = numCuenta;
		this.nombreTitular = nombreTitular;
	}

	public String getNombreBanco() {
		return nombreBanco;
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
}
