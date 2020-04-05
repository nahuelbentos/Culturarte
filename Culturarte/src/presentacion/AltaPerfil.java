package presentacion;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.prefs.Preferences;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.toedter.calendar.JDateChooser;

import datatype.DtColaborador;
import datatype.DtProponente;
import datatype.DtUsuario;
import excepciones.UsuarioYaExisteElEmailException;
import excepciones.UsuarioYaExisteElUsuarioException;
import logica.IUsuarioController;
import javax.swing.JPasswordField;

@SuppressWarnings("serial")
public class AltaPerfil extends JInternalFrame {

	private static final String DEFAULT_IMAGEN_USUARIO = "979f1e6f-5458-4eb5-a3d2-f64f5bb30c8e";
	
	private IUsuarioController iUsuarioController;
	
	private JTextField txtNickname;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtEmail;
	private JRadioButton rdbtnProponente;
	private JRadioButton rdbtnColaborador;
	private JFileChooser fileChooser = new JFileChooser();
	private ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField txtSitioWeb;
	private JTextField txtDireccion;
	private JLabel lblIngreseLosSiguientes;
	private JLabel lblNickname;
	private JPanel panelImagen;
	private JLabel lblImagen;
	private JLabel lblNombre;
	private JLabel lblApellido;
	private JLabel lblEmail;
	private JButton btnAceptar;
	private JButton btnTestNbentos;
	private JLabel lblTestNbentos;
	private JButton btnCancelar;
	private JButton btnSeleecionarImagen;
	private JLabel lblFechaDeNacimiento;
	private JLabel lblDireccion;
	private JLabel lblBiografia;
	private JLabel lblSitioWeb;
	private JDateChooser dateChooser;
	private JTextArea txtBiografia;
	private JLabel lblRol;
	private ImageIcon imagenUsuario;
	private byte[] imagenUsuarioByte;
	private JPasswordField txtPassword;
	private JPasswordField txtPasswordConfirmar;

	/**
	 * Create the frame.
	 */
	public AltaPerfil(IUsuarioController IUC) {
		iUsuarioController = IUC;
		
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        getContentPane().setLayout(null);
        setTitle("Registrar un Usuario");
        setBounds(10, 10, 563, 600);
		
        getContentPane().setLayout(null);
		
		lblNickname = new JLabel("Nickname:");
		lblNickname.setBounds(65, 66, 80, 14);
		getContentPane().add(lblNickname);
		
		lblIngreseLosSiguientes = new JLabel("Ingrese los siguientes datos:");
		lblIngreseLosSiguientes.setBounds(65, 25, 193, 14);
		getContentPane().add(lblIngreseLosSiguientes);
		
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Images", "jpg", "png"));
		
		panelImagen = new JPanel();
		panelImagen.setBounds(389, 66, 133, 133);
		getContentPane().add(panelImagen);
		
		lblImagen = new JLabel("");
		panelImagen.add(lblImagen);
		
		setearImagenUsuario();
		
		txtNickname = new JTextField();
		txtNickname.setBounds(155, 63, 174, 20);
		getContentPane().add(txtNickname);
		txtNickname.setColumns(10);
		
		lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(65, 164, 80, 14);
		getContentPane().add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(155, 161, 174, 20);
		getContentPane().add(txtNombre);
		
		lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(65, 194, 80, 14);
		getContentPane().add(lblApellido);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(155, 191, 174, 20);
		getContentPane().add(txtApellido);
		
		lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(65, 222, 80, 14);
		getContentPane().add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(155, 222, 174, 20);
		getContentPane().add(txtEmail);
		
		rdbtnProponente = new JRadioButton("Proponente");
		buttonGroup.add(rdbtnProponente);
		rdbtnProponente.setBounds(157, 293, 91, 23);
		getContentPane().add(rdbtnProponente);
		
		rdbtnColaborador = new JRadioButton("Colaborador");
		buttonGroup.add(rdbtnColaborador);
		rdbtnColaborador.setBounds(255, 293, 109, 23);
		getContentPane().add(rdbtnColaborador);
		btnSeleecionarImagen = new JButton("Selecionar Imagen");
		btnSeleecionarImagen.setHorizontalAlignment(SwingConstants.LEFT);
		btnSeleecionarImagen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int retorno = fileChooser.showOpenDialog(getContentPane());
                if (retorno == JFileChooser.APPROVE_OPTION) {
                    String pathName = fileChooser.getSelectedFile().getPath();
                    JOptionPane.showMessageDialog(null, pathName);
                    imagenUsuario = new ImageIcon(pathName);
                    Image imagenPrevia = imagenUsuario.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                    imagenUsuario = new ImageIcon(imagenPrevia, pathName);
                    lblImagen.setIcon(imagenUsuario);
                    try {
                    	imagenUsuarioByte = levantarImagen(pathName);
                    	imagenUsuarioByte = scale(imagenUsuarioByte, 150, 150);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
                }
			}
		});
		btnSeleecionarImagen.setBounds(389, 226, 133, 20);
		getContentPane().add(btnSeleecionarImagen);

		btnTestNbentos = new JButton("Test nbentos");
		btnTestNbentos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Test nbentos funca");

				//registrarUsuarioActionPerformed(arg0);
			}
		});
		btnTestNbentos.setBounds(155, 555, 99, 23);
		getContentPane().add(btnTestNbentos);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrarUsuarioActionPerformed(arg0);
		    }
		});
		btnAceptar.setBounds(155, 495, 99, 23);
		getContentPane().add(btnAceptar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(316, 495, 99, 23);
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limpiarFormulario();
                setVisible(false);
            }
        });
		getContentPane().add(btnCancelar);
		
		txtSitioWeb = new JTextField();
		txtSitioWeb.setColumns(10);
		txtSitioWeb.setBounds(155, 440, 174, 20);
		getContentPane().add(txtSitioWeb);
		
		txtDireccion = new JTextField();
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(155, 340, 174, 20);
		getContentPane().add(txtDireccion);
		txtDireccion.setVisible(false);
		
		lblFechaDeNacimiento = new JLabel("<html>Fecha de <br/>nacimiento:</html>");
		lblFechaDeNacimiento.setBounds(65, 250, 62, 28);
		getContentPane().add(lblFechaDeNacimiento);
		
		lblDireccion = new JLabel("Direcci\u00F3n:");
		lblDireccion.setBounds(65, 343, 80, 14);
		getContentPane().add(lblDireccion);
		lblDireccion.setVisible(false);
		
		lblBiografia = new JLabel("Biograf\u00EDa:");
		lblBiografia.setBounds(65, 374, 80, 14);
		getContentPane().add(lblBiografia);
		lblBiografia.setVisible(false);
		
		lblSitioWeb = new JLabel("Sitio web:");
		lblSitioWeb.setBounds(65, 443, 80, 14);
		getContentPane().add(lblSitioWeb);
		lblSitioWeb.setVisible(false);
		txtSitioWeb.setVisible(false);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(155, 255, 174, 20);
		getContentPane().add(dateChooser);
		
		txtBiografia = new JTextArea();
		txtBiografia.setBounds(155, 371, 174, 58);
		getContentPane().add(txtBiografia);
		txtBiografia.setVisible(false);
		
		lblRol = new JLabel("Rol:");
		lblRol.setBounds(65, 297, 46, 14);
		getContentPane().add(lblRol);
		
		JLabel lblPassword = new JLabel("Contrase\u00F1a:");
		lblPassword.setBounds(65, 91, 80, 14);
		getContentPane().add(lblPassword);
		
		JLabel lblConfirmarContrasea = new JLabel("<html>Confirmar <bt/>contrase\u00F1a:</html>");
		lblConfirmarContrasea.setBounds(65, 116, 80, 36);
		getContentPane().add(lblConfirmarContrasea);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(155, 94, 174, 20);
		getContentPane().add(txtPassword);
		
		txtPasswordConfirmar = new JPasswordField();
		txtPasswordConfirmar.setBounds(155, 130, 174, 20);
		getContentPane().add(txtPasswordConfirmar);
		
		rdbtnProponente.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
			    if (e.getStateChange() == ItemEvent.SELECTED) {
			    	if (rdbtnProponente.isSelected()) {
			    		habilitarProponente();
			    	} else {
			    		deshabilitarProponente();
			    	}
			    }
			    else if (e.getStateChange() == ItemEvent.DESELECTED) {
			    	if (rdbtnProponente.isSelected()) {
			    		habilitarProponente();
			    	} else {
			    		deshabilitarProponente();
			    	}
			    }
			}
		});
	}
	
    private void limpiarFormulario() {
        txtNickname.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtEmail.setText("");
        txtDireccion.setText("");
        txtBiografia.setText("");
        txtSitioWeb.setText("");
        dateChooser.setDate(null);
        buttonGroup.clearSelection();
        lblImagen.setIcon(null);
        txtPassword.setText("");
        txtPasswordConfirmar.setText("");
    }
    
    private void habilitarProponente() {
		lblDireccion.setVisible(true);
		lblBiografia.setVisible(true);
		lblSitioWeb.setVisible(true);
		txtDireccion.setVisible(true);
		txtBiografia.setVisible(true);
		txtSitioWeb.setVisible(true);
    }
    
    private void deshabilitarProponente() {
		lblDireccion.setVisible(false);
		lblBiografia.setVisible(false);
		lblSitioWeb.setVisible(false);
		txtDireccion.setVisible(false);
		txtBiografia.setVisible(false);
		txtSitioWeb.setVisible(false);
    }
    
    protected void registrarUsuarioActionPerformed(ActionEvent arg0) {
        String nickname = txtNickname.getText();
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String email = txtEmail.getText();
        char[] password = txtPassword.getPassword();
        GregorianCalendar fechaNacimiento = new GregorianCalendar();
        if (dateChooser.getDate() != null) {
        	fechaNacimiento.setTime(dateChooser.getDate());
        }
        String direccion = txtDireccion.getText();
        String biografia = txtBiografia.getText();
        String sitioWeb = txtSitioWeb.getText();
        DtUsuario dtUsuario = null;
        
        if (checkFormulario()) {
    		if (rdbtnColaborador.isSelected()) {
    			dtUsuario = new DtColaborador(nickname, nombre, apellido, email, password, fechaNacimiento, imagenUsuarioByte);
    		} else if (rdbtnProponente.isSelected()) {
    			dtUsuario = new DtProponente(nickname, nombre, apellido, email, password, fechaNacimiento, imagenUsuarioByte, 
    					direccion, biografia, sitioWeb);
    		}
    		try{
    			iUsuarioController.agregarUsuario(dtUsuario);
            	JOptionPane.showMessageDialog(this, "El Usuario se ha creado con �xito", "Registrar Usuario",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (UsuarioYaExisteElUsuarioException | UsuarioYaExisteElEmailException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Registrar Usuario", JOptionPane.ERROR_MESSAGE);
            }
            limpiarFormulario();
            setVisible(false);
        }
    }
    
    private boolean checkFormulario() {
        String nickname = txtNickname.getText();
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String email = txtEmail.getText();
        String direccion = txtDireccion.getText();
        GregorianCalendar fechaNacimiento = new GregorianCalendar();
        if (dateChooser.getDate() != null) {
        	fechaNacimiento.setTime(dateChooser.getDate());
        }
        if (!Arrays.equals(txtPassword.getPassword(), txtPasswordConfirmar.getPassword())) {
	        JOptionPane.showMessageDialog(this, "Las contrase�as no coinciden", "Registrar Usuario Proponente",
	                JOptionPane.ERROR_MESSAGE);
	        return false;
        } else if (rdbtnColaborador.isSelected()) {
            if (nickname.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || email.isEmpty() 
            		|| txtPassword.getPassword().length == 0 || txtPasswordConfirmar.getPassword().length == 0 
            		|| dateChooser.getDate() == null) {
                JOptionPane.showMessageDialog(this, "Los campos Nickname, Nombre, Apellido, Email "
                		+ "Password, Confirmar Password y Fecha de Nacimiento son requeridos.", "Registrar Usuario Colaborador",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            } else {
            	return true;
            }
    	} else if (rdbtnProponente.isSelected()) {
            if (nickname.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || email.isEmpty() || direccion.isEmpty() || dateChooser.getDate() == null) {
                JOptionPane.showMessageDialog(this, "Los campos Nickname, Nombre, Apellido, Email, "
                		+ "Fecha de Nacimiento y Direccion son requeridos.", "Registrar Usuario Proponente",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            } else {
            	return true;
            }
    	} else {
            JOptionPane.showMessageDialog(this, "Debe seleccionar Proponente o Colaborador", "Registrar Usuario",
                    JOptionPane.ERROR_MESSAGE);
            return false;
    	}
    }
    
    private byte[] levantarImagen(String pathName) throws IOException {
    	File file = new File(pathName);
    	byte[] picInBytes = new byte[(int) file.length()];
    	FileInputStream fileInputStream = new FileInputStream(file);
    	fileInputStream.read(picInBytes);
    	fileInputStream.close();
		return picInBytes;
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
    
    private byte[] scale(byte[] fileData, int width, int height) throws IOException {
        ByteArrayInputStream in = new ByteArrayInputStream(fileData);
        BufferedImage img = ImageIO.read(in);
        if(height == 0) {
            height = (width * img.getHeight())/ img.getWidth(); 
        }
        if(width == 0) {
            width = (height * img.getWidth())/ img.getHeight();
        }
        Image scaledImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage imageBuff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        imageBuff.getGraphics().drawImage(scaledImage, 0, 0, new Color(0,0,0), null);
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        ImageIO.write(imageBuff, "jpg", buffer);
        return buffer.toByteArray();
    }
    
    private void setearImagenUsuario() {
    	try {
			imagenUsuarioByte = obtenerImagen(DEFAULT_IMAGEN_USUARIO);
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
		String pathName = Principal.class.getResource("/datosDePrueba/imagenes/" + DEFAULT_IMAGEN_USUARIO + ".jpg").getPath();
        imagenUsuario = new ImageIcon(pathName);
        Image imagenPrevia = imagenUsuario.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        imagenUsuario = new ImageIcon(imagenPrevia, pathName);
        lblImagen.setIcon(imagenUsuario);
    }
}
