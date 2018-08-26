package presentacion;

import javax.swing.JInternalFrame;

import logica.IUsuarioController;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ConsultaColaboracionPropuesta extends JInternalFrame {
	
	private ListarColaboradores panel;
	/**
	 * Create the frame.
	 */
	public ConsultaColaboracionPropuesta(IUsuarioController IUC) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		panel = new ListarColaboradores(IUC);
		panel.setBounds(12, 12, 416, 99);
		getContentPane().add(panel);

	}
}
