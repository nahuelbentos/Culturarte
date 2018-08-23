package presentacion;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.UUID;

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
import logica.IUsuarioController;

@SuppressWarnings("serial")
public class AltaPerfil extends JInternalFrame {

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
        setBounds(10, 10, 563, 520);
		
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
		
		txtNickname = new JTextField();
		txtNickname.setBounds(155, 63, 174, 20);
		getContentPane().add(txtNickname);
		txtNickname.setColumns(10);
		
		lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(65, 97, 80, 14);
		getContentPane().add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(155, 94, 174, 20);
		getContentPane().add(txtNombre);
		
		lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(65, 127, 80, 14);
		getContentPane().add(lblApellido);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(155, 124, 174, 20);
		getContentPane().add(txtApellido);
		
		lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(65, 155, 80, 14);
		getContentPane().add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(155, 152, 174, 20);
		getContentPane().add(txtEmail);
		
		rdbtnProponente = new JRadioButton("Proponente");
		buttonGroup.add(rdbtnProponente);
		rdbtnProponente.setBounds(157, 226, 91, 23);
		getContentPane().add(rdbtnProponente);
		
		rdbtnColaborador = new JRadioButton("Colaborador");
		buttonGroup.add(rdbtnColaborador);
		rdbtnColaborador.setBounds(255, 226, 109, 23);
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
                    Image imagenPrevia = imagenUsuario.getImage().getScaledInstance(133, 133, Image.SCALE_SMOOTH);
                    imagenUsuario = new ImageIcon(imagenPrevia, pathName);
                    lblImagen.setIcon(imagenUsuario);
                }
			}
		});
		btnSeleecionarImagen.setBounds(389, 226, 133, 20);
		getContentPane().add(btnSeleecionarImagen);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrarUsuarioActionPerformed(arg0);
		    }
		});
		btnAceptar.setBounds(155, 428, 99, 23);
		getContentPane().add(btnAceptar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(316, 428, 99, 23);
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limpiarFormulario();
                setVisible(false);
            }
        });
		getContentPane().add(btnCancelar);
		
		txtSitioWeb = new JTextField();
		txtSitioWeb.setColumns(10);
		txtSitioWeb.setBounds(155, 373, 174, 20);
		getContentPane().add(txtSitioWeb);
		
		txtDireccion = new JTextField();
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(155, 273, 174, 20);
		getContentPane().add(txtDireccion);
		txtDireccion.setVisible(false);
		
		lblFechaDeNacimiento = new JLabel("<html>Fecha de <br/>nacimiento:</html>");
		lblFechaDeNacimiento.setBounds(65, 183, 62, 28);
		getContentPane().add(lblFechaDeNacimiento);
		
		lblDireccion = new JLabel("Direcci\u00F3n:");
		lblDireccion.setBounds(65, 276, 80, 14);
		getContentPane().add(lblDireccion);
		lblDireccion.setVisible(false);
		
		lblBiografia = new JLabel("Biograf\u00EDa:");
		lblBiografia.setBounds(65, 307, 80, 14);
		getContentPane().add(lblBiografia);
		lblBiografia.setVisible(false);
		
		lblSitioWeb = new JLabel("Sitio web:");
		lblSitioWeb.setBounds(65, 376, 80, 14);
		getContentPane().add(lblSitioWeb);
		lblSitioWeb.setVisible(false);
		txtSitioWeb.setVisible(false);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(155, 188, 174, 20);
		getContentPane().add(dateChooser);
		
		txtBiografia = new JTextArea();
		txtBiografia.setBounds(155, 304, 174, 58);
		getContentPane().add(txtBiografia);
		txtBiografia.setVisible(false);
		
		lblRol = new JLabel("Rol:");
		lblRol.setBounds(65, 230, 46, 14);
		getContentPane().add(lblRol);
		
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
        rdbtnProponente.setSelected(false);
        rdbtnColaborador.setSelected(false);
        lblImagen.setIcon(null);
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
        GregorianCalendar fechaNacimiento = new GregorianCalendar();
        if (dateChooser.getDate() != null) {
        	fechaNacimiento.setTime(dateChooser.getDate());
        }
        String direccion = txtDireccion.getText();
        String biografia = txtBiografia.getText();
        String sitioWeb = txtSitioWeb.getText();
        DtUsuario dtUsuario = null;
        if (checkFormulario()) {
        	String nombreImagen = guardarImagen();
    		if (rdbtnColaborador.isSelected()) {
    			dtUsuario = new DtColaborador(nickname, nombre, apellido, email, fechaNacimiento, nombreImagen);
    		} else if (rdbtnProponente.isSelected()) {
    			dtUsuario = new DtProponente(nickname, nombre, apellido, email, fechaNacimiento, nombreImagen, 
    					direccion, biografia, sitioWeb);
    		}
    		if (iUsuarioController.agregarUsuario(dtUsuario)) {
            	JOptionPane.showMessageDialog(this, "El Usuario se ha creado con �xito", "Registrar Usuario",
                        JOptionPane.INFORMATION_MESSAGE);
	            limpiarFormulario();
	            setVisible(false);
    		}
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
    	if (rdbtnColaborador.isSelected()) {
            if (nickname.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || email.isEmpty() || dateChooser.getDate() == null) {
                JOptionPane.showMessageDialog(this, "Los campos Nickname, Nombre, Apellido, Email "
                		+ "y Fecha de Nacimiento son requeridos.", "Registrar Usuario Colaborador",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            } else {
            	return true;
            }
    	} else if (rdbtnProponente.isSelected()) {
            if (nickname.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || email.isEmpty() || direccion.isEmpty() || dateChooser.getDate() == null) {
                JOptionPane.showMessageDialog(this, "Los campos Nickname, Nombre, Apellido, Email, "
                		+ "Fecha de Nacimiento y Direcci�n son requeridos.", "Registrar Usuario Proponente",
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
    
    private String guardarImagen() {
    	String nombreArchivo = "";
    	try {
	    	Image img = imagenUsuario.getImage();
	    	BufferedImage bufferedImage = new BufferedImage(img.getWidth(null),img.getHeight(null),BufferedImage.TYPE_INT_ARGB);
	    	Graphics2D g2 = bufferedImage.createGraphics();
	    	g2.drawImage(img, 0, 0, null);
	    	g2.dispose();
    		nombreArchivo = UUID.randomUUID().toString();
    		// Para probar lo guardamos como jpg, deber�a tomar la extensi�n de la imagen original
			ImageIO.write(bufferedImage, "jpg", new File("src/imagenes/" + nombreArchivo + ".jpg"));
		} catch (IOException | NullPointerException e) {
			return nombreArchivo;
		}
    	return nombreArchivo;
    }
}
