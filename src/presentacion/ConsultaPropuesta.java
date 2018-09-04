package presentacion;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import datatype.DtPropuesta;
import excepciones.PropuestaNoExisteException;
import excepciones.UsuarioNoExisteElUsuarioException;
import logica.IPropuestaController;
import presentacion.gen.ListarColaboradores;
import presentacion.gen.ListarPropuestas;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.awt.event.ActionEvent;

public class ConsultaPropuesta extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	private IPropuestaController iPropCon;
	private ListarPropuestas listarPropuestas;
	private ConsultarPropuesta consultarPropuesta;
	
	/**
	 * Create the frame.
	 * @throws PropuestaNoExisteException 
	 * @throws UsuarioNoExisteElUsuarioException 
	 * @throws PropertyVetoException 
	 */
	public ConsultaPropuesta(IPropuestaController IPU) throws UsuarioNoExisteElUsuarioException, PropuestaNoExisteException, PropertyVetoException {
		setResizable(true);
		setMaximizable(true);
		setClosable(true);
		setBounds(100, 100, 768, 733);
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
        panelConsultarPropuesta.setBounds(10, 162, 713, 478);
        getContentPane().add(panelConsultarPropuesta);
        panelConsultarPropuesta.setLayout(null);
        
        JButton btnVerPropuesta = new JButton("Ver Propuesta");
        btnVerPropuesta.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		try {
        			consultarPropuesta.removeAll();
        			System.out.println("Propuesta titulo seleccionada: "  + listarPropuestas.getPropuestaTituloSeleccionada());
        			consultarPropuesta = new ConsultarPropuesta(IPU, listarPropuestas.getPropuestaTituloSeleccionada());
					consultarPropuesta.setBounds(0, 25, 500, 355);
			        panelConsultarPropuesta.add(consultarPropuesta);
			        
			        getContentPane().add(panelConsultarPropuesta);
			        panelConsultarPropuesta.setLayout(null);
			        
				} catch (UsuarioNoExisteElUsuarioException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (PropuestaNoExisteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		
        	}
        });
        btnVerPropuesta.setBounds(312, 125, 139, 25);
        getContentPane().add(btnVerPropuesta);
        
        
        if(listarPropuestas.getPropuestaTituloSeleccionada() != null)
        	consultarPropuesta = new ConsultarPropuesta(IPU, listarPropuestas.getPropuestaTituloSeleccionada());
		else 
			if(listarPropuestas.getPrimerTitulo()!=null)
				consultarPropuesta = new ConsultarPropuesta(IPU, listarPropuestas.getPrimerTitulo());
		

	}
}
