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

import datatype.DtDatosPropuesta;
import datatype.DtPerfilColaborador;
import datatype.DtPerfilProponente;
import datatype.DtPropuesta;
import datatype.DtPropuestaColaborada;
import datatype.DtPropuestaMinificado;
import datatype.DtUsuario;
import excepciones.PropuestaNoExisteException;
import excepciones.UsuarioNoExisteElUsuarioException;
import logica.IPropuestaController;
import logica.IUsuarioController;
import presentacion.gen.ListarPropuestas;
import presentacion.gen.ListarPropuestasProponente;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;

public class ConsultarPropuesta extends JPanel {
	private JTable tableDatos;
	private IPropuestaController iPropController;
	private JList<String> listPropuestas;
	private DefaultListModel<String> modelTitulos;
	private GregorianCalendar f;
	private DtPerfilProponente dtp;
	private JTextField txtTitulo;
	private JTextField txtDescripcion;
	private JTextField txtMontoNecesario;
	private JTextField txtFechaEspectaculo;
	private JTextField txtFechaPublicacion;
	private JTextField txtImagen;
	private JTable tablePropuestaPublicada;
	
	private Object[][] data;
	
	private final Object[] columnNames = { 
	                              "Titulo:",
	                              "Por:"};	
	private JTable tableColaboradores;
	private JTextField txtLugar;
	private JTextField txtPrecioEntrada;
	private JTextField txtTipo;
	private JTextField txtMontoRecaudado;
	
	private JLabel lblColaboradores;
	/**
	 * Create the panel.
	 * @throws UsuarioNoExisteElUsuarioException 
	 * @throws PropuestaNoExisteException 
	 */
	public ConsultarPropuesta(IPropuestaController IPC,String titulo) throws UsuarioNoExisteElUsuarioException, PropuestaNoExisteException {
		setLayout(null);
		
		
		
		txtTitulo = new JTextField();
		txtTitulo.setEnabled(false);
		txtTitulo.setBounds(164, 27, 124, 19);
		add(txtTitulo);
		txtTitulo.setColumns(10);
		
		JLabel lblTitulo = new JLabel("Titulo:");
		lblTitulo.setBounds(12, 27, 101, 15);
		add(lblTitulo);
		
		JLabel lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setBounds(12, 56, 101, 15);
		add(lblDescripcion);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setEnabled(false);
		txtDescripcion.setEditable(true);
		txtDescripcion.setText("");
		txtDescripcion.setBounds(164, 56, 124, 19);
		add(txtDescripcion);
		txtDescripcion.setColumns(10);
		
		JLabel lblMontoNecesario = new JLabel("Monto Necesario:");
		lblMontoNecesario.setBounds(12, 83, 134, 15);
		add(lblMontoNecesario);
		
		txtMontoNecesario = new JTextField();
		txtMontoNecesario.setEnabled(false);
		txtMontoNecesario.setBounds(164, 87, 124, 19);
		add(txtMontoNecesario);
		txtMontoNecesario.setColumns(10);
		
		JLabel lblFechaEspectaculo = new JLabel("Fecha espectaculo:");
		lblFechaEspectaculo.setBounds(12, 114, 145, 15);
		add(lblFechaEspectaculo);
		
		txtFechaEspectaculo = new JTextField();
		txtFechaEspectaculo.setEnabled(false);
		txtFechaEspectaculo.setBounds(164, 114, 124, 19);
		add(txtFechaEspectaculo);
		txtFechaEspectaculo.setColumns(10);
		
		JLabel lblFechaPublicacion = new JLabel("Fecha de publicacion:");
		lblFechaPublicacion.setBounds(12, 137, 159, 15);
		add(lblFechaPublicacion);
		
		txtFechaPublicacion = new JTextField();
		txtFechaPublicacion.setEnabled(false);
		txtFechaPublicacion.setBounds(164, 139, 124, 19);
		add(txtFechaPublicacion);
		txtFechaPublicacion.setColumns(10);
		
		txtImagen = new JTextField();
		txtImagen.setEnabled(false);
		txtImagen.setBounds(164, 168, 124, 19);
		add(txtImagen);
		txtImagen.setColumns(10);
		
		JLabel lblImagen = new JLabel("Imagen:");
		lblImagen.setBounds(12, 166, 66, 15);
		add(lblImagen);
		
		tableColaboradores = new JTable();
		tableColaboradores.setBounds(319, 97, 203, 117);
		add(tableColaboradores);
		
		txtLugar = new JTextField();
		txtLugar.setText((String) null);
		txtLugar.setEnabled(false);
		txtLugar.setColumns(10);
		txtLugar.setBounds(164, 195, 124, 19);
		add(txtLugar);
		
		JLabel lblLugar = new JLabel("Lugar:");
		lblLugar.setBounds(12, 193, 66, 15);
		add(lblLugar);
		
		JLabel lblPrecioEntrada = new JLabel("Precio entrada:");
		lblPrecioEntrada.setBounds(12, 220, 145, 15);
		add(lblPrecioEntrada);
		
		txtPrecioEntrada = new JTextField();
		txtPrecioEntrada.setText((String) null);
		txtPrecioEntrada.setEnabled(false);
		txtPrecioEntrada.setColumns(10);
		txtPrecioEntrada.setBounds(164, 222, 124, 19);
		add(txtPrecioEntrada);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(12, 247, 66, 15);
		add(lblTipo);
		
		txtTipo = new JTextField();
		txtTipo.setText((String) null);
		txtTipo.setEnabled(false);
		txtTipo.setColumns(10);
		txtTipo.setBounds(164, 249, 124, 19);
		add(txtTipo);
		
		JLabel lblMontoRecaudado = new JLabel("Monto recaudado:");
		lblMontoRecaudado.setBounds(12, 274, 145, 15);
		add(lblMontoRecaudado);
		
		txtMontoRecaudado = new JTextField();
		txtMontoRecaudado.setText((String) null);
		txtMontoRecaudado.setEnabled(false);
		txtMontoRecaudado.setColumns(10);
		txtMontoRecaudado.setBounds(164, 276, 124, 19);
		add(txtMontoRecaudado);
		
		lblColaboradores = new JLabel("Colaboradores:");
		lblColaboradores.setBounds(356, 70, 117, 15);
		add(lblColaboradores);
		
		
		iPropController = IPC;
		
//		DtPerfilProponente dtp = iUsuController.verPerfilProponente(nickname);
		modelTitulos = new DefaultListModel<String>();
		listPropuestas = new JList<>(modelTitulos);
		

		if(titulo!=null)
			setTable(IPC, titulo);
	}
	
	public void setTable(IPropuestaController IPC,String t) throws UsuarioNoExisteElUsuarioException, PropuestaNoExisteException {
		System.out.println("Entro en setTableString \n");
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
		System.out.println("\n Titulo: " + t);
		if(t!=null) {
			DtDatosPropuesta dtp2 =  IPC.consultarPropuesta(t);
			System.out.println("\n dtp2: " + dtp2.getTitulo());
			if(dtp2!=null) {
				System.out.println(" \n  Entra en adentro: " + dtp2.getTitulo());
				txtTitulo.setText(dtp2.getTitulo());
				txtDescripcion.setText(dtp2.getDescripcion());
//				txtImagen.setText(dtp2.getImagen());
				txtMontoNecesario.setText(Double.toString(dtp2.getMontoNecesario()));
				if(dtp2.getFechaEspecatulo()!=null)
					txtFechaEspectaculo.setText(sdf.format(dtp2.getFechaEspecatulo().getTime()));
				if(dtp2.getFechaPublicacion()!=null)
					txtFechaPublicacion.setText(sdf.format(dtp2.getFechaPublicacion().getTime()));
				txtLugar.setText(dtp2.getLugar());
				txtPrecioEntrada.setText(Double.toString(dtp2.getPrecioEntrada()));
				//txtFechaDeNacimiento.setText(dtp2.getFechaNacimiento().getCalendarType());
				txtTipo.setText(dtp2.getTitulo());
				txtMontoRecaudado.setText(Double.toString(dtp2.getRecaudado()));
				
				if(dtp2.getColaboradores() != null)
					agregarDatosTable(dtp2.getColaboradores());
			}
		}
		
//		
		
		System.out.println("Termino setListaDeProponentes \n");
	}
	
	public void agregarDatosTable(ArrayList<String> colaboradores) {
		if(colaboradores.size()!=0) {
//			tableColaboradores.setVisible(true);
//			lblColaboradores.setText("Colaboradores:");
			System.out.println("agregar datos table");
			tableColaboradores.removeAll();
			DefaultTableModel dm = new DefaultTableModel(0, 0);
			System.out.println("dm.getRowCount(): " + dm.getRowCount());
			while (dm.getRowCount()>0)        {
				dm.removeRow(0);
	        }
			
		    String header[] = new String[] { "NICKNAME:" }; //, "Descripcion", "Imagen","Prop. Nickname", "Prop. Nombre", "Prop. Apellido", "Prop. Email", "Monto aportado", "Estado" };
		    dm.setColumnIdentifiers(header);
		    dm.addRow(header);
		    
		    tableColaboradores.setModel(dm);
		    tableColaboradores.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		    System.out.println("colaboradores.size(): " + colaboradores.size() + "\n"); 
		    for (String col: colaboradores) {
			
		        Vector<Object> data = new Vector<Object>();
		        data.add(col);
		        
		        System.out.println("Colaborador :- " + col);
		        dm.addRow(data);
	
		    }
		}else {
//			System.out.println("No tiene colaboradores. \n");
//			tableColaboradores.setVisible(false);
//			lblColaboradores.setText("La propuesta no tiene colaboradores.");
		}
			
	   
	}
}
