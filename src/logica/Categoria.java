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
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

    public ArrayList<DtCategoria> getDtSubCategorias(){
    	ArrayList<DtCategoria> dtc = new ArrayList<DtCategoria>() ;
    	for (Categoria c : subCategorias) {
    		dtc.add(c.getDtCategoria());
		}
    	return dtc;
    }

    public ArrayList<DtCategoria> getArraySuperCategorias(){
    	ArrayList<DtCategoria> dtc = new ArrayList<DtCategoria>();
    	for (Categoria c : superCategorias) {
    		dtc.add(c.getDtCategoria());
		}
    	return dtc;
    }

	public DtCategoria getDtCategoria() {
		DtCategoria dtc = new DtCategoria(nombre,this.getDtSubCategorias(),this.getDtSuperCategorias());
		return dtc;
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
