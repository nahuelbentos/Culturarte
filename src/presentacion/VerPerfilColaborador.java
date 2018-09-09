package presentacion;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.event.TableColumnModelListener;
import javax.swing.event.TableModelListener;

import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import datatype.DtPerfilColaborador;
import datatype.DtPerfilProponente;
import datatype.DtPropuesta;
import datatype.DtPropuestaColaborada;
import datatype.DtPropuestaMinificado;
import datatype.DtUsuario;
import datatype.EstadoPropuesta;
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
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import java.awt.GridBagLayout;
import javax.swing.border.TitledBorder;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class VerPerfilColaborador extends JPanel {
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
	private JTable tablePropuestaPublicada;
	
	private Object[][] data;
	
	private final Object[] columnNames = { 
	                              "Titulo:",
	                              "Por:"};	
	private JTable tableColaboracionesHechas;
	private JPanel perfilCompleto;
	private JLabel lblImagen;
	private JPanel panel;
	/**
	 * Create the panel.
	 * @throws UsuarioNoExisteElUsuarioException 
	 * @throws PropuestaNoExisteException 
	 */
	public VerPerfilColaborador(IUsuarioController IUC,String nickname) throws UsuarioNoExisteElUsuarioException, PropuestaNoExisteException {
		setLayout(null);
		
		JLabel lblImagen = new JLabel("Imagen:");
		lblImagen.setBounds(70, 27, 66, 15);
		add(lblImagen);
		
		tableColaboracionesHechas = new JTable();
		tableColaboracionesHechas.setBounds(12, 231, 850, 172);
		add(tableColaboracionesHechas);
		
		perfilCompleto = new JPanel();
		perfilCompleto.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Perfil", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		perfilCompleto.setBounds(362, 12, 270, 207);
		add(perfilCompleto);
		GridBagLayout gbl_perfilCompleto = new GridBagLayout();
		gbl_perfilCompleto.columnWidths = new int[]{0, 0, 0, 0};
		gbl_perfilCompleto.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_perfilCompleto.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_perfilCompleto.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		perfilCompleto.setLayout(gbl_perfilCompleto);
		
		JLabel lblNickname = new JLabel("Nickname:");
		GridBagConstraints gbc_lblNickname = new GridBagConstraints();
		gbc_lblNickname.insets = new Insets(0, 0, 5, 5);
		gbc_lblNickname.gridx = 1;
		gbc_lblNickname.gridy = 0;
		perfilCompleto.add(lblNickname, gbc_lblNickname);
		
		txtNickname = new JTextField();
		txtNickname.setEnabled(false);
		GridBagConstraints gbc_txtNickname = new GridBagConstraints();
		gbc_txtNickname.insets = new Insets(0, 0, 5, 0);
		gbc_txtNickname.gridx = 2;
		gbc_txtNickname.gridy = 0;
		perfilCompleto.add(txtNickname, gbc_txtNickname);
		txtNickname.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre:");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 1;
		gbc_lblNombre.gridy = 1;
		perfilCompleto.add(lblNombre, gbc_lblNombre);
		
		txtNombre = new JTextField();
		GridBagConstraints gbc_txtNombre = new GridBagConstraints();
		gbc_txtNombre.insets = new Insets(0, 0, 5, 0);
		gbc_txtNombre.gridx = 2;
		gbc_txtNombre.gridy = 1;
		perfilCompleto.add(txtNombre, gbc_txtNombre);
		txtNombre.setEnabled(false);
		txtNombre.setEditable(true);
		txtNombre.setText("");
		txtNombre.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellido:");
		GridBagConstraints gbc_lblApellido = new GridBagConstraints();
		gbc_lblApellido.insets = new Insets(0, 0, 5, 5);
		gbc_lblApellido.gridx = 1;
		gbc_lblApellido.gridy = 2;
		perfilCompleto.add(lblApellido, gbc_lblApellido);
		
		txtApellido = new JTextField();
		GridBagConstraints gbc_txtApellido = new GridBagConstraints();
		gbc_txtApellido.insets = new Insets(0, 0, 5, 0);
		gbc_txtApellido.gridx = 2;
		gbc_txtApellido.gridy = 2;
		perfilCompleto.add(txtApellido, gbc_txtApellido);
		txtApellido.setEnabled(false);
		txtApellido.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email:");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 1;
		gbc_lblEmail.gridy = 3;
		perfilCompleto.add(lblEmail, gbc_lblEmail);
		
		txtEmail = new JTextField();
		GridBagConstraints gbc_txtEmail = new GridBagConstraints();
		gbc_txtEmail.insets = new Insets(0, 0, 5, 0);
		gbc_txtEmail.gridx = 2;
		gbc_txtEmail.gridy = 3;
		perfilCompleto.add(txtEmail, gbc_txtEmail);
		txtEmail.setEnabled(false);
		txtEmail.setColumns(10);
		
		JLabel lblFechaDeNacimiento = new JLabel("F. Nacimiento:");
		GridBagConstraints gbc_lblFechaDeNacimiento = new GridBagConstraints();
		gbc_lblFechaDeNacimiento.insets = new Insets(0, 0, 0, 5);
		gbc_lblFechaDeNacimiento.gridx = 1;
		gbc_lblFechaDeNacimiento.gridy = 4;
		perfilCompleto.add(lblFechaDeNacimiento, gbc_lblFechaDeNacimiento);
		
		txtFechaDeNacimiento = new JTextField();
		GridBagConstraints gbc_txtFechaDeNacimiento = new GridBagConstraints();
		gbc_txtFechaDeNacimiento.gridx = 2;
		gbc_txtFechaDeNacimiento.gridy = 4;
		perfilCompleto.add(txtFechaDeNacimiento, gbc_txtFechaDeNacimiento);
		txtFechaDeNacimiento.setEnabled(false);
		txtFechaDeNacimiento.setColumns(10);
		
		panel = new JPanel();
		panel.setBounds(335, 278, 198, 92);
		add(panel);
		
		
		iUsuController = IUC;
		
//		DtPerfilProponente dtp = iUsuController.verPerfilProponente(nickname);
		modelTitulos = new DefaultListModel<String>();
		listPropuestas = new JList<>(modelTitulos);
		

		if (nickname != null) {
			setTable(IUC, nickname);	
		}
		
	}
	
	public void setTable(IUsuarioController IUC,String n) throws UsuarioNoExisteElUsuarioException, PropuestaNoExisteException {
		System.out.println("Entro en setTableString \n");
		
		DtPerfilColaborador dtp2 =  IUC.verPerfilColaborador(n);
		System.out.println(" Nickname: " + dtp2.getNickname());
		txtNickname.setText(dtp2.getNickname());
		txtNombre.setText(dtp2.getNombre());
		txtApellido.setText(dtp2.getApellido());
		txtEmail.setText(dtp2.getEmail());
		//txtFechaDeNacimiento.setText(dtp2.getFechaNacimiento().getCalendarType());
		//txtImagen.setText(dtp2.getImagen());
		
		agregarDatosTable(dtp2.getColaboracionesHechas());
		
		System.out.println("Termino setListaDeProponentes \n");
	}
	
	public void agregarDatosTable(ArrayList<DtPropuestaColaborada> ColaboracionesHechas) {
		
		tableColaboracionesHechas.removeAll();
		DefaultTableModel dm = new DefaultTableModel(0, 0);
		
		while (dm.getRowCount()>0) {
			dm.removeRow(0);
        }
		
	    String header[] = new String[] { "Titulo", "Descripcion", "Imagen",
	            "Prop. Nickname", "Prop. Nombre", "Prop. Apellido", "Prop. Email", "Monto aportado", "Estado" };
	    dm.setColumnIdentifiers(header);
	    dm.addRow(header);
	    tableColaboracionesHechas.setModel(dm);
	    tableColaboracionesHechas.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	    System.out.println("ColaboracionesHechas.size(): " + ColaboracionesHechas.size() + "\n"); 
	    for (DtPropuestaColaborada dtp : ColaboracionesHechas) {
		
	        Vector<Object> data = new Vector<Object>();
	        data.add(dtp.getTitulo());
	        data.add(dtp.getDescripcion());
	        data.add(dtp.getImagen());
	        data.add(dtp.getProponenteACargo().getNickname());
	        data.add(dtp.getProponenteACargo().getNombre());
	        data.add(dtp.getProponenteACargo().getApellido());
	        data.add(dtp.getProponenteACargo().getEmail());
	        data.add(dtp.getMontoAportado());
	        data.add(dtp.getEstadoActual());
	        
	        System.out.println("Titulo :- " + dtp.getTitulo());
	        dm.addRow(data);

	    }
	}

	private void cargarPerfil(String nickname) {
		limpiarPanel();
		DtPerfilColaborador dtc =  iUsuController.verPerfilColaborador(nickname);
		if(dtc.getImagen() != null) {
			ImageIcon imageIcon = new ImageIcon(dtc.getImagen());
			lblImagen.setIcon(imageIcon);
		}
		txtNickname.setText(dtc.getNickname());
		txtNombre.setText(dtc.getNombre());
		txtApellido.setText(dtc.getApellido());
		txtEmail.setText(dtc.getEmail());
		
	}
	
	private void limpiarPanel() {
		lblImagen.setIcon(null);
		txtNickname.setText("");
		txtNombre.setText("");
		txtApellido.setText("");
		txtEmail.setText("");
		
//		initTableGrilla(true, EstadoPropuesta.ingresada);
		//initPanelPropuestas(true, EstadoPropuesta.ingresada);
	}
}
