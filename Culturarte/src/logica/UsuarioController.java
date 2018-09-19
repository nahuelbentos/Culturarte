package logica;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;

import datatype.DtColaboracion;
import datatype.DtColaborador;
import datatype.DtPerfilColaborador;
import datatype.DtPerfilProponente;
import datatype.DtProponente;
import datatype.DtPropuesta;
import datatype.DtPropuestaColaborada;
import datatype.DtUsuario;
import datatype.EstadoPropuesta;
import datatype.TipoRetorno;
import excepciones.ColaboracionNoExisteException;
import excepciones.ColaboradorNoExisteException;
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
		try {
			usuarioDos = (Usuario) em.createQuery("FROM Usuario where correoElectronico = :correoElectronico")
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
		em.getTransaction().begin();
		
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
	public  DtUsuario[] listarProponentes() {
		cph = ConexionPostgresHibernate.getInstancia();
		emf = cph.getEntityManager();
		em = emf.createEntityManager();
		em.getTransaction().begin();
		
		DtUsuario[] dtUsuario = null;
        @SuppressWarnings("unchecked")
		List<Usuario> usuarios = em.createQuery("FROM Usuario WHERE TIPOUSUARIO = 'P'").getResultList();
        if (usuarios != null) {
            dtUsuario = new DtUsuario[usuarios.size()];
            Usuario usuario;
            for (int i = 0; i < usuarios.size(); i++) {
                usuario = usuarios.get(i);
                dtUsuario[i] = new DtUsuario(usuario.getNickname(), usuario.getNombre(),
                		usuario.getApellido(), usuario.getCorreoElectronico(), usuario.getPassword(), 
                		usuario.getFechaNacimiento(), usuario.getImagen());
            }
        }
        em.close();
        return dtUsuario;
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
		em.getTransaction().begin();

		DtUsuario[] dtUsuario = null;
        @SuppressWarnings("unchecked")
		List<Usuario> usuarios = em.createQuery("FROM Usuario").getResultList();
        if (usuarios != null) {
            dtUsuario = new DtUsuario[usuarios.size()];
            Usuario usuario;
            for (int i = 0; i < usuarios.size(); i++) {
                usuario = usuarios.get(i);
                dtUsuario[i] = new DtUsuario(usuario.getNickname(), usuario.getNombre(),
                		usuario.getApellido(), usuario.getCorreoElectronico(), usuario.getPassword() ,
                		usuario.getFechaNacimiento(), usuario.getImagen());
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
		em.getTransaction().begin();

		Usuario usuario = em.find(Usuario.class, nickname);
        DtUsuario dtUsuario = null;
        if (usuario != null) {
        	if (usuario instanceof Proponente) {
				Proponente proponente = (Proponente) usuario;
				dtUsuario = new DtProponente(proponente.getNickname(), proponente.getNombre(), proponente.getApellido(),
						proponente.getCorreoElectronico(), proponente.getPassword(), proponente.getFechaNacimiento(), proponente.getImagen(),
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
		em.getTransaction().begin();
		
        @SuppressWarnings("unchecked")
		List<Usuario> usuarios = em.createQuery("SELECT usuarioDos FROM UsuarioSigue").getResultList();
		if (usuarios != null) {
	        DtUsuario[] listaDeUsuarios = new DtUsuario[usuarios.size()];
	        Usuario usuarioDos;
	        for (int i = 0; i < usuarios.size(); i++) {
	        	usuarioDos = usuarios.get(i);
	        	listaDeUsuarios[i] = new DtUsuario(usuarioDos.getNickname(), usuarioDos.getNombre(),
	        			usuarioDos.getApellido(), usuarioDos.getCorreoElectronico(), usuarioDos.getPassword(), 
	        			usuarioDos.getFechaNacimiento(), usuarioDos.getImagen());
	        }
	        return listaDeUsuarios;
		}
		return null;
	}

	@Override
	public DtUsuario[] listarColaboradores() throws ColaboradorNoExisteException {
		cph = ConexionPostgresHibernate.getInstancia();
		emf = cph.getEntityManager();
		em = emf.createEntityManager();
		em.getTransaction().begin();
		
		DtUsuario[] dtUsuario = null;
        @SuppressWarnings("unchecked")
		List<Usuario> usuarios = em.createQuery("FROM Usuario WHERE TIPOUSUARIO = 'C'").getResultList();
        if (usuarios != null) {
            dtUsuario = new DtUsuario[usuarios.size()];
            Usuario usuario;
            for (int i = 0; i < usuarios.size(); i++) {
                usuario = usuarios.get(i);
                dtUsuario[i] = new DtUsuario(usuario.getNickname(), usuario.getNombre(),
                		usuario.getApellido(), usuario.getCorreoElectronico(), usuario.getPassword(), 
                		usuario.getFechaNacimiento(), usuario.getImagen());
            }
            em.close();
            return dtUsuario;
        }else {
        	em.close();
        	throw new ColaboradorNoExisteException("No hay colaboradores registrados");
        }
	}


	@Override
	public DtPerfilProponente verPerfilProponente(String nickname) {
		cph = ConexionPostgresHibernate.getInstancia();
		emf = cph.getEntityManager();
		em = emf.createEntityManager();
		em.getTransaction().begin();

        @SuppressWarnings("unchecked")
		List<Propuesta> propouestas = em.createQuery("FROM Propuesta").getResultList();
        @SuppressWarnings("unchecked")
		List<Colaboracion> colabs = em.createQuery("FROM Colaboracion").getResultList();
    	Usuario usuario = em.find(Usuario.class, nickname);

    	em.close();

    	if (usuario != null) {
        	if (usuario instanceof Proponente) {
				Proponente p = (Proponente) usuario;
	        	System.out.println("parm nickname: " + nickname + " \n");
	        	System.out.println("Usuario nombre: " + p.getNombre() + " \n");

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
	    				
	    				System.out.println("dataPro.Estadoactual: " + dataPro.getEstadoActual() + " \n");
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
        		return  new  DtPerfilProponente("Fallo1pruebaController", "Fallo1pruebaController", "Fallo1pruebaController", "pruebaController", "", null,
            			null,"pruebaController", "pruebaController", "pruebaController",null,null, null, null, null, null);

        }else {
        	return  new  DtPerfilProponente("FallopruebaController", "FallopruebaController", "FallopruebaController", "pruebaController", "", null,
        			null,"pruebaController", "pruebaController", "pruebaController",null,null, null, null, null, null);
        }


	}

	@Override
	public DtPerfilColaborador verPerfilColaborador(String nickname) {
		cph = ConexionPostgresHibernate.getInstancia();
		emf = cph.getEntityManager();
		em = emf.createEntityManager();
		em.getTransaction().begin();

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
							 System.out.println("p.titulo: " + p.getTitulo());
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
        				"falo_perfil.getCorreoElectronico()", "", null,null,null);
        }else
        	return  new  DtPerfilColaborador("Fallo2_perfil.getNickname()", "Fallo2_perfil.getNombre()", "Fallo2_perfil.getApellido()",
    				"Fallo2_perfil.getCorreoElectronico()", "", null,null,null);
        

	}

	@Override
	public DtPropuesta[] listarPropuestasDeUnColaborador(String nickname) {
		cph = ConexionPostgresHibernate.getInstancia();
		emf = cph.getEntityManager();
		em = emf.createEntityManager();
		em.getTransaction().begin();
		
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
				em.getTransaction().begin();
				
				DtPropuesta[] dtp = null;
				
				@SuppressWarnings("unchecked")
				List<Colaboracion> colaboraciones = em.createQuery("SELECT c FROM Colaboracion c, Propuesta p WHERE c.propuestaColaborada=p.titulo AND c.colaborador = :colaborador AND p.estadoActual = :estado AND c.comentario IS NULL")
						.setParameter("colaborador", usuarioLogueado)
						.setParameter("estado", EstadoPropuesta.financiada)
						.getResultList();
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
				
				
				
			}else{
				throw new UsuarioSinLoguearseException("Debes ser colaborador para ver tus colaboraciones.");
			}
		}else {
			throw new UsuarioSinLoguearseException("Debes iniciar sesion para agregar Propuestas a sus favoritos");
		}
	}

	@Override
	public void agregarComentarioAPropuesta(String comentario, String titulo, DtUsuario usuarioLogueado) throws UsuarioSinLoguearseException{
		
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
	public DtUsuario iniciarSesion(String datoSesion, String password) throws UsuarioNoExisteElUsuarioException {
		
		cph = ConexionPostgresHibernate.getInstancia();
		emf = cph.getEntityManager();
		em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Usuario u = em.find(Usuario.class, datoSesion);
		if (u == null) {
			u = (Usuario)em.createQuery("FROM Usuario WHERE email= :correo").setParameter("correo", datoSesion).getSingleResult();
			
			if (u == null) {
				throw new UsuarioNoExisteElUsuarioException("Nickname / Email o Password incorrectos");
			}
		}
		em.close();
		
		return u.getDtUsuario();
		

	}

	@Override
	public void cerrarSesion() throws UsuarioSinLoguearseException {
		
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

}
