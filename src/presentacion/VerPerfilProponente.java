package presentacion;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.event.TableModelListener;

import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import datatype.DtPerfilProponente;
import datatype.DtPropuesta;
import datatype.DtPropuestaColaborada;
import datatype.DtPropuestaMinificado;
import datatype.DtUsuario;
import excepciones.PropuestaNoExisteException;
import excepciones.UsuarioNoExisteElUsuarioException;
import logica.IUsuarioController;
import presentacion.gen.ListarPropuestas;
import presentacion.gen.ListarPropuestasProponente;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class VerPerfilProponente extends JPanel {
	private JTable tableDatos;
	private IUsuarioController iUsuController;
	private JList<String> listPropuestas;
	private DefaultListModel<String> modelTitulos;
	private GregorianCalendar f;
	private DtPerfilProponente dtp;
	private JTextField txtNickname;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtEmail;
	private JTextField txtFechaDeNacimiento;
	private JTextField txtImagen;
	private JTextField txtDireccion;
	private JTextField txtBiografia;
	private JLabel lblBiografia;
	private JTextField txtLinkWeb;
	private JLabel lblLinkweb;
	
	
	
	private Object[][] data;
	
	private final Object[] columnNames = { 
	                              "Titulo:",
	                              "Por:"};	
	
	private JTable tablePropuestaPublicada;
	private JTable tablePropuestaCancelada;
	private JTable tablePropuestaEnFinanciacion;
	private JTable tablePropuestaFinanciada;
	private JTable tablePropuestaNoFinanciada;
	private JPanel panel;
	private JTabbedPane propsTab;
	private JPanel panelPropuestas;
	/**
	 * Create the panel.
	 * @throws UsuarioNoExisteElUsuarioException 
	 * @throws PropuestaNoExisteException 
	 */
	public VerPerfilProponente(IUsuarioController IUC,String nickname) throws UsuarioNoExisteElUsuarioException, PropuestaNoExisteException {
		setLayout(null);
		
		
		iUsuController = IUC;
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Datos b\u00E1sicos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 11, 268, 260);
		add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{123, 18, 57, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblNickname = new JLabel("Nickname:");
		GridBagConstraints gbc_lblNickname = new GridBagConstraints();
		gbc_lblNickname.anchor = GridBagConstraints.EAST;
		gbc_lblNickname.insets = new Insets(0, 0, 5, 5);
		gbc_lblNickname.gridx = 0;
		gbc_lblNickname.gridy = 0;
		panel.add(lblNickname, gbc_lblNickname);
		
		
		
		txtNickname = new JTextField();
		GridBagConstraints gbc_txtNickname = new GridBagConstraints();
		gbc_txtNickname.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNickname.insets = new Insets(0, 0, 5, 0);
		gbc_txtNickname.gridx = 2;
		gbc_txtNickname.gridy = 0;
		panel.add(txtNickname, gbc_txtNickname);
		txtNickname.setEnabled(false);
		txtNickname.setDisabledTextColor(Color.DARK_GRAY);
		txtNickname.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre:");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.EAST;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 0;
		gbc_lblNombre.gridy = 1;
		panel.add(lblNombre, gbc_lblNombre);
		
		txtNombre = new JTextField();
		GridBagConstraints gbc_txtNombre = new GridBagConstraints();
		gbc_txtNombre.insets = new Insets(0, 0, 5, 0);
		gbc_txtNombre.gridx = 2;
		gbc_txtNombre.gridy = 1;
		panel.add(txtNombre, gbc_txtNombre);
		txtNombre.setEnabled(false);
		txtNombre.setEditable(true);
		txtNombre.setText("");
		txtNombre.setDisabledTextColor(Color.DARK_GRAY);
		txtNombre.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellido:");
		GridBagConstraints gbc_lblApellido = new GridBagConstraints();
		gbc_lblApellido.anchor = GridBagConstraints.EAST;
		gbc_lblApellido.insets = new Insets(0, 0, 5, 5);
		gbc_lblApellido.gridx = 0;
		gbc_lblApellido.gridy = 2;
		panel.add(lblApellido, gbc_lblApellido);
		
		txtApellido = new JTextField();
		GridBagConstraints gbc_txtApellido = new GridBagConstraints();
		gbc_txtApellido.insets = new Insets(0, 0, 5, 0);
		gbc_txtApellido.gridx = 2;
		gbc_txtApellido.gridy = 2;
		panel.add(txtApellido, gbc_txtApellido);
		txtApellido.setEnabled(false);
		txtApellido.setDisabledTextColor(Color.DARK_GRAY);
		txtApellido.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email:");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.EAST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 0;
		gbc_lblEmail.gridy = 3;
		panel.add(lblEmail, gbc_lblEmail);
		
		txtEmail = new JTextField();
		GridBagConstraints gbc_txtEmail = new GridBagConstraints();
		gbc_txtEmail.insets = new Insets(0, 0, 5, 0);
		gbc_txtEmail.gridx = 2;
		gbc_txtEmail.gridy = 3;
		panel.add(txtEmail, gbc_txtEmail);
		txtEmail.setEnabled(false);
		txtEmail.setDisabledTextColor(Color.DARK_GRAY);
		txtEmail.setColumns(10);
		
		JLabel lblFechaDeNacimiento = new JLabel("Fecha de nacimiento:");
		GridBagConstraints gbc_lblFechaDeNacimiento = new GridBagConstraints();
		gbc_lblFechaDeNacimiento.anchor = GridBagConstraints.EAST;
		gbc_lblFechaDeNacimiento.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaDeNacimiento.gridx = 0;
		gbc_lblFechaDeNacimiento.gridy = 4;
		panel.add(lblFechaDeNacimiento, gbc_lblFechaDeNacimiento);
		
		txtFechaDeNacimiento = new JTextField();
		GridBagConstraints gbc_txtFechaDeNacimiento = new GridBagConstraints();
		gbc_txtFechaDeNacimiento.insets = new Insets(0, 0, 5, 0);
		gbc_txtFechaDeNacimiento.gridx = 2;
		gbc_txtFechaDeNacimiento.gridy = 4;
		panel.add(txtFechaDeNacimiento, gbc_txtFechaDeNacimiento);
		txtFechaDeNacimiento.setEnabled(false);
		txtFechaDeNacimiento.setDisabledTextColor(Color.DARK_GRAY);
		txtFechaDeNacimiento.setColumns(10);
		
		JLabel lblImagen = new JLabel("Imagen:");
		GridBagConstraints gbc_lblImagen = new GridBagConstraints();
		gbc_lblImagen.anchor = GridBagConstraints.EAST;
		gbc_lblImagen.insets = new Insets(0, 0, 5, 5);
		gbc_lblImagen.gridx = 0;
		gbc_lblImagen.gridy = 5;
		panel.add(lblImagen, gbc_lblImagen);
		
		txtImagen = new JTextField();
		GridBagConstraints gbc_txtImagen = new GridBagConstraints();
		gbc_txtImagen.insets = new Insets(0, 0, 5, 0);
		gbc_txtImagen.gridx = 2;
		gbc_txtImagen.gridy = 5;
		panel.add(txtImagen, gbc_txtImagen);
		txtImagen.setEnabled(false);
		txtImagen.setDisabledTextColor(Color.DARK_GRAY);
		txtImagen.setColumns(10);
		
		JLabel lblDireccion = new JLabel("DirecciÃ³n:");
		GridBagConstraints gbc_lblDireccion = new GridBagConstraints();
		gbc_lblDireccion.anchor = GridBagConstraints.EAST;
		gbc_lblDireccion.insets = new Insets(0, 0, 5, 5);
		gbc_lblDireccion.gridx = 0;
		gbc_lblDireccion.gridy = 6;
		panel.add(lblDireccion, gbc_lblDireccion);
		
		txtDireccion = new JTextField();
		GridBagConstraints gbc_txtDireccion = new GridBagConstraints();
		gbc_txtDireccion.insets = new Insets(0, 0, 5, 0);
		gbc_txtDireccion.gridx = 2;
		gbc_txtDireccion.gridy = 6;
		panel.add(txtDireccion, gbc_txtDireccion);
		txtDireccion.setEnabled(false);
		txtDireccion.setDisabledTextColor(Color.DARK_GRAY);
		txtDireccion.setColumns(10);
		
		lblBiografia = new JLabel("BiografÃ­a:");
		GridBagConstraints gbc_lblBiografia = new GridBagConstraints();
		gbc_lblBiografia.anchor = GridBagConstraints.EAST;
		gbc_lblBiografia.insets = new Insets(0, 0, 5, 5);
		gbc_lblBiografia.gridx = 0;
		gbc_lblBiografia.gridy = 7;
		panel.add(lblBiografia, gbc_lblBiografia);
		
		txtBiografia = new JTextField();
		GridBagConstraints gbc_txtBiografia = new GridBagConstraints();
		gbc_txtBiografia.insets = new Insets(0, 0, 5, 0);
		gbc_txtBiografia.gridx = 2;
		gbc_txtBiografia.gridy = 7;
		panel.add(txtBiografia, gbc_txtBiografia);
		txtBiografia.setEnabled(false);
		txtBiografia.setDisabledTextColor(Color.DARK_GRAY);
		txtBiografia.setColumns(10);
		
		lblLinkweb = new JLabel("Linkweb:");
		GridBagConstraints gbc_lblLinkweb = new GridBagConstraints();
		gbc_lblLinkweb.anchor = GridBagConstraints.EAST;
		gbc_lblLinkweb.insets = new Insets(0, 0, 0, 5);
		gbc_lblLinkweb.gridx = 0;
		gbc_lblLinkweb.gridy = 8;
		panel.add(lblLinkweb, gbc_lblLinkweb);
		
		txtLinkWeb = new JTextField();
		GridBagConstraints gbc_txtLinkWeb = new GridBagConstraints();
		gbc_txtLinkWeb.gridx = 2;
		gbc_txtLinkWeb.gridy = 8;
		panel.add(txtLinkWeb, gbc_txtLinkWeb);
		txtLinkWeb.setEnabled(false);
		txtLinkWeb.setDisabledTextColor(Color.DARK_GRAY);
		txtLinkWeb.setColumns(10);
		
		panelPropuestas = new JPanel();
		panelPropuestas.setBorder(new TitledBorder(null, "Propuestas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelPropuestas.setBounds(290, 11, 355, 260);
		add(panelPropuestas);
		
		panelPropuestas.setLayout(null);

		tablePropuestaPublicada = new JTable();
		tablePropuestaPublicada.setBounds(12, 330, 90, 100);
		tablePropuestaCancelada = new JTable();
		tablePropuestaCancelada.setBounds(123, 330, 84, 100);
		tablePropuestaEnFinanciacion = new JTable();
		tablePropuestaEnFinanciacion.setBounds(217, 330, 84, 100);
		tablePropuestaFinanciada = new JTable();
		tablePropuestaFinanciada.setBounds(319, 330, 84, 100);
		tablePropuestaNoFinanciada = new JTable();
		tablePropuestaNoFinanciada.setBounds(434, 330, 84, 100);
		
		propsTab = new JTabbedPane(JTabbedPane.TOP);
		propsTab.setBounds(10, 21, 335, 228);
		panelPropuestas.add(propsTab);
		propsTab.addTab("Publicadas", tablePropuestaPublicada);
		propsTab.addTab("En financiación", tablePropuestaEnFinanciacion);
		propsTab.addTab("Financiadas", tablePropuestaFinanciada);
		propsTab.addTab("Sin financiar", tablePropuestaNoFinanciada);
		propsTab.addTab("Canceladas", tablePropuestaCancelada);
		
//		DtPerfilProponente dtp = iUsuController.verPerfilProponente(nickname);
		modelTitulos = new DefaultListModel<String>();
		listPropuestas = new JList<>(modelTitulos);
		

		if (nickname != null) {
			setTable(IUC, nickname);			
		}
		
	}
	
	public void setTable(IUsuarioController IUC,String n) throws UsuarioNoExisteElUsuarioException, PropuestaNoExisteException {
		System.out.println("Entro en setTableString \n");
		
		DtPerfilProponente dtp2 =  IUC.verPerfilProponente(n);
		System.out.println(" Nickname: " + dtp2.getNickname());
		txtNickname.setText(dtp2.getNickname());
		txtNombre.setText(dtp2.getNombre());
		txtApellido.setText(dtp2.getApellido());
		txtEmail.setText(dtp2.getEmail());
		//txtFechaDeNacimiento.setText(dtp2.getFechaNacimiento().getCalendarType());
		//txtImagen.setText(dtp2.getImagen());
		txtDireccion.setText(dtp2.getDireccion());
		txtBiografia.setText(dtp2.getBiografia());
		txtLinkWeb.setText(dtp2.getSitioWeb());	
		
		System.out.println("armo la table prPublicadas.size: " + dtp2.getPrPublicadas().size() +  " \n");
		System.out.println("armo la table prCanceladas.size: " + dtp2.getPrCanceladas().size() +  " \n");
		System.out.println("armo la table prEnFinanciacion.size: " + dtp2.getPrEnFinanciacion().size() +  " \n");
		System.out.println("armo la table prFinanciadas.size: " + dtp2.getPrFinanciadas().size() +  " \n");
		System.out.println("armo la table prNoFinanciadas.size: " + dtp2.getPrNoFinanciadas().size() +  " \n");
		
		System.out.println("Termino setListaDeProponentes \n");
		
		if(dtp2.getPrPublicadas().size() >0)
			agregarDatosTablePublicadas(dtp2.getPrPublicadas());
		if(dtp2.getPrCanceladas().size() >0)
			agregarDatosTableCanceladas(dtp2.getPrCanceladas());
		if(dtp2.getPrEnFinanciacion().size() >0)
			agregarDatosTableEnFinanciacion(dtp2.getPrEnFinanciacion());
		if(dtp2.getPrFinanciadas().size() >0)
			agregarDatosTableEnFinanciacion(dtp2.getPrFinanciadas());
		if(dtp2.getPrNoFinanciadas().size() >0)
			agregarDatosTableEnFinanciacion(dtp2.getPrNoFinanciadas());
		
		// una vez cargada las tablas armo los tabs de nuevo
		propsTab.removeAll();
		
		propsTab = new JTabbedPane(JTabbedPane.TOP);
		propsTab.setBounds(10, 21, 335, 228);
		panelPropuestas.add(propsTab);
		propsTab.addTab("Publicadas", tablePropuestaPublicada);
		propsTab.addTab("En financiación", tablePropuestaEnFinanciacion);
		propsTab.addTab("Financiadas", tablePropuestaFinanciada);
		propsTab.addTab("Sin financiar", tablePropuestaNoFinanciada);
		propsTab.addTab("Canceladas", tablePropuestaCancelada);
	}
	
	public void agregarDatosTablePublicadas(ArrayList<DtPropuesta> prPublicadas) {
		System.out.println("agregar datos table - Publicadas");
		tablePropuestaPublicada.removeAll();
		DefaultTableModel dm = new DefaultTableModel(0, 0);
		System.out.println("dm.getRowCount(): " + dm.getRowCount());
		while (dm.getRowCount()>0)
        {
			dm.removeRow(0);
        }
		
	    String header[] = new String[] { "Titulo", "Descripcion"};
	    dm.setColumnIdentifiers(header);
	    dm.addRow(header);
	    tablePropuestaPublicada.setModel(dm);
	    tablePropuestaPublicada.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	    
	    System.out.println("Publicadas.size(): " + prPublicadas.size() + "\n"); 
	    for (DtPropuesta dtp : prPublicadas) {
		
	        Vector<Object> data = new Vector<Object>();
	        data.add(dtp.getTitulo());
	        data.add(dtp.getDescripcion());
	        
	        System.out.println("Titulo :- " + dtp.getTitulo());
	        dm.addRow(data);

	    }
	}
	
	public void agregarDatosTableCanceladas(ArrayList<DtPropuesta> prCanceladas) {
		System.out.println("agregar datos table - Canceladas");
		tablePropuestaCancelada.removeAll();
		DefaultTableModel dm = new DefaultTableModel(0, 0);
		System.out.println("dm.getRowCount(): " + dm.getRowCount());
		while (dm.getRowCount()>0)
        {
			dm.removeRow(0);
        }
		
	    String header[] = new String[] { "Titulo", "Descripcion"};
	    dm.setColumnIdentifiers(header);
	    dm.addRow(header);
	    tablePropuestaCancelada.setModel(dm);
	    tablePropuestaCancelada.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	    System.out.println("Publicadas.size(): " + prCanceladas.size() + "\n"); 
	    for (DtPropuesta dtp : prCanceladas) {
		
	        Vector<Object> data = new Vector<Object>();
	        data.add(dtp.getTitulo());
	        data.add(dtp.getDescripcion());
	        
	        System.out.println("Titulo :- " + dtp.getTitulo());
	        dm.addRow(data);

	    }
	}
	
	public void agregarDatosTableEnFinanciacion(ArrayList<DtPropuesta> prEnFinanciacion) {
		System.out.println("agregar datos table - EnFinanciacion");
		tablePropuestaEnFinanciacion.removeAll();
		DefaultTableModel dm = new DefaultTableModel(0, 0);
		System.out.println("dm.getRowCount(): " + dm.getRowCount());
		while (dm.getRowCount()>0)
        {
			dm.removeRow(0);
        }
		
	    String header[] = new String[] { "Titulo", "Descripcion"};
	    dm.setColumnIdentifiers(header);
	    dm.addRow(header);
	    tablePropuestaEnFinanciacion.setModel(dm);
	    tablePropuestaEnFinanciacion.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	    System.out.println("EnFinanciacion.size(): " + prEnFinanciacion.size() + "\n"); 
	    for (DtPropuesta dtp : prEnFinanciacion) {
		
	        Vector<Object> data = new Vector<Object>();
	        data.add(dtp.getTitulo());
	        data.add(dtp.getDescripcion());
	        
	        System.out.println("Titulo :- " + dtp.getTitulo());
	        dm.addRow(data);

	    }
	}
	
	public void agregarDatosTableFinanciadas(ArrayList<DtPropuesta> prFinanciadas) {
		System.out.println("agregar datos table");
		tablePropuestaFinanciada.removeAll();
		DefaultTableModel dm = new DefaultTableModel(0, 0);
		System.out.println("dm.getRowCount(): " + dm.getRowCount());
		while (dm.getRowCount()>0)
        {
			dm.removeRow(0);
        }
		
	    String header[] = new String[] { "Titulo", "Descripcion"};
	    dm.setColumnIdentifiers(header);
	    dm.addRow(header);
	    tablePropuestaFinanciada.setModel(dm);
	    tablePropuestaFinanciada.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	    System.out.println("Publicadas.size(): " + prFinanciadas.size() + "\n"); 
	    for (DtPropuesta dtp : prFinanciadas) {
		
	        Vector<Object> data = new Vector<Object>();
	        data.add(dtp.getTitulo());
	        data.add(dtp.getDescripcion());
	        
	        System.out.println("Titulo :- " + dtp.getTitulo());
	        dm.addRow(data);

	    }
	}
	
	public void agregarDatosTableNoFinanciadas(ArrayList<DtPropuesta> prNoFinanciadas) {
		System.out.println("agregar datos table");
		tablePropuestaNoFinanciada.removeAll();
		DefaultTableModel dm = new DefaultTableModel(0, 0);
		System.out.println("dm.getRowCount(): " + dm.getRowCount());
		while (dm.getRowCount()>0)
        {
			dm.removeRow(0);
        }
		
	    String header[] = new String[] { "Titulo", "Descripcion"};
	    dm.setColumnIdentifiers(header);
	    dm.addRow(header);
	    tablePropuestaNoFinanciada.setModel(dm);
	    tablePropuestaNoFinanciada.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	    System.out.println("Publicadas.size(): " + prNoFinanciadas.size() + "\n"); 
	    for (DtPropuesta dtp : prNoFinanciadas) {
		
	        Vector<Object> data = new Vector<Object>();
	        data.add(dtp.getTitulo());
	        data.add(dtp.getDescripcion());
	        
	        System.out.println("Titulo :- " + dtp.getTitulo());
	        dm.addRow(data);

	    }
	}
}
