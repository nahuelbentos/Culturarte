package presentacion;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import logica.IUsuarioController;

import java.awt.BorderLayout;

@SuppressWarnings("serial")
public class ConsultaColaboracionPropuesta extends JInternalFrame {
	private JPanel panelListaColaboradores;

	/**
	 * Create the frame.
	 */
	public ConsultaColaboracionPropuesta(IUsuarioController IUC) {
		setBounds(100, 100, 450, 300);
		{
			panelListaColaboradores = new ListarColaboradores(IUC);
			getContentPane().add(panelListaColaboradores, BorderLayout.CENTER);
		}

	}

}
