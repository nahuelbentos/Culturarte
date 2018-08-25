package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JInternalFrame;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import datatype.DtColaborador;
import datatype.DtProponente;
import datatype.DtUsuario;
import excepciones.UsuarioNoExisteElUsuarioException;
import excepciones.UsuarioYaSigueAlUsuarioException;
import logica.IUsuarioController;
import javax.swing.JButton;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class SeguirUsuario extends JInternalFrame {

	private IUsuarioController iUsuarioController;
	private JComboBox<String> cmbUsuarioUno;
	private JComboBox<String> cmbUsuarioDos;
	private JLabel lblSeleecioneElUsuario;
	private JLabel lblseleecioneElUsuario;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private static final String TEXTO_COMBO_UNO = "No hay usuarios registrados en el sistema";
	private static final String TEXTO_COMBO_DOS = "No hay más usuarios registrados en el sistema";
	private static final String TEXTO_COMBO_DOS_INICIAL = "Seleccione un Usuario";
	private JTextField txtNicknameUno;
	private JTextField txtNombreUno;
	private JTextField txtApellidoUno;
	private JTextField txtEmailUno;
	private JTextField txtFechaDeNacimientoUno;
	private JTextField txtRolUno;
	private JTextField txtNicknameDos;
	private JTextField txtNombreDos;
	private JTextField txtApellidoDos;
	private JTextField txtEmailDos;
	private JTextField txtRolDos;
	private JTextField txtFechaDeNacimientoDos;
	
	/**
	 * Create the frame.
	 */
	public SeguirUsuario(IUsuarioController IUC) {
		
		iUsuarioController = IUC;
		
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        getContentPane().setLayout(null);
        setTitle("Seguir un Usuario");
        setBounds(10, 10, 563, 520);
		
        getContentPane().setLayout(null);
		
		cmbUsuarioUno = new JComboBox<String>();
		cmbUsuarioUno.setBounds(28, 135, 197, 20);
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
		cmbUsuarioDos.setBounds(312, 135, 197, 20);
		cmbUsuarioDos.addItemListener(new ItemListener() {	
			@Override
			public void itemStateChanged(ItemEvent e) {
				if ((e.getStateChange() == ItemEvent.SELECTED)) {
	                Object selected = cmbUsuarioDos.getSelectedItem();
	                if (!selected.toString().equals(TEXTO_COMBO_DOS_INICIAL) && 
	                		!selected.toString().equals(TEXTO_COMBO_DOS)) {
						try {
							setearPerfilUsuarioDos();
						} catch (UsuarioNoExisteElUsuarioException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	                }
				}
			}
		});
		getContentPane().add(cmbUsuarioDos);
		cmbUsuarioDos.addItem("Seleccione un Usuario");
		
		lblSeleecioneElUsuario = new JLabel("<html>Seleecione el usuario <br/>que desea realizar el seguimiento:</html>");
		lblSeleecioneElUsuario.setBounds(28, 55, 197, 69);
		getContentPane().add(lblSeleecioneElUsuario);
		
		lblseleecioneElUsuario = new JLabel("<html>Seleecione el usuario <br/>al que "
				+ "desea seguir:</html>");
		lblseleecioneElUsuario.setBounds(312, 55, 197, 69);
		getContentPane().add(lblseleecioneElUsuario);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seguirUsuarioActionPerformed(e);
			}
		});
		btnAceptar.setBounds(125, 428, 89, 23);
		getContentPane().add(btnAceptar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiarFormulario();
				setVisible(false);
			}
		});
		btnCancelar.setBounds(309, 428, 89, 23);
		getContentPane().add(btnCancelar);
		
		txtNicknameUno = new JTextField();
		txtNicknameUno.setEditable(false);
		txtNicknameUno.setColumns(10);
		txtNicknameUno.setBounds(118, 190, 107, 20);
		getContentPane().add(txtNicknameUno);
		
		JLabel label = new JLabel("Nickname:");
		label.setBounds(28, 193, 80, 14);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Nombre:");
		label_1.setBounds(28, 224, 80, 14);
		getContentPane().add(label_1);
		
		txtNombreUno = new JTextField();
		txtNombreUno.setEditable(false);
		txtNombreUno.setColumns(10);
		txtNombreUno.setBounds(118, 221, 107, 20);
		getContentPane().add(txtNombreUno);
		
		txtApellidoUno = new JTextField();
		txtApellidoUno.setEditable(false);
		txtApellidoUno.setColumns(10);
		txtApellidoUno.setBounds(118, 251, 107, 20);
		getContentPane().add(txtApellidoUno);
		
		JLabel label_2 = new JLabel("Apellido:");
		label_2.setBounds(28, 254, 80, 14);
		getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("E-mail:");
		label_3.setBounds(28, 282, 80, 14);
		getContentPane().add(label_3);
		
		txtEmailUno = new JTextField();
		txtEmailUno.setEditable(false);
		txtEmailUno.setColumns(10);
		txtEmailUno.setBounds(118, 282, 107, 20);
		getContentPane().add(txtEmailUno);
		
		JLabel label_4 = new JLabel("<html>Fecha de <br/>nacimiento:</html>");
		label_4.setBounds(28, 310, 62, 28);
		getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("Rol:");
		label_5.setBounds(28, 352, 46, 14);
		getContentPane().add(label_5);
		
		txtFechaDeNacimientoUno = new JTextField();
		txtFechaDeNacimientoUno.setEditable(false);
		txtFechaDeNacimientoUno.setColumns(10);
		txtFechaDeNacimientoUno.setBounds(118, 318, 107, 20);
		getContentPane().add(txtFechaDeNacimientoUno);
		
		txtRolUno = new JTextField();
		txtRolUno.setEditable(false);
		txtRolUno.setColumns(10);
		txtRolUno.setBounds(118, 349, 107, 20);
		getContentPane().add(txtRolUno);
		
		JLabel label_6 = new JLabel("Nickname:");
		label_6.setBounds(312, 193, 80, 14);
		getContentPane().add(label_6);
		
		txtNicknameDos = new JTextField();
		txtNicknameDos.setEditable(false);
		txtNicknameDos.setColumns(10);
		txtNicknameDos.setBounds(402, 190, 107, 20);
		getContentPane().add(txtNicknameDos);
		
		txtNombreDos = new JTextField();
		txtNombreDos.setEditable(false);
		txtNombreDos.setColumns(10);
		txtNombreDos.setBounds(402, 221, 107, 20);
		getContentPane().add(txtNombreDos);
		
		JLabel label_7 = new JLabel("Nombre:");
		label_7.setBounds(312, 224, 80, 14);
		getContentPane().add(label_7);
		
		JLabel label_8 = new JLabel("Apellido:");
		label_8.setBounds(312, 254, 80, 14);
		getContentPane().add(label_8);
		
		txtApellidoDos = new JTextField();
		txtApellidoDos.setEditable(false);
		txtApellidoDos.setColumns(10);
		txtApellidoDos.setBounds(402, 251, 107, 20);
		getContentPane().add(txtApellidoDos);
		
		txtEmailDos = new JTextField();
		txtEmailDos.setEditable(false);
		txtEmailDos.setColumns(10);
		txtEmailDos.setBounds(402, 282, 107, 20);
		getContentPane().add(txtEmailDos);
		
		JLabel label_9 = new JLabel("<html>Fecha de <br/>nacimiento:</html>");
		label_9.setBounds(312, 310, 62, 28);
		getContentPane().add(label_9);
		
		JLabel label_10 = new JLabel("E-mail:");
		label_10.setBounds(312, 282, 80, 14);
		getContentPane().add(label_10);
		
		JLabel label_11 = new JLabel("Rol:");
		label_11.setBounds(312, 352, 46, 14);
		getContentPane().add(label_11);
		
		txtRolDos = new JTextField();
		txtRolDos.setEditable(false);
		txtRolDos.setColumns(10);
		txtRolDos.setBounds(402, 349, 107, 20);
		getContentPane().add(txtRolDos);
		
		txtFechaDeNacimientoDos = new JTextField();
		txtFechaDeNacimientoDos.setEditable(false);
		txtFechaDeNacimientoDos.setColumns(10);
		txtFechaDeNacimientoDos.setBounds(402, 318, 107, 20);
		getContentPane().add(txtFechaDeNacimientoDos);

	}
	
	protected void seguirUsuarioActionPerformed(ActionEvent arg0) {
		if (validarSelecciones()) {
			try {
				iUsuarioController.seguirUsuario(cmbUsuarioUno.getSelectedItem().toString(), 
						cmbUsuarioDos.getSelectedItem().toString());
            	JOptionPane.showMessageDialog(this, "Se ha registrado la acción.\nEl usuario: " 
						+ cmbUsuarioUno.getSelectedItem().toString() + " ahora sigue al usuario: " 
            			+ cmbUsuarioDos.getSelectedItem().toString(), "Registrar Usuario",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (UsuarioYaSigueAlUsuarioException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Seguir Usuario", JOptionPane.ERROR_MESSAGE);
            }
		}
	}
	
	protected void actualizarDatos(ItemEvent arg0) throws UsuarioNoExisteElUsuarioException {
		try {
			setearPerfilUsuarioUno();
			setListaDeUsuariosDos();
        } catch (UsuarioNoExisteElUsuarioException e) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error inesperado", "Seguir Usuario", JOptionPane.ERROR_MESSAGE);
        }
	}
	
	private boolean validarSelecciones() {
		if (cmbUsuarioUno.getSelectedItem().toString().equals(TEXTO_COMBO_UNO) || 
				cmbUsuarioDos.getSelectedItem().toString().equals(TEXTO_COMBO_DOS))  {
            JOptionPane.showMessageDialog(this, "Debe seleccionar dos usuarios", "Seguir Usuario",
                    JOptionPane.ERROR_MESSAGE);
			return false;
		} else {
			return true;
		}
	}
	
	public void setListaDeUsuariosUno() {
		cmbUsuarioUno.removeAllItems();
        DtUsuario[] usuarios = iUsuarioController.listarUsuarios();
        if (usuarios != null) {
            for (int i = 0; i < usuarios.length; i++) {
            	cmbUsuarioUno.addItem(usuarios[i].getNickname());
            }
        } else {
        	cmbUsuarioUno.addItem(TEXTO_COMBO_UNO);
        }
	}
	
	public void setListaDeUsuariosDos() {
		cmbUsuarioDos.removeAllItems();
        DtUsuario[] usuarios = iUsuarioController.listarUsuarios();
        if (usuarios.length > 1) {
            for (int i = 0; i < usuarios.length; i++) {
            	if (!usuarios[i].getNickname().equals(cmbUsuarioUno.getSelectedItem().toString())) {
            		cmbUsuarioDos.addItem(usuarios[i].getNickname());
            	}
            }
        } else {
        	cmbUsuarioDos.removeAllItems();
        	cmbUsuarioDos.addItem(TEXTO_COMBO_DOS);
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
			txtFechaDeNacimientoUno.setText(dtProponente.getFechaNacimiento().toString());
			txtRolUno.setText("Proponente");
		} else if (dtUsuario instanceof DtColaborador) {
			DtColaborador dtColaborador = (DtColaborador) dtUsuario;
			txtNicknameUno.setText(dtColaborador.getNickname());
			txtNombreUno.setText(dtColaborador.getNombre());
			txtApellidoUno.setText(dtColaborador.getApellido());
			txtEmailUno.setText(dtColaborador.getEmail());
			txtFechaDeNacimientoUno.setText(dtColaborador.getFechaNacimiento().toString());
			txtRolUno.setText("Colaborador");
		}
	}
	
	private void setearPerfilUsuarioDos() throws UsuarioNoExisteElUsuarioException {
		DtUsuario dtUsuario = iUsuarioController.verPerfilUsuario(cmbUsuarioDos.getSelectedItem().toString());
		if (dtUsuario instanceof DtProponente) {
			DtProponente dtProponente = (DtProponente) dtUsuario;
			txtNicknameDos.setText(dtProponente.getNickname());
			txtNombreDos.setText(dtProponente.getNombre());
			txtApellidoDos.setText(dtProponente.getApellido());
			txtEmailDos.setText(dtProponente.getEmail());
			txtFechaDeNacimientoDos.setText(dtProponente.getFechaNacimiento().toString());
			txtRolDos.setText("Proponente");
		} else if (dtUsuario instanceof DtColaborador) {
			DtColaborador dtColaborador = (DtColaborador) dtUsuario;
			txtNicknameDos.setText(dtColaborador.getNickname());
			txtNombreDos.setText(dtColaborador.getNombre());
			txtApellidoDos.setText(dtColaborador.getApellido());
			txtEmailDos.setText(dtColaborador.getEmail());
			txtFechaDeNacimientoDos.setText(dtColaborador.getFechaNacimiento().toString());
			txtRolDos.setText("Colaborador");
		}
	}
	
    private void limpiarFormulario() {
        txtNicknameUno.setText("");
        txtNombreUno.setText("");
        txtApellidoUno.setText("");
        txtEmailUno.setText("");
        txtFechaDeNacimientoUno.setText("");
        txtRolUno.setText("");
        txtNicknameDos.setText("");
        txtNombreDos.setText("");
        txtApellidoDos.setText("");
        txtEmailDos.setText("");
        txtFechaDeNacimientoDos.setText("");
        txtRolDos.setText("");
    }
}
