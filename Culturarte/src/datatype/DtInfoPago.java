package datatype;

public class DtInfoPago {
	private String titPropuesta;
	private String nickProponente;
	private DtPago pago;
	
	public DtInfoPago(String titPropuesta, String nickProponente, DtPago pago) {
		super();
		this.titPropuesta = titPropuesta;
		this.nickProponente = nickProponente;
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

	public String getNickProponente() {
		return nickProponente;
	}

	public void setNickProponente(String nickProponente) {
		this.nickProponente = nickProponente;
	}

	public DtPago getPago() {
		return pago;
	}

	public void setPago(DtPago pago) {
		this.pago = pago;
	}
}
