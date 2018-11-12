package datatype;

import java.util.GregorianCalendar;

public class DtColaboracion {
    
	private String tituloPropuesta;
	private String colaborador;
	private double monto;
	private GregorianCalendar fechaAporte;
	private TipoRetorno tipo;
	private boolean pago;
	
	public boolean isPago() {
		return pago;
	}

	public void setPago(boolean pago) {
		this.pago = pago;
	}

	public DtColaboracion(String tituloPropuesta, String colaborador, double monto, GregorianCalendar fechaAporte,
			TipoRetorno tipo) {
		super();
		this.tituloPropuesta = tituloPropuesta;
		this.colaborador = colaborador;
		this.monto = monto;
		this.fechaAporte = fechaAporte;
		this.tipo = tipo;
	}
	
	public DtColaboracion(String tituloPropuesta, String colaborador, double monto, GregorianCalendar fechaAporte, TipoRetorno tipo, boolean pago) {
		super();
		this.tituloPropuesta = tituloPropuesta;
		this.colaborador = colaborador;
		this.monto = monto;
		this.fechaAporte = fechaAporte;
		this.tipo = tipo;
		this.pago = pago;
	}
			
	public String getTituloPropuesta() {
		return tituloPropuesta;
	}
	public String getColaborador() {
		return colaborador;
	}
	public double getMonto() {
		return monto;
	}
	public GregorianCalendar getFechaAporte() {
		return fechaAporte;
	}
	public TipoRetorno getTipo() {
		return tipo;
	}

	public void setTituloPropuesta(String tituloPropuesta) {
		this.tituloPropuesta = tituloPropuesta;
	}

	public void setColaborador(String colaborador) {
		this.colaborador = colaborador;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public void setFechaAporte(GregorianCalendar fechaAporte) {
		this.fechaAporte = fechaAporte;
	}

	public void setTipo(TipoRetorno tipo) {
		this.tipo = tipo;
	}

	public DtColaboracion() {
		super();
	}
	
	
	
	
}