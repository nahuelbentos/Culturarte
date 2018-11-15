package datatype;

import java.util.Calendar;

public class DtPagoTrfBancaria extends DtPago {
	private String nombreBanco;
	private String numCuenta;
	private String nombreTitular;
	
	public DtPagoTrfBancaria(Long id, double montoAPagar, Calendar fechaEmitido, boolean compEmitido, String nombreBanco, String numCuenta, String nombreTitular) {
		super(id, montoAPagar, fechaEmitido, compEmitido);
		this.nombreBanco = nombreBanco;
		this.numCuenta = numCuenta;
		this.nombreTitular = nombreTitular;
	}
	
	public DtPagoTrfBancaria() {
		super();
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
