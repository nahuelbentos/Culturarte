package datatype;

import java.util.Calendar;

public class DtPagoTarjeta extends DtPago {
	private TipoTarjeta tipoTarjeta;
	private double nroTarjeta;
	private Calendar fechaVenc;
	private int cvc;
	private String nombreTitular;
	
	public DtPagoTarjeta(Long id, double montoAPagar, TipoTarjeta tipoTarjeta, double nroTarjeta, Calendar fechaVenc, int cvc, String nombreTitular) {
		super(id, montoAPagar);
		this.tipoTarjeta = tipoTarjeta;
		this.nroTarjeta = nroTarjeta;
		this.fechaVenc = fechaVenc;
		this.cvc = cvc;
		this.nombreTitular = nombreTitular;
	}
	
	public DtPagoTarjeta() {
		super();
	}

	public TipoTarjeta getTipoTarjeta() {
		return tipoTarjeta;
	}

	public void setTipoTarjeta(TipoTarjeta tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}

	public double getNroTarjeta() {
		return nroTarjeta;
	}

	public void setNroTarjeta(double nroTarjeta) {
		this.nroTarjeta = nroTarjeta;
	}

	public Calendar getFechaVenc() {
		return fechaVenc;
	}

	public void setFechaVenc(Calendar fechaVenc) {
		this.fechaVenc = fechaVenc;
	}

	public int getCvc() {
		return cvc;
	}

	public void setCvc(int cvc) {
		this.cvc = cvc;
	}

	public String getNombreTitular() {
		return nombreTitular;
	}

	public void setNombreTitular(String nombreTitular) {
		this.nombreTitular = nombreTitular;
	}
}
