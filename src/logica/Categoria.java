package logica;

import java.util.ArrayList;

public class Categoria {
	private String nombre;
	
	// PseudoAtributos
	private ArrayList<Categoria> superCategorias;
	//private Map<String, Categoria> subCategorias;

	public Categoria(String nombre) {
		super();
		this.nombre = nombre;
		superCategorias = new ArrayList<>();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void addPadre(Categoria categoria) {
		this.superCategorias.add(categoria);
	}
	
	public ArrayList<Categoria> getSuperCategorias() {
		return this.superCategorias;
	}
	
	public ArrayList<String> getDtSuperCategorias() {
		ArrayList<String> padres = new ArrayList<>();
		for (int i = 0; i < this.superCategorias.size(); i++)
			padres.add(this.superCategorias.get(i).getNombre());
		return padres;
	}
}
