package presentacion;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import logica.IPropuestaController;

import presentacion.gen.ListarPropuestasIngresadas;
import presentacion.gen.PropuestaSeleccionada;

import datatype.DtPropuesta;
import datatype.EstadoPropuesta;
import excepciones.PropuestaNoExisteException;

@SuppressWarnings("serial")
public class EvaluarPropuestas extends JInternalFrame {
	
	private ListarPropuestasIngresadas grillaPropuestas;
	private PropuestaSeleccionada propSeleccionada;
	private JLabel lblPropuestasDelSistema;
	private JButton btnSeleccionarPropuesta;
	
	private DtPropuesta propuesta;
	
	private IPropuestaController iPropCon;
	private JButton btnAprobar;
	private JButton btnCancelarPropuesta;
	
	/**
	 * Create the frame.
	 */
	public EvaluarPropuestas(IPropuestaController IPU) {
		setBounds(100, 10, 809, 604);
		
		iPropCon = IPU;
		
		getContentPane().setLayout(null);
		
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setClosable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		grillaPropuestas = new ListarPropuestasIngresadas(iPropCon);
		grillaPropuestas.setBounds(10, 60, 268, 244);
		getContentPane().add(grillaPropuestas);
		
		propSeleccionada = new PropuestaSeleccionada();
		propSeleccionada.setSize(495, 502);
		propSeleccionada.setLocation(288, 27);
		propSeleccionada.setVisible(false);
		getContentPane().add(propSeleccionada);
		
		lblPropuestasDelSistema = new JLabel("Propuestas del sistema");
		lblPropuestasDelSistema.setBounds(10, 19, 268, 30);
		getContentPane().add(lblPropuestasDelSistema);
		
		
		btnSeleccionarPropuesta = new JButton("Seleccionar Propuesta");
		btnSeleccionarPropuesta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				propuesta = grillaPropuestas.getPropuestaSeleccionada();
				propSeleccionada.setPropuesta(propuesta);
				propSeleccionada.setVisible(true);
			}
		});
		btnSeleccionarPropuesta.setBounds(122, 315, 156, 23);
		getContentPane().add(btnSeleccionarPropuesta);
		{
			btnAprobar = new JButton("Aprobar Propuesta");
			btnAprobar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					evaluarPropuesta(EstadoPropuesta.publicada);
				}
			});
			btnAprobar.setBounds(640, 540, 143, 23);
			getContentPane().add(btnAprobar);
		}
		{
			btnCancelarPropuesta = new JButton("Cancelar Propuesta");
			btnCancelarPropuesta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					evaluarPropuesta(EstadoPropuesta.cancelada);
				}
			});
			btnCancelarPropuesta.setBounds(487, 540, 143, 23);
			getContentPane().add(btnCancelarPropuesta);
		}
			
	}
	
	/**
	 * Metodo que se crea para que al 
	 * recargue todos los datos necesarios del panel.
	 */
	public void refreshFrame() {
		actualizarPropuestas();
	}
	
	private void actualizarPropuestas() {
		grillaPropuestas.actualizarPropuestas();
	}
	
	private void evaluarPropuesta(EstadoPropuesta estado) {
		
		try {
			iPropCon.evaluarPropuesta(propuesta.getTitulo(), estado);
			JOptionPane.showMessageDialog(this, "Se actualizo el estado de la propuesta "+propuesta.getTitulo(), this.getTitle(), JOptionPane.INFORMATION_MESSAGE);
			actualizarPropuestas();
			propSeleccionada.setVisible(false);
			
		} catch (PropuestaNoExisteException e1) {
			JOptionPane.showMessageDialog(this, e1.getMessage(), this.getTitle(), JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}
		
	}
}
