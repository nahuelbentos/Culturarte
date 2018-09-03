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
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

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
	private JTextField txtImagen;
	private JTable tablePropuestaPublicada;
	
	private Object[][] data;
	
	private final Object[] columnNames = { 
	                              "Titulo:",
	                              "Por:"};	
	private JTable tableColaboracionesHechas;
	/**
	 * Create the panel.
	 * @throws UsuarioNoExisteElUsuarioException 
	 * @throws PropuestaNoExisteException 
	 */
	public VerPerfilColaborador(IUsuarioController IUC,String nickname) throws UsuarioNoExisteElUsuarioException, PropuestaNoExisteException {
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
		
		tableColaboracionesHechas = new JTable();
		tableColaboracionesHechas.setBounds(12, 231, 850, 172);
		add(tableColaboracionesHechas);
		
		
		iUsuController = IUC;
		
//		DtPerfilProponente dtp = iUsuController.verPerfilProponente(nickname);
		modelTitulos = new DefaultListModel<String>();
		listPropuestas = new JList<>(modelTitulos);
		

		
		setTable(IUC, nickname);
	}
	
	public void setTable(IUsuarioController IUC,String n) throws UsuarioNoExisteElUsuarioException, PropuestaNoExisteException {
		System.out.println("Entro en setTableString \n");
		
		DtPerfilColaborador dtp2 =  IUC.verPerfilColaborador(n);
		System.out.println(" a ver si vos funcionas la concha de tu madre: " + dtp2.getNickname());
		txtNickname.setText(dtp2.getNickname());
		txtNombre.setText(dtp2.getNombre());
		txtApellido.setText(dtp2.getApellido());
		txtEmail.setText(dtp2.getEmail());
		//txtFechaDeNacimiento.setText(dtp2.getFechaNacimiento().getCalendarType());
		txtImagen.setText(dtp2.getImagen());
		
		
//		ArrayList<DtPropuestaColaborada> ColaboracionesHechas = dtp2.getColaboracionesHechas();
		
		/*
		data = new Object[ColaboracionesHechas.size()][columnNames.length];
		System.out.println("armo la table ColaboracionesHechas.size: " + ColaboracionesHechas.size() +  " \n");
		for (int i = 0; i < ColaboracionesHechas.size(); i++) {
			for (int j = 0; j < columnNames.length; j++) {
				switch (j) {
				case 0:
					data[i][j] = ColaboracionesHechas.get(i).getTitulo();
					System.out.println("Titulo " + i + " " + ColaboracionesHechas.get(i).getTitulo() + "\n");
					break;
				case 1:
					data[i][j] = ColaboracionesHechas.get(i).getDescripcion();
					System.out.println("Titulo " + i + " " + ColaboracionesHechas.get(i).getTitulo() + "\n");
					break;
				}
			}
			
		}
		
		tablePropuestaPublicada = new JTable(data, columnNames);
				*/
		agregarDatosTable(dtp2.getColaboracionesHechas());
		
		System.out.println("Termino setListaDeProponentes \n");
	}
	
	public void agregarDatosTable(ArrayList<DtPropuestaColaborada> ColaboracionesHechas) {
		System.out.println("agregar datos table");
		tableColaboracionesHechas.removeAll();
		DefaultTableModel dm = new DefaultTableModel(0, 0);
		System.out.println("dm.getRowCount(): " + dm.getRowCount());
		while (dm.getRowCount()>0)
        {
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
}
