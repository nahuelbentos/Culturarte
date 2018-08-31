package logica;

import java.util.ArrayList;

import datatype.DtCategoria;

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
	
	public DtCategoria getDtCategoria() {
		return new DtCategoria(nombre, superCategorias);
	}
	
	public ArrayList<Categoria> getDtSuperCategorias() {
		ArrayList<Categoria> padres = new ArrayList<>();
		for (int i = 0; i < this.superCategorias.size(); i++)
			padres.add(superCategorias.get(i));
		return padres;
	}
}
