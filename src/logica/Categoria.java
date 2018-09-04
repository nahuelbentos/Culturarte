package logica;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.swing.tree.DefaultMutableTreeNode;

import datatype.DtCategoria;

@Entity
@Table(name="CATEGORIAS")
public class Categoria {

	@Id
	@Column(name="NOMBRE", nullable=false, length=50)
	private String nombre;
	
	// Pseudoatributos
//	@ManyToMany
//	@JoinColumn(name="ID_CAT_SUPER")
//	private List<Categoria> superCategorias = new ArrayList<>();
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="ID_CAT_SUB")
	private List<Categoria> subCategorias = new ArrayList<>();
	
	public Categoria() {
		super();
	}
	
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
	
	public List<Categoria> getSubCategorias() {
		return this.subCategorias;
	}
	
	public DtCategoria getDtCategoria() {
		
		ArrayList<DtCategoria> hijos = new ArrayList<>();
		for (Categoria c : subCategorias) {
			hijos.add(new DtCategoria(c.getNombre()));
//			hijos.add(rec(c));
		}
		
		return new DtCategoria(this.nombre, hijos);
	}
	
	public DtCategoria getDtCategoriaFull() {
		
		ArrayList<DtCategoria> hijos = new ArrayList<>();
		for (Categoria c : subCategorias) {
			hijos.add(rec(c));
		}
		
		return new DtCategoria(this.nombre, hijos);
	}
	
	private DtCategoria rec(Categoria categ) {
		if (categ == null) {
			return null;
		} else {
//			System.out.println(categ.getNombre());
			DtCategoria dtC = new DtCategoria(categ.getNombre(), new ArrayList<>());
			if (categ.getSubCategorias()!=null) {
				for (Categoria sub : categ.getSubCategorias()) {
					DtCategoria hijo = new DtCategoria(sub.getNombre());
					hijo = rec(sub);
					if (hijo!=null) {
						dtC.addHijo(hijo);
					}
				}
			}
			return dtC;
		}
	}
	
	public DtCategoria getDtCategoriaSimple() {
		return new DtCategoria(nombre);
	}
	
	public ArrayList<Categoria> getDtSubCategorias() {
		ArrayList<Categoria> hijos = new ArrayList<>();
		for (int i = 0; i < this.subCategorias.size(); i++)
			hijos.add(subCategorias.get(i));
		return hijos;
	}
	
	public void addHijo(Categoria hijo) {
		this.subCategorias.add(hijo);
	}
}
