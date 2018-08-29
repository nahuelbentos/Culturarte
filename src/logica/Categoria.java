package logica;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import datatype.DtCategoria;

@Entity
@Table(name="CATEGORIAS")
public class Categoria {


	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_CAT")
	private int id;
	@Column(name="NOMBRE", nullable=false, length=50)
	private String nombre;

	public Categoria() {
		super();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	// PseudoAtributos
	//private ArrayList<Categoria> superCategorias;
	//private ArrayList<Categoria> subCategorias;
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
/*
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
		return new DtCategoria(nombre, this.getArraySuperCategorias(),this.getDtSubCategorias());
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
		*/
}
