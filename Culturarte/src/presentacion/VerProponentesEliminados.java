package presentacion;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.format.DateTimeFormatter;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import datatype.DtColaborador;
import datatype.DtProponente;
import datatype.DtUsuario;
import excepciones.NoExistenProponentesEliminadosException;
import excepciones.ProponenteNoExisteException;
import excepciones.UsuarioNoExisteElUsuarioException;
import logica.IUsuarioController;

@SuppressWarnings("serial")
public class VerProponentesEliminados extends JInternalFrame {

	private IUsuarioController iUsuarioController;
	
	private JComboBox<String> cmbUsuario;
	private JComboBox<String> cmbPropuestas;
	private JComboBox<String> cmbColaboraciones;
	private JLabel lblSeleecionarUsuario;
	private JLabel lblSeleccionarPropuesta;
	private JLabel lblSeleccionarColaboracion;
	private static final String TEXTO_COMBO_UNO = "No hay proponentes eliminados en el sistema";
	private static final String TEXTO_COMBO_DOS = "El proponente no había ingresado propuestas";
	private static final String TEXTO_COMBO_TRES = "No se habían registrado colaboraciones";
	private static final String TEXTO_COMBO_DOS_INICIAL = "Seleccione una de sus propuestas";
	private static final String TEXTO_COMBO_TRES_INICIAL = "Seleccione una de sus colaboraciones";
	private JTextField txtNickname;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtEmail;
	private JTextField txtFechaDeNacimiento;
	private JTextField txtFechaDeEliminacion;
	private JLabel lblImagen;
	public VerProponentesEliminados(IUsuarioController IUC) {
		
		iUsuarioController = IUC;
		
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        getContentPane().setLayout(null);
        setTitle("Ver Proponentes Eliminados");
        setBounds(10, 10, 1000, 648);
		
        getContentPane().setLayout(null);
		
		cmbUsuario = new JComboBox<String>();
		cmbUsuario.setBounds(28, 116, 230, 20);
		cmbUsuario.addItemListener(new ItemListener() {	
			@Override
			public void itemStateChanged(ItemEvent e) {
				if ((e.getStateChange() == ItemEvent.SELECTED)) {
	                Object selected = cmbUsuario.getSelectedItem();
	                if (!selected.toString().equals(TEXTO_COMBO_UNO)) {
	                	try {
							actualizarDatos(e);
						} catch (UsuarioNoExisteElUsuarioException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	                }
				}
			}
		});
		
		getContentPane().add(cmbUsuario);
		
		cmbPropuestas = new JComboBox<String>();
		cmbPropuestas.setBounds(312, 116, 230, 20);
		cmbPropuestas.addItemListener(new ItemListener() {	
			@Override
			public void itemStateChanged(ItemEvent e) {
				if ((e.getStateChange() == ItemEvent.SELECTED)) {
	                Object selected = cmbPropuestas.getSelectedItem();
	                if (!selected.toString().equals(TEXTO_COMBO_DOS_INICIAL) && 
	                		!selected.toString().equals(TEXTO_COMBO_DOS)) {
//						try {
//							//setearPerfilUsuarioDos();
//						} catch (UsuarioNoExisteElUsuarioException e1) {
//							// TODO Auto-generated catch block
//							e1.printStackTrace();
//						}
	                }
				}
			}
		});
		getContentPane().add(cmbPropuestas);
		cmbPropuestas.addItem(TEXTO_COMBO_DOS_INICIAL);
		
		cmbColaboraciones = new JComboBox<String>();
		cmbColaboraciones.setBounds(620, 116, 250, 20);
		cmbColaboraciones.addItemListener(new ItemListener() {	
			@Override
			public void itemStateChanged(ItemEvent e) {
				if ((e.getStateChange() == ItemEvent.SELECTED)) {
	                Object selected = cmbColaboraciones.getSelectedItem();
	                if (!selected.toString().equals(TEXTO_COMBO_TRES_INICIAL) && 
	                		!selected.toString().equals(TEXTO_COMBO_TRES)) {
//						try {
//							//setearPerfilUsuarioDos();
//						} catch (UsuarioNoExisteElUsuarioException e1) {
//							// TODO Auto-generated catch block
//							e1.printStackTrace();
//						}
	                }
				}
			}
		});
		getContentPane().add(cmbColaboraciones);
		cmbColaboraciones.addItem(TEXTO_COMBO_TRES_INICIAL);
		
		lblSeleecionarUsuario = new JLabel("<html>Seleecione uno de los proponentes <br/>para ver sus datos:</html>");
		lblSeleecionarUsuario.setBounds(28, 36, 197, 69);
		getContentPane().add(lblSeleecionarUsuario);
		
		lblSeleccionarPropuesta = new JLabel("<html>Seleecione una de sus propuestas<br/>para ver "
				+ "sus detalles y colaboraciones:</html>");
		lblSeleccionarPropuesta.setBounds(312, 36, 197, 69);
		getContentPane().add(lblSeleccionarPropuesta);
		
		lblSeleccionarColaboracion = new JLabel("<html>Seleecione una de sus colaboraciones<br/>para ver "
				+ "sus detalles:</html>");
		lblSeleccionarColaboracion.setBounds(620, 36, 197, 69);
		getContentPane().add(lblSeleccionarColaboracion);
			
		txtNickname = new JTextField();
		txtNickname.setEditable(false);
		txtNickname.setColumns(10);
		txtNickname.setBounds(118, 345, 107, 20);
		getContentPane().add(txtNickname);
		
		JLabel label = new JLabel("Nickname:");
		label.setBounds(28, 348, 80, 14);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Nombre:");
		label_1.setBounds(28, 379, 80, 14);
		getContentPane().add(label_1);
		
		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setColumns(10);
		txtNombre.setBounds(118, 376, 107, 20);
		getContentPane().add(txtNombre);
		
		txtApellido = new JTextField();
		txtApellido.setEditable(false);
		txtApellido.setColumns(10);
		txtApellido.setBounds(118, 406, 107, 20);
		getContentPane().add(txtApellido);
		
		JLabel label_2 = new JLabel("Apellido:");
		label_2.setBounds(28, 409, 80, 14);
		getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("E-mail:");
		label_3.setBounds(28, 437, 80, 14);
		getContentPane().add(label_3);
		
		txtEmail = new JTextField();
		txtEmail.setEditable(false);
		txtEmail.setColumns(10);
		txtEmail.setBounds(118, 437, 107, 20);
		getContentPane().add(txtEmail);
		
		JLabel label_4 = new JLabel("<html>Fecha de <br/>nacimiento:</html>");
		label_4.setBounds(28, 465, 62, 28);
		getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("<html>Fecha de <br/>eliminación:</html>");
		label_5.setBounds(28, 507, 46, 14);
		getContentPane().add(label_5);
		
		txtFechaDeNacimiento = new JTextField();
		txtFechaDeNacimiento.setEditable(false);
		txtFechaDeNacimiento.setColumns(10);
		txtFechaDeNacimiento.setBounds(118, 473, 107, 20);
		getContentPane().add(txtFechaDeNacimiento);
		
		txtFechaDeEliminacion = new JTextField();
		txtFechaDeEliminacion.setEditable(false);
		txtFechaDeEliminacion.setColumns(10);
		txtFechaDeEliminacion.setBounds(118, 504, 107, 20);
		getContentPane().add(txtFechaDeEliminacion);
		
		lblImagen = new JLabel("");
		lblImagen.setBounds(52, 168, 150, 150);
		getContentPane().add(lblImagen);

	}
	
	protected void actualizarDatos(ItemEvent arg0) throws UsuarioNoExisteElUsuarioException {
		try {
			setearPerfilUsuarioUno();
        } catch (UsuarioNoExisteElUsuarioException e) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error inesperado", "Seguir Usuario", JOptionPane.ERROR_MESSAGE);
        }
	}
	
	public void setListaDeProponentesEliminados() {
		cmbUsuario.removeAllItems();
        DtUsuario[] usuarios;
		try {
			usuarios = iUsuarioController.listarProponentes();
            for (int i = 0; i < usuarios.length; i++) {
            	cmbUsuario.addItem(usuarios[i].getNickname());
            }
		} catch (ProponenteNoExisteException e) {
			cmbUsuario.addItem(TEXTO_COMBO_UNO);
		}
	}
	
	private void setearPerfilUsuarioUno() throws UsuarioNoExisteElUsuarioException {
		DtUsuario dtUsuario = iUsuarioController.verPerfilUsuario(cmbUsuario.getSelectedItem().toString());
		DtProponente dtProponente = (DtProponente) dtUsuario;
		txtNickname.setText(dtProponente.getNickname());
		txtNombre.setText(dtProponente.getNombre());
		txtApellido.setText(dtProponente.getApellido());
		txtEmail.setText(dtProponente.getEmail());
		txtFechaDeNacimiento.setText(dtProponente.getFechaNacimiento().toZonedDateTime()
			       .format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		txtFechaDeEliminacion.setText("PENDIENTE");
		if(dtProponente.getImagen() != null) {
			ImageIcon imageIcon = new ImageIcon(dtProponente.getImagen());
			lblImagen.setIcon(imageIcon);
		}
	}
	
    private void limpiarFormulario() {
        txtNickname.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtEmail.setText("");
        txtFechaDeNacimiento.setText("");
        txtFechaDeEliminacion.setText("");
    }
}
