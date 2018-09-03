package presentacion;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import logica.IPropuestaController;
import presentacion.gen.ListarColaboradores;
import presentacion.gen.ListarPropuestas;

public class ConsultaPropuesta extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	private IPropuestaController iPropCon;
	private ListarPropuestas listarPropuestas;	
	
	/**
	 * Create the frame.
	 */
	public ConsultaPropuesta(IPropuestaController IPU) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JPanel panelListarPropuesta = new JPanel();
		panelListarPropuesta.setBounds(0, 0, 300, 150);
		getContentPane().add(panelListarPropuesta);		
		panelListarPropuesta.setLayout(null);
        
        listarPropuestas = new ListarPropuestas(IPU);
        listarPropuestas.actualizarPropuestas();
        listarPropuestas.setBounds(0, 0, 400, 250);
        panelListarPropuesta.add(listarPropuestas);
        
        JPanel panelConsultarPropuesta = new JPanel();
        panelConsultarPropuesta.setBounds(103, 226, 10, 10);
        getContentPane().add(panelConsultarPropuesta);

	}
}
