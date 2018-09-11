package datatype;

import java.util.ArrayList;

import logica.Categoria;

public class DtCategoria {

	private String nombre;

	private ArrayList<DtCategoria> superCategorias;

	private ArrayList<DtCategoria> subCategorias;

	public DtCategoria(String nombre) {
		this.nombre = nombre;
	}
	
	public DtCategoria(String nombre, ArrayList<DtCategoria> subCategorias) {
		this.nombre = nombre;
		this.subCategorias = subCategorias;
	}
	
	public DtCategoria(String nombre, ArrayList<DtCategoria> superCategorias, ArrayList<DtCategoria> subCategorias) {
		this.nombre = nombre;
		this.superCategorias = superCategorias;
		this.subCategorias = subCategorias;
	}

	public String getNombre() {
		return nombre;
	}
	
	public ArrayList<DtCategoria> getSuperCategorias() {
		return superCategorias;
	}
	
	public ArrayList<DtCategoria> getSubCategorias() {
		return subCategorias;
	}
	
	public void addHijo(DtCategoria dtC) {
		subCategorias.add(dtC);
	}

}

