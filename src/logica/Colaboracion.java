package logica;

import java.util.Date;
import java.util.GregorianCalendar;

import datatype.TipoRetorno;

public class Colaboracion {

	private Integer monto;
	private GregorianCalendar fechaAporte;
	private TipoRetorno tipo;
	
	public Colaboracion(Integer monto, GregorianCalendar fechaAporte, TipoRetorno tipo) {
		super();
		this.monto = monto;
		this.fechaAporte = fechaAporte;
		this.tipo = tipo;
	}

	public Integer getMonto() {
		return monto;
	}

	public void setMonto(Integer monto) {
		this.monto = monto;
	}

	public GregorianCalendar getFechaAporte() {
		return fechaAporte;
	}

	public void setFechaAporte(GregorianCalendar fechaAporte) {
		this.fechaAporte = fechaAporte;
	}

	public TipoRetorno getTipo() {
		return tipo;
	}

	public void setTipo(TipoRetorno tipo) {
		this.tipo = tipo;
	}
	
}
