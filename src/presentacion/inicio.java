package presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JInternalFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.ButtonGroup;

public class inicio extends JFrame {

	private JPanel contentPane;
	private JPanel panelPrincipal;
	private JTree treeListaDeCategorias;
	private JInternalFrame intFrameAltaCategoria;
	private JInternalFrame intFrameAltaPerfil;
	private JTextField txtNickname;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtEmail;
	private JTextField txtLoginNombre;
	private JPasswordField txtLoginPassword;
	private JFileChooser fileChooser = new JFileChooser();
	private ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					inicio frame = new inicio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public inicio() {
		setTitle("Culturarte");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 469, 515);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		crearFramePrincipal();
		crearMenu();
		crearInternalFrameAltaPerfil();
		crearInternalFrameAltaCategoria();
		ocultarInternalFrames();
	}
	
	public void crearFramePrincipal() {
		panelPrincipal = new JPanel();
		panelPrincipal.setBounds(5, 23, 438, 421);
		contentPane.add(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre de usuario:");
		lblNombreDeUsuario.setBounds(71, 120, 126, 14);
		panelPrincipal.add(lblNombreDeUsuario);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setBounds(71, 152, 126, 14);
		panelPrincipal.add(lblContrasea);
		
		txtLoginNombre = new JTextField();
		txtLoginNombre.setBounds(208, 117, 139, 20);
		panelPrincipal.add(txtLoginNombre);
		txtLoginNombre.setColumns(10);
		
		txtLoginPassword = new JPasswordField();
		txtLoginPassword.setBounds(207, 149, 140, 20);
		panelPrincipal.add(txtLoginPassword);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.setBounds(178, 214, 89, 23);
		panelPrincipal.add(btnIngresar);
		
		JLabel lblPlataformaDeAdministracin = new JLabel("Plataforma de Administraci\u00F3n");
		lblPlataformaDeAdministracin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPlataformaDeAdministracin.setBounds(121, 54, 209, 14);
		panelPrincipal.add(lblPlataformaDeAdministracin);
	}
	
	public void crearMenu() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
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
				ocultarInternalFrames();
				panelPrincipal.setVisible(false);
				intFrameAltaCategoria.setVisible(true);
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
				ocultarInternalFrames();
				panelPrincipal.setVisible(false);
				intFrameAltaPerfil.setVisible(true);
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
	
	public void crearInternalFrameAltaCategoria() {
		intFrameAltaCategoria = new JInternalFrame("Alta de Categoría");
		intFrameAltaCategoria.setBounds(0, 0, 453, 444);
		contentPane.add(intFrameAltaCategoria);
		intFrameAltaCategoria.getContentPane().setLayout(null);
		
		JPanel panelAltaCategoria = new JPanel();
		panelAltaCategoria.setBounds(10, 11, 417, 403);
		intFrameAltaCategoria.getContentPane().add(panelAltaCategoria);
		panelAltaCategoria.setLayout(null);
		
		JLabel lblListaDeCategorías = new JLabel("Lista de categorías existentes");
		lblListaDeCategorías.setBounds(65, 11, 250, 14);
		panelAltaCategoria.add(lblListaDeCategorías);
		listarCategorias();
		panelAltaCategoria.add(treeListaDeCategorias);
	}
	
	public void listarCategorias() {
        DefaultMutableTreeNode categorías = new DefaultMutableTreeNode("Categorías");
        DefaultMutableTreeNode teatro = new DefaultMutableTreeNode("Teatro");
        DefaultMutableTreeNode musica = new DefaultMutableTreeNode("Música");
        DefaultMutableTreeNode danza = new DefaultMutableTreeNode("Danza");
        DefaultMutableTreeNode rock = new DefaultMutableTreeNode("Rock");
        DefaultMutableTreeNode salsa = new DefaultMutableTreeNode("Salsa");
        DefaultMutableTreeNode pop = new DefaultMutableTreeNode("Pop");
        DefaultMutableTreeNode rockClasico = new DefaultMutableTreeNode("Rock clásico");
        DefaultMutableTreeNode rockAlternativo = new DefaultMutableTreeNode("Rock alternativo");
        categorías.add(teatro);
        categorías.add(musica);
        categorías.add(danza);
        rock.add(rockClasico);
        rock.add(rockAlternativo);
        rockClasico.add(new DefaultMutableTreeNode("Hoja"));
        rockAlternativo.add(new DefaultMutableTreeNode("Hoja"));
        teatro.add(new DefaultMutableTreeNode("Hoja"));
        danza.add(new DefaultMutableTreeNode("Hoja"));
        salsa.add(new DefaultMutableTreeNode("Hoja"));
        pop.add(new DefaultMutableTreeNode("Hoja"));
        musica.add(rock);
        musica.add(salsa);
        musica.add(pop);
        treeListaDeCategorias = new JTree(categorías);
        treeListaDeCategorias.setBounds(31, 49, 376, 343);
	}
	
	public void crearInternalFrameAltaPerfil() {
		intFrameAltaPerfil = new JInternalFrame("Registrar un Usuario");
		intFrameAltaPerfil.setBounds(0, 11, 443, 433);
		contentPane.add(intFrameAltaPerfil);
		intFrameAltaPerfil.getContentPane().setLayout(null);
		
		JPanel panelRegistrarUsuario = new JPanel();
		panelRegistrarUsuario.setBounds(10, 11, 407, 381);
		intFrameAltaPerfil.getContentPane().add(panelRegistrarUsuario);
		panelRegistrarUsuario.setLayout(null);
		
		JLabel lblNickname = new JLabel("Nickname:");
		lblNickname.setBounds(65, 46, 80, 14);
		panelRegistrarUsuario.add(lblNickname);
		
		JLabel lblIngreseLosSiguientes = new JLabel("Ingrese los siguientes datos");
		lblIngreseLosSiguientes.setBounds(96, 11, 193, 14);
		panelRegistrarUsuario.add(lblIngreseLosSiguientes);
		
		txtNickname = new JTextField();
		txtNickname.setBounds(155, 43, 174, 20);
		panelRegistrarUsuario.add(txtNickname);
		txtNickname.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(65, 74, 80, 14);
		panelRegistrarUsuario.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(155, 71, 174, 20);
		panelRegistrarUsuario.add(txtNombre);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(65, 102, 80, 14);
		panelRegistrarUsuario.add(lblApellido);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(155, 99, 174, 20);
		panelRegistrarUsuario.add(txtApellido);
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(65, 130, 80, 14);
		panelRegistrarUsuario.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(155, 127, 174, 20);
		panelRegistrarUsuario.add(txtEmail);
		
		JRadioButton rdbtnProponente = new JRadioButton("Proponente");
		buttonGroup.add(rdbtnProponente);
		rdbtnProponente.setBounds(116, 172, 91, 23);
		panelRegistrarUsuario.add(rdbtnProponente);
		
		JRadioButton rdbtnColaborador = new JRadioButton("Colaborador");
		buttonGroup.add(rdbtnColaborador);
		rdbtnColaborador.setBounds(214, 172, 109, 23);
		panelRegistrarUsuario.add(rdbtnColaborador);
		JButton btnSeleecionarImagen = new JButton("Seleecionar Imagen");
		btnSeleecionarImagen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileChooser.showOpenDialog(panelRegistrarUsuario);
			}
		});
		btnSeleecionarImagen.setBounds(96, 214, 233, 23);
		panelRegistrarUsuario.add(btnSeleecionarImagen);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(94, 263, 99, 23);
		panelRegistrarUsuario.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(230, 263, 99, 23);
		panelRegistrarUsuario.add(btnCancelar);
	}
	
	public void ocultarInternalFrames() {
		intFrameAltaPerfil.setVisible(false);
		intFrameAltaCategoria.setVisible(false);
	}
}
