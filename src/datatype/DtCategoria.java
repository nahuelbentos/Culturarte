package datatype;

import java.util.ArrayList;

import logica.Categoria;

public class DtCategoria {

	private String nombre;

	private ArrayList<DtCategoria> superCategorias;

//	private ArrayList<String> categoriaPadre;

	public DtCategoria(String nombre) {
		this.nombre = nombre;
	}
	
	public DtCategoria(String nombre, ArrayList<DtCategoria> superCategorias) {
		this.nombre = nombre;
		this.superCategorias = superCategorias;
	}

	public String getNombre() {
		return nombre;
	}
	public ArrayList<DtCategoria> getSuperCategorias() {
		return superCategorias;
	}

//	public ArrayList<String> getPadres() {
//		return categoriaPadre;
//	}


}
