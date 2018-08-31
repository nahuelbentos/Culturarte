package presentacion;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import logica.IPropuestaController;
import logica.IUsuarioController;
import logica.exceptions.ColaboradorNoExisteException;
import logica.exceptions.PropuestaNoExisteException;
import presentacion.gen.ListarPropuestas;
import presentacion.gen.PropuestaSeleccionada;
import javax.swing.JLabel;

import datatype.DtColaboracion;
import datatype.DtPropuesta;
import datatype.DtUsuario;
import datatype.TipoRetorno;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class RegistrarColaboracion extends JInternalFrame {
	
	private static final String TEXTO_COMBO_UNO = "No hay usuarios registrados en el sistema";
	
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
	private JComboBox<String> comboBoxColaborador;
	
	private IPropuestaController iPropCon;
	private IUsuarioController iUsuCon;
	
	/**
	 * Create the frame.
	 */
	public RegistrarColaboracion(IPropuestaController IPU, IUsuarioController IUC) {
		setBounds(100, 100, 800, 600);
		
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
		propSeleccionada.setSize(256, 276);
		propSeleccionada.setLocation(288, 60);
		propSeleccionada.setVisible(false);
		getContentPane().add(propSeleccionada);
		{
			lblPropuestasDelSistema = new JLabel("Propuestas del sistema");
			lblPropuestasDelSistema.setBounds(10, 19, 268, 30);
			getContentPane().add(lblPropuestasDelSistema);
		}
		{
			btnSeleccionarPropuesta = new JButton("Seleccionar Propuesta");
			btnSeleccionarPropuesta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					propuesta = grillaPropuestas.getPropuestaSeleccionada();
					propSeleccionada.setPropuesta(propuesta);
					propSeleccionada.setVisible(true);
					
					panelColaboracion.setVisible(true);
					System.out.println("En el jframe veo el proponente: "+propuesta.getTipo());
				}
			});
			btnSeleccionarPropuesta.setBounds(122, 315, 156, 23);
			getContentPane().add(btnSeleccionarPropuesta);
		}
		{
			panelColaboracion = new JPanel();
			panelColaboracion.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, Color.GRAY));
			panelColaboracion.setBounds(288, 347, 256, 199);
			panelColaboracion.setVisible(false);
			getContentPane().add(panelColaboracion);
			panelColaboracion.setLayout(null);
			{
				lblMontoAAportar = new JLabel("Monto a aportar:");
				lblMontoAAportar.setBounds(10, 11, 97, 14);
				panelColaboracion.add(lblMontoAAportar);
			}

			{
				txtMonto = new JTextField();
				txtMonto.setBounds(149, 8, 97, 20);
				panelColaboracion.add(txtMonto);
				txtMonto.setColumns(10);
			}
			{
				lblTipoRetorno = new JLabel("Tipo Retorno:");
				lblTipoRetorno.setBounds(10, 36, 97, 14);
				panelColaboracion.add(lblTipoRetorno);
			}
			{
				lblColaborador = new JLabel("Colaborador:");
				lblColaborador.setBounds(10, 61, 97, 14);
				panelColaboracion.add(lblColaborador);
			}
			{
				comboBoxColaborador = new JComboBox<String>();
				comboBoxColaborador.setBounds(149, 58, 97, 20);
				panelColaboracion.add(comboBoxColaborador);
				comboBoxColaborador.addItemListener(new ItemListener() {
					
					@Override
					public void itemStateChanged(ItemEvent e) {
						if ((e.getStateChange() == ItemEvent.SELECTED)) {
			                Object selected = comboBoxColaborador.getSelectedItem();
			                if (!selected.toString().equals(TEXTO_COMBO_UNO)) {
			                	actualizarDatos(e);
			                }
						}
					}
				});
			}
			
			{
				btnAgregar = new JButton("Agregar");
				btnAgregar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						DtColaboracion colaboracion = new DtColaboracion(propuesta.getTitulo(), "cande", Double.parseDouble(txtMonto.getText()), new GregorianCalendar(2018,8,1), TipoRetorno.entradasGratis);
						
						try {
							iPropCon.generarColaboracion(colaboracion);
						} catch (ColaboradorNoExisteException | PropuestaNoExisteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
				});
				btnAgregar.setBounds(157, 165, 89, 23);
				panelColaboracion.add(btnAgregar);
			}
		}
	}
	
	protected void actualizarDatos(ItemEvent arg0) {
		setListaDeColaboradores();
	}
	
	private void setListaDeColaboradores() {
		comboBoxColaborador.removeAllItems();
        DtUsuario[] usuarios = iUsuCon.listarUsuarios();
        if (usuarios.length > 1) {
            for (int i = 0; i < usuarios.length; i++) {
            	if (!usuarios[i].getNickname().equals(comboBoxColaborador.getSelectedItem().toString())) {
            		comboBoxColaborador.addItem(usuarios[i].getNickname());
            	}
            }
        } else {
        	comboBoxColaborador.removeAllItems();
        	comboBoxColaborador.addItem(TEXTO_COMBO_UNO);
        }
	}
}
