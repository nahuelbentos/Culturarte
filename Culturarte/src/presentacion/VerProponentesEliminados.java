package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.format.DateTimeFormatter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import excepciones.UsuarioNoExisteElUsuarioException;
import logica.IUsuarioController;

@SuppressWarnings("serial")
public class VerProponentesEliminados extends JInternalFrame {

	private IUsuarioController iUsuarioController;
	
	private JComboBox<String> cmbUsuarioUno;
	private JComboBox<String> cmbUsuarioDos;
	private JLabel lblSeleecioneElUsuario;
	private JLabel lblseleecioneElUsuario;
	private JButton btnCancelar;
	private static final String TEXTO_COMBO_UNO = "No hay proponentes eliminados en el sistema";
	private static final String TEXTO_COMBO_DOS = "El proponente no había ingresado propuestas";
	private static final String TEXTO_COMBO_DOS_INICIAL = "Seleccione una de sus propuestas";
	private JTextField txtNicknameUno;
	private JTextField txtNombreUno;
	private JTextField txtApellidoUno;
	private JTextField txtEmailUno;
	private JTextField txtFechaDeNacimientoUno;
	private JTextField txtRolUno;
	private JLabel lblImagenUno;
	public VerProponentesEliminados(IUsuarioController IUC) {
		
		iUsuarioController = IUC;
		
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        getContentPane().setLayout(null);
        setTitle("Ver Proponentes Eliminados");
        setBounds(10, 10, 557, 648);
		
        getContentPane().setLayout(null);
		
		cmbUsuarioUno = new JComboBox<String>();
		cmbUsuarioUno.setBounds(28, 116, 197, 20);
		cmbUsuarioUno.addItemListener(new ItemListener() {	
			@Override
			public void itemStateChanged(ItemEvent e) {
				if ((e.getStateChange() == ItemEvent.SELECTED)) {
	                Object selected = cmbUsuarioUno.getSelectedItem();
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
		
		getContentPane().add(cmbUsuarioUno);
		
		cmbUsuarioDos = new JComboBox<String>();
		cmbUsuarioDos.setBounds(312, 116, 197, 20);
		cmbUsuarioDos.addItemListener(new ItemListener() {	
			@Override
			public void itemStateChanged(ItemEvent e) {
				if ((e.getStateChange() == ItemEvent.SELECTED)) {
	                Object selected = cmbUsuarioDos.getSelectedItem();
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
		getContentPane().add(cmbUsuarioDos);
		cmbUsuarioDos.addItem(TEXTO_COMBO_DOS_INICIAL);
		
		lblSeleecioneElUsuario = new JLabel("<html>Seleecione uno de los proponentes <br/>para ver sus datos:</html>");
		lblSeleecioneElUsuario.setBounds(28, 36, 197, 69);
		getContentPane().add(lblSeleecioneElUsuario);
		
		lblseleecioneElUsuario = new JLabel("<html>Seleecione una de sus propuestas<br/>para ver "
				+ "sus detalles y colaboraciones:</html>");
		lblseleecioneElUsuario.setBounds(312, 36, 197, 69);
		getContentPane().add(lblseleecioneElUsuario);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiarFormulario();
				setVisible(false);
			}
		});
		btnCancelar.setBounds(309, 552, 89, 23);
		getContentPane().add(btnCancelar);
		
		txtNicknameUno = new JTextField();
		txtNicknameUno.setEditable(false);
		txtNicknameUno.setColumns(10);
		txtNicknameUno.setBounds(118, 345, 107, 20);
		getContentPane().add(txtNicknameUno);
		
		JLabel label = new JLabel("Nickname:");
		label.setBounds(28, 348, 80, 14);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Nombre:");
		label_1.setBounds(28, 379, 80, 14);
		getContentPane().add(label_1);
		
		txtNombreUno = new JTextField();
		txtNombreUno.setEditable(false);
		txtNombreUno.setColumns(10);
		txtNombreUno.setBounds(118, 376, 107, 20);
		getContentPane().add(txtNombreUno);
		
		txtApellidoUno = new JTextField();
		txtApellidoUno.setEditable(false);
		txtApellidoUno.setColumns(10);
		txtApellidoUno.setBounds(118, 406, 107, 20);
		getContentPane().add(txtApellidoUno);
		
		JLabel label_2 = new JLabel("Apellido:");
		label_2.setBounds(28, 409, 80, 14);
		getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("E-mail:");
		label_3.setBounds(28, 437, 80, 14);
		getContentPane().add(label_3);
		
		txtEmailUno = new JTextField();
		txtEmailUno.setEditable(false);
		txtEmailUno.setColumns(10);
		txtEmailUno.setBounds(118, 437, 107, 20);
		getContentPane().add(txtEmailUno);
		
		JLabel label_4 = new JLabel("<html>Fecha de <br/>nacimiento:</html>");
		label_4.setBounds(28, 465, 62, 28);
		getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("Rol:");
		label_5.setBounds(28, 507, 46, 14);
		getContentPane().add(label_5);
		
		txtFechaDeNacimientoUno = new JTextField();
		txtFechaDeNacimientoUno.setEditable(false);
		txtFechaDeNacimientoUno.setColumns(10);
		txtFechaDeNacimientoUno.setBounds(118, 473, 107, 20);
		getContentPane().add(txtFechaDeNacimientoUno);
		
		txtRolUno = new JTextField();
		txtRolUno.setEditable(false);
		txtRolUno.setColumns(10);
		txtRolUno.setBounds(118, 504, 107, 20);
		getContentPane().add(txtRolUno);
		
		lblImagenUno = new JLabel("");
		lblImagenUno.setBounds(52, 168, 150, 150);
		getContentPane().add(lblImagenUno);

	}
	
	protected void actualizarDatos(ItemEvent arg0) throws UsuarioNoExisteElUsuarioException {
		try {
			setearPerfilUsuarioUno();
        } catch (UsuarioNoExisteElUsuarioException e) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error inesperado", "Seguir Usuario", JOptionPane.ERROR_MESSAGE);
        }
	}
	
	public void setListaDeProponentesEliminados() {
		cmbUsuarioUno.removeAllItems();
        DtUsuario[] usuarios;
		try {
			usuarios = iUsuarioController.verProponentesEliminados();
            for (int i = 0; i < usuarios.length; i++) {
            	cmbUsuarioUno.addItem(usuarios[i].getNickname());
            }
		} catch (NoExistenProponentesEliminadosException e) {
			cmbUsuarioUno.addItem(TEXTO_COMBO_UNO);
		}
	}
	
	private void setearPerfilUsuarioUno() throws UsuarioNoExisteElUsuarioException {
		DtUsuario dtUsuario = iUsuarioController.verPerfilUsuario(cmbUsuarioUno.getSelectedItem().toString());
		if (dtUsuario instanceof DtProponente) {
			DtProponente dtProponente = (DtProponente) dtUsuario;
			txtNicknameUno.setText(dtProponente.getNickname());
			txtNombreUno.setText(dtProponente.getNombre());
			txtApellidoUno.setText(dtProponente.getApellido());
			txtEmailUno.setText(dtProponente.getEmail());
			txtFechaDeNacimientoUno.setText(dtProponente.getFechaNacimiento().toZonedDateTime()
				       .format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			txtRolUno.setText("Proponente");
			if(dtProponente.getImagen() != null) {
				ImageIcon imageIcon = new ImageIcon(dtProponente.getImagen());
				lblImagenUno.setIcon(imageIcon);
			}
		} else if (dtUsuario instanceof DtColaborador) {
			DtColaborador dtColaborador = (DtColaborador) dtUsuario;
			txtNicknameUno.setText(dtColaborador.getNickname());
			txtNombreUno.setText(dtColaborador.getNombre());
			txtApellidoUno.setText(dtColaborador.getApellido());
			txtEmailUno.setText(dtColaborador.getEmail());
			txtFechaDeNacimientoUno.setText(dtColaborador.getFechaNacimiento().toZonedDateTime()
				       .format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			txtRolUno.setText("Colaborador");
			if(dtColaborador.getImagen() != null) {
				ImageIcon imageIcon = new ImageIcon(dtColaborador.getImagen());
				lblImagenUno.setIcon(imageIcon);
			}
		}
	}
	
    private void limpiarFormulario() {
        txtNicknameUno.setText("");
        txtNombreUno.setText("");
        txtApellidoUno.setText("");
        txtEmailUno.setText("");
        txtFechaDeNacimientoUno.setText("");
        txtRolUno.setText("");
    }
}
