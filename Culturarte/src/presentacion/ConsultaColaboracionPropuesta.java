package presentacion;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import logica.IUsuarioController;
import presentacion.gen.ListarColaboradores;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

import datatype.DtColaboracion;
import datatype.DtPropuesta;
import excepciones.ColaboracionNoExisteException;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.ListSelectionModel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ConsultaColaboracionPropuesta extends JInternalFrame {
	
	// Objetos visuales
	private ListarColaboradores panelColaboradores;
	private JButton btnVerColaboraciones;
	private JPanel panelColaborador;
	private JList<String> listPropuestas;
	private DefaultListModel<String> modelPropuestas;
	private JButton btnVerInfo;
	private JPanel panelInfoColaboracion;
	private JLabel lblMonto;
	private JLabel lblFechaAportado;
	private JLabel lblTipoRetorno;
	private JTextField txtMonto;
	private JTextField txtFchAportado;
	private JTextField txtTipoRet;
	
	private IUsuarioController iUsuCont;
	private String colSeleccionado;
	private String propSel;
	private DtPropuesta[] propColaboradas;
	private DtColaboracion colaboracionInfo;
	
	/**
	 * Create the frame.
	 */
	public ConsultaColaboracionPropuesta(IUsuarioController IUC) {
		
		iUsuCont = IUC;
		
		setBounds(50, 50, 1000, 800);
		getContentPane().setLayout(null);
		
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setClosable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		panelColaboradores = new ListarColaboradores(iUsuCont);
		panelColaboradores.setBounds(12, 12, 250, 270);
		getContentPane().add(panelColaboradores);
		
		{
			btnVerColaboraciones = new JButton("Ver colaboraciones");
			btnVerColaboraciones.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setPanelColaborador(); 
				}
			});
			btnVerColaboraciones.setBounds(83, 294, 179, 25);
			getContentPane().add(btnVerColaboraciones);
		}
		{
			panelColaborador = new JPanel();
			panelColaborador.setBorder(new TitledBorder(null, "Propuestas Colaboradas por [colaborador]", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelColaborador.setBounds(274, 12, 569, 330);
			panelColaborador.setVisible(false);
			getContentPane().add(panelColaborador);
			panelColaborador.setLayout(null);
			{
				modelPropuestas = new DefaultListModel<String>();
				listPropuestas = new JList<String>(modelPropuestas);
				listPropuestas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				listPropuestas.setBorder(new LineBorder(Color.DARK_GRAY));
				listPropuestas.setBounds(22, 51, 162, 199);
				panelColaborador.add(listPropuestas);
			}
			{
				btnVerInfo = new JButton("Ver info");
				btnVerInfo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setInfoColaboracion();
					}
				});
				btnVerInfo.setBounds(67, 262, 117, 25);
				panelColaborador.add(btnVerInfo);
			}
			{
				panelInfoColaboracion = new JPanel();
				panelInfoColaboracion.setBorder(new TitledBorder(null, "Información de colaboración", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panelInfoColaboracion.setBounds(196, 44, 361, 146);
				panelInfoColaboracion.setVisible(false);
				panelColaborador.add(panelInfoColaboracion);
				GridBagLayout gbl_panelInfoColaboracion = new GridBagLayout();
				gbl_panelInfoColaboracion.columnWidths = new int[] {21, 0, 105, 1};
				gbl_panelInfoColaboracion.rowHeights = new int[] {0, 0, 0, 0, 3};
				gbl_panelInfoColaboracion.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
				gbl_panelInfoColaboracion.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
				panelInfoColaboracion.setLayout(gbl_panelInfoColaboracion);
				{
					lblMonto = new JLabel("Monto:");
					GridBagConstraints gbc_lblMonto = new GridBagConstraints();
					gbc_lblMonto.anchor = GridBagConstraints.EAST;
					gbc_lblMonto.insets = new Insets(0, 0, 5, 5);
					gbc_lblMonto.gridx = 1;
					gbc_lblMonto.gridy = 1;
					panelInfoColaboracion.add(lblMonto, gbc_lblMonto);
				}
				{
					txtMonto = new JTextField();
					txtMonto.setEditable(false);
					txtMonto.setEnabled(false);
					GridBagConstraints gbc_txtMonto = new GridBagConstraints();
					gbc_txtMonto.fill = GridBagConstraints.HORIZONTAL;
					gbc_txtMonto.insets = new Insets(0, 0, 5, 0);
					gbc_txtMonto.gridx = 2;
					gbc_txtMonto.gridy = 1;
					panelInfoColaboracion.add(txtMonto, gbc_txtMonto);
					txtMonto.setColumns(10);
				}
				{
					lblFechaAportado = new JLabel("Fecha aportado:");
					GridBagConstraints gbc_lblFechaAportado = new GridBagConstraints();
					gbc_lblFechaAportado.anchor = GridBagConstraints.EAST;
					gbc_lblFechaAportado.insets = new Insets(0, 0, 5, 5);
					gbc_lblFechaAportado.gridx = 1;
					gbc_lblFechaAportado.gridy = 2;
					panelInfoColaboracion.add(lblFechaAportado, gbc_lblFechaAportado);
				}
				{
					txtFchAportado = new JTextField();
					txtFchAportado.setEditable(false);
					txtFchAportado.setEnabled(false);
					GridBagConstraints gbc_txtFchAportado = new GridBagConstraints();
					gbc_txtFchAportado.fill = GridBagConstraints.HORIZONTAL;
					gbc_txtFchAportado.insets = new Insets(0, 0, 5, 0);
					gbc_txtFchAportado.gridx = 2;
					gbc_txtFchAportado.gridy = 2;
					panelInfoColaboracion.add(txtFchAportado, gbc_txtFchAportado);
					txtFchAportado.setColumns(10);
				}
				{
					lblTipoRetorno = new JLabel("Tipo retorno:");
					GridBagConstraints gbc_lblTipoRetorno = new GridBagConstraints();
					gbc_lblTipoRetorno.insets = new Insets(0, 0, 0, 5);
					gbc_lblTipoRetorno.anchor = GridBagConstraints.EAST;
					gbc_lblTipoRetorno.gridx = 1;
					gbc_lblTipoRetorno.gridy = 3;
					panelInfoColaboracion.add(lblTipoRetorno, gbc_lblTipoRetorno);
				}
				{
					txtTipoRet = new JTextField();
					txtTipoRet.setEditable(false);
					txtTipoRet.setEnabled(false);
					GridBagConstraints gbc_txtTipoRet = new GridBagConstraints();
					gbc_txtTipoRet.fill = GridBagConstraints.HORIZONTAL;
					gbc_txtTipoRet.gridx = 2;
					gbc_txtTipoRet.gridy = 3;
					panelInfoColaboracion.add(txtTipoRet, gbc_txtTipoRet);
					txtTipoRet.setColumns(10);
				}
			}
		}

	}
	
	public void setListarColaboradores() {
		panelColaboradores.actualizarColaboradores();
	}
	
	private void setPanelColaborador() {
		colSeleccionado = panelColaboradores.getColaboradorSeleccionado();
		propColaboradas = iUsuCont.listarPropuestasDeUnColaborador(colSeleccionado);
		panelColaborador.setBorder(new TitledBorder(null, "Propuestas Colaboradas por "+colSeleccionado, TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelColaborador.setVisible(true);
		setListaPropuestas();
	}
	
	private void setListaPropuestas() {
		modelPropuestas.removeAllElements();
		for (int i = 0; i < propColaboradas.length; i++) {
			modelPropuestas.addElement(propColaboradas[i].getTitulo());
		}
	}
	
	private void setInfoColaboracion() {
		propSel = listPropuestas.getSelectedValue();
		panelInfoColaboracion.setVisible(true);
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
		
		try {
			colaboracionInfo = iUsuCont.listarColaboracion(propSel, colSeleccionado);
			txtMonto.setText(Double.toString(colaboracionInfo.getMonto()));
			txtFchAportado.setText(sdf.format(colaboracionInfo.getFechaAporte().getTime()));
		} catch (ColaboracionNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
