package datatype;

public class DtPropuestaColaborada {
   private String titulo;
   private String descripcion;
   private String imagen;
   private double montoAportado;
   // PseudoAtributos
   private DtProponente proponenteACargo;
   private EstadoPropuesta estadoActual;
	   
   public DtPropuestaColaborada(String titulo, String descripcion, String imagen, double montoAportado,
			DtProponente proponenteACargo, EstadoPropuesta estadoActual) {
		super();
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.imagen = imagen;
		this.montoAportado = montoAportado;
		this.proponenteACargo = proponenteACargo;
		this.estadoActual = estadoActual;
	}
	public String getTitulo() {
		return titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public String getImagen() {
		return imagen;
	}
	public double getMontoAportado() {
		return montoAportado;
	}
	public DtProponente getProponenteACargo() {
		return proponenteACargo;
	}
	public EstadoPropuesta getEstadoActual() {
		return estadoActual;
	}
	   
	   
}
