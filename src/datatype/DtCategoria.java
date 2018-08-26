package datatype;

import java.util.ArrayList;

public class DtCategoria {

	private String nombre;
	private ArrayList<String> categoriaPadre;
	
	public DtCategoria(String nombre, ArrayList<String> categoriaPadre) {
		this.nombre = nombre;
		this.categoriaPadre = categoriaPadre;
	}
	
	public DtCategoria(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public ArrayList<String> getPadres() {
		return categoriaPadre;
	}
	
}
