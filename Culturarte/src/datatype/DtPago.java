package datatype;

public abstract class DtPago {
	private Long id;
	private double montoAPagar;
	
	public DtPago(Long id, double montoAPagar) {
		super();
		this.id = id;
		this.montoAPagar = montoAPagar;
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
