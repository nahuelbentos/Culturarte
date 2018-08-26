package presentacion;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class AltaPropuesta extends JInternalFrame {

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
		
	}
	
}
