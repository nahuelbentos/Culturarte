package logica;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import datatype.DtCategoria;
import excepciones.CategoriaNoExisteException;
import excepciones.CategoriaYaExisteException;

public class CategoriaController implements ICategoriaController {
	
	private static EntityManager em;
	private static EntityManagerFactory emf;

	@Override
	public DtCategoria[] listarCategorias(){
		
		inicializarTablaVacia();	//	Si la tabla no tiene registros, crea el nodo base "Categorías"
		
		emf = Persistence.createEntityManagerFactory("Conexion");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		
		DtCategoria[] dtC = null;
		// Me traigo la categoría base "Categorías", la cual me traera todas por ser sus hijas
		@SuppressWarnings("unchecked")
		List<Categoria> cats = em.createQuery("FROM Categoria WHERE NOMBRE<>'Categorías'").getResultList();
		
        if (cats != null) {
            dtC = new DtCategoria[cats.size()];
            DtCategoria categ=null;

            for (int i = 0; i < cats.size(); i++) {
                categ = cats.get(i).getDtCategoriaFull();
                dtC[i] = new DtCategoria(categ.getNombre(), categ.getSuperCategorias(), categ.getSubCategorias());
            }
        }
        em.close();
        return dtC;
	}
	
	@Override
	public DtCategoria[] listarCategoriasJTree(){
		
		inicializarTablaVacia();	//	Si la tabla no tiene registros, crea el nodo base "Categorías"
		
		emf = Persistence.createEntityManagerFactory("Conexion");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		
		DtCategoria[] dtC = null;
		// Me traigo la categoría base "Categorías", la cual me traera todas por ser sus hijas
		@SuppressWarnings("unchecked")
		List<Categoria> cats = em.createQuery("FROM Categoria WHERE NOMBRE='Categorías'").getResultList();
		
        if (cats != null) {
            dtC = new DtCategoria[cats.size()];
            DtCategoria categ=null;

            for (int i = 0; i < cats.size(); i++) {
                categ = cats.get(i).getDtCategoriaFull();
                dtC[i] = new DtCategoria(categ.getNombre(), categ.getSuperCategorias(), categ.getSubCategorias());
            }
        }
        em.close();
        return dtC;
	}
	
	private void inicializarTablaVacia() {
		emf = Persistence.createEntityManagerFactory("Conexion");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		
		@SuppressWarnings("unchecked")
		List<Categoria> cats = em.createQuery("FROM Categoria").getResultList();
		
        if (cats.isEmpty()) {
        	Categoria categoria = new Categoria("Categorías");
			em.persist(categoria);
			em.getTransaction().commit();
        }
        em.close();
	}
	
	@Override
	public void agregarCategoria(DtCategoria dtC) throws CategoriaYaExisteException, CategoriaNoExisteException {
		
		emf = Persistence.createEntityManagerFactory("Conexion");
		em = emf.createEntityManager();
		em.getTransaction().begin();

		Categoria categoria = em.find(Categoria.class, dtC.getNombre());
		
		if (categoria != null) {
			em.getTransaction().rollback();
			em.close();
			throw new CategoriaYaExisteException("Ya existe la categoría " + dtC.getNombre());
		}else {
			
			categoria = new Categoria(dtC.getNombre());
			em.persist(categoria);
			
			if (!dtC.getSuperCategorias().isEmpty()) {
				for (DtCategoria p : dtC.getSuperCategorias()) {
					Categoria padre = em.find(Categoria.class, p.getNombre());
					if (padre != null) {
						padre.addHijo(categoria);
						em.persist(padre);
					} else {
						em.getTransaction().rollback();
						em.close();
						throw new CategoriaNoExisteException("No existe la categoría padre: " + p.getNombre());
					}
				}
			}else {
				Categoria padre = em.find(Categoria.class, "Categorías");
				if (padre != null) {
					padre.addHijo(categoria);
					em.persist(padre);
				} else {
					em.getTransaction().rollback();
					em.close();
					throw new CategoriaNoExisteException("No existe la categoría padre: Categorías. No se puede crear una categoría.");
				}
			}
			
			em.getTransaction().commit();
			em.close();
		}
	}

}
