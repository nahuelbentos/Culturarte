package logica;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import datatype.DtCategoria;
import excepciones.CategoriaNoExisteException;
import excepciones.CategoriaYaExisteException;
import persistencia.ConexionPostgresHibernate;

public class CategoriaController implements ICategoriaController {
	
	private static ConexionPostgresHibernate cph;
	private static EntityManagerFactory emf;
	private static EntityManager em;

	@Override
	public DtCategoria[] listarCategorias(){
		
		inicializarTablaVacia();	//	Si la tabla no tiene registros, crea el nodo base "Categorias"
		
		//emf = Persistence.createEntityManagerFactory("Conexion");
		//em = emf.createEntityManager();
		
		cph = ConexionPostgresHibernate.getInstancia();
		emf = cph.getEntityManager();
		em = emf.createEntityManager();
		em.getTransaction().begin();
		
		DtCategoria[] dtC = null;
		// Me traigo la categoria base "Categorias", la cual me traera todas por ser sus hijas
		@SuppressWarnings("unchecked")
		List<Categoria> cats = em.createQuery("FROM Categoria WHERE NOMBRE<>'Categorias'").getResultList();
		
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
		
		inicializarTablaVacia();	//	Si la tabla no tiene registros, crea el nodo base "Categorias"
		
		cph = ConexionPostgresHibernate.getInstancia();
		emf = cph.getEntityManager();
		em = emf.createEntityManager();
		em.getTransaction().begin();
		
		DtCategoria[] dtC = null;
		// Me traigo la categoria base "Categorias", la cual me traera todas por ser sus hijas
		@SuppressWarnings("unchecked")
		List<Categoria> cats = em.createQuery("FROM Categoria WHERE NOMBRE='Categorias'").getResultList();
		
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
		cph = ConexionPostgresHibernate.getInstancia();
		emf = cph.getEntityManager();
		em = emf.createEntityManager();
		em.getTransaction().begin();
		
		@SuppressWarnings("unchecked")
		List<Categoria> cats = em.createQuery("FROM Categoria").getResultList();
		
        if (cats.isEmpty()) {
        	Categoria categoria = new Categoria("Categorias");
			em.persist(categoria);
			em.getTransaction().commit();
        }
        em.close();
	}
	
	@Override
	public void agregarCategoria(DtCategoria dtC) throws CategoriaYaExisteException, CategoriaNoExisteException {
		
		cph = ConexionPostgresHibernate.getInstancia();
		emf = cph.getEntityManager();
		em = emf.createEntityManager();
		em.getTransaction().begin();

		Categoria categoria = em.find(Categoria.class, dtC.getNombre());
		
		if (categoria != null) {
			em.getTransaction().rollback();
			em.close();
			throw new CategoriaYaExisteException("Ya existe la categoria " + dtC.getNombre());
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
						throw new CategoriaNoExisteException("No existe la categoria padre: " + p.getNombre());
					}
				}
			}else {
				Categoria padre = em.find(Categoria.class, "Categorias");
				if (padre != null) {
					padre.addHijo(categoria);
					em.persist(padre);
				} else {
					em.getTransaction().rollback();
					em.close();
					throw new CategoriaNoExisteException("No existe la categoria padre: Categorias. No se puede crear una categoria.");
				}
			}
			
			em.getTransaction().commit();
			em.close();
		}
	}
	
	@Override
	public void borrarCategorias() {
		cph = ConexionPostgresHibernate.getInstancia();
		emf = cph.getEntityManager();
		em = emf.createEntityManager();
		em.getTransaction().begin();
		em.createQuery("delete from Categoria").executeUpdate();
		em.getTransaction().commit();
		em.close();
	}

}
