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
import datatype.DtInfoPago;
import datatype.DtPropuesta;
import datatype.DtPropuestaMinificado;
import datatype.DtUsuario;
import datatype.EstadoPropuesta;
import excepciones.CategoriaNoExisteException;
import excepciones.ColaboracionExistenteException;
import excepciones.ColaboracionNoExisteException;
import excepciones.ColaboradorNoExisteException;
import excepciones.ProponenteNoExisteException;
import excepciones.PropuestaNoExisteException;
import excepciones.PropuestaRepetidaException;
import excepciones.TipoPagoInexistenteExpection;
import excepciones.UsuarioSinLoguearseException;
import excepciones.UsuarioYaExisteFavoritaException;
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
		List<Propuesta> propuestas = em.createQuery("FROM Propuesta WHERE ESTAELIMINADA = :no").setParameter("no", false).getResultList();
        em.close();
        if (!propuestas.isEmpty()) {
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
					EstadoPropuesta estadoActual = null;
					boolean actualizo = true;
					
					// Actualizo el estado de la propuesta.
					/* Busco las colaboraciones que tenga la propuesta, 		 *
					 * calculo el recaudado y cambio el estado segun corresponda */
					@SuppressWarnings("unchecked")
					List<Colaboracion> colaboraciones = em.createQuery("FROM Colaboracion WHERE propuestaColaborada = :propuesta").setParameter("propuesta", p).getResultList();
					if (!colaboraciones.isEmpty()) {
						double recaudado = colaboracion.getMonto();
						
						for (Colaboracion auxCol : colaboraciones)
							recaudado += auxCol.getMonto();
						
						if (recaudado >= p.getMontoNecesario())
							estadoActual = EstadoPropuesta.financiada;
						else 
							actualizo = false; // no debo actualizar estado
						
					} else {
						estadoActual = EstadoPropuesta.enFinanciacion;
					}
					
					Colaboracion beanCol = new Colaboracion(colaboracion.getMonto(),colaboracion.getFechaAporte(),colaboracion.getTipo());
					beanCol.setColaborador(c);
					beanCol.setPropuestaColaborada(p);
					
					em.persist(beanCol);
					
					if (actualizo) {
						// Actualizo estado Actual de la propuesta
						em.createQuery("UPDATE Propuesta SET estadoActual = :estado WHERE titulo = :titulo")
						.setParameter("estado", estadoActual)
						.setParameter("titulo", p.getTitulo())
						.executeUpdate();
						
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
		
		DtColaboracion[] dtC = null;
		@SuppressWarnings("unchecked")
		List<Colaboracion> cols = em.createQuery("FROM Colaboracion WHERE PROPUESTA='" + titulo + "'").getResultList();
		em.close();
        if (!cols.isEmpty()) {
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
		
        @SuppressWarnings("unchecked")
		List<Propuesta> propuestas = em.createQuery("FROM Propuesta WHERE ESTADO_ACTUAL ='" + estadoPropuesta + "' AND ESTAELIMINADA = :no")
				.setParameter("no", false)
				.getResultList();
        em.close();
        if (!propuestas.isEmpty()) {
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
	public DtDatosPropuesta consultarPropuesta(String titulo) throws ProponenteNoExisteException {
		cph = ConexionPostgresHibernate.getInstancia();
		emf = cph.getEntityManager();
		em = emf.createEntityManager();
		
		Propuesta p = em.find(Propuesta.class, titulo); //1
		
		if (p != null) {
			@SuppressWarnings("unchecked")
			List<Colaboracion> colColab = em.createQuery("FROM Colaboracion WHERE propuesta = :p")
									.setParameter("p",p)
									.getResultList();

			em.close();
			DtDatosPropuesta dtp = p.getDtDatosPropuesta();
			double montoTotal = 0;
			ArrayList<String> colaboradores = new ArrayList<String>();
			for (Colaboracion colaboracion : colColab) {
				montoTotal = montoTotal + colaboracion.getMonto();				
				dtp.addColaborador(colaboracion.getColaborador().getNickname());
			}
			dtp.setColaboradores(colaboradores);
			dtp.setRecaudado(montoTotal);
			return dtp;
		}else {
			em.close();
			throw new ProponenteNoExisteException("No existe la propuesta");
		}
	}

	@Override
	public DtDatosPropuesta consultarDatosPropuesta(String titulo) {
		cph = ConexionPostgresHibernate.getInstancia();
		emf = cph.getEntityManager();
		em = emf.createEntityManager();
		
		Propuesta p = em.find(Propuesta.class, titulo); //1
		
        @SuppressWarnings("unchecked")
		List<Colaboracion> colColab = em.createQuery("FROM Colaboracion").getResultList();

		em.close();
		DtDatosPropuesta dtp = new DtDatosPropuesta();
		if (p != null) {
			DtDatosPropuesta datapro = p.getDtDatosPropuesta(); //2
	        if(datapro!=null) {
				double montoTotal=0;
				for (Colaboracion col : colColab) { //3
					if(col.tieneProp(titulo)) { //4 
						montoTotal += col.getMonto(); //5.1 
						datapro.addColaborador(col.getColaborador().getNickname()); //5.2				
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
		List<Propuesta> propuestas = em.createQuery("FROM Propuesta WHERE estado_actual = 'ingresada' AND estaeliminada = :no")
				.setParameter("no", false)
				.getResultList();
        em.close();
        
        if (!propuestas.isEmpty()) {
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
		GregorianCalendar now = (GregorianCalendar) GregorianCalendar.getInstance();
		
        @SuppressWarnings("unchecked")
		List<Propuesta> ps = em.createQuery("FROM Propuesta WHERE ESTAELIMINADA = :no and estado_actual = :estado and NICK_PROPONENTE = :nicknameProponente and fechaFinalizacion >= :now")
											.setParameter("no", false)
											.setParameter("estado", estado.toString())
											.setParameter("nicknameProponente", nicknameProponente)
											.setParameter("now", now)
											.getResultList();
        em.close();
        
        if (!ps.isEmpty()) {
			DtPropuestaMinificado[] props = new DtPropuestaMinificado[ps.size()];
			Propuesta pro;
			
			for (int i = 0; i < props.length; i++) {
				pro = ps.get(i);
				
				props[i] = new DtPropuestaMinificado(pro.getTitulo(),pro.getProponenteACargo().getNickname(),pro.getImagen());
			}
			return props;
		}else {
			throw new PropuestaNoExisteException("No existen propuestas en el estado " + estado + " para el proponente " + nicknameProponente);
		}
	}
	
	@Override
	public DtPropuestaMinificado[] listarPropuestasProponente(String nicknameProponente) throws PropuestaNoExisteException{
		cph = ConexionPostgresHibernate.getInstancia();
		emf = cph.getEntityManager();
		em = emf.createEntityManager();
		
        @SuppressWarnings("unchecked")
		List<Propuesta> ps = em.createQuery("FROM Propuesta WHERE ESTAELIMINADA = :si "
				+ "AND NICK_PROPONENTE = :nicknameProponente")
				.setParameter("si", true)
				.setParameter("nicknameProponente", nicknameProponente)
				.getResultList();
        em.close();
        
        if (!ps.isEmpty()) {
			DtPropuestaMinificado[] props = new DtPropuestaMinificado[ps.size()];
			Propuesta pro;
			
			for (int i = 0; i < props.length; i++) {
				pro = ps.get(i);
				
				props[i] = new DtPropuestaMinificado(pro.getTitulo(),pro.getProponenteACargo().getNickname(),pro.getImagen());
			}
			return props;
		}else {
			throw new PropuestaNoExisteException("No existen propuestas del proponente " + nicknameProponente);
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
		List<Propuesta> ps = em.createQuery("FROM Propuesta WHERE estado_actual <> 'ingresada' and estaeliminada = :no")
					.setParameter("no", false)
					.getResultList();
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
	public void agregarFavorita(String titulo, DtUsuario usuarioLogueado) throws UsuarioSinLoguearseException {
		/** Controlo que el usuario este logueado, en caso que no este logueado lanzo exception **/
		if (usuarioLogueado != null) {
			
			cph = ConexionPostgresHibernate.getInstancia();
			emf = cph.getEntityManager();
			em = emf.createEntityManager();
			em.getTransaction().begin();
			
			Propuesta p = em.find(Propuesta.class, titulo);
			
			if (p != null) {
				Usuario u = em.find(Usuario.class, usuarioLogueado.getNickname());
				
				try {
					u.addFavorita(p);
					em.merge(u);
					
					em.getTransaction().commit();
					em.close();
				} catch (UsuarioYaExisteFavoritaException e) {
					em.getTransaction().rollback();
					em.close();
				}
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
		List<Propuesta> propuestas = em.createQuery("FROM Propuesta WHERE estaeliminada = : no")
					.setParameter("no", false)
					.getResultList();
        
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

	@Override
	public DtPropuesta[] getPropuestasPopulares() {
		cph = ConexionPostgresHibernate.getInstancia();
		emf = cph.getEntityManager();
		em = emf.createEntityManager();
		
		@SuppressWarnings("unchecked")
		List<Propuesta> populares = em.createQuery("SELECT p FROM Usuario u "
				+ "JOIN u.propuestasFavoritas p "
				+ "WHERE u.estaeliminado = :no "
				+ "GROUP BY p "
				+ "ORDER BY count(p) DESC")
				.setParameter("no", false)
				.setMaxResults(5)
				.getResultList();
		
		em.close();
		
		DtPropuesta[] dtpop = new DtPropuesta[populares.size()]; 
		
		for (int i = 0; i < dtpop.length; i++) {
			dtpop[i] = populares.get(i).getDtPropuesta();		
		}
		
		return dtpop;
	}
	
	@Override
	public DtPropuestaMinificado[] propuestasDesdeBuscador(String buscar) {
		
		cph = ConexionPostgresHibernate.getInstancia();
		emf = cph.getEntityManager();
		em = emf.createEntityManager();
		
		buscar = "%" + buscar.trim().toLowerCase() + "%";
		@SuppressWarnings("unchecked")
		List<Propuesta> resultado = em.createQuery("FROM Propuesta WHERE " + 
													"(lower(titulo) like '" + buscar + "') " + 
													"or (lower(descripcion) like '" + buscar + "') " + 
													"or (lower(lugar) like '" + buscar + "')" +
													"and estaeliminada = :no")
									.setParameter("no", false)
									.getResultList();
		
		em.close();
		
		DtPropuestaMinificado[] dtResu = new DtPropuestaMinificado[resultado.size()]; 
		
		for (int i = 0; i < dtResu.length; i++) {
			dtResu[i] = new DtPropuestaMinificado(resultado.get(i).getTitulo(), resultado.get(i).getProponenteACargo().getNickname(), resultado.get(i).getImagen());
		}
		
		return dtResu;
	}

	@Override
	public void pagarColaboracion(DtInfoPago infoPago) throws TipoPagoInexistenteExpection {
		cph = ConexionPostgresHibernate.getInstancia();
		emf = cph.getEntityManager();
		em = emf.createEntityManager();
		
		ColaboracionID claveColaboracion = new ColaboracionID();
		claveColaboracion.setIdColaborador(infoPago.getNickColaborador());
		claveColaboracion.setIdPropuesta(infoPago.getTitPropuesta());
		
		Colaboracion colaboracion = em.find(Colaboracion.class, claveColaboracion);
		em.close();
		
		colaboracion.crearPago(infoPago.getPago());
		
	}
	
	@Override
	public DtColaboracion[] listarColaboracionesAPagar(String nickColaborador) throws ColaboracionNoExisteException {
		cph = ConexionPostgresHibernate.getInstancia();
		emf = cph.getEntityManager();
		em = emf.createEntityManager();
		
		DtColaboracion[] colabs = null;
        @SuppressWarnings("unchecked")
		List<Colaboracion> colaboraciones = em.createQuery("FROM Colaboracion WHERE COLABORADOR = :colab")
				.setParameter("colab", nickColaborador)
				.getResultList();
        em.close();
        
        if (!colaboraciones.isEmpty()) {
            colabs = new DtColaboracion[colaboraciones.size()];
            int i = 0;
            for (Colaboracion col : colaboraciones) {
            	// El comprobante de pago se puede emitir unicamente si 
            	// el pago está hecho y si aun no se ha emitido antes
            	boolean puedoEmitir = false;
            	if (col.getPago() != null)
            		puedoEmitir = !col.getPago().isCompEmitido();
            	else
            		puedoEmitir = false;
            	
                colabs[i] = new DtColaboracion(col.getPropuestaColaborada().getTitulo(), col.getColaborador().getNickname(), col.getMonto(), col.getFechaAporte(), col.getTipo(), puedoEmitir);
                i++;
            }
            
            return colabs;
        } else {
        	throw new ColaboracionNoExisteException("No se encontraron colaboraciones.");
        }
	}
	
	@Override
	public DtInfoPago obtenerComprobanteDePagoDeColaboracion(String nickColaborador, String tituloPropuesta) throws TipoPagoInexistenteExpection, ColaboracionNoExisteException {
		cph = ConexionPostgresHibernate.getInstancia();
		emf = cph.getEntityManager();
		em = emf.createEntityManager();
		
		DtInfoPago infoPago = null;
		@SuppressWarnings("unchecked")
		List<Colaboracion> colaboraciones = em.createQuery("FROM Colaboracion WHERE COLABORADOR = :colab and PROPUESTA = :prop")
												.setParameter("colab", nickColaborador)
												.setParameter("prop", tituloPropuesta)
												.getResultList();
		
		if (!colaboraciones.isEmpty()) {
			em.getTransaction().begin();
			for (Colaboracion col : colaboraciones) {
				infoPago = col.getDtInfoPago();
				col.marcarPagoComoEmitido();
				em.persist(col);
			}
			em.getTransaction().commit();
			em.close();
			return infoPago;
		} else {
			em.getTransaction().rollback();
			em.close();
			throw new ColaboracionNoExisteException("No se encontró la colaboración de " + nickColaborador + " para la propuesta " + tituloPropuesta + ".");
		}
	}

}
