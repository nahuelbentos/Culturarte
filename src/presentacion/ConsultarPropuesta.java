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
import javax.swing.border.TitledBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

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
	private JPanel panelDatos;
	private JPanel panelColaboraciones;
	/**
	 * Create the panel.
	 * @throws UsuarioNoExisteElUsuarioException 
	 * @throws PropuestaNoExisteException 
	 */
	public ConsultarPropuesta(IPropuestaController IPC,String titulo) throws UsuarioNoExisteElUsuarioException, PropuestaNoExisteException {
		setLayout(null);
		{
			panelDatos = new JPanel();
			panelDatos.setBorder(new TitledBorder(null, "Datos b\u00E1sicos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelDatos.setBounds(10, 11, 359, 271);
			add(panelDatos);
			GridBagLayout gbl_panelDatos = new GridBagLayout();
			gbl_panelDatos.columnWidths = new int[]{122, 0, 175, 0};
			gbl_panelDatos.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			gbl_panelDatos.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_panelDatos.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			panelDatos.setLayout(gbl_panelDatos);
			
			JLabel lblTitulo = new JLabel("Titulo:");
			GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
			gbc_lblTitulo.anchor = GridBagConstraints.EAST;
			gbc_lblTitulo.insets = new Insets(0, 0, 5, 5);
			gbc_lblTitulo.gridx = 0;
			gbc_lblTitulo.gridy = 0;
			panelDatos.add(lblTitulo, gbc_lblTitulo);
			
			
			
			txtTitulo = new JTextField();
			GridBagConstraints gbc_txtTitulo = new GridBagConstraints();
			gbc_txtTitulo.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtTitulo.insets = new Insets(0, 0, 5, 0);
			gbc_txtTitulo.gridx = 2;
			gbc_txtTitulo.gridy = 0;
			panelDatos.add(txtTitulo, gbc_txtTitulo);
			txtTitulo.setEnabled(false);
			txtTitulo.setColumns(10);
			
			JLabel lblDescripcion = new JLabel("Descripcion:");
			GridBagConstraints gbc_lblDescripcion = new GridBagConstraints();
			gbc_lblDescripcion.anchor = GridBagConstraints.EAST;
			gbc_lblDescripcion.insets = new Insets(0, 0, 5, 5);
			gbc_lblDescripcion.gridx = 0;
			gbc_lblDescripcion.gridy = 1;
			panelDatos.add(lblDescripcion, gbc_lblDescripcion);
			
			txtDescripcion = new JTextField();
			GridBagConstraints gbc_txtDescripcion = new GridBagConstraints();
			gbc_txtDescripcion.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtDescripcion.insets = new Insets(0, 0, 5, 0);
			gbc_txtDescripcion.gridx = 2;
			gbc_txtDescripcion.gridy = 1;
			panelDatos.add(txtDescripcion, gbc_txtDescripcion);
			txtDescripcion.setEnabled(false);
			txtDescripcion.setEditable(true);
			txtDescripcion.setText("");
			txtDescripcion.setColumns(10);
			
			JLabel lblMontoNecesario = new JLabel("Monto Necesario:");
			GridBagConstraints gbc_lblMontoNecesario = new GridBagConstraints();
			gbc_lblMontoNecesario.anchor = GridBagConstraints.EAST;
			gbc_lblMontoNecesario.insets = new Insets(0, 0, 5, 5);
			gbc_lblMontoNecesario.gridx = 0;
			gbc_lblMontoNecesario.gridy = 2;
			panelDatos.add(lblMontoNecesario, gbc_lblMontoNecesario);
			
			txtMontoNecesario = new JTextField();
			GridBagConstraints gbc_txtMontoNecesario = new GridBagConstraints();
			gbc_txtMontoNecesario.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtMontoNecesario.insets = new Insets(0, 0, 5, 0);
			gbc_txtMontoNecesario.gridx = 2;
			gbc_txtMontoNecesario.gridy = 2;
			panelDatos.add(txtMontoNecesario, gbc_txtMontoNecesario);
			txtMontoNecesario.setEnabled(false);
			txtMontoNecesario.setColumns(10);
			
			JLabel lblFechaEspectaculo = new JLabel("Fecha espectaculo:");
			GridBagConstraints gbc_lblFechaEspectaculo = new GridBagConstraints();
			gbc_lblFechaEspectaculo.anchor = GridBagConstraints.EAST;
			gbc_lblFechaEspectaculo.insets = new Insets(0, 0, 5, 5);
			gbc_lblFechaEspectaculo.gridx = 0;
			gbc_lblFechaEspectaculo.gridy = 3;
			panelDatos.add(lblFechaEspectaculo, gbc_lblFechaEspectaculo);
			
			txtFechaEspectaculo = new JTextField();
			GridBagConstraints gbc_txtFechaEspectaculo = new GridBagConstraints();
			gbc_txtFechaEspectaculo.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtFechaEspectaculo.insets = new Insets(0, 0, 5, 0);
			gbc_txtFechaEspectaculo.gridx = 2;
			gbc_txtFechaEspectaculo.gridy = 3;
			panelDatos.add(txtFechaEspectaculo, gbc_txtFechaEspectaculo);
			txtFechaEspectaculo.setEnabled(false);
			txtFechaEspectaculo.setColumns(10);
			
			JLabel lblFechaPublicacion = new JLabel("Fecha de publicacion:");
			GridBagConstraints gbc_lblFechaPublicacion = new GridBagConstraints();
			gbc_lblFechaPublicacion.anchor = GridBagConstraints.EAST;
			gbc_lblFechaPublicacion.insets = new Insets(0, 0, 5, 5);
			gbc_lblFechaPublicacion.gridx = 0;
			gbc_lblFechaPublicacion.gridy = 4;
			panelDatos.add(lblFechaPublicacion, gbc_lblFechaPublicacion);
			
			txtFechaPublicacion = new JTextField();
			GridBagConstraints gbc_txtFechaPublicacion = new GridBagConstraints();
			gbc_txtFechaPublicacion.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtFechaPublicacion.insets = new Insets(0, 0, 5, 0);
			gbc_txtFechaPublicacion.gridx = 2;
			gbc_txtFechaPublicacion.gridy = 4;
			panelDatos.add(txtFechaPublicacion, gbc_txtFechaPublicacion);
			txtFechaPublicacion.setEnabled(false);
			txtFechaPublicacion.setColumns(10);
			
			JLabel lblImagen = new JLabel("Imagen:");
			GridBagConstraints gbc_lblImagen = new GridBagConstraints();
			gbc_lblImagen.anchor = GridBagConstraints.EAST;
			gbc_lblImagen.insets = new Insets(0, 0, 5, 5);
			gbc_lblImagen.gridx = 0;
			gbc_lblImagen.gridy = 5;
			panelDatos.add(lblImagen, gbc_lblImagen);
			
			txtImagen = new JTextField();
			GridBagConstraints gbc_txtImagen = new GridBagConstraints();
			gbc_txtImagen.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtImagen.insets = new Insets(0, 0, 5, 0);
			gbc_txtImagen.gridx = 2;
			gbc_txtImagen.gridy = 5;
			panelDatos.add(txtImagen, gbc_txtImagen);
			txtImagen.setEnabled(false);
			txtImagen.setColumns(10);
			
			JLabel lblLugar = new JLabel("Lugar:");
			GridBagConstraints gbc_lblLugar = new GridBagConstraints();
			gbc_lblLugar.anchor = GridBagConstraints.EAST;
			gbc_lblLugar.insets = new Insets(0, 0, 5, 5);
			gbc_lblLugar.gridx = 0;
			gbc_lblLugar.gridy = 6;
			panelDatos.add(lblLugar, gbc_lblLugar);
			
			txtLugar = new JTextField();
			GridBagConstraints gbc_txtLugar = new GridBagConstraints();
			gbc_txtLugar.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtLugar.insets = new Insets(0, 0, 5, 0);
			gbc_txtLugar.gridx = 2;
			gbc_txtLugar.gridy = 6;
			panelDatos.add(txtLugar, gbc_txtLugar);
			txtLugar.setText((String) null);
			txtLugar.setEnabled(false);
			txtLugar.setColumns(10);
			
			JLabel lblPrecioEntrada = new JLabel("Precio entrada:");
			GridBagConstraints gbc_lblPrecioEntrada = new GridBagConstraints();
			gbc_lblPrecioEntrada.anchor = GridBagConstraints.EAST;
			gbc_lblPrecioEntrada.insets = new Insets(0, 0, 5, 5);
			gbc_lblPrecioEntrada.gridx = 0;
			gbc_lblPrecioEntrada.gridy = 7;
			panelDatos.add(lblPrecioEntrada, gbc_lblPrecioEntrada);
			
			txtPrecioEntrada = new JTextField();
			GridBagConstraints gbc_txtPrecioEntrada = new GridBagConstraints();
			gbc_txtPrecioEntrada.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtPrecioEntrada.insets = new Insets(0, 0, 5, 0);
			gbc_txtPrecioEntrada.gridx = 2;
			gbc_txtPrecioEntrada.gridy = 7;
			panelDatos.add(txtPrecioEntrada, gbc_txtPrecioEntrada);
			txtPrecioEntrada.setText((String) null);
			txtPrecioEntrada.setEnabled(false);
			txtPrecioEntrada.setColumns(10);
			
			JLabel lblTipo = new JLabel("Tipo:");
			GridBagConstraints gbc_lblTipo = new GridBagConstraints();
			gbc_lblTipo.anchor = GridBagConstraints.EAST;
			gbc_lblTipo.insets = new Insets(0, 0, 5, 5);
			gbc_lblTipo.gridx = 0;
			gbc_lblTipo.gridy = 8;
			panelDatos.add(lblTipo, gbc_lblTipo);
			
			txtTipo = new JTextField();
			GridBagConstraints gbc_txtTipo = new GridBagConstraints();
			gbc_txtTipo.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtTipo.insets = new Insets(0, 0, 5, 0);
			gbc_txtTipo.gridx = 2;
			gbc_txtTipo.gridy = 8;
			panelDatos.add(txtTipo, gbc_txtTipo);
			txtTipo.setText((String) null);
			txtTipo.setEnabled(false);
			txtTipo.setColumns(10);
			
			JLabel lblMontoRecaudado = new JLabel("Monto recaudado:");
			GridBagConstraints gbc_lblMontoRecaudado = new GridBagConstraints();
			gbc_lblMontoRecaudado.anchor = GridBagConstraints.EAST;
			gbc_lblMontoRecaudado.insets = new Insets(0, 0, 0, 5);
			gbc_lblMontoRecaudado.gridx = 0;
			gbc_lblMontoRecaudado.gridy = 9;
			panelDatos.add(lblMontoRecaudado, gbc_lblMontoRecaudado);
			
			txtMontoRecaudado = new JTextField();
			GridBagConstraints gbc_txtMontoRecaudado = new GridBagConstraints();
			gbc_txtMontoRecaudado.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtMontoRecaudado.gridx = 2;
			gbc_txtMontoRecaudado.gridy = 9;
			panelDatos.add(txtMontoRecaudado, gbc_txtMontoRecaudado);
			txtMontoRecaudado.setText((String) null);
			txtMontoRecaudado.setEnabled(false);
			txtMontoRecaudado.setColumns(10);
		}
		
		panelColaboraciones = new JPanel();
		panelColaboraciones.setBorder(new TitledBorder(null, "Colaboraciones", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelColaboraciones.setBounds(10, 305, 594, 271);
		add(panelColaboraciones);
		panelColaboraciones.setLayout(null);
		
		tableColaboradores = new JTable();
		tableColaboradores.setBounds(10, 24, 519, 198);
		panelColaboraciones.add(tableColaboradores);
		
		
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
