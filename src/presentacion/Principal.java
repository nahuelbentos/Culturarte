package presentacion;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import logica.Factory;
import logica.IUsuarioController;

public class Principal {

	private JFrame frmPaginaPrincipal;
	private AltaPerfil altaPerfil;
	private AltaCategoria altaCategoria;
	private IUsuarioController IUC;
	
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
	 */
	
	public Principal() {
		initialize();
		
        Factory factory = Factory.getInstance();
        IUC = factory.getIUsuarioController();
		
		altaPerfil = new AltaPerfil(IUC);
		altaPerfil.setVisible(false);
		
		altaCategoria = new AltaCategoria();
		altaCategoria.setVisible(false);
		
		frmPaginaPrincipal.getContentPane().setLayout(null);
		frmPaginaPrincipal.getContentPane().add(altaPerfil);
		frmPaginaPrincipal.getContentPane().add(altaCategoria);
	}
	
	public void initialize() {
		frmPaginaPrincipal = new JFrame();
		frmPaginaPrincipal.setTitle("Culturarte");
		frmPaginaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPaginaPrincipal.setBounds(10, 40, 600, 600);
		crearMenu();
	}
	
	public void crearMenu() {
		JMenuBar menuBar = new JMenuBar();
		frmPaginaPrincipal.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Sistema");
		menuBar.add(mnNewMenu);
		
		JMenu mnPropuesta = new JMenu("Propuestas");
		mnNewMenu.add(mnPropuesta);
		
		JMenuItem mntmAltaDe = new JMenuItem("Alta de Propuesta");
		mnPropuesta.add(mntmAltaDe);
		
		JMenuItem mntmModificarDatosDe = new JMenuItem("Modificar datos de Propuesta");
		mnPropuesta.add(mntmModificarDatosDe);
		
		JMenuItem mntmConsultaDePropuesta = new JMenuItem("Consulta de Propuesta");
		mnPropuesta.add(mntmConsultaDePropuesta);
		
		JMenuItem mntmConsultaDePropuestas = new JMenuItem("Consulta de Propuestas por estado");
		mnPropuesta.add(mntmConsultaDePropuestas);
		
		JMenu mnCategra = new JMenu("Categor\u00EDas");
		mnNewMenu.add(mnCategra);
		
		JMenuItem mntmAltaDeCategrpia = new JMenuItem("Alta de Categor\u00EDa");
		mntmAltaDeCategrpia.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				altaCategoria.setVisible(true);
			}
		});
		mnCategra.add(mntmAltaDeCategrpia);
		
		JMenu mnColaboraciones = new JMenu("Colaboraciones");
		mnNewMenu.add(mnColaboraciones);
		
		JMenuItem mntmRegistrarColaboracinA = new JMenuItem("Registrar colaboraci\u00F3n a Propuesta");
		mnColaboraciones.add(mntmRegistrarColaboracinA);
		
		JMenuItem mntmCancelarColaboracinA = new JMenuItem("Cancelar colaboraci\u00F3n a Propuesta");
		mnColaboraciones.add(mntmCancelarColaboracinA);
		
		JMenuItem mntmConsultaDeColaboracin = new JMenuItem("Consulta de colaboraci\u00F3n a Propuesta");
		mnColaboraciones.add(mntmConsultaDeColaboracin);
		
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
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Consulta de Perfil de proponente");
		mnPerfiles.add(mntmNewMenuItem);
		
		JMenuItem mntmConsultaDePerfil = new JMenuItem("Consulta de Perfil de colaborador");
		mnPerfiles.add(mntmConsultaDePerfil);
		
		JMenuItem mntmSeguir = new JMenuItem("Seguir Usuario");
		mnUsuarios.add(mntmSeguir);
		
		JMenuItem mntmDejarDeSe = new JMenuItem("Dejar de Seguir Usuario");
		mnUsuarios.add(mntmDejarDeSe);
	}

}
