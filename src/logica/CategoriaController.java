package logica;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import datatype.DtCategoria;
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
            Categoria categ;

            for (int i = 0; i < cats.size(); i++) {
                categ = cats.get(i);
                dtC[i] = new DtCategoria(categ.getNombre(), categ.getDtSuperCategorias());
            }
        }
        em.close();
        return dtC;
	}

	@Override
	public void agregarCategoria(DtCategoria dtC) throws CategoriaYaExisteException {
		
		emf = Persistence.createEntityManagerFactory("Conexion");
		em = emf.createEntityManager();
		em.getTransaction().begin();

		Categoria categoria = em.find(Categoria.class, dtC.getNombre());
		
		if (categoria != null)
			throw new CategoriaYaExisteException("Ya existe la categoría " + dtC.getNombre());
		else {

			categoria = new Categoria(dtC.getNombre());
			
			ArrayList<String> padres = dtC.getPadres();
			if (padres !=null) {
				for (int i = 0; i < padres.size(); i++) {
					Categoria padre = em.find(Categoria.class, padres.get(i));
					if (padre !=null)
						categoria.addPadre(padre);
				}
			}
			
			em.persist(categoria);
			em.getTransaction().commit();
			em.close();
		}
	}

}
