package datatype;

import java.util.ArrayList;

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
	
	public void addHijo(DtCategoria dtC) {
		subCategorias.add(dtC);
	}
	
	// Getters, Setters, Constructor
	public String getNombre() {
		return nombre;
	}
	
	public ArrayList<DtCategoria> getSuperCategorias() {
		return superCategorias;
	}
	
	public ArrayList<DtCategoria> getSubCategorias() {
		return subCategorias;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setSuperCategorias(ArrayList<DtCategoria> superCategorias) {
		this.superCategorias = superCategorias;
	}

	public void setSubCategorias(ArrayList<DtCategoria> subCategorias) {
		this.subCategorias = subCategorias;
	}

	public DtCategoria() {
		super();
	}
	
	

}

