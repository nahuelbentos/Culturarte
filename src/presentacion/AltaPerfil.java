package presentacion;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

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

@SuppressWarnings("serial")
public class AltaPerfil extends JInternalFrame {

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

	/**
	 * Create the frame.
	 */
	public AltaPerfil() {
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setLayout(null);
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
                    ImageIcon icon = new ImageIcon(pathName);
                    Image imagenPrevia = icon.getImage().getScaledInstance(133, 133, Image.SCALE_SMOOTH);
                    icon = new ImageIcon(imagenPrevia, pathName);
                    lblImagen.setIcon(icon);
                }
			}
		});
		btnSeleecionarImagen.setBounds(389, 226, 133, 20);
		getContentPane().add(btnSeleecionarImagen);
		
		btnAceptar = new JButton("Aceptar");
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
        rdbtnProponente.setSelected(false);
        rdbtnColaborador.setSelected(false);
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
}
