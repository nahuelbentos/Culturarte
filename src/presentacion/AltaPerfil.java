package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class AltaPerfil extends JInternalFrame {

	private JTextField txtNickname;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtEmail;
	JRadioButton rdbtnProponente;
	JRadioButton rdbtnColaborador;
	private JFileChooser fileChooser = new JFileChooser();
	private ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Create the frame.
	 */
	public AltaPerfil() {
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Registrar un Usuario");
        setBounds(10, 40, 417, 351);
		
        getContentPane().setLayout(null);
		
		JLabel lblNickname = new JLabel("Nickname:");
		lblNickname.setBounds(65, 46, 80, 14);
		getContentPane().add(lblNickname);
		
		JLabel lblIngreseLosSiguientes = new JLabel("Ingrese los siguientes datos");
		lblIngreseLosSiguientes.setBounds(96, 11, 193, 14);
		getContentPane().add(lblIngreseLosSiguientes);
		
		txtNickname = new JTextField();
		txtNickname.setBounds(155, 43, 174, 20);
		getContentPane().add(txtNickname);
		txtNickname.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(65, 74, 80, 14);
		getContentPane().add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(155, 71, 174, 20);
		getContentPane().add(txtNombre);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(65, 102, 80, 14);
		getContentPane().add(lblApellido);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(155, 99, 174, 20);
		getContentPane().add(txtApellido);
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(65, 130, 80, 14);
		getContentPane().add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(155, 127, 174, 20);
		getContentPane().add(txtEmail);
		
		rdbtnProponente = new JRadioButton("Proponente");
		buttonGroup.add(rdbtnProponente);
		rdbtnProponente.setBounds(116, 172, 91, 23);
		getContentPane().add(rdbtnProponente);
		
		rdbtnColaborador = new JRadioButton("Colaborador");
		buttonGroup.add(rdbtnColaborador);
		rdbtnColaborador.setBounds(214, 172, 109, 23);
		getContentPane().add(rdbtnColaborador);
		JButton btnSeleecionarImagen = new JButton("Seleecionar Imagen");
		btnSeleecionarImagen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileChooser.showOpenDialog(getContentPane());
			}
		});
		btnSeleecionarImagen.setBounds(96, 214, 233, 23);
		getContentPane().add(btnSeleecionarImagen);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(94, 263, 99, 23);
		getContentPane().add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(230, 263, 99, 23);
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limpiarFormulario();
                setVisible(false);
            }
        });
		getContentPane().add(btnCancelar);
	}
	
    private void limpiarFormulario() {
        txtNickname.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtEmail.setText("");
        rdbtnProponente.setSelected(false);
        rdbtnColaborador.setSelected(false);
    }

}
