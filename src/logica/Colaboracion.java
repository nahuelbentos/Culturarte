package logica;

import java.util.Date;

import datatype.TipoRetorno;

public class Colaboracion {

	private Integer monto;
	private Date fechaAporte;
	private TipoRetorno tipo;
	
	public Colaboracion(Integer monto, Date fechaAporte, TipoRetorno tipo) {
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

	public Date getFechaAporte() {
		return fechaAporte;
	}

	public void setFechaAporte(Date fechaAporte) {
		this.fechaAporte = fechaAporte;
	}

	public TipoRetorno getTipo() {
		return tipo;
	}

	public void setTipo(TipoRetorno tipo) {
		this.tipo = tipo;
	}
	
}
