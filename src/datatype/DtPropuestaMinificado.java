package datatype;

public class DtPropuestaMinificado {

	private String titulo;
	private String proponente;
	
	public DtPropuestaMinificado(String titulo, String proponente) {
		super();
		this.titulo = titulo;
		this.proponente = proponente;
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
	
}
