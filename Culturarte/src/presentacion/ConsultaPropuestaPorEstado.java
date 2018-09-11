package presentacion;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import excepciones.PropuestaNoExisteException;
import excepciones.UsuarioNoExisteElUsuarioException;
import logica.IPropuestaController;
import presentacion.gen.ListarPropuestasPorEstado;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

import datatype.DtColaboracion;
import datatype.DtPropuesta;


@SuppressWarnings("serial")
public class ConsultaPropuestaPorEstado extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	private ListarPropuestasPorEstado listarPropuestas;
	private ConsultaDePropuesta panelConsultaDePropuesta;
	private JPanel panelConsultarPropuesta = new JPanel();
	
	
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
		setBounds(100, 100, 1100, 650);
		getContentPane().setLayout(null);
		
		JPanel panelListarPropuesta = new JPanel();
		panelListarPropuesta.setBorder(new TitledBorder(null, "Propuestas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelListarPropuesta.setBounds(10, 11, 312, 552);
		getContentPane().add(panelListarPropuesta);		
		panelListarPropuesta.setLayout(null);
        
        listarPropuestas = new ListarPropuestasPorEstado(IPU);
        listarPropuestas.actualizarPropuestas();
        listarPropuestas.setBounds(10, 26, 290, 514);
        panelListarPropuesta.add(listarPropuestas);
        
        JButton btnVerPropuesta = new JButton("Ver Propuesta");
        btnVerPropuesta.setBounds(183, 575, 139, 25);
        getContentPane().add(btnVerPropuesta);
        
        
        panelConsultarPropuesta.setBorder(new TitledBorder(null, "Propuesta seleccionada", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelConsultarPropuesta.setBounds(331, 11, 749, 589);
        getContentPane().add(panelConsultarPropuesta);
        panelConsultarPropuesta.setLayout(null);
        /* Cargo el panel ConsultaDePropuesta*/
        panelConsultaDePropuesta = new ConsultaDePropuesta();
        panelConsultaDePropuesta.setSize(721, 550);
        panelConsultaDePropuesta.setLocation(12, 22);
        panelConsultarPropuesta.add(panelConsultaDePropuesta);
        
        
        btnVerPropuesta.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		cargarPropuestaSeleccionada(IPU);

        	}
        });
	}
	
	public void cargarPropuestaSeleccionada(IPropuestaController IPU) {
		System.out.println("cargarPropuestaSeleccionada");
		if (listarPropuestas.getPropuestaSeleccionada() != null) {
			DtPropuesta dtP = listarPropuestas.getPropuestaSeleccionada();
			DtColaboracion[] colabs = IPU.listarColaboraciones(dtP.getTitulo());
			panelConsultaDePropuesta.setPropuesta(dtP, colabs);
			panelConsultaDePropuesta.setVisible(true);
			panelConsultarPropuesta.setVisible(true);
		}else
			JOptionPane.showMessageDialog(this, "Debe seleccionar una propuesta", "Consulta propuesta", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void refreshFrame() {
		listarPropuestas.actualizarPropuestas();
	}
}
