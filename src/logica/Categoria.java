package logica;

import java.util.ArrayList;

import datatype.DtCategoria;

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
	
    public ArrayList<DtCategoria> getDtSubCategorias(){
    	ArrayList<DtCategoria> dtc = new ArrayList<DtCategoria>() ;
    	for (Categoria c : subCategorias) {
    		dtc.add(c.getDtCategoria());			
		}
    	return dtc;
    }
    
    public ArrayList<DtCategoria> getDtSuperCategorias(){
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
}
