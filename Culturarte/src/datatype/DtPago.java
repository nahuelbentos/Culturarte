package datatype;

import java.util.Calendar;

public abstract class DtPago {
	private Long id;
	private boolean compEmitido;
	private Calendar fechaEmitido;
	private double montoAPagar;
	
	public DtPago(Long id, double montoAPagar, Calendar fechaEmitido, boolean compEmitido) {
		super();
		this.id = id;
		this.compEmitido = compEmitido;
		this.fechaEmitido = fechaEmitido;
		this.montoAPagar = montoAPagar;
	}
	
	public Calendar getFechaEmitido() {
		return fechaEmitido;
	}

	public void setFechaEmitido(Calendar fechaEmitido) {
		this.fechaEmitido = fechaEmitido;
	}

	public boolean isCompEmitido() {
		return compEmitido;
	}

	public void setCompEmitido(boolean compEmitido) {
		this.compEmitido = compEmitido;
	}

	public DtPago() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getMontoAPagar() {
		return montoAPagar;
	}

	public void setMontoAPagar(double montoAPagar) {
		this.montoAPagar = montoAPagar;
	}
	
	
	
}
