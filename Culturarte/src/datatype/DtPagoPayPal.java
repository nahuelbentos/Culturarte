package datatype;

public class DtPagoPayPal extends DtPago {
	private String numeroCuenta;
	private String nombreTitular;
	
	public DtPagoPayPal(Long id, double montoAPagar, String numeroCuenta, String nombreTitular) {
		super(id, montoAPagar);
		this.numeroCuenta = numeroCuenta;
		this.nombreTitular = nombreTitular;
	}
	
	public DtPagoPayPal() {
		super();
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
