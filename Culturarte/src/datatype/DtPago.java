package datatype;

public abstract class DtPago {
	private Long id;
	private boolean compEmitido;
	private double montoAPagar;
	
	public DtPago(Long id, double montoAPagar, boolean compEmitido) {
		super();
		this.id = id;
		this.compEmitido = compEmitido;
		this.montoAPagar = montoAPagar;
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
