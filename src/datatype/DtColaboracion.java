package datatype;

public class DtColaboracion {
    private String propuesta;
    private int monto;
    private String colaborador;

    public DtColaboracion(String propuesta, int monto, String colaborador) {
        this.propuesta = propuesta;
        this.monto = monto;
        this.colaborador = colaborador;
    }

    public String getColaborador() {
        return colaborador;
    }

    public int getMonto() {
        return monto;
    }

    public String getPropuesta() {
        return propuesta;
    }
    
    
}
