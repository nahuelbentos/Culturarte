package logica;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import datatype.DtCategoria;

@Entity
@Table(name="CATEGORIAS")
public class Categoria {

	@Id
	@Column(name="NOMBRE", nullable=false, length=50)
	private String nombre;
	
	// Pseudoatributos
	@ManyToMany
	@JoinColumn(name="ID_CAT_SUPER")
	private List<Categoria> superCategorias = new ArrayList<>();
	
	public Categoria() {
		super();
	}
	
	public Categoria(String nombre) {
		super();
		this.nombre = nombre;
	}
	
	public Categoria(String nombre, ArrayList<Categoria> superCategorias) {
		super();
		this.nombre = nombre;
		this.superCategorias = superCategorias;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public ArrayList<Categoria> getSuperCategorias() {
		return (ArrayList<Categoria>) this.superCategorias;
	}
	
	public DtCategoria getDtCategoria() {
		ArrayList<DtCategoria> padres = new ArrayList<>();
		for (Categoria c : superCategorias) {
			padres.add(new DtCategoria(c.getNombre()));
		}
		
		return new DtCategoria(this.nombre, padres);
	}
	
	public DtCategoria getDtCategoriaSimple() {
		return new DtCategoria(nombre);
	}
	
	public ArrayList<Categoria> getDtSuperCategorias() {
		ArrayList<Categoria> padres = new ArrayList<>();
		for (int i = 0; i < this.superCategorias.size(); i++)
			padres.add(superCategorias.get(i));
		return padres;
	}

	public void addPadre(Categoria padre) {
		this.superCategorias.add(padre);
	}
}
