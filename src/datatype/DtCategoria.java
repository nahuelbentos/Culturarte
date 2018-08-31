package datatype;

import java.util.ArrayList;

import logica.Categoria;

public class DtCategoria {

	private String nombre;

	private ArrayList<Categoria> superCategorias;

	private ArrayList<String> categoriaPadre;

	public DtCategoria(String nombre) {
		this.nombre = nombre;
	}
	
	public DtCategoria(String nombre, ArrayList<Categoria> superCategorias) {
		this.nombre = nombre;
		this.superCategorias = superCategorias;
	}

	public String getNombre() {
		return nombre;
	}
	public ArrayList<Categoria> getSuperCategorias() {
		return superCategorias;
	}

	public ArrayList<String> getPadres() {
		return categoriaPadre;
	}


}
