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
	private JTable tablePropuestaPublicada;
	
	private Object[][] data;
	
	private final Object[] columnNames = { 
	                              "Titulo:",
	                              "Por:"};	
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
		tablePropuestaPublicada.setBounds(137, 284, 168, 100);
		add(tablePropuestaPublicada);
		
//		DtPerfilProponente dtp = iUsuController.verPerfilProponente(nickname);
		modelTitulos = new DefaultListModel<String>();
		listPropuestas = new JList<>(modelTitulos);
		

		
		setTable(IUC, nickname);
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
		txtImagen.setText(dtp2.getImagen());
		txtDireccion.setText(dtp2.getDireccion());
		txtBiografia.setText(dtp2.getBiografia());
		txtLinkWeb.setText(dtp2.getSitioWeb());	
		
		
		ArrayList<DtPropuesta> prPublicadas = dtp2.getPrPublicadas();
		ArrayList<DtPropuesta> prCanceladas = dtp2.getPrCanceladas();
		ArrayList<DtPropuesta> prEnFinanciacion = dtp2.getPrEnFinanciacion();
		ArrayList<DtPropuesta> prFinanciadas= dtp2.getPrFinanciadas();
		ArrayList<DtPropuesta> prNoFinanciadas = dtp2.getPrNoFinanciadas();
		data = new Object[prPublicadas.size()][columnNames.length];
		System.out.println("armo la table prPublicadas.size: " + prPublicadas.size() +  " \n");
		System.out.println("armo la table prCanceladas.size: " + prCanceladas.size() +  " \n");
		System.out.println("armo la table prEnFinanciacion.size: " + prEnFinanciacion.size() +  " \n");
		System.out.println("armo la table prFinanciadas.size: " + prFinanciadas.size() +  " \n");
		System.out.println("armo la table prNoFinanciadas.size: " + prNoFinanciadas.size() +  " \n");
		for (int i = 0; i < prPublicadas.size(); i++) {
			for (int j = 0; j < columnNames.length; j++) {
				switch (j) {
				case 0:
					data[i][j] = prPublicadas.get(i).getTitulo();
					System.out.println("Titulo " + i + " " + prPublicadas.get(i).getTitulo() + "\n");
					break;
				case 1:
					data[i][j] = prPublicadas.get(i).getDescripcion();
					System.out.println("Titulo " + i + " " + prPublicadas.get(i).getTitulo() + "\n");
					break;
				}
			}
			
		}
		
		tablePropuestaPublicada = new JTable(data, columnNames);
				
		
		System.out.println("Termino setListaDeProponentes \n");
	}
}
