package logica;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import datatype.DtColaboracion;
import datatype.DtDatosPropuesta;
import datatype.DtPropuesta;
import datatype.DtPropuestaMinificado;
import datatype.DtUsuario;
import datatype.EstadoPropuesta;
import excepciones.CategoriaNoExisteException;
import excepciones.ColaboracionExistenteException;
import excepciones.ColaboradorNoExisteException;
import excepciones.ProponenteNoExisteException;
import excepciones.PropuestaNoExisteException;
import excepciones.PropuestaRepetidaException;
import excepciones.UsuarioSinLoguearseException;
import persistencia.ConexionPostgresHibernate;

public class PropuestaController implements IPropuestaController {

	private static ConexionPostgresHibernate cph;
	private static EntityManagerFactory emf;
	private static EntityManager em;
	
	public PropuestaController() {
		super();
	}
	
	@Override
	public void altaPropuesta(DtPropuesta dtPropuesta) throws PropuestaRepetidaException, ProponenteNoExisteException, CategoriaNoExisteException{
		cph = ConexionPostgresHibernate.getInstancia();
		emf = cph.getEntityManager();
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
		
		cph = ConexionPostgresHibernate.getInstancia();
		emf = cph.getEntityManager();
		em = emf.createEntityManager();
		em.getTransaction().begin();
		
        @SuppressWarnings("unchecked")
		List<Propuesta> propuestas = em.createQuery("FROM Propuesta").getResultList();
        em.close();
        if (propuestas != null) {
			DtPropuestaMinificado[] propsMin = new DtPropuestaMinificado[propuestas.size()];
			Propuesta pro;
			for (int i = 0; i < propsMin.length; i++) {
				pro = propuestas.get(i);
				propsMin[i] = new DtPropuestaMinificado(pro.getTitulo(), pro.getProponenteACargo().getNickname(), pro.getImagen());
			}
			
			
			return propsMin;
		}else {
			
			throw new PropuestaNoExisteException("No existen propuestas en el sistema.");
		}
	}
	
	@Override
	public void generarColaboracion(DtColaboracion colaboracion) throws ColaboradorNoExisteException, PropuestaNoExisteException, ColaboracionExistenteException{
		cph = ConexionPostgresHibernate.getInstancia();
		emf = cph.getEntityManager();
		em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Colaborador c = em.find(Colaborador.class, colaboracion.getColaborador());
		if (c != null) {
			Propuesta p = em.find(Propuesta.class, colaboracion.getTituloPropuesta());
			
			if (p != null) {
				/* Verifico que no exista una colaboracion del colaborador para la propuesta */
				ColaboracionID claveColaboracion = new ColaboracionID();
				claveColaboracion.setIdColaborador(colaboracion.getColaborador());
				claveColaboracion.setIdPropuesta(colaboracion.getTituloPropuesta());
				Colaboracion auxC = em.find(Colaboracion.class, claveColaboracion);
				
				if (auxC != null) {
					em.getTransaction().rollback();
					em.close();
					throw new ColaboracionExistenteException("Ya Existe Colaboracion para el colaborador");
				}else {
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
				}
								
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
		cph = ConexionPostgresHibernate.getInstancia();
		emf = cph.getEntityManager();
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
		cph = ConexionPostgresHibernate.getInstancia();
		emf = cph.getEntityManager();
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
	public DtColaboracion[] listarColaboraciones(String titulo) {
		cph = ConexionPostgresHibernate.getInstancia();
		emf = cph.getEntityManager();
		em = emf.createEntityManager();
		em.getTransaction().begin();
		
		DtColaboracion[] dtC = null;
		@SuppressWarnings("unchecked")
		List<Colaboracion> cols = em.createQuery("FROM Colaboracion WHERE PROPUESTA='" + titulo + "'").getResultList();
		em.close();
        if (cols != null) {
            dtC = new DtColaboracion[cols.size()];
            DtColaboracion colab=null;

            for (int i = 0; i < cols.size(); i++) {
                colab = cols.get(i).getDataColaboracion();
                dtC[i] = new DtColaboracion(colab.getTituloPropuesta(), colab.getColaborador(), colab.getMonto(), colab.getFechaAporte(), colab.getTipo());
            }
        }
        
        return dtC;
	}

	@Override
	public DtPropuesta[] listarPropuestasExistentes() {
		cph = ConexionPostgresHibernate.getInstancia();
		emf = cph.getEntityManager();
		em = emf.createEntityManager();
		em.getTransaction().begin();
		
		DtPropuesta[] dtPropuesta = null;
        @SuppressWarnings("unchecked")
		List<Propuesta> propuestas = em.createQuery("FROM Propuesta").getResultList();
        em.close();
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
        return dtPropuesta;
	}
	
	@Override
	public DtPropuestaMinificado[] listarPropuestasPorEstado(EstadoPropuesta estadoPropuesta) throws PropuestaNoExisteException {
		cph = ConexionPostgresHibernate.getInstancia();
		emf = cph.getEntityManager();
		em = emf.createEntityManager();
		em.getTransaction().begin();
		
        @SuppressWarnings("unchecked")
		List<Propuesta> propuestas = em.createQuery("FROM Propuesta WHERE ESTADO_ACTUAL ='" + estadoPropuesta + "'").getResultList();
        em.close();
        if (propuestas != null) {
			DtPropuestaMinificado[] propsMin = new DtPropuestaMinificado[propuestas.size()];
			Propuesta pro;
			for (int i = 0; i < propsMin.length; i++) {
				pro = propuestas.get(i);
				propsMin[i] = new DtPropuestaMinificado(pro.getTitulo(), pro.getProponenteACargo().getNickname(),pro.getImagen());
			}
			
			return propsMin;
		}else {
			
			throw new PropuestaNoExisteException("No existen propuestas en el sistema con estado " + estadoPropuesta + ".");
		}
	}

	@Override
	public DtDatosPropuesta consultarPropuesta(String titulo) {
		// la comento, hay que revisar si se puede usar otro Dt.

		
		cph = ConexionPostgresHibernate.getInstancia();
		emf = cph.getEntityManager();
		em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Propuesta p = em.find(Propuesta.class, titulo); //1
		
        @SuppressWarnings("unchecked")
		List<Colaboracion> colColab = em.createQuery("FROM Colaboracion").getResultList();

		em.close();
		DtDatosPropuesta dtp = new DtDatosPropuesta();
		if (p != null) {
//			System.out.println("1 \n");
//			System.out.println("\n p.titulo: " + p.getTitulo());
			DtDatosPropuesta datapro= p.getDtDatosPropuesta(); //2

//			System.out.println("2 \n");
	        if(datapro!=null) {
//				System.out.println("3 \n");
//				System.out.println("\n datapro.Descripcion: " + datapro.getDescripcion());
				ArrayList<String> colaboradores = new ArrayList<String>();
				double montoTotal=0;
				for (Colaboracion col : colColab) { //3
					if(col.tieneProp(titulo)) { //4 
						montoTotal += col.getMonto(); //5.1 
						colaboradores.add(col.getColaborador().getNickname()); //5.2				
					}
				}
				
				datapro.setRecaudado(montoTotal);
				
				return  datapro;
				
	        }else
	        	return dtp;
		}else 
			return dtp;
	}

	@Override
	public void evaluarPropuesta(String titulo, EstadoPropuesta estado) throws PropuestaNoExisteException {
		cph = ConexionPostgresHibernate.getInstancia();
		emf = cph.getEntityManager();
		em = emf.createEntityManager();
		em.getTransaction().begin();
		
		
		em.createQuery("UPDATE Propuesta SET estado_actual = :estado WHERE titulo = :titulo")
		.setParameter("estado",estado.toString())
		.setParameter("titulo", titulo)
		.executeUpdate();
		
		Propuesta p = em.find(Propuesta.class, titulo);
		if (p != null) {
			/* ** Agrego estado al historial de la propuesta ** */
			// Actualizo historial del estado de la propuesta
			Estado e = new Estado(estado, p, new GregorianCalendar());
			em.persist(e);
			
			em.getTransaction().commit();
			em.close();
		}else {
			em.getTransaction().rollback();
			em.close();
			throw new PropuestaNoExisteException("No existe la propuesta " + titulo + " en el sistema.");
		}

		
	}

	@Override
	public DtPropuestaMinificado[] listadoPropuestasIngresadas() throws PropuestaNoExisteException {
		cph = ConexionPostgresHibernate.getInstancia();
		emf = cph.getEntityManager();
		em = emf.createEntityManager();
		em.getTransaction().begin();
		
        @SuppressWarnings("unchecked")
		List<Propuesta> propuestas = em.createQuery("FROM Propuesta WHERE estado_actual = 'ingresada'").getResultList();
        em.close();
        
        if (propuestas != null) {
			DtPropuestaMinificado[] propsMin = new DtPropuestaMinificado[propuestas.size()];
			Propuesta pro;
			for (int i = 0; i < propsMin.length; i++) {
				pro = propuestas.get(i);
				propsMin[i] = new DtPropuestaMinificado(pro.getTitulo(), pro.getProponenteACargo().getNickname(),pro.getImagen());
			}
			
			
			return propsMin;
		}else {
			
			throw new PropuestaNoExisteException("No quedan propuestas por evaluar");
		}
	}
	
	@Override
	public DtPropuestaMinificado[] listarPropuestasProponentePorEstado(String nicknameProponente, EstadoPropuesta estado) throws PropuestaNoExisteException{
		cph = ConexionPostgresHibernate.getInstancia();
		emf = cph.getEntityManager();
		em = emf.createEntityManager();
		em.getTransaction().begin();
		
		GregorianCalendar now = (GregorianCalendar) GregorianCalendar.getInstance();
		
        @SuppressWarnings("unchecked")
		List<Propuesta> ps = em.createQuery("FROM Propuesta WHERE estado_actual = :estado and NICK_PROPONENTE = :nicknameProponente and fechaFinalizacion >= :now")
											.setParameter("estado", estado.toString())
											.setParameter("nicknameProponente", nicknameProponente)
											.setParameter("now", now)
											.getResultList();
        em.close();
        
        if (ps != null) {
			DtPropuestaMinificado[] props = new DtPropuestaMinificado[ps.size()];
			Propuesta pro;
			
			for (int i = 0; i < props.length; i++) {
				pro = ps.get(i);
				
				props[i] = new DtPropuestaMinificado(pro.getTitulo(),pro.getProponenteACargo().getNickname(),pro.getImagen());
			}
//			System.out.println("Se encontraron " + props.length + " propuestas en el estado " + estado + " para el proponente " + nicknameProponente);
			
			return props;
		}else {
//			System.out.println("No existen propuestas en el estado " + estado + " para el proponente " + nicknameProponente);
			throw new PropuestaNoExisteException("No existen propuestas en el estado " + estado + " para el proponente " + nicknameProponente);
		}
	}
	
	@Override
	public void extenderFinanciacion(String tituloPropuesta) throws PropuestaNoExisteException{
		cph = ConexionPostgresHibernate.getInstancia();
		emf = cph.getEntityManager();
		em = emf.createEntityManager();
		em.getTransaction().begin();
		
		GregorianCalendar fechaFinalizacion = (GregorianCalendar) GregorianCalendar.getInstance();
		fechaFinalizacion.add(GregorianCalendar.DAY_OF_MONTH, 30);	//	agrego 30 días a la caducidad
		
		Propuesta propuesta = em.find(Propuesta.class, tituloPropuesta);
		
		if (propuesta!=null) {
			// guardo la nueva fecha de finalización
			propuesta.setFechaFinalizacion(fechaFinalizacion);
			em.persist(propuesta);
			
			Estado historico = new Estado(propuesta.getEstadoActual(), propuesta, (GregorianCalendar) GregorianCalendar.getInstance());
			em.persist(historico);
		}else {
			em.getTransaction().rollback();
			em.close();
			throw new PropuestaNoExisteException("No se encontró la propuesta " + tituloPropuesta);
		}
		
		em.getTransaction().commit();
		em.close();
	}
	
	@Override
	public void cancelarPropuesta(String tituloPropuesta) throws PropuestaNoExisteException{
		cph = ConexionPostgresHibernate.getInstancia();
		emf = cph.getEntityManager();
		em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Propuesta propuesta = em.find(Propuesta.class, tituloPropuesta);
		
		if (propuesta!=null) {
			// guardo la nueva fecha de finalización
			propuesta.setEstadoActual(EstadoPropuesta.cancelada);
			em.persist(propuesta);
			
			GregorianCalendar fechaCancelacion = (GregorianCalendar) GregorianCalendar.getInstance();
			Estado hist = new Estado(EstadoPropuesta.cancelada, propuesta, fechaCancelacion);
			em.persist(hist);
		}else {
			em.getTransaction().rollback();
			em.close();
			throw new PropuestaNoExisteException("No se encontró la propuesta " + tituloPropuesta);
		}
		
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public DtPropuestaMinificado[] listarPropuestasActivas() {
		cph = ConexionPostgresHibernate.getInstancia();
		emf = cph.getEntityManager();
		em = emf.createEntityManager();
		em.getTransaction().begin();
		
        @SuppressWarnings("unchecked")
		List<Propuesta> ps = em.createQuery("FROM Propuesta WHERE estado_actual <> 'ingresada'").getResultList();
        em.close();
        
        if (ps != null) {
			DtPropuestaMinificado[] props = new DtPropuestaMinificado[ps.size()];
			Propuesta pro;
			
			for (int i = 0; i < props.length; i++) {
				pro = ps.get(i);
				
				props[i] = new DtPropuestaMinificado(pro.getTitulo(),pro.getProponenteACargo().getNickname(),pro.getImagen());
			}
			
			return props;
		}else {
			
			return null;
		}
        
	}

	@Override
	public void agregarFavorita(String titulo, DtUsuario usuarioLogueado) throws UsuarioSinLoguearseException{
		/** Controlo que el usuario este logueado, en caso que no este logueado lanzo exception **/
		if (usuarioLogueado != null) {
			
			System.out.println(usuarioLogueado.getNickname());
			
			cph = ConexionPostgresHibernate.getInstancia();
			emf = cph.getEntityManager();
			em = emf.createEntityManager();
			em.getTransaction().begin();
			
			Propuesta p = em.find(Propuesta.class, titulo);
			
			if (p != null) {
				System.out.println(p.getTitulo());
				Usuario u = em.find(Usuario.class, usuarioLogueado.getNickname());
				
				u.addFavorita(p);
				
				em.merge(u);
				
				em.getTransaction().commit();
				em.close();
				
			}else {
				em.getTransaction().rollback();
				em.close();
			}
			
		}else {
			throw new UsuarioSinLoguearseException("Debe iniciar sesion para agregar Propuestas a sus favoritos");
		}
		
	}
	
	@Override
	public void borrarPropuestas() {
		cph = ConexionPostgresHibernate.getInstancia();
		emf = cph.getEntityManager();
		em = emf.createEntityManager();
		em.getTransaction().begin();
		em.createQuery("delete from Estado").executeUpdate();
		em.createQuery("delete from Colaboracion").executeUpdate();
		em.createQuery("delete from Propuesta").executeUpdate();
		em.getTransaction().commit();
		em.close();
	}
	
	@Override
	public void setearEstadosPropuests(String estado, String propuesta, String fechaCambioString) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date fechaCambio = sdf.parse(fechaCambioString);
		
		cph = ConexionPostgresHibernate.getInstancia();
		emf = cph.getEntityManager();
		em = emf.createEntityManager();
		em.getTransaction().begin();
		
		em.createNativeQuery("INSERT INTO estados_propuesta (estado, propuesta, fecha_cambio) "
				+ "VALUES (:estado, :propuesta, :fechaCambio)")
		.setParameter("estado", estado)
		.setParameter("propuesta", propuesta)
		.setParameter("fechaCambio", fechaCambio)
		.executeUpdate();
		
		em.getTransaction().commit();
		em.close();
	}
	 
	public ArrayList<DtPropuesta> listarPropuestasPorCategoria(String nombreCat){
		cph = ConexionPostgresHibernate.getInstancia();
		emf = cph.getEntityManager();
		em = emf.createEntityManager();
		em.getTransaction().begin();
        @SuppressWarnings("unchecked")
		List<Propuesta> propuestas = em.createQuery("FROM Propuesta").getResultList();
        em.close();

		ArrayList<DtPropuesta> dtps = new ArrayList<DtPropuesta>();
        for (Propuesta p : propuestas) {
			if(p.tieneCategoria(nombreCat))
				dtps.add(p.getDtPropuesta());
		}
		return dtps;
	}

	@Override
	public void borrarEstadosPropuestas() {
		cph = ConexionPostgresHibernate.getInstancia();
		emf = cph.getEntityManager();
		em = emf.createEntityManager();
		em.getTransaction().begin();
		em.createQuery("delete from Estado").executeUpdate();
		em.getTransaction().commit();
		em.close();
	}
	
}
