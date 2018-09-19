package presentacion;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.beans.PropertyVetoException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import datatype.DtCategoria;
import datatype.DtColaborador;
import datatype.DtProponente;
import datatype.DtPropuesta;
import datatype.DtUsuario;
import datatype.TipoRetorno;
import excepciones.CategoriaNoExisteException;
import excepciones.CategoriaYaExisteException;
import excepciones.ProponenteNoExisteException;
import excepciones.PropuestaNoExisteException;
import excepciones.PropuestaRepetidaException;
import excepciones.UsuarioNoExisteElUsuarioException;
import excepciones.UsuarioYaExisteElEmailException;
import excepciones.UsuarioYaExisteElUsuarioException;
import excepciones.UsuarioYaSigueAlUsuarioException;
import logica.Factory;
import logica.ICategoriaController;
import logica.IPropuestaController;
import logica.IUsuarioController;

public class Principal {
	
	private JFrame frmPaginaPrincipal;
	private AltaPerfil altaPerfil;
	private AltaPropuesta altaPropuesta;
	private ModificarPropuesta modificarPropuesta;
	private AltaCategoria altaCategoria;
	private SeguirUsuario seguirUsuario;
	private DejarDeSeguirUsuario dejarDeSeguirUsuario;
	private ConsultaColaboracionPropuesta consColProp;
	private RegistrarColaboracion registrarColaboracion;
	private EvaluarPropuestas evaluarPropuestas;
	
	
	private ConsultaPerfilProponente consultaPerfilProponente;
	private ConsultaPerfilColaborador consultaPerfilColaborador;
	private ConsultaPropuesta consultaPropuesta;

	
	private ConsultaPropuestaPorEstado consultaPropuestaPorEstado;
	
	private IUsuarioController IUC;
	private ICategoriaController ICC;
	private IPropuestaController IPC;
	private JMenuItem mntmEvaluarPropuesta;
	
	/**
	 * Launch the application.
	*/

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frmPaginaPrincipal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws UsuarioNoExisteElUsuarioException 
	 * @throws PropuestaNoExisteException 
	 * @throws PropertyVetoException 
	 */

	public Principal() throws UsuarioNoExisteElUsuarioException, PropuestaNoExisteException, PropertyVetoException {
		initialize();
 
        Factory factory = Factory.getInstance();
        IUC = factory.getIUsuarioController();
        ICC = factory.getICategoriaController();
        IPC = factory.getIPropuestaController();
        
        altaPerfil = new AltaPerfil(IUC);
		altaPerfil.setVisible(false);

		altaCategoria = new AltaCategoria(ICC);
		altaCategoria.setVisible(false);

		altaPropuesta = new AltaPropuesta(IUC, ICC, IPC);
		altaPropuesta.setVisible(false);
		
		modificarPropuesta = new ModificarPropuesta(IUC, ICC, IPC);
		modificarPropuesta.setVisible(false);
		
		evaluarPropuestas = new EvaluarPropuestas(IPC);
		evaluarPropuestas.setVisible(false);

		seguirUsuario = new SeguirUsuario(IUC);
		seguirUsuario.setVisible(false);

		dejarDeSeguirUsuario = new DejarDeSeguirUsuario(IUC);
		dejarDeSeguirUsuario.setVisible(false);

		consColProp = new ConsultaColaboracionPropuesta(IUC);
		consColProp.setVisible(false);
		
		registrarColaboracion = new RegistrarColaboracion(IPC,IUC);
		registrarColaboracion.setVisible(false);
		
		/* *-**-*-*--*-* [codigo nbentos] *--*-*-*-*-* */
		consultaPerfilProponente = new ConsultaPerfilProponente(IUC);
		consultaPerfilProponente.setVisible(false);
		consultaPerfilColaborador = new ConsultaPerfilColaborador(IUC);
		consultaPerfilColaborador.setVisible(false);
		consultaPropuesta = new ConsultaPropuesta(IPC);
		consultaPropuesta.setVisible(false);
		/* *-**-*-*--*-* [codigo nbentos] *--*-*-*-*-* */
		
		consultaPropuestaPorEstado = new ConsultaPropuestaPorEstado(IPC);
		consultaPropuestaPorEstado.setVisible(false);
		
		
		frmPaginaPrincipal.getContentPane().setLayout(null);
		frmPaginaPrincipal.getContentPane().add(altaPerfil);
		
		frmPaginaPrincipal.getContentPane().add(altaCategoria);
		frmPaginaPrincipal.getContentPane().add(altaPropuesta);
		frmPaginaPrincipal.getContentPane().add(modificarPropuesta);
		frmPaginaPrincipal.getContentPane().add(evaluarPropuestas);
		frmPaginaPrincipal.getContentPane().add(seguirUsuario);
		frmPaginaPrincipal.getContentPane().add(dejarDeSeguirUsuario);
		frmPaginaPrincipal.getContentPane().add(consColProp);
		frmPaginaPrincipal.getContentPane().add(registrarColaboracion);


		/* *-**-*-*--*-* [codigo nbentos] *--*-*-*-*-* */
		frmPaginaPrincipal.getContentPane().add(consultaPerfilProponente);
		frmPaginaPrincipal.getContentPane().add(consultaPerfilColaborador);
		frmPaginaPrincipal.getContentPane().add(consultaPropuesta);
		/* *-**-*-*--*-* [codigo nbentos] *--*-*-*-*-* */
		
		frmPaginaPrincipal.getContentPane().add(consultaPropuestaPorEstado);
	}

	public void initialize() {
		frmPaginaPrincipal = new JFrame();
		frmPaginaPrincipal.setTitle("Culturarte");
		frmPaginaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPaginaPrincipal.setBounds(10, 10, 1366, 768);
		crearMenu();
	}

	public void crearMenu() {
		JMenuBar menuBar = new JMenuBar();
		frmPaginaPrincipal.setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Sistema");
		menuBar.add(mnNewMenu);

		JMenu mnPropuesta = new JMenu("Propuestas");
		mnNewMenu.add(mnPropuesta);

		JMenuItem mntmAltaDeProp = new JMenuItem("Alta de Propuesta");
		mntmAltaDeProp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				altaPropuesta.refreshFrame();
				altaPropuesta.setVisible(true);
			}
		});
		mnPropuesta.add(mntmAltaDeProp);

		JMenuItem mntmModificarDatosDe = new JMenuItem("Modificar datos de Propuesta");
		mntmModificarDatosDe.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				modificarPropuesta.setListaDePropuestas();
				modificarPropuesta.setVisible(true);
			}
		});
		{
			mntmEvaluarPropuesta = new JMenuItem("Evaluar Propuestas");
			mntmEvaluarPropuesta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					evaluarPropuestas.refreshFrame();
					evaluarPropuestas.setVisible(true);
				}
			});
			mnPropuesta.add(mntmEvaluarPropuesta);
		}
		mnPropuesta.add(mntmModificarDatosDe);

		JMenuItem mntmConsultaDePropuesta = new JMenuItem("Consulta de Propuesta");
		mntmConsultaDePropuesta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				consultaPropuesta.refreshFrame();
				consultaPropuesta.setVisible(true);
			}
		});
		mnPropuesta.add(mntmConsultaDePropuesta);

		JMenuItem mntmConsultaDePropuestasPorEstado = new JMenuItem("Consulta de Propuestas por estado");
		mntmConsultaDePropuestasPorEstado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				consultaPropuestaPorEstado.refreshFrame();
				consultaPropuestaPorEstado.setVisible(true);
			}
		});
		mnPropuesta.add(mntmConsultaDePropuestasPorEstado);

		JMenu mnCategra = new JMenu("Categor\u00EDas");
		mnNewMenu.add(mnCategra);

		JMenuItem mntmAltaDeCategrpia = new JMenuItem("Alta de Categor\u00EDa");
		mntmAltaDeCategrpia.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				altaCategoria.listarCategorias();
				altaCategoria.setVisible(true);
			}
		});
		mnCategra.add(mntmAltaDeCategrpia);
		
		JMenu mnColaboraciones = new JMenu("Colaboraciones");
		mnNewMenu.add(mnColaboraciones);
				
		JMenuItem mntmRegistrarColaboracinA = new JMenuItem("Registrar colaboraci\u00F3n a Propuesta");
		mntmRegistrarColaboracinA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registrarColaboracion.refreshFrame();
				registrarColaboracion.setVisible(true);
			}
		});
		mnColaboraciones.add(mntmRegistrarColaboracinA);

		JMenuItem mntmCancelarColaboracinA = new JMenuItem("Cancelar colaboraci\u00F3n a Propuesta");
		mnColaboraciones.add(mntmCancelarColaboracinA);
		
		JMenuItem mntmConsultaDeColaboracin = new JMenuItem("Consulta de colaboraci\u00F3n a Propuesta");
		mntmConsultaDeColaboracin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consColProp.setListarColaboradores();
				consColProp.setVisible(true);
			}
		});
		mnColaboraciones.add(mntmConsultaDeColaboracin);
		
		JMenu mnNewMenu_1 = new JMenu("Datos");
		mnNewMenu.add(mnNewMenu_1);
		
		JMenuItem mntmAgregarDatos = new JMenuItem("Cargar datos");
		mntmAgregarDatos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					agregarUsuarios(e);
					seguirUsuarios(e);
					agregarCategorias(e);
					agregarPropuestas(e);
					JOptionPane.showMessageDialog(null, "Los datos se cargaron con exito", "Cargar Datos",
		                    JOptionPane.INFORMATION_MESSAGE);
				} catch (ParseException | IOException | CategoriaYaExisteException | CategoriaNoExisteException | URISyntaxException | PropuestaRepetidaException | ProponenteNoExisteException | UsuarioYaExisteElUsuarioException | UsuarioYaExisteElEmailException | UsuarioYaSigueAlUsuarioException e1) {
					JOptionPane.showMessageDialog(null, "Ocurrio un error", "Cargar Datos", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnNewMenu_1.add(mntmAgregarDatos);
		
		JMenuItem mntmBorrarDatos = new JMenuItem("Borrar datos");
		mntmBorrarDatos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				IPC.borrarPropuestas();
				IUC.borrarUsuarios();
				ICC.borrarCategorias();
				JOptionPane.showMessageDialog(null, "Los datos se borraron con exito", 
						"Borrar Datos", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		mnNewMenu_1.add(mntmBorrarDatos);

		JMenu mnUsuarios = new JMenu("Usuarios");
		menuBar.add(mnUsuarios);

		JMenu mnPerfiles = new JMenu("Perfiles");
		mnUsuarios.add(mnPerfiles);

		JMenuItem mntmAltaDePerfil = new JMenuItem("Alta de Perfil");
		mntmAltaDePerfil.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				altaPerfil.setVisible(true);
			}
		});

		mnPerfiles.add(mntmAltaDePerfil);

		JMenuItem mntmConsultaDeProponente = new JMenuItem("Consulta de Perfil de proponente");
		mntmConsultaDeProponente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				consultaPerfilProponente.refreshFrame();
				consultaPerfilProponente.setVisible(true);
			}
		});
		mnPerfiles.add(mntmConsultaDeProponente);

		JMenuItem mntmConsultaDePerfilColaborador = new JMenuItem("Consulta de Perfil de colaborador");
		mntmConsultaDePerfilColaborador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				consultaPerfilColaborador.listarColaboradores.actualizarColaboradores();
				consultaPerfilColaborador.setVisible(true);
				try {
					consultaPerfilColaborador.setMaximum(true);
				} catch (PropertyVetoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		mnPerfiles.add(mntmConsultaDePerfilColaborador);

		JMenuItem mntmSeguir = new JMenuItem("Seguir Usuario");
		mntmSeguir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				seguirUsuario.setListaDeUsuariosUno();
				seguirUsuario.setVisible(true);
			}
		});
		mnUsuarios.add(mntmSeguir);

		JMenuItem mntmDejarDeSe = new JMenuItem("Dejar de Seguir Usuario");
		mntmDejarDeSe.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dejarDeSeguirUsuario.setListaDeUsuariosUno();
				dejarDeSeguirUsuario.setVisible(true);
			}
		});
		mnUsuarios.add(mntmDejarDeSe);
	}
	
	private void agregarUsuarios(ActionEvent e) throws ParseException, UnsupportedEncodingException, IOException, URISyntaxException, UsuarioYaExisteElUsuarioException, UsuarioYaExisteElEmailException {
        String line = "";
        String cvsSplitBy = "\\|";
        
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("datosDePrueba\\usuarios.csv");
        
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"))) {
            while ((line = br.readLine()) != null) {
            	String[] datosUsuario = line.split(cvsSplitBy);
            	String nickname = datosUsuario[0];
            	String email = datosUsuario[1];
            	String nombre = datosUsuario[2];
            	String apellido = datosUsuario[3];
            	GregorianCalendar fechaNacimiento = parsearFecha(datosUsuario[4]);
            	String tipoUsuario = datosUsuario[5];
            	String password = datosUsuario[10];
            	byte[] imagen = null;
            	if (!"null".equals(datosUsuario[6])) {
            		imagen = obtenerImagen(datosUsuario[6]);
            	}
            	DtUsuario dtUsuario = null;
            	if ("P".equals(tipoUsuario)) {
                	String direccion = datosUsuario[7];
                	String sitioWeb = null;
                	if (!"null".equals(datosUsuario[8])) {
                		sitioWeb = datosUsuario[8];
                	}
                	String biografia = null;
                	if (!"null".equals(datosUsuario[9])) {
                		biografia = datosUsuario[9];
                	}
            		dtUsuario = new DtProponente(nickname, nombre, apellido, email, password,
            				fechaNacimiento, imagen, direccion, biografia, sitioWeb);
            		IUC.agregarUsuario(dtUsuario);
            	} else if ("C".equals(tipoUsuario)) {  
            		dtUsuario = new DtColaborador(nickname, nombre, apellido, 
            				email, password, fechaNacimiento, imagen);
            		IUC.agregarUsuario(dtUsuario);
            	}
            }
        }
	}
	
	private void seguirUsuarios(ActionEvent e) throws ParseException, UsuarioYaSigueAlUsuarioException, IOException {
        String line = "";
        String cvsSplitBy = "\\|";
        
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("datosDePrueba\\usuarioSigue.csv");
        
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"))) {
            while ((line = br.readLine()) != null) {
            	String[] datosUsuario = line.split(cvsSplitBy);
            	String usuarioUno = datosUsuario[0];
            	String usuarioDos = datosUsuario[1];
            	IUC.seguirUsuario(usuarioUno, usuarioDos);
            }
        }
	}
	
	private void agregarCategorias(ActionEvent e) throws ParseException, IOException, CategoriaYaExisteException, CategoriaNoExisteException {
        String line = "";
        String cvsSplitBy = "\\|";
        
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("datosDePrueba\\categorias.csv");
        
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"))) {
            while ((line = br.readLine()) != null) {
            	String[] datosCategoria = line.split(cvsSplitBy);
            	ArrayList<DtCategoria> padres = new ArrayList<>();
            	String categoria = datosCategoria[0];
            	String categoriaPadre = datosCategoria[1];
            	if (!"null".equals(categoriaPadre)) {
            		padres.add(new DtCategoria(categoriaPadre));
            	}
            	DtCategoria dtCategoria = new DtCategoria(categoria, padres, null);
            	ICC.agregarCategoria(dtCategoria);
            }
        }
	}
	
	private void agregarPropuestas(ActionEvent e) throws ParseException, IOException, URISyntaxException, PropuestaRepetidaException, ProponenteNoExisteException, CategoriaNoExisteException {
        String line = "";
        String cvsSplitBy = "\\|";
        
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("datosDePrueba\\propuestas.csv");
        
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"))) {
            while ((line = br.readLine()) != null) {
            	String[] datosPropuesta = line.split(cvsSplitBy);
            	DtProponente dtProponente = new DtProponente(datosPropuesta[0], "", "", "", "", null, null, "", "", "");
            	String titulo = datosPropuesta[1];
            	DtCategoria dtCategoria = new DtCategoria(datosPropuesta[2]);
            	GregorianCalendar fechaEspectaculo = parsearFecha(datosPropuesta[3]);
            	String lugar = datosPropuesta[4];
            	float precioEntrada = Float.valueOf(datosPropuesta[5]);
            	float monto = Float.valueOf(datosPropuesta[6]);
            	TipoRetorno tipoRetorno = TipoRetorno.valueOf(datosPropuesta[7]);
            	String descripcion = datosPropuesta[8];
            	byte[] imagen = null;

            	DtPropuesta dtPropuesta = new DtPropuesta(titulo, descripcion, imagen, monto, 
            			new GregorianCalendar(), fechaEspectaculo, lugar, precioEntrada, 
            			tipoRetorno, 0, dtProponente, null, null, dtCategoria, null);
            	IPC.altaPropuesta(dtPropuesta);
            	
            }
        }
	}
	
	private GregorianCalendar parsearFecha(String fecha) throws ParseException {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date date = df.parse(fecha);
		GregorianCalendar fechaRetorno = new GregorianCalendar();
		fechaRetorno.setTime(date);
		return fechaRetorno;
	}
	
	private byte[] obtenerImagen(String pathName) throws IOException, URISyntaxException {
		URL resource = Principal.class.getResource("/datosDePrueba/imagenes/" + pathName + ".jpg");
    	File file = Paths.get(resource.toURI()).toFile();
    	byte[] picInBytes = new byte[(int) file.length()];
    	FileInputStream fileInputStream = new FileInputStream(file);
    	fileInputStream.read(picInBytes);
    	fileInputStream.close();
		return picInBytes;
	}
}