package datatype;

public class DtColaboracion {
    private DtPropuesta propuesta;
    private int monto;
    private DtColaborador colaborador;
    
	public DtColaboracion(DtPropuesta propuesta, int monto, DtColaborador colaborador) {
		super();
		this.propuesta = propuesta;
		this.monto = monto;
		this.colaborador = colaborador;
	}
	public DtPropuesta getPropuesta() {
		return propuesta;
	}
	public int getMonto() {
		return monto;
	}
	public DtColaborador getColaborador() {
		return colaborador;
	}

    
    
}
