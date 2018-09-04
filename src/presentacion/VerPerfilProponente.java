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
	private JLabel lblFinanciada;
	private JLabel lblNoFinanciada;
	/**
	 * Create the panel.
	 * @throws UsuarioNoExisteElUsuarioException 
	 * @throws PropuestaNoExisteException 
	 */
	public VerPerfilProponente(IUsuarioController IUC,String nickname) throws UsuarioNoExisteElUsuarioException, PropuestaNoExisteException {
		setLayout(null);
		
		
		
		txtNickname = new JTextField();
		txtNickname.setEnabled(false);
		txtNickname.setBounds(164, 27, 124, 19);
		add(txtNickname);
		txtNickname.setColumns(10);
		
		JLabel lblNickname = new JLabel("Nickname:");
		lblNickname.setBounds(12, 27, 101, 15);
		add(lblNickname);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(12, 56, 66, 15);
		add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setEnabled(false);
		txtNombre.setEditable(true);
		txtNombre.setText("");
		txtNombre.setBounds(164, 56, 124, 19);
		add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(12, 83, 66, 15);
		add(lblApellido);
		
		txtApellido = new JTextField();
		txtApellido.setEnabled(false);
		txtApellido.setBounds(164, 87, 124, 19);
		add(txtApellido);
		txtApellido.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(12, 114, 66, 15);
		add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setEnabled(false);
		txtEmail.setBounds(164, 114, 124, 19);
		add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblFechaDeNacimiento = new JLabel("Fecha de nacimiento:");
		lblFechaDeNacimiento.setBounds(12, 137, 159, 15);
		add(lblFechaDeNacimiento);
		
		txtFechaDeNacimiento = new JTextField();
		txtFechaDeNacimiento.setEnabled(false);
		txtFechaDeNacimiento.setBounds(164, 139, 124, 19);
		add(txtFechaDeNacimiento);
		txtFechaDeNacimiento.setColumns(10);
		
		txtImagen = new JTextField();
		txtImagen.setEnabled(false);
		txtImagen.setBounds(164, 168, 124, 19);
		add(txtImagen);
		txtImagen.setColumns(10);
		
		JLabel lblImagen = new JLabel("Imagen:");
		lblImagen.setBounds(12, 166, 66, 15);
		add(lblImagen);
		
		txtDireccion = new JTextField();
		txtDireccion.setEnabled(false);
		txtDireccion.setBounds(164, 194, 124, 19);
		add(txtDireccion);
		txtDireccion.setColumns(10);
		
		JLabel lblDireccion = new JLabel("Dirección:");
		lblDireccion.setBounds(12, 192, 84, 15);
		add(lblDireccion);
		
		txtBiografia = new JTextField();
		txtBiografia.setEnabled(false);
		txtBiografia.setBounds(164, 222, 124, 19);
		add(txtBiografia);
		txtBiografia.setColumns(10);
		
		lblBiografia = new JLabel("Biografía:");
		lblBiografia.setBounds(12, 220, 66, 15);
		add(lblBiografia);
		
		txtLinkWeb = new JTextField();
		txtLinkWeb.setEnabled(false);
		txtLinkWeb.setBounds(164, 253, 124, 19);
		add(txtLinkWeb);
		txtLinkWeb.setColumns(10);
		
		lblLinkweb = new JLabel("Linkweb:");
		lblLinkweb.setBounds(12, 247, 66, 15);
		add(lblLinkweb);
		
		
		iUsuController = IUC;

		tablePropuestaPublicada = new JTable();
		tablePropuestaPublicada.setBounds(12, 330, 90, 100);
		add(tablePropuestaPublicada);
		
		tablePropuestaCancelada = new JTable();
		tablePropuestaCancelada.setBounds(123, 330, 84, 100);
		add(tablePropuestaCancelada);
		
		tablePropuestaEnFinanciacion = new JTable();
		tablePropuestaEnFinanciacion.setBounds(217, 330, 84, 100);
		add(tablePropuestaEnFinanciacion);
		
		tablePropuestaFinanciada = new JTable();
		tablePropuestaFinanciada.setBounds(319, 330, 84, 100);
		add(tablePropuestaFinanciada);
		
		tablePropuestaNoFinanciada = new JTable();
		tablePropuestaNoFinanciada.setBounds(434, 330, 84, 100);
		add(tablePropuestaNoFinanciada);
		
		JLabel lblPublicadas = new JLabel("Publicadas");
		lblPublicadas.setBounds(12, 305, 66, 14);
		add(lblPublicadas);
		
		JLabel lblCanceladas = new JLabel("Canceladas");
		lblCanceladas.setBounds(136, 305, 55, 14);
		add(lblCanceladas);
		
		JLabel lblEnFinanciacion = new JLabel("En financiacion");
		lblEnFinanciacion.setBounds(218, 305, 83, 14);
		add(lblEnFinanciacion);
		
		lblFinanciada = new JLabel("Financiada");
		lblFinanciada.setBounds(319, 305, 66, 14);
		add(lblFinanciada);
		
		lblNoFinanciada = new JLabel("No financiada");
		lblNoFinanciada.setBounds(434, 305, 84, 14);
		add(lblNoFinanciada);
		
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
