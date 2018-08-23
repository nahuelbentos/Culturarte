package presentacion;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class AgregarPropuesta extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgregarPropuesta frame = new AgregarPropuesta();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AgregarPropuesta() {
		setBounds(100, 100, 450, 300);

	}

}
