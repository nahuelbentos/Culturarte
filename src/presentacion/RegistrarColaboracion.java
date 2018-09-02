package presentacion;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import logica.IPropuestaController;
import logica.IUsuarioController;
import presentacion.gen.ListarPropuestas;
import presentacion.gen.ListarPropuestasProponente;
import presentacion.gen.PropuestaSeleccionada;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import datatype.DtColaboracion;
import datatype.DtPropuesta;
import datatype.DtUsuario;
import datatype.TipoRetorno;
import excepciones.ColaboradorNoExisteException;
import excepciones.PropuestaNoExisteException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class RegistrarColaboracion extends JInternalFrame {
	
	private ListarPropuestas grillaPropuestas;
	private PropuestaSeleccionada propSeleccionada;
	private JLabel lblPropuestasDelSistema;
	private JButton btnSeleccionarPropuesta;
	
	private DtPropuesta propuesta;
	private JPanel panelColaboracion;
	private JLabel lblMontoAAportar;
	private JButton btnAgregar;
	private JTextField txtMonto;
	private JLabel lblTipoRetorno;
	private JLabel lblColaborador;
	private JComboBox<String> cbColaborador;
	
	private IPropuestaController iPropCon;
	private IUsuarioController iUsuCon;
	private JComboBox comboBox;
	
	/**
	 * Create the frame.
	 */
	public RegistrarColaboracion(IPropuestaController IPU, IUsuarioController IUC) {
		setBounds(100, 10, 800, 600);
		
		iPropCon = IPU;
		iUsuCon = IUC;
		
		getContentPane().setLayout(null);
		
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setClosable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		grillaPropuestas = new ListarPropuestas(iPropCon);
		grillaPropuestas.setBounds(10, 60, 268, 244);
		getContentPane().add(grillaPropuestas);
		
		propSeleccionada = new PropuestaSeleccionada();
		propSeleccionada.setSize(433, 276);
		propSeleccionada.setLocation(288, 60);
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
				
				panelColaboracion.setVisible(true);
			}
		});
		btnSeleccionarPropuesta.setBounds(122, 315, 156, 23);
		getContentPane().add(btnSeleccionarPropuesta);
		
		
		panelColaboracion = new JPanel();
		panelColaboracion.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, Color.GRAY));
		panelColaboracion.setBounds(288, 347, 256, 123);
		panelColaboracion.setVisible(false);
		
		getContentPane().add(panelColaboracion);
		panelColaboracion.setLayout(null);
		
		lblMontoAAportar = new JLabel("Monto a aportar:");
		lblMontoAAportar.setBounds(10, 11, 121, 14);
		panelColaboracion.add(lblMontoAAportar);
		
		txtMonto = new JTextField();
		txtMonto.setBounds(149, 8, 97, 20);
		panelColaboracion.add(txtMonto);
		txtMonto.setColumns(10);
	
		lblTipoRetorno = new JLabel("Tipo Retorno:");
		lblTipoRetorno.setBounds(10, 37, 121, 14);
		panelColaboracion.add(lblTipoRetorno);
	
		lblColaborador = new JLabel("Colaborador:");
		lblColaborador.setBounds(10, 63, 121, 14);
		panelColaboracion.add(lblColaborador);
	
		cbColaborador = new JComboBox<String>();
		cbColaborador.setBounds(149, 60, 97, 20);
		panelColaboracion.add(cbColaborador);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrarColaboracionActionPerformed(arg0);
				
			}
		});
		btnAgregar.setBounds(125, 89, 121, 23);
		panelColaboracion.add(btnAgregar);
		
		comboBox = new JComboBox();
		comboBox.setBounds(149, 34, 97, 20);
		panelColaboracion.add(comboBox);
			
	}
	
	/**
	 * Metodo que se crea para que al 
	 * recargue todos los datos necesarios del panel.
	 */
	public void refreshFrame() {
		actualizarPropuestas();
		setListaDeColaboradores();
	}
	
	private void setListaDeColaboradores() {
		cbColaborador.removeAllItems();
		DtUsuario[] usuarios;
		try {
			usuarios = iUsuCon.listarColaboradores();
	        for (int i = 0; i < usuarios.length; i++) 
	        	cbColaborador.addItem(usuarios[i].getNickname());
		} catch (ColaboradorNoExisteException e) {
			e.printStackTrace();
		}
	}
	
	private void actualizarPropuestas() {
		grillaPropuestas.actualizarPropuestas();
	}

	protected void registrarColaboracionActionPerformed(ActionEvent arg0) {
		String c = (String)cbColaborador.getSelectedItem();
		DtColaboracion colaboracion = new DtColaboracion(propuesta.getTitulo(), c, Double.parseDouble(txtMonto.getText()), new GregorianCalendar(), TipoRetorno.EntradasGratis);
		
		try {
			iPropCon.generarColaboracion(colaboracion);
			JOptionPane.showMessageDialog(this, "Se genero correctamente la colaboracion de "+c+" para la propuesta "+propuesta.getTitulo(), "Registrar Colaboración", JOptionPane.INFORMATION_MESSAGE);
		} catch (ColaboradorNoExisteException | PropuestaNoExisteException e1) {
			JOptionPane.showMessageDialog(this, e1.getMessage(), "Registrar Colaboración", JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}
	}
}
