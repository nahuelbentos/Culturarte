package datatype;

import java.util.GregorianCalendar;

public class DtColaboracion {
    private DtPropuesta propuesta;
    private float monto;
    private DtColaborador colaborador;
	private GregorianCalendar fechaAporte;
	private TipoRetorno tipo;
	
	
	public DtColaboracion(DtPropuesta propuesta, float monto, DtColaborador colaborador, GregorianCalendar fechaAporte,
			TipoRetorno tipo) {
		super();
		this.propuesta = propuesta;
		this.monto = monto;
		this.colaborador = colaborador;
		this.fechaAporte = fechaAporte;
		this.tipo = tipo;
	}
	public DtPropuesta getPropuesta() {
		return propuesta;
	}
	public float getMonto() {
		return monto;
	}
	public DtColaborador getColaborador() {
		return colaborador;
	}
	public GregorianCalendar getFechaAporte() {
		return fechaAporte;
	}
	public TipoRetorno getTipo() {
		return tipo;
	}
	
	
    
}
