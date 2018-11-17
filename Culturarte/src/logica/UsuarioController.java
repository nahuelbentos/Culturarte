package logica;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;

import datatype.DtColaboracion;
import datatype.DtColaborador;
import datatype.DtPerfilColaborador;
import datatype.DtPerfilProponente;
import datatype.DtPerfilUsuario;
import datatype.DtProponente;
import datatype.DtPropuesta;
import datatype.DtPropuestaColaborada;
import datatype.DtUsuario;
import datatype.EstadoPropuesta;
import datatype.TipoRetorno;
import excepciones.ColaboracionNoExisteException;
import excepciones.ColaboradorNoExisteException;
import excepciones.NoExistenProponentesEliminadosException;
import excepciones.ProponenteNoExisteException;
import excepciones.UsuarioNoExisteElUsuarioException;
import excepciones.UsuarioSinLoguearseException;
import excepciones.UsuarioYaExisteElEmailException;
import excepciones.UsuarioYaExisteElUsuarioException;
import excepciones.UsuarioYaSigueAlUsuarioException;
import persistencia.ConexionPostgresHibernate;

public class UsuarioController implements IUsuarioController {

	private static ConexionPostgresHibernate cph;
	private static EntityManagerFactory emf;
	private static EntityManager em;
	
	public UsuarioController() {
		super();
	}

	@Override
	public void agregarUsuario(DtUsuario dtUsuario) throws UsuarioYaExisteElUsuarioException, UsuarioYaExisteElEmailException {
		cph = ConexionPostgresHibernate.getInstancia();
		emf = cph.getEntityManager();
		em = emf.createEntityManager();
		em.getTransaction().begin();

		Usuario usuario = em.find(Usuario.class, dtUsuario.getNickname());
		Usuario usuarioDos = null;
		
		/* Le asigno la contraseña de String a char[] porque se rompia el webservice */
		dtUsuario.setPassword(dtUsuario.getPasswordString().toCharArray());
		
		try {
			usuarioDos = (Usuario) em.createQuery("FROM Usuario where correoElectronico = :correoElectronico"
					+ " and flagElm = :no")
					.setParameter("no", false)
					.setParameter("correoElectronico", dtUsuario.getEmail()).getSingleResult();
		} catch (NoResultException nre){}
		if (usuario != null) {
			throw new UsuarioYaExisteElUsuarioException("El usuario " + dtUsuario.getNickname() + " ya esta registrado");
		} else if (usuarioDos != null) {
			throw new UsuarioYaExisteElEmailException("El email " + dtUsuario.getEmail() + " ya esta registrado");
			
		} else {
			if (dtUsuario instanceof DtProponente) {
				DtProponente dtProponente = (DtProponente) dtUsuario;
				usuario = new Proponente(dtProponente.getDireccion(), dtProponente.getBiografia(),
						dtProponente.getSitioWeb(), dtProponente.getNickname(), dtProponente.getNombre(),
						dtProponente.getFechaNacimiento(), dtProponente.getEmail(), dtProponente.getPassword(), 
						dtProponente.getApellido(), dtProponente.getImagen());

			} else if (dtUsuario instanceof DtColaborador) {
				DtColaborador dtColaborador = (DtColaborador) dtUsuario;
				usuario = new Colaborador(dtColaborador.getNickname(), dtColaborador.getNombre(),
						dtColaborador.getFechaNacimiento(), dtColaborador.getEmail(), dtColaborador.getPassword(), 
						dtColaborador.getApellido(), dtColaborador.getImagen());
			}

			em.persist(usuario);
			em.getTransaction().commit();
			em.close();
		}
	}

	@Override
	public DtColaboracion listarColaboracion(String titulo, String nickname) throws ColaboracionNoExisteException{
		cph = ConexionPostgresHibernate.getInstancia();
		emf = cph.getEntityManager();
		em = emf.createEntityManager();
		 
		/*Defino la clave en colaboracion a buscar, recibida por parametro.*/
		ColaboracionID claveColaboracion = new ColaboracionID();
		claveColaboracion.setIdColaborador(nickname);
		claveColaboracion.setIdPropuesta(titulo);
		
		Colaboracion c = em.find(Colaboracion.class, claveColaboracion);
		em.close();
		if (c != null)
			return new DtColaboracion(titulo,nickname,c.getMonto(),c.getFechaAporte(),c.getTipo());
		else 
			throw new ColaboracionNoExisteException("No existe colaboracion del usuairo " + nickname + " para " + titulo);
	}

	@Override
	public  DtUsuario[] listarProponentes() throws ProponenteNoExisteException {
		cph = ConexionPostgresHibernate.getInstancia();
		emf = cph.getEntityManager();
		em = emf.createEntityManager();
		
		DtUsuario[] dtUsuario = null;
        @SuppressWarnings("unchecked")
		List<Usuario> usuarios = em.createQuery("FROM Usuario WHERE TIPOUSUARIO = 'P'"
				+ " and flagElm = :no")
				.setParameter("no", false)
				.getResultList();
        em.close();
        
        if (!usuarios.isEmpty()) {
            dtUsuario = new DtUsuario[usuarios.size()];
            Usuario usuario;
            for (int i = 0; i < usuarios.size(); i++) {
                usuario = usuarios.get(i);
                dtUsuario[i] = new DtUsuario(usuario.getNickname(), usuario.getNombre(),
                		usuario.getApellido(), usuario.getCorreoElectronico(), usuario.getPassword(), 
                		usuario.getFechaNacimiento(), usuario.getImagen());
            }
            
            return dtUsuario;
        } else {
        	throw new ProponenteNoExisteException("No hay proponentes registrados");
        }
	}

	@Override
	public void seguirUsuario(String nicknameUno, String nicknameDos) throws UsuarioYaSigueAlUsuarioException {
		cph = ConexionPostgresHibernate.getInstancia();
		emf = cph.getEntityManager();
		em = emf.createEntityManager();
		em.getTransaction().begin();

        Usuario usuarioUno = em.find(Usuario.class, nicknameUno);
        Usuario usuarioDos = em.find(Usuario.class, nicknameDos);

        UsuarioSigueID usuarioSIgueID = new UsuarioSigueID(nicknameUno,nicknameDos);
        UsuarioSigue usuarioSigue = em.find(UsuarioSigue.class, usuarioSIgueID);

		if (usuarioSigue != null) {
        	throw new UsuarioYaSigueAlUsuarioException("El usuario " + nicknameUno
        			+ " ya sigue al usuario " + nicknameDos);
		} else {
			usuarioUno.seguirUsuario(usuarioDos);
		}

		em.flush();
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void dejarDeSeguirUsuario(String nicknameUno, String nicknameDos) {
		cph = ConexionPostgresHibernate.getInstancia();
		emf = cph.getEntityManager();
		em = emf.createEntityManager();
		em.getTransaction().begin();

        Usuario usuarioUno = em.find(Usuario.class, nicknameUno);
        Usuario usuarioDos = em.find(Usuario.class, nicknameDos);
        usuarioUno.dejarDeSeguirUsuario(usuarioDos);

        UsuarioSigueID usuarioSigueId = new UsuarioSigueID();
        usuarioSigueId.setUsuarioUno(nicknameUno);
        usuarioSigueId.setUsuarioDos(nicknameDos);

		em.createQuery("delete from UsuarioSigue where id = :id").setParameter("id", usuarioSigueId).executeUpdate();
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public DtUsuario[] listarUsuarios() {
		cph = ConexionPostgresHibernate.getInstancia();
		emf = cph.getEntityManager();
		em = emf.createEntityManager();

		DtUsuario[] dtUsuario = null;
        @SuppressWarnings("unchecked")
		List<Usuario> usuarios = em.createQuery("FROM Usuario WHERE flagElm = :no").setParameter("no", false).getResultList();
        if (usuarios != null) {
            dtUsuario = new DtUsuario[usuarios.size()];
            Usuario usuario;
            for (int i = 0; i < usuarios.size(); i++) {
                usuario = usuarios.get(i);
                dtUsuario[i] = usuario.getDtUsuario();
            }
        }
        em.close();
        return dtUsuario;
	}

	@Override
	public DtUsuario verPerfilUsuario(String nickname) throws UsuarioNoExisteElUsuarioException {
		cph = ConexionPostgresHibernate.getInstancia();
		emf = cph.getEntityManager();
		em = emf.createEntityManager();

		Usuario usuario = em.find(Usuario.class, nickname);
        DtUsuario dtUsuario = null;
        if (usuario != null) {
        	if (usuario instanceof Proponente) {
				Proponente proponente = (Proponente) usuario;
				dtUsuario = new DtProponente(proponente.getNickname(), proponente.getNombre(), proponente.getApellido(),
						proponente.getCorreoElectronico(), proponente.getPassword(), proponente.getFechaNacimiento(), 
						proponente.getFechaDeEliminacion(), proponente.getImagen(),
						proponente.getDireccion(), proponente.getBiografia(), proponente.getLinkWeb());
			} else if (usuario instanceof Colaborador) {
				Colaborador colaborador = (Colaborador) usuario;
				dtUsuario = new DtColaborador(colaborador.getNickname(), colaborador.getNombre(), colaborador.getApellido(),
						colaborador.getCorreoElectronico(), colaborador.getPassword(), colaborador.getFechaNacimiento(), colaborador.getImagen());
			}
        } else {
            throw new UsuarioNoExisteElUsuarioException("El usuario " + nickname + " no existe");
        }
        
        em.close();
        
        return dtUsuario;
	}

	@Override
	public DtUsuario[] listarUsuariosQueSigue(String nickname) {
		cph = ConexionPostgresHibernate.getInstancia();
		emf = cph.getEntityManager();
		em = emf.createEntityManager();
		
		Usuario usuario = em.find(Usuario.class, nickname);
        @SuppressWarnings("unchecked")
		List<Usuario> usuarios = em.createQuery("SELECT usuarioDos FROM UsuarioSigue "
				+ "WHERE usuario_uno_id = :usuarioUno").setParameter("usuarioUno", usuario).getResultList();
        DtUsuario[] listaDeUsuarios = null;
		if (usuarios != null) {
	        listaDeUsuarios = new DtUsuario[usuarios.size()];
	        Usuario usuarioDos;
	        for (int i = 0; i < usuarios.size(); i++) {
	        	usuarioDos = usuarios.get(i);
	        	listaDeUsuarios[i] = new DtUsuario(usuarioDos.getNickname(), usuarioDos.getNombre(),
	        			usuarioDos.getApellido(), usuarioDos.getCorreoElectronico(), usuarioDos.getPassword(), 
	        			usuarioDos.getFechaNacimiento(), usuarioDos.getImagen());
	        	if (usuarioDos instanceof Colaborador) {
					Colaborador usuarioAAgregar = (Colaborador) usuarioDos;
					listaDeUsuarios[i] = new DtColaborador(usuarioAAgregar.getNickname(), usuarioAAgregar.getNombre(),
							usuarioAAgregar.getApellido(), usuarioAAgregar.getCorreoElectronico(), usuarioAAgregar.getPassword(), 
		        			usuarioAAgregar.getFechaNacimiento(), usuarioAAgregar.getImagen());
	        	} else if (usuarioDos instanceof Proponente) {
	        		Proponente usuarioAAgregar = (Proponente) usuarioDos;
					listaDeUsuarios[i] = new DtProponente(usuarioAAgregar.getNickname(), usuarioAAgregar.getNombre(),
							usuarioAAgregar.getApellido(), usuarioAAgregar.getCorreoElectronico(), usuarioAAgregar.getPassword(), 
							usuarioAAgregar.getFechaNacimiento(), usuarioAAgregar.getImagen(), usuarioAAgregar.getDireccion(), 
		        			usuarioAAgregar.getBiografia(), usuarioAAgregar.getLinkWeb());
				}
	        }
		}
		return listaDeUsuarios;
	}

	@Override
	public DtUsuario[] listarColaboradores() throws ColaboradorNoExisteException {
		cph = ConexionPostgresHibernate.getInstancia();
		emf = cph.getEntityManager();
		em = emf.createEntityManager();
		
		DtUsuario[] dtUsuario = null;
        @SuppressWarnings("unchecked")
		List<Usuario> usuarios = em.createQuery("FROM Usuario WHERE TIPOUSUARIO = 'C' "
				+ " and flagElm = :no")
				.setParameter("no", false)
				.getResultList();
        em.close();
        if (!usuarios.isEmpty()) {
            dtUsuario = new DtUsuario[usuarios.size()];
            Usuario usuario;
            for (int i = 0; i < usuarios.size(); i++) {
                usuario = usuarios.get(i);
                dtUsuario[i] = new DtUsuario(usuario.getNickname(), usuario.getNombre(),
                		usuario.getApellido(), usuario.getCorreoElectronico(), usuario.getPassword(), 
                		usuario.getFechaNacimiento(), usuario.getImagen());
            }
            return dtUsuario;
        }else {
        	throw new ColaboradorNoExisteException("No hay colaboradores registrados");
        }
	}

	@Override
	public DtPerfilProponente verPerfilProponente(String nickname) {
		cph = ConexionPostgresHibernate.getInstancia();
		emf = cph.getEntityManager();
		em = emf.createEntityManager();

        @SuppressWarnings("unchecked")
		List<Propuesta> propouestas = em.createQuery("FROM Propuesta").getResultList();
        @SuppressWarnings("unchecked")
		List<Colaboracion> colabs = em.createQuery("FROM Colaboracion").getResultList();
    	Usuario usuario = em.find(Usuario.class, nickname);

    	em.close();

    	if (usuario != null) {
        	if (usuario instanceof Proponente) {
				Proponente p = (Proponente) usuario;

	        	DtPerfilProponente auxUsuProponente = p.getDatosBasicos(); //2

	    		ArrayList<DtPropuesta> prPublicadas = new ArrayList<DtPropuesta>();
	    		ArrayList<DtPropuesta> prCanceladas = new ArrayList<DtPropuesta>();
	    		ArrayList<DtPropuesta> prEnFinanciacion = new ArrayList<DtPropuesta>();
	    		ArrayList<DtPropuesta> prFinanciadas = new ArrayList<DtPropuesta>();
	    		ArrayList<DtPropuesta> prNoFinanciadas = new ArrayList<DtPropuesta>();
	    		ArrayList<DtPropuesta> prIngresadas = new ArrayList<DtPropuesta>();

	    		for(int i = 0; i < propouestas.size(); i++) { //3
	    			Propuesta prop = propouestas.get(i);
	    			if(prop.isProponenteACargo(nickname)) {

	    				ArrayList<DtColaboracion> colaboraciones = new ArrayList<DtColaboracion>();
	    				for(Colaboracion col : colabs) { //6
	    					if(col.tieneProp(prop.getTitulo())) {
	    						colaboraciones.add(col.getDataColaboracion());
	    					}
	    				}
//	    					**--*-**-*--*-*-*-*-* [VOLVER PARA ATRAS DESPUES EL getDtCategoriaSimpla -> gtDtCategoria]*-*--*-*-*-*-*-*-*-*
	    				DtPropuesta dataPro = new DtPropuesta(prop.getTitulo(), prop.getDescripcion(), prop.getImagen(),prop.getMontoNecesario(),
	    				 prop.getFechaPublicacion(), prop.getFechaEspecatulo(), prop.getLugar(), prop.getPrecioEntrada(), TipoRetorno.EntradasGratis, 0,
	    				 prop.getProponenteACargo().getDtProponente(), prop.getEstadoActual(), prop.getDtEstadoHistorial(),
	    				 prop.getCategoria().getDtCategoriaSimple(), colaboraciones);
	    				
//	    				System.out.println("dataPro.Estadoactual: " + dataPro.getEstadoActual() + " \n");
	    				switch (dataPro.getEstadoActual()){
	    					case publicada:
	    						prPublicadas.add(dataPro);
	    						break;
	    					case cancelada:
	    						prCanceladas.add(dataPro);
	    						break;
	    					case enFinanciacion:
	    						prEnFinanciacion.add(dataPro);
	    						break;
	    					case financiada:
	    						prFinanciadas.add(dataPro);
	    						break;
	    					case noFinanciada:
	    						prNoFinanciadas.add(dataPro);
	    						break;
	    					case ingresada:
	    						prIngresadas.add(dataPro);
	    						break;
	    					default:
	    						break;
	    				}
	    			}
	    		}

	    		return new DtPerfilProponente(auxUsuProponente.getNickname(), auxUsuProponente.getNombre(),
	    				auxUsuProponente.getApellido(),auxUsuProponente.getEmail(), auxUsuProponente.getPassword(), 
	    				auxUsuProponente.getFechaNacimiento(), auxUsuProponente.getImagen(),
	    				auxUsuProponente.getDireccion(), auxUsuProponente.getBiografia(), auxUsuProponente.getSitioWeb(),
	    				prIngresadas, prPublicadas, prCanceladas, prEnFinanciacion, prFinanciadas, prNoFinanciadas);
        	}else
        		return  new  DtPerfilProponente("Fallo1pruebaController", "Fallo1pruebaController", "Fallo1pruebaController", "pruebaController", null, null,
            			null,"pruebaController", "pruebaController", "pruebaController",null,null, null, null, null, null);

        }else {
        	return  new  DtPerfilProponente("FallopruebaController", "FallopruebaController", "FallopruebaController", "pruebaController", null, null,
        			null,"pruebaController", "pruebaController", "pruebaController",null,null, null, null, null, null);
        }


	}

	@Override
	public DtPerfilColaborador verPerfilColaborador(String nickname) {
		cph = ConexionPostgresHibernate.getInstancia();
		emf = cph.getEntityManager();
		em = emf.createEntityManager();

		Usuario usuario = em.find(Usuario.class, nickname); //1y2

        @SuppressWarnings("unchecked")
		List<Colaboracion> colabs = em.createQuery("FROM Colaboracion").getResultList();

		ArrayList<DtPropuestaColaborada> colaboracionesHechas = new ArrayList<DtPropuestaColaborada>();

		em.close();
    	if (usuario != null) {
        	if (usuario instanceof Colaborador) {
				Colaborador perfil = (Colaborador) usuario;
		
				for(Colaboracion c : colabs) { //1*
					if(c.tieneColaborador(nickname)) { //2* y 2.1*
						 double montoAportado = c.getMonto(); //3*
						 DtPropuestaColaborada p = c.getPropuestaFromColaboracion(); //4* y 4.1*
						 if (p != null) {
							 DtPropuestaColaborada colaboracion = new DtPropuestaColaborada(p.getTitulo(), p.getDescripcion(), p.getImagen(), montoAportado,
								 p.getProponenteACargo(), p.getEstadoActual()); //3.2*
							 colaboracionesHechas.add(colaboracion);
						 }else {
							 // revisar
						 }
						 
					}
				}
				return new DtPerfilColaborador(perfil.getNickname(), perfil.getNombre(), perfil.getApellido(), 
						perfil.getCorreoElectronico(), perfil.getPassword(), perfil.getFechaNacimiento(), perfil.getImagen(), colaboracionesHechas);
        	}else
        		return  new  DtPerfilColaborador("Fallo_perfil.getNickname()", "fallo_perfil.getNombre()", "Fallo_perfil.getApellido()",
        				"falo_perfil.getCorreoElectronico()", null, null,null,null);
        }else
        	return  new  DtPerfilColaborador("Fallo2_perfil.getNickname()", "Fallo2_perfil.getNombre()", "Fallo2_perfil.getApellido()",
    				"Fallo2_perfil.getCorreoElectronico()", null, null,null,null);
        

	}

	@Override
	public DtPropuesta[] listarPropuestasDeUnColaborador(String nickname) {
		cph = ConexionPostgresHibernate.getInstancia();
		emf = cph.getEntityManager();
		em = emf.createEntityManager();
		
		DtPropuesta[] dtp = null;
		
		/** Obtengo el colaborador que deseo buscar sus colaboraciones. **/
		Colaborador col = em.find(Colaborador.class, nickname);
		
		/** Obtengo las colaboraciones del colaborador "col" **/
		@SuppressWarnings("unchecked")
		List<Colaboracion> colaboraciones = em.createQuery("FROM Colaboracion WHERE colaborador = :colaborador").setParameter("colaborador", col).getResultList();
		em.close();
		if (colaboraciones != null) {
			dtp = new DtPropuesta[colaboraciones.size()];
			int i = 0;
			for (Colaboracion colaboracion : colaboraciones) {
				DtPropuesta itemDtp = colaboracion.obtPropuesta();
				dtp[i] = itemDtp;
				
				i++;
			}
		}
		return dtp;
	}

	@Override
	public DtPropuesta[] listarPropuestasColaborador(DtUsuario usuarioLogueado) throws UsuarioSinLoguearseException {
		if (usuarioLogueado != null) {
			/** Obtengo las colaboraciones del colaborador "usuarioLogueado" que no haya comentado y esten financiadas **/
			if (usuarioLogueado instanceof DtColaborador) {
				
				cph = ConexionPostgresHibernate.getInstancia();
				emf = cph.getEntityManager();
				em = emf.createEntityManager();
				
				DtPropuesta[] dtp = null;
				Usuario usuario = em.find(Usuario.class, usuarioLogueado.getNickname());
				
				@SuppressWarnings("unchecked")
				List<Colaboracion> colaboraciones = em.createQuery("SELECT c FROM Colaboracion c, Propuesta p WHERE c.propuestaColaborada=p.titulo AND c.colaborador = :colaborador AND p.estadoActual = :estado AND c.comentario IS NULL")
						.setParameter("colaborador", usuario)
						.setParameter("estado", EstadoPropuesta.financiada)
						.getResultList();
				em.close();
				if (colaboraciones != null) {
					dtp = new DtPropuesta[colaboraciones.size()];
					int i = 0;
					for (Colaboracion colaboracion : colaboraciones) {
						DtPropuesta itemDtp = colaboracion.obtenerDtPropuesta();
						dtp[i] = itemDtp;
						
						i++;
					}
				}
				return dtp;
			}else{
				throw new UsuarioSinLoguearseException("Debes ser colaborador para ver tus colaboraciones.");
			}
		}else {
			throw new UsuarioSinLoguearseException("Debes iniciar sesion para agregar Propuestas a sus favoritos");
		}
	}

	@Override
	public void agregarComentarioAPropuesta(String comentario, String titulo, DtUsuario usuarioLogueado) throws UsuarioSinLoguearseException {
		if (usuarioLogueado != null) {
			// obtengo las colaboraciones del usuario logueado
			if (usuarioLogueado instanceof DtColaborador) {
				cph = ConexionPostgresHibernate.getInstancia();
				emf = cph.getEntityManager();
				em = emf.createEntityManager();
				em.getTransaction().begin();
				
				/*Defino la clave en colaboracion a buscar, recibida por parametro.*/
				ColaboracionID claveColaboracion = new ColaboracionID();
				claveColaboracion.setIdColaborador(usuarioLogueado.getNickname());
				claveColaboracion.setIdPropuesta(titulo);
				
				Colaboracion c = em.find(Colaboracion.class, claveColaboracion);
				
				c.setComentario(comentario);
				em.merge(c);
				em.getTransaction().commit();
				em.close();
				
			}else{
				throw new UsuarioSinLoguearseException("Debes ser colaborador para ver tus colaboraciones.");
			}
		}else {
			throw new UsuarioSinLoguearseException("Debes iniciar sesion para agregar Propuestas a sus favoritos");
		}
		
	}

	@Override
	public DtUsuario iniciarSesion(String datoSesion, char[] password) throws UsuarioNoExisteElUsuarioException {
		cph = ConexionPostgresHibernate.getInstancia();
		emf = cph.getEntityManager();
		em = emf.createEntityManager();
		Usuario u = null;
		DtUsuario dtu = null;
		
		try {
			u = em.find(Usuario.class, datoSesion);
			if (u == null) {
				u = (Usuario)em.createQuery("FROM Usuario WHERE email= :correo "
						+ "and flagElm = :no")
						.setParameter("no", false)
						.setParameter("correo", datoSesion)
						.getSingleResult();
			}
			// lo pongo afuera del if para devolver algo.
			dtu = u.getDtUsuario();
			if (u != null && !u.isFlagElm()) {
				
				for (Propuesta p : u.getPropuestasFavoritas()) {
					dtu.addTituloFavoritas(p.getTitulo());
				}
		        @SuppressWarnings("unchecked")
		        List<Usuario> seguidos = em.createQuery("select u.usuarioDos FROM UsuarioSigue u WHERE u.usuarioUno = :nickname")
		        		.setParameter("nickname", u)
		        		.getResultList();
				for (Usuario s : seguidos) {
					dtu.addUsuarioSeguido(s.getNickname());
				}
			}else {
				em.close();
				throw new UsuarioNoExisteElUsuarioException("Nickname / Email o Password incorrectos");
			}	
			return dtu;
		} catch (NoResultException nre){
			em.close();
			throw new UsuarioNoExisteElUsuarioException("Nickname / Email o Password incorrectos");
		}
	}
	
	@Override
	public void borrarUsuarios() {
		cph = ConexionPostgresHibernate.getInstancia();
		emf = cph.getEntityManager();
		em = emf.createEntityManager();
		em.getTransaction().begin();
		em.createQuery("delete from UsuarioSigue").executeUpdate();
		em.createQuery("delete from Colaboracion").executeUpdate();
		em.createQuery("delete from Usuario").executeUpdate();
		em.getTransaction().commit();
		em.close();
	}
	
	public DtPerfilUsuario obtenerPerfilUsuario(String nickname, DtUsuario usuarioLogueado) {
		cph = ConexionPostgresHibernate.getInstancia();
		emf = cph.getEntityManager();
		em = emf.createEntityManager();

		Usuario u = em.find(Usuario.class, nickname);
        @SuppressWarnings("unchecked")
        List<Usuario> seguidores = em.createQuery("select u.usuarioUno FROM UsuarioSigue u WHERE u.usuarioDos = :nickname")
        		.setParameter("nickname", u)
        		.getResultList();
        @SuppressWarnings("unchecked")
        List<Usuario> seguidos = em.createQuery("select u.usuarioDos FROM UsuarioSigue u WHERE u.usuarioUno = :nickname")
        		.setParameter("nickname", u)
        		.getResultList();
		
        List<Propuesta> propuestasFavoritas = new ArrayList<>();
        ArrayList<DtPropuesta> dtPropuestasFavoritas = new ArrayList<>();
        ArrayList<DtProponente> seguidosProponentes = new ArrayList<>();
        ArrayList<DtColaborador> seguidosColaboradores = new ArrayList<>();
        ArrayList<DtProponente> seguidoresProponentes = new ArrayList<>();
        ArrayList<DtColaborador> seguidoresColaboradores = new ArrayList<>();
    	ArrayList<DtPropuesta> propuestasPublicadas = new ArrayList<>();
    	ArrayList<DtPropuesta> propuestasCreadas = new ArrayList<>();
    	ArrayList<DtPropuestaColaborada> propuestasColaboradas = new ArrayList<>();
    	ArrayList<DtColaboracion> colaboracionesHechas = new ArrayList<>();
    	DtPerfilUsuario perfil = new DtPerfilUsuario();
        
        for (Usuario seguido : seguidos) {
        	if (seguido instanceof Colaborador)
        		seguidosColaboradores.add(((Colaborador) seguido).getDtColaborador());
        	if(seguido instanceof Proponente)
        		seguidosProponentes.add(((Proponente) seguido).getDtProponente());
		}        
        
        for (Usuario seguidor : seguidores) {
        	if (seguidor instanceof Colaborador)
        		seguidoresColaboradores.add(((Colaborador) seguidor).getDtColaborador());
        	if (seguidor instanceof Proponente)
        		seguidoresProponentes.add(((Proponente) seguidor).getDtProponente());
		}
       
    	if (u != null) {
    		propuestasFavoritas = u.getPropuestasFavoritas();
//        	if(propuestasFavoritas != null) {
    	        for (Propuesta propuesta : propuestasFavoritas) {
    				dtPropuestasFavoritas.add(propuesta.getDtPropuesta());
    			}
//        	}
	    	if (u instanceof Colaborador) {
	            @SuppressWarnings("unchecked")
	            List<Colaboracion> colabs = em.createQuery("FROM Colaboracion WHERE colaborador = :colaborador")
	            	.setParameter("colaborador", u)
	            	.getResultList();
	            for (Colaboracion c : colabs) {
					if(c.tieneColaborador(nickname)) {
						double montoAportado = c.getMonto(); //3*
						 DtPropuestaColaborada p = c.getPropuestaFromColaboracion(); //4* y 4.1*
						 if (p != null) 
							 propuestasColaboradas.add(new DtPropuestaColaborada(p.getTitulo(), p.getDescripcion(), p.getImagen(), montoAportado,
									 p.getProponenteACargo(), p.getEstadoActual()));
						if(usuarioLogueado!= null && usuarioLogueado.getNickname().equals(nickname)) {
							DtColaboracion ch = c.getDataColaboracion();
							if(ch != null)
								colaboracionesHechas.add(ch);
						}
					}
					
				}
//	    		em.close();
	    	}
	    	
	    	/*
	    	 
	    	 */
	    	if (u instanceof Proponente) {
	            @SuppressWarnings("unchecked")
	            List<Propuesta> propuestas = em.createQuery("FROM Propuesta WHERE proponenteACargo = :proponente")
	            	.setParameter("proponente", u)
	            	.getResultList();
	            for (Propuesta prop : propuestas) {
	            	if(prop.isProponenteACargo(nickname) && !prop.getEstadoActual().equals(EstadoPropuesta.ingresada)) {
	            		DtPropuesta dtp = prop.getInfoPropuesta();
	            		if(dtp!=null)
	            			propuestasPublicadas.add(dtp);
	            		
	            	}
	            	if(prop.isProponenteACargo(nickname) &&
	            			prop.getEstadoActual().equals(EstadoPropuesta.ingresada) &&
	            			usuarioLogueado!= null && 
	            			u.getNickname().equals(usuarioLogueado.getNickname())) {
	            				DtPropuesta auxdtp = prop.getInfoPropuesta();
	            				if(auxdtp!=null)
	            					propuestasCreadas.add(auxdtp);
	            			}
				}
	    	}
	    	
			perfil = new DtPerfilUsuario(u.getNickname(), u.getNombre(), u.getApellido(), u.getCorreoElectronico(),u.getPassword(),
	        		u.getFechaNacimiento(), u.getImagen(), seguidoresProponentes, seguidoresColaboradores, seguidosProponentes, seguidosColaboradores,
	        		dtPropuestasFavoritas, propuestasPublicadas, propuestasCreadas, propuestasColaboradas,colaboracionesHechas);

    	}
		// TODO Auto-generated method stub
		em.close();
		return perfil;
	}
	
	@Override
	public DtColaborador[] getMasColaboradores() {
		cph = ConexionPostgresHibernate.getInstancia();
		emf = cph.getEntityManager();
		em = emf.createEntityManager();

		@SuppressWarnings("unchecked")
        List<Colaborador> colaboradores = em.createQuery("SELECT u FROM Colaboracion c1, Usuario u "
        		+ "WHERE c1.colaborador = u.nickname "
        		+ "GROUP BY u "
        		+ "ORDER BY count(u) DESC").setMaxResults(3).getResultList();
        em.close();
        
        DtColaborador[] dtcol = new DtColaborador[colaboradores.size()];
        for (int i = 0; i < dtcol.length; i++) {
			dtcol[i] = colaboradores.get(i).getDtColaborador();
		}
        
        return dtcol;
	}

	@Override
	public DtProponente[] getMasProponedores() {
		cph = ConexionPostgresHibernate.getInstancia();
		emf = cph.getEntityManager();
		em = emf.createEntityManager();

		@SuppressWarnings("unchecked")
		List<Proponente> proponentes = em.createQuery("SELECT u FROM Propuesta p, Usuario u "
				+ "WHERE p.proponenteACargo = u.nickname "
				+ "AND p.flagElm = :false "
				+ "GROUP BY u "
				+ "ORDER BY count(u) DESC")
				.setParameter("false", false)
				.setMaxResults(3)
				.getResultList();
        em.close();
        
        DtProponente[] dtcol = new DtProponente[proponentes.size()];
        for (int i = 0; i < dtcol.length; i++) {
			dtcol[i] = proponentes.get(i).getDtProponente();
		}
        
        return dtcol;
	}

	@Override
	public DtPerfilProponente[] verProponentesEliminados() throws NoExistenProponentesEliminadosException {
		cph = ConexionPostgresHibernate.getInstancia();
		emf = cph.getEntityManager();
		em = emf.createEntityManager();

		@SuppressWarnings("unchecked")
		List<Proponente> proponentesEliminados = em.createQuery("FROM Usuario WHERE "
				+ "TIPOUSUARIO = 'P' AND flagElm = :si")
				.setParameter("si", true)
				.getResultList();
		
        em.close();
        
        DtPerfilProponente[] dtProponentesEliminados = null;
        
        if (!proponentesEliminados.isEmpty()) {
        	dtProponentesEliminados = new DtPerfilProponente[proponentesEliminados.size()];
            Usuario proponente;
            for (int i = 0; i < proponentesEliminados.size(); i++) {
                proponente = proponentesEliminados.get(i);
                dtProponentesEliminados[i] = verPerfilProponente(proponente.getNickname());
            }
        }else {
        	throw new NoExistenProponentesEliminadosException("No hay proponentes eliminados.");
        }
        return dtProponentesEliminados;
	}

	@Override
	public DtUsuario[] verRankingUsuarios() {
		
		cph = ConexionPostgresHibernate.getInstancia();
		emf = cph.getEntityManager();
		em = emf.createEntityManager();
		
		@SuppressWarnings("unchecked")
		List<Usuario> usuarios = em.createQuery("SELECT u FROM UsuarioSigue us, Usuario u "
				+ "WHERE us.usuarioDos = u AND u.flagElm = :estaEliminado "
				+ "GROUP BY u "
				+ "HAVING COUNT(u) > 0 "
				+ "ORDER BY count(us) DESC ").setParameter("estaEliminado",false).getResultList();
        em.close();
        DtUsuario[] dtcol = new DtUsuario[usuarios.size()];
        for (int i = 0; i < dtcol.length; i++) {
			dtcol[i] = usuarios.get(i).getDtUsuario();
			dtcol[i].setCantSeguidores(this.getCantSeguidores(dtcol[i].getNickname()));
		}

        return dtcol;
	}
	
	public int getCantSeguidores(String nickname) {
		cph = ConexionPostgresHibernate.getInstancia();
		emf = cph.getEntityManager();
		em = emf.createEntityManager();
		

		Usuario u = em.find(Usuario.class, nickname);
        @SuppressWarnings("unchecked")
        List<Usuario> seguidores = em.createQuery("select u.usuarioUno FROM UsuarioSigue u WHERE u.usuarioDos = :nickname")
        		.setParameter("nickname", u)
        		.getResultList();
        em.close();
		return seguidores.size();
	}
	

	@Override
	public DtPropuesta[] listarFavoritasUsuario(String nickname) {
		cph = ConexionPostgresHibernate.getInstancia();
		emf = cph.getEntityManager();
		em = emf.createEntityManager();
		
		System.out.println("listarFavoritasUsuario \n");
		Usuario u = em.find(Usuario.class, nickname);
		System.out.println("1.1 nickname:"+nickname+ "\n");
		System.out.println("1.2 nickname:"+u.getNickname()+ "\n");
		

        @SuppressWarnings("unchecked")
		List<Propuesta> favoritas = em.createQuery("select u.propuestasFavoritas FROM Usuario u WHERE u.nickname = :nickname")
        		.setParameter("nickname", nickname)
        		.getResultList();
        em.close();
        
//        List<Propuesta> propuestas = u.getPropuestasFavoritas();        
		System.out.println("3 size: "+favoritas.size() + " \n");

		DtPropuesta[] dtpop = new DtPropuesta[favoritas.size()];
		System.out.println("4 \n"); 
		
		for (int i = 0; i < dtpop.length; i++) {
			System.out.println("5." +i+ " \n");
			dtpop[i] = favoritas.get(i).getDtPropuestaLazy();	
		}		
		System.out.println("6 \n");
		return dtpop;
	}
	
	@Override
	public void eliminarCuenta(String nickname) throws UsuarioNoExisteElUsuarioException {
		cph = ConexionPostgresHibernate.getInstancia();
		emf = cph.getEntityManager();
		em = emf.createEntityManager();
		
		GregorianCalendar now = (GregorianCalendar) GregorianCalendar.getInstance();
		Usuario usuario = em.find(Usuario.class, nickname);
		if (usuario instanceof Proponente) {
			em.getTransaction().begin();
			usuario.setFlagElm(true);
			usuario.setFechaEliminacion(now);
			em.merge(usuario);
			
	        @SuppressWarnings("unchecked")
			List<Propuesta> propuestas = em.createQuery("FROM Propuesta WHERE proponenteACargo = :proponente")
	            	.setParameter("proponente", usuario)
	            	.getResultList();
	        
	        for (Propuesta propuesta : propuestas) {
	        	// elimino el historico de estados.
	        	em.createQuery("DELETE FROM Estado WHERE propuesta = :prop").setParameter("prop", propuesta).executeUpdate();
				// elimino la propuesta
	        	propuesta.setFlagElm(true);
	        	
	        	// elimino las colaboraciones de la propuesta.
		        @SuppressWarnings("unchecked")
				List<Colaboracion> colaboraciones = em.createQuery("FROM Colaboracion WHERE propuestaColaborada = :propuesta")
		            	.setParameter("propuesta", propuesta)
		            	.getResultList();
		        for (Colaboracion colaboracion : colaboraciones) {
		        	colaboracion.setFlagElm(true);
		        	em.merge(colaboracion);
		        }
	        	em.merge(propuesta);
			}
			
	        // elimino seguidos y seguidores
	        em.createQuery("DELETE FROM UsuarioSigue where usuarioUno = :usuario").setParameter("usuario", usuario).executeUpdate();
	        em.createQuery("DELETE FROM UsuarioSigue where usuarioDos = :usuario").setParameter("usuario", usuario).executeUpdate();
	        
			em.getTransaction().commit();
			em.close();
		} else {
			em.close();
			throw new UsuarioNoExisteElUsuarioException("El usuario no es proponente");
		}
	}

	@Override
	public void registrarAccesoAlSitio(String ip, String url, String navegador, String so) {
		try(FileWriter fw = new FileWriter("src/main/resources/accesos.csv", true);
        	    BufferedWriter bw = new BufferedWriter(fw);
        	    PrintWriter out = new PrintWriter(bw))
        	{
				GregorianCalendar fecha = (GregorianCalendar) GregorianCalendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        	    out.print(ip);
        	    out.print(",");
        	    out.print(url);
        	    out.print(",");
        	    out.print(navegador);
        	    out.print(",");
        	    out.print(so);
        	    out.print(",");
        	    out.println(sdf.format(fecha.getTime()));
        	} catch (IOException e) {
        	    System.out.println("Ocurrio un error, no se pudo registrar el acceso al sitio.");
        	}
	}
}
