package datatype;

public class DtCategoria {

	private String nombre;
	private String categoriaPadre;
	
	public DtCategoria(String nombre, String categoriaPadre) {
		this.nombre = nombre;
		this.categoriaPadre = categoriaPadre;
	}
	
	public DtCategoria(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getCategoriaPadre() {
		return categoriaPadre;
	}
	
	public void setCategoriaPadre(String categoriaPadre) {
		this.categoriaPadre = categoriaPadre;
	}
	
}
