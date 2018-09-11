package presentacion;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import logica.IPropuestaController;
import logica.IUsuarioController;
import presentacion.gen.ListarPropuestas;
import presentacion.gen.PropuestaSeleccionada;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import datatype.DtColaboracion;
import datatype.DtPropuesta;
import datatype.DtUsuario;
import datatype.TipoRetorno;
import excepciones.ColaboracionExistenteException;
import excepciones.ColaboradorNoExisteException;
import excepciones.PropuestaNoExisteException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

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
	private JComboBox<TipoRetorno> comboBox;
	
	/**
	 * Create the frame.
	 */
	public RegistrarColaboracion(IPropuestaController IPU, IUsuarioController IUC) {
		setBounds(100, 10, 800, 726);
		
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
		propSeleccionada.setSize(450, 435);
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
		panelColaboracion.setBorder(new TitledBorder(null, "Colaboraci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelColaboracion.setBounds(288, 506, 256, 140);
		panelColaboracion.setVisible(false);
		
		getContentPane().add(panelColaboracion);
		GridBagLayout gbl_panelColaboracion = new GridBagLayout();
		gbl_panelColaboracion.columnWidths = new int[]{100, 0, 107, 0};
		gbl_panelColaboracion.rowHeights = new int[]{0, 0, 0, 39, 0};
		gbl_panelColaboracion.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelColaboracion.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelColaboracion.setLayout(gbl_panelColaboracion);
		
			lblColaborador = new JLabel("Colaborador:");
			GridBagConstraints gbc_lblColaborador = new GridBagConstraints();
			gbc_lblColaborador.anchor = GridBagConstraints.EAST;
			gbc_lblColaborador.insets = new Insets(0, 0, 5, 5);
			gbc_lblColaborador.gridx = 0;
			gbc_lblColaborador.gridy = 0;
			panelColaboracion.add(lblColaborador, gbc_lblColaborador);
			
				cbColaborador = new JComboBox<String>();
				GridBagConstraints gbc_cbColaborador = new GridBagConstraints();
				gbc_cbColaborador.insets = new Insets(0, 0, 5, 0);
				gbc_cbColaborador.fill = GridBagConstraints.HORIZONTAL;
				gbc_cbColaborador.gridx = 2;
				gbc_cbColaborador.gridy = 0;
				panelColaboracion.add(cbColaborador, gbc_cbColaborador);
				
				lblMontoAAportar = new JLabel("Monto a aportar:");
				GridBagConstraints gbc_lblMontoAAportar = new GridBagConstraints();
				gbc_lblMontoAAportar.anchor = GridBagConstraints.EAST;
				gbc_lblMontoAAportar.insets = new Insets(0, 0, 5, 5);
				gbc_lblMontoAAportar.gridx = 0;
				gbc_lblMontoAAportar.gridy = 1;
				panelColaboracion.add(lblMontoAAportar, gbc_lblMontoAAportar);
				
				txtMonto = new JTextField();
				GridBagConstraints gbc_txtMonto = new GridBagConstraints();
				gbc_txtMonto.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtMonto.insets = new Insets(0, 0, 5, 0);
				gbc_txtMonto.gridx = 2;
				gbc_txtMonto.gridy = 1;
				panelColaboracion.add(txtMonto, gbc_txtMonto);
				txtMonto.setColumns(10);
			
				lblTipoRetorno = new JLabel("Tipo Retorno:");
				GridBagConstraints gbc_lblTipoRetorno = new GridBagConstraints();
				gbc_lblTipoRetorno.anchor = GridBagConstraints.EAST;
				gbc_lblTipoRetorno.insets = new Insets(0, 0, 5, 5);
				gbc_lblTipoRetorno.gridx = 0;
				gbc_lblTipoRetorno.gridy = 2;
				panelColaboracion.add(lblTipoRetorno, gbc_lblTipoRetorno);
					
					comboBox = new JComboBox<TipoRetorno>();
					GridBagConstraints gbc_comboBox = new GridBagConstraints();
					gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
					gbc_comboBox.insets = new Insets(0, 0, 5, 0);
					gbc_comboBox.gridx = 2;
					gbc_comboBox.gridy = 2;
					comboBox.setModel(new DefaultComboBoxModel<>(TipoRetorno.values()));
					panelColaboracion.add(comboBox, gbc_comboBox);
					
					btnAgregar = new JButton("Agregar");
					GridBagConstraints gbc_btnAgregar = new GridBagConstraints();
					gbc_btnAgregar.anchor = GridBagConstraints.EAST;
					gbc_btnAgregar.gridx = 2;
					gbc_btnAgregar.gridy = 3;
					panelColaboracion.add(btnAgregar, gbc_btnAgregar);
					btnAgregar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							registrarColaboracionActionPerformed(arg0);
							
						}
					});
			
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
		} catch (ColaboradorNoExisteException | PropuestaNoExisteException | ColaboracionExistenteException e1) {
			JOptionPane.showMessageDialog(this, e1.getMessage(), "Registrar Colaboración", JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}
	}
}
