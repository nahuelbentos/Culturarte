package presentacion;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AgregarUsuario extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField inputNickname;

	/**
	 * Create the panel.
	 */
	public AgregarUsuario() {
		setLayout(null);
		
		JLabel lblNickname = new JLabel("Nickname");
		lblNickname.setBounds(29, 42, 68, 15);
		add(lblNickname);
		
		inputNickname = new JTextField();
		inputNickname.setBounds(116, 40, 114, 19);
		add(inputNickname);
		inputNickname.setColumns(10);

	}
}
