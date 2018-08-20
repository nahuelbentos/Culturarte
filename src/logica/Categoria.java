package logica;

import java.util.ArrayList;

public class Categoria {
	private String nombre;
	
	// PseudoAtributos
	private ArrayList<Categoria> superCategorias;
	private ArrayList<Categoria> subCategorias;

	public Categoria(String nombre) {
		super();
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
