package presentacion;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import excepciones.PropuestaNoExisteException;
import excepciones.UsuarioNoExisteElUsuarioException;
import logica.IPropuestaController;
import presentacion.gen.ListarPropuestas;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

public class ConsultaPropuestaPorEstado extends JInternalFrame {

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
	public ConsultaPropuestaPorEstado(IPropuestaController IPU) throws UsuarioNoExisteElUsuarioException, PropuestaNoExisteException, PropertyVetoException {
		setResizable(true);
		setMaximizable(true);
		setClosable(true);
		setBounds(100, 100, 946, 468);
		getContentPane().setLayout(null);
		
		JPanel panelListarPropuesta = new JPanel();
		panelListarPropuesta.setBorder(new TitledBorder(null, "Propuestas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelListarPropuesta.setBounds(10, 11, 312, 261);
		getContentPane().add(panelListarPropuesta);		
		panelListarPropuesta.setLayout(null);
        
        listarPropuestas = new ListarPropuestas(IPU);
        listarPropuestas.actualizarPropuestas();
        listarPropuestas.setBounds(10, 26, 290, 224);
        panelListarPropuesta.add(listarPropuestas);
        
        JButton btnVerPropuesta = new JButton("Ver Propuesta");
        btnVerPropuesta.setBounds(183, 284, 139, 25);
        getContentPane().add(btnVerPropuesta);
        
        JPanel panelConsultarPropuesta = new JPanel();
        panelConsultarPropuesta.setBorder(new TitledBorder(null, "Propuesta seleccionada", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelConsultarPropuesta.setBounds(331, 11, 589, 417);
        getContentPane().add(panelConsultarPropuesta);
        panelConsultarPropuesta.setLayout(null);
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
        			e.printStackTrace();
        		} catch (PropuestaNoExisteException e) {
        			e.printStackTrace();
        		}

        	}
        });
        
        
        if(listarPropuestas.getPropuestaTituloSeleccionada() != null)
        	consultarPropuesta = new ConsultarPropuesta(IPU, listarPropuestas.getPropuestaTituloSeleccionada());
		else 
			if(!(listarPropuestas.getPrimerTitulo().isEmpty()))
				consultarPropuesta = new ConsultarPropuesta(IPU, listarPropuestas.getPrimerTitulo());
		

	}
}