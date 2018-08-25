package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JInternalFrame;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import datatype.DtUsuario;
import excepciones.UsuarioYaSigueAlUsuarioException;
import logica.IUsuarioController;
import javax.swing.JButton;

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
		cmbUsuarioUno.setBounds(41, 135, 163, 20);
		cmbUsuarioUno.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Object selected = cmbUsuarioUno.getSelectedItem();
                if (!selected.toString().equals(TEXTO_COMBO_UNO)) {
                	setListaDeUsuariosDos();
                }
            }
        });
		getContentPane().add(cmbUsuarioUno);
		
		cmbUsuarioDos = new JComboBox<String>();
		cmbUsuarioDos.setBounds(312, 135, 163, 20);
		getContentPane().add(cmbUsuarioDos);
		cmbUsuarioDos.addItem("Seleccione un Usuario");
		
		lblSeleecioneElUsuario = new JLabel("<html>Seleecione el usuario <br/>que "
				+ "realizar el seguimiento:</html>");
		lblSeleecioneElUsuario.setBounds(41, 55, 197, 69);
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
		btnAceptar.setBounds(124, 365, 89, 23);
		getContentPane().add(btnAceptar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(308, 365, 89, 23);
		getContentPane().add(btnCancelar);

	}
	
	protected void seguirUsuarioActionPerformed(ActionEvent arg0) {
		if (validarSelecciones()) {
			try {
				iUsuarioController.seguirUsuario(cmbUsuarioUno.getSelectedItem().toString(), 
						cmbUsuarioDos.getSelectedItem().toString());
            } catch (UsuarioYaSigueAlUsuarioException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Seguir Usuario", JOptionPane.ERROR_MESSAGE);
            }
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
            	cmbUsuarioDos.addItem(usuarios[i].getNickname());
            }
            cmbUsuarioDos.removeItem(cmbUsuarioUno.getSelectedItem());
        } else {
        	cmbUsuarioDos.removeAllItems();
        	cmbUsuarioDos.addItem(TEXTO_COMBO_DOS);
        }
	}
}
