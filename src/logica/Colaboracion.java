package logica;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import datatype.TipoRetorno;

public class Colaboracion {

	private float monto;
	private GregorianCalendar fechaAporte;
	private TipoRetorno tipo;
	
	// PseudoAtributos
	private Propuesta propuestaColaborada;
	private Colaborador colaborador;
	
	public Colaboracion(float monto, GregorianCalendar fechaAporte, TipoRetorno tipo) {
		super();
		this.monto = monto;
		this.fechaAporte = fechaAporte;
		this.tipo = tipo;
	}

	public float getMonto() {
		return monto;
	}

	public void setMonto(float monto) {
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
