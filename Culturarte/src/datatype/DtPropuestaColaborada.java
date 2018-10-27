package datatype;

public class DtPropuestaColaborada {
   private String titulo;
   private String descripcion;
   private byte[] imagen;
   private double montoAportado;
   // PseudoAtributos
   private DtProponente proponenteACargo;
   private EstadoPropuesta estadoActual;
	   
   public DtPropuestaColaborada(String titulo, String descripcion, byte[] imagen, double montoAportado,
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

public void setTitulo(String titulo) {
	this.titulo = titulo;
}

public String getDescripcion() {
	return descripcion;
}

public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
}

public byte[] getImagen() {
	return imagen;
}

public void setImagen(byte[] imagen) {
	this.imagen = imagen;
}

public double getMontoAportado() {
	return montoAportado;
}

public void setMontoAportado(double montoAportado) {
	this.montoAportado = montoAportado;
}

public DtProponente getProponenteACargo() {
	return proponenteACargo;
}

public void setProponenteACargo(DtProponente proponenteACargo) {
	this.proponenteACargo = proponenteACargo;
}

public EstadoPropuesta getEstadoActual() {
	return estadoActual;
}

public void setEstadoActual(EstadoPropuesta estadoActual) {
	this.estadoActual = estadoActual;
}

public DtPropuestaColaborada() {
	super();
}
	
	   
}
