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
import datatype.DtUsuario;
import datatype.EstadoPropuesta;
import excepciones.CategoriaNoExisteException;
import excepciones.ColaboradorNoExisteException;
import excepciones.ProponenteNoExisteException;
import excepciones.PropuestaNoExisteException;
import excepciones.PropuestaRepetidaException;
import logica.handler.ColaboracionHandler;

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
		
		// Inicializo el historial de la propuesta.
		Estado e = new Estado(EstadoPropuesta.ingresada,propuesta,new GregorianCalendar());
		em.persist(e);
		
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public DtPropuestaMinificado[] listarPropuestas() throws PropuestaNoExisteException {
		
		emf = Persistence.createEntityManagerFactory("Conexion");
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
				
				em.persist(beanCol);
				
				/* Una vez registrada la colaboracion actualizo el estado de la propuesta. */
				
				EstadoPropuesta estadoActual = null;
				boolean actualizo = true;
				
				// Actualizo el estado de la propuesta.
				/* Busco las colaboraciones que tenga la propuesta, 		 *
				 * calculo el recaudado y cambio el estado segun corresponda */
				@SuppressWarnings("unchecked")
				List<Colaboracion> colaboraciones = em.createQuery("FROM Colaboracion WHERE propuestaColaborada = :propuesta").setParameter("propuesta", p).getResultList();
				if (colaboraciones != null) {
					double recaudado = 0;
					
					for (Colaboracion auxCol : colaboraciones)
						recaudado += auxCol.getMonto();
					
					if (recaudado >= p.getMontoNecesario())
						estadoActual = EstadoPropuesta.financiada;
					else 
						actualizo = false; // no debo actualizar estado
					
				} else {
					estadoActual = EstadoPropuesta.enFinanciacion;
				}
				
				if (actualizo) {
					// Actualizo estado Actual de la propuesta
					em.createQuery("UPDATE Propuesta SET estadoActual = :estado").setParameter("estado", estadoActual).executeUpdate();
					
					// Actualizo historial del estado de la propuesta
					Estado e = new Estado(estadoActual, p, new GregorianCalendar());
					em.persist(e);
				}
				
				em.getTransaction().commit();
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
		
		DtPropuesta dtp = null;
		Propuesta p = em.find(Propuesta.class, titulo);
		if(p != null) {
			dtp = p.getDtPropuesta();
		}
		
		em.close();
		
		return dtp;
	}

	@Override
	public boolean modificarPropuesta(DtPropuesta dtPropuesta) {
		emf = Persistence.createEntityManagerFactory("Conexion");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Proponente proponente = em.find(Proponente.class, dtPropuesta.getProponenteACargo().getNickname());
		Categoria categoria = em.find(Categoria.class, dtPropuesta.getCategoria().getNombre());
		
		em.createQuery("UPDATE Propuesta SET descripcion = :descripcion, "
				+ "imagen = :imagen, "
				+ "fechaPublicacion = :fechaPublicacion, "
				+ "fechaEspecatulo = :fechaEspecatulo, "
				+ "montoNecesario = :montoNecesario, "
				+ "precioEntrada = :precioEntrada, "
				+ "lugar = :lugar, "
				+ "tipo = :tipo, "
				+ "proponenteACargo = :proponente, "
				+ "categoria = :categoria "
				+ "where titulo = :titulo")
		.setParameter("titulo", dtPropuesta.getTitulo())
		.setParameter("descripcion", dtPropuesta.getDescripcion())
		.setParameter("imagen", dtPropuesta.getImagen())
		.setParameter("fechaPublicacion", dtPropuesta.getFechaPublicacion())
		.setParameter("fechaEspecatulo", dtPropuesta.getFechaEspecatulo())
		.setParameter("montoNecesario", dtPropuesta.getMontoNecesario())
		.setParameter("precioEntrada", dtPropuesta.getPrecioEntrada())
		.setParameter("lugar", dtPropuesta.getLugar())
		.setParameter("tipo", dtPropuesta.getTipo())
		.setParameter("proponente", proponente)
		.setParameter("categoria", categoria)
		.executeUpdate();

		em.getTransaction().commit();
		em.close();
		
		return true;
	}

	@Override
	public DtColaboracion[] listarColaboraciones() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DtPropuesta[] listarPropuestasExistentes() {
		emf = Persistence.createEntityManagerFactory("Conexion");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		
		DtPropuesta[] dtPropuesta = null;
        List<Propuesta> propuestas = em.createQuery("FROM Propuesta").getResultList();
        if (propuestas  != null) {
            dtPropuesta = new DtPropuesta[propuestas.size()];
            Propuesta propuesta;
            for (int i = 0; i < propuestas.size(); i++) {
                propuesta = propuestas.get(i);
                dtPropuesta[i] = new DtPropuesta(propuesta.getTitulo(), propuesta.getDescripcion(), propuesta.getImagen(), 
                		propuesta.getMontoNecesario(), propuesta.getFechaPublicacion(), propuesta.getFechaEspecatulo(), 
                		propuesta.getLugar(), propuesta.getPrecioEntrada(), propuesta.getTipo(), 0, 
                		propuesta.getProponenteACargo().getDtProponente(), null, null, 
                		propuesta.getCategoria().getDtCategoriaSimple(), null); 
            }
        }
        em.close();
        return dtPropuesta;
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
		
		emf = Persistence.createEntityManagerFactory("Conexion");
		em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		Propuesta p = em.find(Propuesta.class, titulo); //1
		
		DtDatosPropuesta datapro= p.getDtDatosPropuesta(); //2
		
        List<Colaboracion> colColab = em.createQuery("FROM Colaboracion").getResultList();
        
		ArrayList<String> colaboradores = new ArrayList<String>();
		double montoTotal=0;
		for (Colaboracion col : colColab) { //3
			if(col.tieneProp(titulo)) { //4 
				montoTotal += col.getMonto(); //5.1 
				colaboradores.add(col.getColaborador().getNickname()); //5.2				
			}
		}
		

		em.close();
		
		return new DtDatosPropuesta(datapro.getTitulo(), datapro.getDescripcion(), datapro.getImagen(),
		datapro.getMontoNecesario(), datapro.getFechaPublicacion(), datapro.getFechaEspecatulo(), datapro.getLugar(),
		datapro.getPrecioEntrada(), datapro.getTipo(), montoTotal, colaboradores);
		
	}

}
