package datatype;

import java.util.GregorianCalendar;

public class DtColaboracion {
    
	private String tituloPropuesta;
	private String colaborador;
	private double monto;
	private GregorianCalendar fechaAporte;
	private TipoRetorno tipo;
	
	public DtColaboracion(String tituloPropuesta, String colaborador, double monto, GregorianCalendar fechaAporte,
			TipoRetorno tipo) {
		super();
		this.tituloPropuesta = tituloPropuesta;
		this.colaborador = colaborador;
		this.monto = monto;
		this.fechaAporte = fechaAporte;
		this.tipo = tipo;
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
	
	
	
	
}
