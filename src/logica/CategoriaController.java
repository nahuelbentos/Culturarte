package logica;
import java.util.ArrayList;
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
		
		emf = Persistence.createEntityManagerFactory("Conexion");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		
		DtCategoria[] dtC = null;
        List<Categoria> cats = em.createQuery("FROM Categoria").getResultList();
		
        if (cats != null) {
            dtC = new DtCategoria[cats.size()];
            DtCategoria categ;

            for (int i = 0; i < cats.size(); i++) {
                categ = cats.get(i).getDtCategoria();
                dtC[i] = new DtCategoria(categ.getNombre(), categ.getSuperCategorias());
            }
        }
        em.close();
        return dtC;
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
			
			ArrayList<Categoria> padres = new ArrayList<>();
			for (DtCategoria p : dtC.getSuperCategorias()) {
				Categoria padre = em.find(Categoria.class, p.getNombre());
				if (padre != null) {
					padres.add(padre);
				} else {
					em.getTransaction().rollback();
					em.close();
					throw new CategoriaNoExisteException("No existe la categoría padre: " + p.getNombre());
				}
			}
			
			categoria = new Categoria(dtC.getNombre(), padres);
			em.persist(categoria);
			em.getTransaction().commit();
			em.close();
		}
	}

}
