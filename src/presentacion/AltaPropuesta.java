package presentacion;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class AltaPropuesta extends JInternalFrame {
	private JTextField textField;

	/**
	 * Create the frame.
	 */
	public AltaPropuesta() {
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Alta de Propuesta");
        setBounds(10, 40, 417, 351);
		
        getContentPane().setLayout(null);
        
        JLabel lblTitulo = new JLabel("Titulo");
        lblTitulo.setBounds(47, 31, 70, 15);
        getContentPane().add(lblTitulo);
        
        textField = new JTextField();
        textField.setBounds(162, 29, 114, 19);
        getContentPane().add(textField);
        textField.setColumns(10);
        
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(145, 258, 117, 25);
        getContentPane().add(btnGuardar);
		
	}
	
}
