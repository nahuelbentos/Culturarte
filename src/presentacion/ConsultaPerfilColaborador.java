package presentacion;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JTextField;

import excepciones.PropuestaNoExisteException;
import excepciones.UsuarioNoExisteElUsuarioException;
import logica.IUsuarioController;
import presentacion.gen.ListarColaboradores;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTable;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import datatype.DtPerfilColaborador;
import datatype.DtPropuesta;
import datatype.DtPropuestaColaborada;
import datatype.EstadoPropuesta;

import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class ConsultaPerfilColaborador extends JInternalFrame {

	/**
	 * Create the frame.
	 * @throws UsuarioNoExisteElUsuarioException 
	 */
//	private VerPerfilColaborador verPerfilColaborador;
	private String nickname;
	public ListarColaboradores listarColaboradores;
	private JTextField txtNickname;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtEmail;
	private JTextField txtFechaNacimiento;
	private JTable tableColaboracionesHechas;
	private final Object[] columnNames = { "Titulo", "Descripcion", //"Imagen",
            "Prop. Nickname", "Prop. Nombre", "Prop. Apellido", "Prop. Email", "M. Aportado", "Estado" };
	private Object[][] data;
	private JLabel lblImagen;
	private IUsuarioController iUsuController;
	private DtPerfilColaborador dtc;
	private JPanel panelColaboraciones;
	
	public ConsultaPerfilColaborador(IUsuarioController IUC) throws UsuarioNoExisteElUsuarioException, PropuestaNoExisteException, PropertyVetoException{
		setClosable(true);
		iUsuController=IUC;

//		setNormalBounds(new Rectangle(0, 0, 0, 50));
		//setBounds(100, 100, 673, 425);
		setBounds(10, 10, 1045, 905);
        setIconifiable(true);
        getContentPane().setLayout(null);
        
        JPanel panelListarColaboradores = new JPanel();
        panelListarColaboradores.setBounds(0, 0, 400, 250);
        getContentPane().add(panelListarColaboradores);
        panelListarColaboradores.setLayout(null);
        
        listarColaboradores = new ListarColaboradores(IUC);
        listarColaboradores.actualizarColaboradores();
        listarColaboradores.setBounds(0, 0, 400, 250);
        panelListarColaboradores.add(listarColaboradores);
        
        
        JPanel panelPerfil = new JPanel();
        panelPerfil.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Perfil", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelPerfil.setBounds(596, 12, 270, 207);
        getContentPane().add(panelPerfil);
        GridBagLayout gbl_panelPerfil = new GridBagLayout();
        gbl_panelPerfil.columnWidths = new int[]{0, 0, 0, 0};
        gbl_panelPerfil.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
        gbl_panelPerfil.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_panelPerfil.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        panelPerfil.setLayout(gbl_panelPerfil);
        
        JLabel lblNickname = new JLabel("Nickname:");
        GridBagConstraints gbc_lblNickname = new GridBagConstraints();
        gbc_lblNickname.insets = new Insets(0, 0, 5, 5);
        gbc_lblNickname.gridx = 1;
        gbc_lblNickname.gridy = 0;
        panelPerfil.add(lblNickname, gbc_lblNickname);
        
        txtNickname = new JTextField();
        txtNickname.setEnabled(false);
        txtNickname.setColumns(10);
        GridBagConstraints gbc_txtNickname = new GridBagConstraints();
        gbc_txtNickname.insets = new Insets(0, 0, 5, 0);
        gbc_txtNickname.gridx = 2;
        gbc_txtNickname.gridy = 0;
        panelPerfil.add(txtNickname, gbc_txtNickname);
        
        JLabel lblNombre = new JLabel("Nombre:");
        GridBagConstraints gbc_lblNombre = new GridBagConstraints();
        gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
        gbc_lblNombre.gridx = 1;
        gbc_lblNombre.gridy = 1;
        panelPerfil.add(lblNombre, gbc_lblNombre);
        
        txtNombre = new JTextField();
        txtNombre.setText("");
        txtNombre.setEnabled(false);
        txtNombre.setEditable(true);
        txtNombre.setColumns(10);
        GridBagConstraints gbc_txtNombre = new GridBagConstraints();
        gbc_txtNombre.insets = new Insets(0, 0, 5, 0);
        gbc_txtNombre.gridx = 2;
        gbc_txtNombre.gridy = 1;
        panelPerfil.add(txtNombre, gbc_txtNombre);
        
        JLabel lblApellido = new JLabel("Apellido:");
        GridBagConstraints gbc_lblApellido = new GridBagConstraints();
        gbc_lblApellido.insets = new Insets(0, 0, 5, 5);
        gbc_lblApellido.gridx = 1;
        gbc_lblApellido.gridy = 2;
        panelPerfil.add(lblApellido, gbc_lblApellido);
        
        txtApellido = new JTextField();
        txtApellido.setEnabled(false);
        txtApellido.setColumns(10);
        GridBagConstraints gbc_txtApellido = new GridBagConstraints();
        gbc_txtApellido.insets = new Insets(0, 0, 5, 0);
        gbc_txtApellido.gridx = 2;
        gbc_txtApellido.gridy = 2;
        panelPerfil.add(txtApellido, gbc_txtApellido);
        
        JLabel lblEmail = new JLabel("Email:");
        GridBagConstraints gbc_lblEmail = new GridBagConstraints();
        gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
        gbc_lblEmail.gridx = 1;
        gbc_lblEmail.gridy = 3;
        panelPerfil.add(lblEmail, gbc_lblEmail);
        
        txtEmail = new JTextField();
        txtEmail.setEnabled(false);
        txtEmail.setColumns(10);
        GridBagConstraints gbc_txtEmail = new GridBagConstraints();
        gbc_txtEmail.insets = new Insets(0, 0, 5, 0);
        gbc_txtEmail.gridx = 2;
        gbc_txtEmail.gridy = 3;
        panelPerfil.add(txtEmail, gbc_txtEmail);
        
        JLabel lblFechaNacimiento = new JLabel("F. Nacimiento:");
        GridBagConstraints gbc_lblFechaNacimiento = new GridBagConstraints();
        gbc_lblFechaNacimiento.insets = new Insets(0, 0, 0, 5);
        gbc_lblFechaNacimiento.gridx = 1;
        gbc_lblFechaNacimiento.gridy = 4;
        panelPerfil.add(lblFechaNacimiento, gbc_lblFechaNacimiento);
        
        txtFechaNacimiento = new JTextField();
        txtFechaNacimiento.setEnabled(false);
        txtFechaNacimiento.setColumns(10);
        GridBagConstraints gbc_txtFechaNacimiento = new GridBagConstraints();
        gbc_txtFechaNacimiento.gridx = 2;
        gbc_txtFechaNacimiento.gridy = 4;
        panelPerfil.add(txtFechaNacimiento, gbc_txtFechaNacimiento);
        
        lblImagen = new JLabel("");
        lblImagen.setBounds(428, 12, 150, 150);
        getContentPane().add(lblImagen);
        
        panelColaboraciones = new JPanel();
        panelColaboraciones.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Colaboraciones", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelColaboraciones.setBounds(10, 267, 918, 250);
        getContentPane().add(panelColaboraciones);
        panelColaboraciones.setLayout(null);
        

//		data = new Object[1][columnNames.length];
//		tableColaboracionesHechas = new JTable(data,columnNames);
        tableColaboracionesHechas = new JTable();
     
        tableColaboracionesHechas.setBounds(12, 37, 850, 172);
	    tableColaboracionesHechas.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        panelColaboraciones.add(tableColaboracionesHechas);
        
        

        
		if(listarColaboradores.getColaboradorSeleccionado() != null)
			cargarPerfil(listarColaboradores.getColaboradorSeleccionado());
		else {
			cargarPerfil(listarColaboradores.getPrimerColaborador());
		}	
        
        JButton btnVerPerfil = new JButton("Ver Perfil");
        btnVerPerfil.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		if (listarColaboradores.getColaboradorSeleccionado() != null) 
					cargarPerfil(listarColaboradores.getColaboradorSeleccionado());
        	}
        });
        
        btnVerPerfil.setBounds(405, 225, 114, 25);
        getContentPane().add(btnVerPerfil);
        

	}
	
public void agregarDatosTable(ArrayList<DtPropuestaColaborada> ColaboracionesHechas) {
		
		tableColaboracionesHechas.removeAll();
		DefaultTableModel dm = new DefaultTableModel();
		
		while (dm.getRowCount()>0) {
			dm.removeRow(0);
        }
		
	    String header[] = new String[] { "Titulo", "Descripcion", //"Imagen",
	            "Prop. Nickname", "Prop. Nombre", "Prop. Apellido", "Prop. Email", "M. Aportado", "Estado" };
	    dm.setColumnIdentifiers(header);
	    
	    dm.addRow(header);
	    
	    tableColaboracionesHechas.setModel(dm);
	    tableColaboracionesHechas.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	    System.out.println("ColaboracionesHechas.size(): " + ColaboracionesHechas.size() + "\n"); 
	    for (DtPropuestaColaborada dtp : ColaboracionesHechas) {
		
	        Vector<Object> data = new Vector<Object>();
	        data.add(dtp.getTitulo());
	        data.add(dtp.getDescripcion());
//	        data.add(dtp.getImagen());
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
	SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
	dtc =  iUsuController.verPerfilColaborador(nickname);
	if(dtc.getImagen() != null) {
		ImageIcon imageIcon = new ImageIcon(dtc.getImagen());
		lblImagen.setIcon(imageIcon);
	}
	txtNickname.setText(dtc.getNickname());
	txtNombre.setText(dtc.getNombre());
	txtApellido.setText(dtc.getApellido());
	txtEmail.setText(dtc.getEmail());
	if(dtc.getFechaNacimiento()!=null)
		txtFechaNacimiento.setText(sdf.format(dtc.getFechaNacimiento().getTime()));
	
	
	if(dtc.getColaboracionesHechas()!=null)
		agregarDatosTable(dtc.getColaboracionesHechas());
//	cargarColaboracionesPerfil(dtc.getColaboracionesHechas());
	
}

public void cargarColaboracionesPerfil(ArrayList<DtPropuestaColaborada> ColaboracionesHechas) {
	
	data = new Object[ColaboracionesHechas.size()][columnNames.length];
	
	for (int i = 0; i < ColaboracionesHechas.size(); i++) {
		for (int j = 0; j < columnNames.length; j++) {
			switch (j) {
			case 0:
				data[i][j] = ColaboracionesHechas.get(i).getTitulo();
				break;
			case 1:
				data[i][j] = ColaboracionesHechas.get(i).getDescripcion();
				break;
			case 2:
				data[i][j] = ColaboracionesHechas.get(i).getProponenteACargo().getNickname();
				break;
			case 3:
				data[i][j] = ColaboracionesHechas.get(i).getProponenteACargo().getNombre();
				break;
			case 4:
				data[i][j] = ColaboracionesHechas.get(i).getProponenteACargo().getApellido();
				break;
			case 5:
				data[i][j] = ColaboracionesHechas.get(i).getProponenteACargo().getEmail();
				break;
			case 6:
				data[i][j] = ColaboracionesHechas.get(i).getMontoAportado();
				break;
			case 7:
				data[i][j] = ColaboracionesHechas.get(i).getEstadoActual();
				break;
			}
			System.out.println("Titulo :- " + ColaboracionesHechas.get(i).getTitulo());
		}
		
	}
		
	panelColaboraciones.remove(tableColaboracionesHechas);
	// borro la grilla que estaba.
	
	
	// inicilizo la grilla para refrescar los datos
	tableColaboracionesHechas = new JTable(data,columnNames) {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public boolean isCellEditable(int rowIndex, int vColIndex) {
            return false;
		}
	};
  tableColaboracionesHechas.setBounds(12, 37, 850, 172);
  tableColaboracionesHechas.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
  panelColaboraciones.add(tableColaboracionesHechas);
	
}

private void limpiarPanel() {
	lblImagen.setIcon(null);
	txtNickname.setText("");
	txtNombre.setText("");
	txtApellido.setText("");
	txtEmail.setText("");
	txtFechaNacimiento.setText("");
	
//	initTableGrilla(true, EstadoPropuesta.ingresada);
	//initPanelPropuestas(true, EstadoPropuesta.ingresada);
}
}
