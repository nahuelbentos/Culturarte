package datatype;

public class DtInfoPago {
	private String titPropuesta;
	private String nickColaborador;
	private DtPago pago;
	
	public DtInfoPago(String titPropuesta, String nickColaborador, DtPago pago) {
		super();
		this.titPropuesta = titPropuesta;
		this.nickColaborador = nickColaborador;
		this.pago = pago;
	}
	
	public DtInfoPago() {
		super();
	}

	public String getTitPropuesta() {
		return titPropuesta;
	}

	public void setTitPropuesta(String titPropuesta) {
		this.titPropuesta = titPropuesta;
	}

	public String getNickColaborador() {
		return nickColaborador;
	}

	public void setNickColaborador(String nickColaborador) {
		this.nickColaborador = nickColaborador;
	}

	public DtPago getPago() {
		return pago;
	}

	public void setPago(DtPago pago) {
		this.pago = pago;
	}
}
