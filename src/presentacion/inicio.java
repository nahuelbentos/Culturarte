package presentacion;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JInternalFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.CardLayout;

public class inicio extends JFrame {

	private JPanel contentPane;
	private JTextField txtNickname;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtEmail;
	private JTextField txtLoginNombre;
	private JPasswordField txtLoginPassword;

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
		setBounds(100, 100, 369, 436);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JFileChooser fileChooser = new JFileChooser();
		contentPane.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(5, 5, 343, 366);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JInternalFrame intFrameAltaPerfil = new JInternalFrame("Registrar un Usuario");
		intFrameAltaPerfil.setBounds(0, 0, 343, 366);
		panel_1.add(intFrameAltaPerfil);
		intFrameAltaPerfil.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 307, 314);
		intFrameAltaPerfil.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNickname = new JLabel("Nickname:");
		lblNickname.setBounds(30, 46, 49, 14);
		panel.add(lblNickname);
		
		JLabel lblIngreseLosSiguientes = new JLabel("Ingrese los siguientes datos");
		lblIngreseLosSiguientes.setBounds(89, 11, 134, 14);
		panel.add(lblIngreseLosSiguientes);
		
		txtNickname = new JTextField();
		txtNickname.setBounds(89, 43, 174, 20);
		panel.add(txtNickname);
		txtNickname.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(30, 74, 49, 14);
		panel.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(89, 71, 174, 20);
		panel.add(txtNombre);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(30, 102, 49, 14);
		panel.add(lblApellido);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(89, 99, 174, 20);
		panel.add(txtApellido);
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(30, 130, 49, 14);
		panel.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(89, 127, 174, 20);
		panel.add(txtEmail);
		
		JRadioButton rdbtnProponente = new JRadioButton("Proponente");
		rdbtnProponente.setBounds(50, 172, 91, 23);
		panel.add(rdbtnProponente);
		
		JRadioButton rdbtnColaborador = new JRadioButton("Colaborador");
		rdbtnColaborador.setBounds(148, 172, 109, 23);
		panel.add(rdbtnColaborador);
		JButton btnSeleecionarImagen = new JButton("Seleecionar Imagen");
		btnSeleecionarImagen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileChooser.showOpenDialog(panel);
			}
		});
		btnSeleecionarImagen.setBounds(30, 214, 233, 23);
		panel.add(btnSeleecionarImagen);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(28, 263, 99, 23);
		panel.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(164, 263, 99, 23);
		panel.add(btnCancelar);
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre de usuario:");
		lblNombreDeUsuario.setBounds(39, 121, 94, 14);
		panel_1.add(lblNombreDeUsuario);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setBounds(39, 153, 94, 14);
		panel_1.add(lblContrasea);
		
		txtLoginNombre = new JTextField();
		txtLoginNombre.setBounds(144, 118, 139, 20);
		panel_1.add(txtLoginNombre);
		txtLoginNombre.setColumns(10);
		
		txtLoginPassword = new JPasswordField();
		txtLoginPassword.setBounds(143, 150, 140, 20);
		panel_1.add(txtLoginPassword);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.setBounds(114, 215, 89, 23);
		panel_1.add(btnIngresar);
		
		JLabel lblPlataformaDeAdministracin = new JLabel("Plataforma de Administraci\u00F3n");
		lblPlataformaDeAdministracin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPlataformaDeAdministracin.setBounds(57, 55, 209, 14);
		panel_1.add(lblPlataformaDeAdministracin);
		intFrameAltaPerfil.setVisible(false);
		
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
}
