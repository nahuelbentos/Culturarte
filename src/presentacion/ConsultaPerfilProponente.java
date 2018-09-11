package presentacion;

import javax.swing.JInternalFrame;

import logica.IUsuarioController;
import presentacion.gen.ListarProponentes;
import presentacion.gen.PropuestaSeleccionada;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import datatype.DtPerfilProponente;
import datatype.DtPropuesta;
import datatype.EstadoPropuesta;

import javax.swing.JLabel;

import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

@SuppressWarnings("serial")
public class ConsultaPerfilProponente extends JInternalFrame {

	/**
	 * Create the frame.
	 * @throws UsuarioNoExisteElUsuarioException 
	 */
	private IUsuarioController iUsuCont;
	private String nickname;
	private DtPerfilProponente dtProponente;

	private ListarProponentes listarProponentes;
	
	private JButton btnVerPerfil;
	private JPanel perfilCompleto;
	private JLabel lblImagen;
	private JPanel panelDatos;
	private JLabel lblNickname;
	private JLabel lblNombreCompleto;
	private JLabel lblCorreo;
	private JLabel lblNewLabel;
	private JLabel lblBiografa;
	private JTextField txtApellido;
	private JTextField txtNombre;
	private JTextField txtNickname;
	private JTextField txtCorreo;
	private JTextField txtDireccion;
	private JTextField txtBiografia;
	private JComboBox<Object> cmbEstado;
	private JPanel panelPropuestas;
	private JLabel lblEstado;
	
	// grilla de propuestas.
	private JScrollPane scrollPanePropuestas;
	private JTable tablePropuestas;
	private JButton btnVerInformacin;
	//
	
	public ConsultaPerfilProponente(IUsuarioController IUC) {
		iUsuCont = IUC;
		
		setBounds(0, 0, 743, 570);
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
		setClosable(true);
		setTitle("Consulta Perfil de proponente");
		getContentPane().setLayout(null);
		{
			listarProponentes = new ListarProponentes(iUsuCont);
			listarProponentes.setBounds(10, 11, 176, 248);
			getContentPane().add(listarProponentes);
		}
		{
			btnVerPerfil = new JButton("Ver perfil");
			btnVerPerfil.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					nickname = listarProponentes.getColaboradorSeleccionado();
					if (listarProponentes.getColaboradorSeleccionado() != null) 
						cargarPerfil(nickname);
						
				}
			});
			btnVerPerfil.setBounds(97, 270, 89, 23);
			getContentPane().add(btnVerPerfil);
		}
		{
			perfilCompleto = new JPanel();
			perfilCompleto.setBorder(new TitledBorder(null, "Perfil", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			perfilCompleto.setBounds(196, 11, 520, 501);
			getContentPane().add(perfilCompleto);
			perfilCompleto.setLayout(null);
			{
				lblImagen = new JLabel("");
				lblImagen.setBounds(10, 36, 150, 150);
				perfilCompleto.add(lblImagen);
			}
			{
				panelDatos = new JPanel();
				panelDatos.setBounds(170, 36, 340, 156);
				perfilCompleto.add(panelDatos);
				GridBagLayout gbl_panelDatos = new GridBagLayout();
				gbl_panelDatos.columnWidths = new int[]{0, 0, 0, 0};
				gbl_panelDatos.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
				gbl_panelDatos.columnWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
				gbl_panelDatos.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
				panelDatos.setLayout(gbl_panelDatos);
				{
					lblNickname = new JLabel("Nickname:");
					GridBagConstraints gbc_lblNickname = new GridBagConstraints();
					gbc_lblNickname.anchor = GridBagConstraints.EAST;
					gbc_lblNickname.insets = new Insets(0, 0, 5, 5);
					gbc_lblNickname.gridx = 0;
					gbc_lblNickname.gridy = 0;
					panelDatos.add(lblNickname, gbc_lblNickname);
				}
				{
					txtNickname = new JTextField();
					txtNickname.setEditable(false);
					txtNickname.setEnabled(false);
					txtNickname.setDisabledTextColor(Color.DARK_GRAY);
					GridBagConstraints gbc_txtNickname = new GridBagConstraints();
					gbc_txtNickname.insets = new Insets(0, 0, 5, 5);
					gbc_txtNickname.fill = GridBagConstraints.HORIZONTAL;
					gbc_txtNickname.gridx = 1;
					gbc_txtNickname.gridy = 0;
					panelDatos.add(txtNickname, gbc_txtNickname);
					txtNickname.setColumns(10);
				}
				{
					lblNombreCompleto = new JLabel("Nombre Completo:");
					GridBagConstraints gbc_lblNombreCompleto = new GridBagConstraints();
					gbc_lblNombreCompleto.anchor = GridBagConstraints.EAST;
					gbc_lblNombreCompleto.insets = new Insets(0, 0, 5, 5);
					gbc_lblNombreCompleto.gridx = 0;
					gbc_lblNombreCompleto.gridy = 1;
					panelDatos.add(lblNombreCompleto, gbc_lblNombreCompleto);
				}
				{
					txtNombre = new JTextField();
					txtNombre.setEditable(false);
					txtNombre.setEnabled(false);
					txtNombre.setDisabledTextColor(Color.DARK_GRAY);
					GridBagConstraints gbc_txtNombre = new GridBagConstraints();
					gbc_txtNombre.insets = new Insets(0, 0, 5, 5);
					gbc_txtNombre.fill = GridBagConstraints.HORIZONTAL;
					gbc_txtNombre.gridx = 1;
					gbc_txtNombre.gridy = 1;
					panelDatos.add(txtNombre, gbc_txtNombre);
					txtNombre.setColumns(10);
				}
				{
					txtApellido = new JTextField();
					txtApellido.setEditable(false);
					txtApellido.setEnabled(false);
					txtApellido.setDisabledTextColor(Color.DARK_GRAY);
					GridBagConstraints gbc_txtApellido = new GridBagConstraints();
					gbc_txtApellido.insets = new Insets(0, 0, 5, 0);
					gbc_txtApellido.fill = GridBagConstraints.HORIZONTAL;
					gbc_txtApellido.gridx = 2;
					gbc_txtApellido.gridy = 1;
					panelDatos.add(txtApellido, gbc_txtApellido);
					txtApellido.setColumns(10);
				}
				{
					lblCorreo = new JLabel("Correo:");
					GridBagConstraints gbc_lblCorreo = new GridBagConstraints();
					gbc_lblCorreo.anchor = GridBagConstraints.EAST;
					gbc_lblCorreo.insets = new Insets(0, 0, 5, 5);
					gbc_lblCorreo.gridx = 0;
					gbc_lblCorreo.gridy = 2;
					panelDatos.add(lblCorreo, gbc_lblCorreo);
				}
				{
					txtCorreo = new JTextField();
					txtCorreo.setEditable(false);
					txtCorreo.setEnabled(false);
					txtCorreo.setDisabledTextColor(Color.DARK_GRAY);
					GridBagConstraints gbc_txtCorreo = new GridBagConstraints();
					gbc_txtCorreo.gridwidth = 2;
					gbc_txtCorreo.insets = new Insets(0, 0, 5, 0);
					gbc_txtCorreo.fill = GridBagConstraints.HORIZONTAL;
					gbc_txtCorreo.gridx = 1;
					gbc_txtCorreo.gridy = 2;
					panelDatos.add(txtCorreo, gbc_txtCorreo);
					txtCorreo.setColumns(10);
				}
				{
					lblNewLabel = new JLabel("Direcci\u00F3n:");
					GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
					gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
					gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
					gbc_lblNewLabel.gridx = 0;
					gbc_lblNewLabel.gridy = 3;
					panelDatos.add(lblNewLabel, gbc_lblNewLabel);
				}
				{
					txtDireccion = new JTextField();
					txtDireccion.setEditable(false);
					txtDireccion.setEnabled(false);
					txtDireccion.setDisabledTextColor(Color.DARK_GRAY);
					GridBagConstraints gbc_txtDireccion = new GridBagConstraints();
					gbc_txtDireccion.gridwidth = 2;
					gbc_txtDireccion.insets = new Insets(0, 0, 5, 0);
					gbc_txtDireccion.fill = GridBagConstraints.HORIZONTAL;
					gbc_txtDireccion.gridx = 1;
					gbc_txtDireccion.gridy = 3;
					panelDatos.add(txtDireccion, gbc_txtDireccion);
					txtDireccion.setColumns(10);
				}
				{
					lblBiografa = new JLabel("Biograf\u00EDa:");
					GridBagConstraints gbc_lblBiografa = new GridBagConstraints();
					gbc_lblBiografa.anchor = GridBagConstraints.EAST;
					gbc_lblBiografa.insets = new Insets(0, 0, 5, 5);
					gbc_lblBiografa.gridx = 0;
					gbc_lblBiografa.gridy = 4;
					panelDatos.add(lblBiografa, gbc_lblBiografa);
				}
				{
					txtBiografia = new JTextField();
					txtBiografia.setEditable(false);
					txtBiografia.setEnabled(false);
					txtBiografia.setDisabledTextColor(Color.DARK_GRAY);
					GridBagConstraints gbc_txtBiografia = new GridBagConstraints();
					gbc_txtBiografia.gridheight = 2;
					gbc_txtBiografia.gridwidth = 2;
					gbc_txtBiografia.fill = GridBagConstraints.BOTH;
					gbc_txtBiografia.gridx = 1;
					gbc_txtBiografia.gridy = 4;
					panelDatos.add(txtBiografia, gbc_txtBiografia);
					txtBiografia.setColumns(10);
				}
			}
			{
				initPanelPropuestas(true,EstadoPropuesta.ingresada);
			}
		}
		iUsuCont = IUC;
	}
	
	
	private void cargarPerfil(String nickname) {
		limpiarPanel();
		dtProponente = iUsuCont.verPerfilProponente(nickname);
		if(dtProponente.getImagen() != null) {
			ImageIcon imageIcon = new ImageIcon(dtProponente.getImagen());
			lblImagen.setIcon(imageIcon);
		}
		txtNickname.setText(dtProponente.getNickname());
		txtNombre.setText(dtProponente.getNombre());
		txtApellido.setText(dtProponente.getApellido());
		txtCorreo.setText(dtProponente.getEmail());
		txtDireccion.setText(dtProponente.getDireccion());
		
	}
	
	public void cargarPropuestasPerfil(ArrayList<DtPropuesta> propuestas) {
		
		// borro la grilla que estaba.
		panelPropuestas.remove(scrollPanePropuestas);
		scrollPanePropuestas.removeAll();
		tablePropuestas.removeAll();
		
		DefaultTableModel tableModel = new DefaultTableModel();
		tableModel.addColumn("Titulo:");
		tableModel.addColumn("Monto necesario");
		for (DtPropuesta dtPropuesta : propuestas) {
			tableModel.addRow(new String[] {dtPropuesta.getTitulo(), Float.toString(dtPropuesta.getMontoNecesario())});
		}
		tablePropuestas.setModel(tableModel);
		
		
	}
	
	private void initTableGrilla(boolean primeraVez, EstadoPropuesta estadoSeleccionado) {
		
		if (primeraVez) {
			
			tablePropuestas = new JTable() {
				public boolean isCellEditable(int rowIndex, int vColIndex) {
		            return false;
				}
			};
			tablePropuestas.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"Titulo:", "Por:"
					}
				));
		}else {
			
			switch (estadoSeleccionado) {
				case ingresada:
					cargarPropuestasPerfil(dtProponente.getPrIngresadas());
					break;
				case publicada:
					cargarPropuestasPerfil(dtProponente.getPrPublicadas());
					break;
				case enFinanciacion:
					cargarPropuestasPerfil(dtProponente.getPrEnFinanciacion());
					break;
				case financiada:
					cargarPropuestasPerfil(dtProponente.getPrFinanciadas());
					break;
				case cancelada:
					cargarPropuestasPerfil(dtProponente.getPrCanceladas());
					break;
				case noFinanciada:
					cargarPropuestasPerfil(dtProponente.getPrNoFinanciadas());
					break;
				default:
					break;
				
			}
		}

		scrollPanePropuestas = new JScrollPane(tablePropuestas);
		scrollPanePropuestas.setBounds(6, 71, 483, 179);
		panelPropuestas.add(scrollPanePropuestas);
		
		btnVerInformacin = new JButton("Ver información");
		btnVerInformacin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				displayPropuesta();
			}
		});
		btnVerInformacin.setBounds(338, 261, 152, 23);
		panelPropuestas.add(btnVerInformacin);
	}
	
	protected void displayPropuesta() {
		int filaSel = tablePropuestas.getSelectedRow();
		//busco el item en la coleccion que corresponda.
		EstadoPropuesta estadoSeleccionado = (EstadoPropuesta)cmbEstado.getSelectedItem();

		PropuestaSeleccionada propuestaSel = new PropuestaSeleccionada();
		
		switch (estadoSeleccionado) {
		 
			case ingresada:
				propuestaSel.setPropuesta(dtProponente.getPrIngresadas().get(filaSel));
				break;
			case publicada:
				propuestaSel.setPropuesta(dtProponente.getPrPublicadas().get(filaSel));
				break;
			case enFinanciacion:
				propuestaSel.setPropuesta(dtProponente.getPrEnFinanciacion().get(filaSel));
				break;
			case financiada:
				propuestaSel.setPropuesta(dtProponente.getPrFinanciadas().get(filaSel));
				break;
			case cancelada:
				propuestaSel.setPropuesta(dtProponente.getPrCanceladas().get(filaSel));
				break;
			case noFinanciada:
				propuestaSel.setPropuesta(dtProponente.getPrNoFinanciadas().get(filaSel));
				break;
			default:
				break;
			
		}
		JFrame popupSel = new JFrame("Información de la propuesta");
		popupSel.setSize(500, 500);
		popupSel.setVisible(true);
		popupSel.add(propuestaSel);
	}


	public void initPanelPropuestas(boolean primeraVez,EstadoPropuesta seleccionado) {
		{
			panelPropuestas = new JPanel();
			panelPropuestas.setBorder(new TitledBorder(null, "Propuestas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelPropuestas.setBounds(10, 197, 500, 293);
			perfilCompleto.add(panelPropuestas);
			panelPropuestas.setLayout(null);
			{
				lblEstado = new JLabel("Estado:");
				lblEstado.setBounds(57, 19, 58, 14);
				panelPropuestas.add(lblEstado);
			}
			{
				// Inicializo una sola vez el combo para que recuerde el seleccionado en cada refresh
				if (primeraVez) {
					cmbEstado = new JComboBox<Object>();
					
					cmbEstado.setModel(new DefaultComboBoxModel<>(EstadoPropuesta.values()));
					cmbEstado.setBounds(120, 16, 374, 20);
				}else {
					panelPropuestas.remove(cmbEstado);
				}
				
				
				cmbEstado.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						if ((e.getStateChange() == ItemEvent.SELECTED)) {
							perfilCompleto.remove(panelPropuestas);
							panelPropuestas.removeAll();
							initPanelPropuestas(false,(EstadoPropuesta)cmbEstado.getSelectedItem());
						}
						
					}
				});
				panelPropuestas.add(cmbEstado);
			}
			{
				initTableGrilla(primeraVez,seleccionado);
			}
		}
	}
	
	private void limpiarPanel() {
		lblImagen.setIcon(null);
		txtNickname.setText("");
		txtNombre.setText("");
		txtApellido.setText("");
		txtCorreo.setText("");
		txtDireccion.setText("");
		
		initTableGrilla(true, EstadoPropuesta.ingresada);
		//initPanelPropuestas(true, EstadoPropuesta.ingresada);
	}
	
	public void refreshFrame() {
		listarProponentes.setListaDeProponentes();
	}
	
}