package datatype;

public class DtPropuestaMinificado {

	private String titulo;
	private String proponente;
	private byte[] imagen;
	
	public DtPropuestaMinificado(String titulo, String proponente, byte[] imagen) {
		super();
		this.titulo = titulo;
		this.proponente = proponente;
		this.imagen = imagen;
	}

	public DtPropuestaMinificado() {
		super();
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getProponente() {
		return proponente;
	}

	public void setProponente(String proponente) {
		this.proponente = proponente;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}
	
}
