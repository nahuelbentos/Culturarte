package logica;

import java.util.ArrayList;
import java.util.GregorianCalendar;
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
import datatype.TipoRetorno;
import logica.exceptions.ColaboradorNoExisteException;
import logica.exceptions.PropuestaNoExisteException;
import logica.handler.ColaboracionHandler;
import logica.handler.ColaboradorHandler;
import logica.handler.ProponenteHandler;
import logica.handler.PropuestaHandler;

public class PropuestaController implements IPropuestaController {

	private static EntityManager em;
	private static EntityManagerFactory emf;
	
	@Override
	public boolean altaPropuesta(DtPropuesta dtPropuesta) {
		// TODO Auto-generated method stub
		return false;
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
	public ArrayList<DtPropuesta> listarPropuestasExistentes() {
		PropuestaHandler mpropue = PropuestaHandler.getInstance();
		Map<String, Propuesta> props = mpropue.getPropuestas();
		
		ArrayList<DtPropuesta> DtPropuestas = new ArrayList<DtPropuesta>(); 
		for(Propuesta p : props.values()) {
			DtPropuestas.add(p.getDtPropuesta());
		}

		return DtPropuestas;
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
		return null;
		/*
		PropuestaHandler mpropue = PropuestaHandler.getInstance();
		Propuesta p = mpropue.obtenerPropuesta(titulo); //1
		
		DtDatosPropuesta datapro = p.getDtDatosPropuesta(); //2
		
		ColaboracionHandler mcolab = ColaboracionHandler.getInstance();
		Colaboracion[] colColab = mcolab.getColaboraciones();
		ArrayList<DtColaborador> colaboradores = new ArrayList<DtColaborador>();
		float montoTotal=0;
		for (Colaboracion col : colColab) { //3
			if(col.tieneProp(titulo)) { //4 
				montoTotal += col.getMonto(); //5.1 
				colaboradores.add(col.getDataColaboracion().getColaborador()); //5.2				
			}
		}
		
		DtDatosPropuesta dtp = new DtDatosPropuesta(datapro.getTitulo(), datapro.getDescripcion(), datapro.getImagen(),
				datapro.getMontoNecesario(), datapro.getFechaPublicacion(), datapro.getFechaEspecatulo(), datapro.getLugar(),
				datapro.getPrecioEntrada(), datapro.getTipo(), montoTotal, colaboradores);
		return dtp;
		*/
	}

	@Override
	public void nuevaColaboracionAuxiliarHarcode() {
		//Configuramos el EMF a trav�s de la unidad de persistencia
		emf = Persistence.createEntityManagerFactory("Conexion");
		//Generamos un EntityManager
		em = emf.createEntityManager();
		
		
		//List<Propuesta> propAux = em.createQuery("SELECT p FROM Propuesta p", Propuesta.class).getResultList();
		em.getTransaction().begin();
		
		DtColaboracion aux = new DtColaboracion("chupa_chichi", "jhona", 5.00, new GregorianCalendar(2018, 8, 10), TipoRetorno.entradasGratis);
		
		Colaborador col = em.find(Colaborador.class, 1);
		Propuesta prop = em.find(Propuesta.class, 6);
		
		Colaboracion auxColaboracion = new Colaboracion(aux.getMonto(), aux.getFechaAporte(), aux.getTipo());
		auxColaboracion.setColaborador(col);
		auxColaboracion.setPropuestaColaborada(prop);
		
		em.persist(auxColaboracion);
		em.getTransaction().commit();
		
		em.close();
		/*
		
		
		try {
			generarColaboracion(aux);
		} catch (ColaboradorNoExisteException | PropuestaNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		*/
	}

}
