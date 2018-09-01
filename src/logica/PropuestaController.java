package logica;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import datatype.DtColaboracion;
import datatype.DtColaborador;
import datatype.DtDatosPropuesta;
import datatype.DtPropuesta;
import datatype.DtPropuestaMinificado;
import datatype.EstadoPropuesta;
import logica.exceptions.CategoriaNoExisteException;
import logica.exceptions.ColaboradorNoExisteException;
import logica.exceptions.ProponenteNoExisteException;
import logica.exceptions.PropuestaNoExisteException;
import logica.exceptions.PropuestaRepetidaException;
import logica.handler.CategoriaHandler;
import logica.handler.ProponenteHandler;
import logica.handler.ColaboracionHandler;
import logica.handler.PropuestaHandler;

public class PropuestaController implements IPropuestaController {

	private static EntityManager em;
	private static EntityManagerFactory emf;
	
	@Override
	public void altaPropuesta(DtPropuesta dtPropuesta) throws PropuestaRepetidaException, ProponenteNoExisteException, CategoriaNoExisteException{
		emf = Persistence.createEntityManagerFactory("Conexion");
		em = emf.createEntityManager();
		em.getTransaction().begin();

		Propuesta propuesta = em.find(Propuesta.class, dtPropuesta.getTitulo());
		
		//------------------------- Seteo los PseudoAtributos -------------------------
		// Proponente a cargo
		String nicknameProponente = dtPropuesta.getProponenteACargo().getNickname();
		Proponente proponente = em.find(Proponente.class, nicknameProponente);
		
		if (proponente == null)
			throw new ProponenteNoExisteException("No existe el proponente " + nicknameProponente);
		
		// Categoria
		String nombreCategoria = dtPropuesta.getCategoria().getNombre();
		Categoria cat = em.find(Categoria.class, nombreCategoria);
		
		if (cat == null)
			throw new CategoriaNoExisteException("No existe la categoría " + nombreCategoria);
		
		
		// Cargo la categoría
		if (propuesta != null)
			throw new PropuestaRepetidaException("Ya existe la propuesta" + dtPropuesta.getTitulo());
		
		propuesta = new Propuesta(dtPropuesta);
		propuesta.setProponenteACargo(proponente);
		propuesta.setCategoria(cat);
		
		em.persist(propuesta);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public DtPropuestaMinificado[] listarPropuestas() throws PropuestaNoExisteException {
		
		//Configuramos el EMF a trav�s de la unidad de persistencia
		emf = Persistence.createEntityManagerFactory("Conexion");
		//Generamos un EntityManager
		em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
        @SuppressWarnings("unchecked")
		List<Propuesta> propuestas = em.createQuery("FROM Propuesta").getResultList();
        
        if (propuestas != null) {
			DtPropuestaMinificado[] propsMin = new DtPropuestaMinificado[propuestas.size()];
			Propuesta pro;
			for (int i = 0; i < propsMin.length; i++) {
				pro = propuestas.get(i);
				propsMin[i] = new DtPropuestaMinificado(pro.getTitulo(), pro.getProponenteACargo().getNickname());
			}
			
			em.close();
			return propsMin;
		}else {
			
			em.close();
			throw new PropuestaNoExisteException("No existen propuestas en el sistema.");
		}
	}
	
	@Override
	public void generarColaboracion(DtColaboracion colaboracion) throws ColaboradorNoExisteException, PropuestaNoExisteException{
		//Configuramos el EMF a trav�s de la unidad de persistencia
		emf = Persistence.createEntityManagerFactory("Conexion");
		//Generamos un EntityManager
		em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		Colaborador c = em.find(Colaborador.class, colaboracion.getColaborador());
		if (c != null) {
			Propuesta p = em.find(Propuesta.class, colaboracion.getTituloPropuesta());
			
			if (p != null) {
				
				Colaboracion beanCol = new Colaboracion(colaboracion.getMonto(),colaboracion.getFechaAporte(),colaboracion.getTipo());
				beanCol.setColaborador(c);
				beanCol.setPropuestaColaborada(p);
				
				//Persistimos el objeto
				em.persist(beanCol);
				//Commmiteamos la transacci�n
				em.getTransaction().commit();
				//Cerramos el EntityManager
				em.close();
				
			}else {
				em.getTransaction().rollback();
				em.close();
				throw new PropuestaNoExisteException("No existe propuesta");
			}
				
		}else {
			em.getTransaction().rollback();
			em.close();
			throw new ColaboradorNoExisteException("No existe colaborador");
		}
		
	}

	@Override
	public DtPropuesta seleccionarPropuesta(String titulo) {
		
		emf = Persistence.createEntityManagerFactory("Conexion");
		em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		Propuesta p = em.find(Propuesta.class, titulo);
		
		DtPropuesta dtp = p.getDtPropuesta();
		
		em.close();
		
		return dtp;
	}

	@Override
	public boolean modificarPropuesta(DtPropuesta dtPropuesta) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public DtColaboracion[] listarColaboraciones() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DtPropuesta[] listarPropuestasExistentes() {
		// la comento, hay que hacerlo desde la base, es mas facil
//		PropuestaHandler mpropue = PropuestaHandler.getInstance();
//		Map<String, Propuesta> props = mpropue.getPropuestas();
//		
//		ArrayList<DtPropuesta> DtPropuestas = new ArrayList<DtPropuesta>(); 
//		for(Propuesta p : props.values()) {
//			DtPropuestas.add(p.getInfoPropuesta());
//		}
//
//		return DtPropuestas;
		return null;
	}
	
	@Override
	public DtPropuesta[] listarPropuestasPorEstado(EstadoPropuesta estadoPropuesta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean altaColaboracion(DtColaboracion dtColaboracion) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminarColaboraciones(String titulo, String nickname) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public DtDatosPropuesta consultarPropuesta(String titulo) {
		// la comento, hay que revisar si se puede usar otro Dt.
//		PropuestaHandler mpropue = PropuestaHandler.getInstance();
//		Propuesta p = mpropue.obtenerPropuesta(titulo); //1
//		
//		DtDatosPropuesta datapro = p.getDtDatosPropuesta(); //2
//		
//		ColaboracionHandler mcolab = ColaboracionHandler.getInstance();
//		Colaboracion[] colColab = mcolab.getColaboraciones();
//		ArrayList<DtColaborador> colaboradores = new ArrayList<DtColaborador>();
//		float montoTotal=0;
//		for (Colaboracion col : colColab) { //3
//			if(col.tieneProp(titulo)) { //4 
//				montoTotal += col.getMonto(); //5.1 
//				colaboradores.add(col.getDataColaboracion().getColaborador()); //5.2				
//			}
//		}
//		
//		DtDatosPropuesta dtp = new DtDatosPropuesta(datapro.getTitulo(), datapro.getDescripcion(), datapro.getImagen(),
//				datapro.getMontoNecesario(), datapro.getFechaPublicacion(), datapro.getFechaEspecatulo(), datapro.getLugar(),
//				datapro.getPrecioEntrada(), datapro.getTipo(), montoTotal, colaboradores);
//		return dtp;
		
		return null;
	}

}
