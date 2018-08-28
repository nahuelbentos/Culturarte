package datatype;

import java.util.ArrayList;


public class DtCategoria {

	private String nombre;

	private ArrayList<DtCategoria> superCategorias;
	private ArrayList<DtCategoria> subCategorias;


	private ArrayList<String> categoriaPadre;

	public DtCategoria(String nombre, ArrayList<String> categoriaPadre) {
		this.nombre = nombre;
		this.categoriaPadre = categoriaPadre;
	}
	public DtCategoria(String nombre, ArrayList<DtCategoria> superCategorias,
			ArrayList<DtCategoria> subCategorias) {
		super();
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

	public ArrayList<DtCategoria> getCategoriaPadre() {
		return subCategorias;
	}


}
