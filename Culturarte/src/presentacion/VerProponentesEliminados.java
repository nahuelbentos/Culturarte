package presentacion;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import datatype.DtColaboracion;
import datatype.DtDatosPropuesta;
import datatype.DtProponente;
import datatype.DtPropuesta;
import datatype.DtPropuestaMinificado;
import datatype.DtUsuario;
import datatype.TipoRetorno;
import excepciones.NoExistenProponentesEliminadosException;
import excepciones.PropuestaNoExisteException;
import excepciones.UsuarioNoExisteElUsuarioException;
import logica.IPropuestaController;
import logica.IUsuarioController;
import java.awt.Color;

@SuppressWarnings("serial")
public class VerProponentesEliminados extends JInternalFrame {

	private IUsuarioController iUsuarioController;
	private IPropuestaController iPropuestaController;
	
	private JComboBox<String> cmbUsuario;
	private JComboBox<String> cmbPropuestas;
	private JLabel lblSeleecionarUsuario;
	private JLabel lblSeleccionarPropuesta;
	private static final String TEXTO_COMBO_UNO = "No hay proponentes eliminados en el sistema";
	private static final String TEXTO_COMBO_DOS = "El proponente no había ingresado propuestas";
	private static final String TEXTO_COMBO_TRES = "No se habían registrado colaboraciones";
	private static final String TEXTO_COMBO_DOS_INICIAL = "Seleccione una de sus propuestas";
	private static final String TEXTO_COMBO_TRES_INICIAL = "Seleccione una de sus colaboraciones";
	private JTextField txtNickname;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtEmail;
	private JTextField txtFechaDeNacimiento;
	private JTextField txtFechaDeEliminacion;
	private JLabel lblImagen;
	
	private JTextField entTitulo;
	private JTextField entDescripcion;
	private JTextField entMontoNecesario;
	private JTextField entFechaEspectaculo;
	private JTextField entFechaPublicacion;
	private JTextField entLugar;
	private JTextField entPrecioEntrada;
	private JTextField entMontoRecaudado;
	private JTextField entTipoRetorno;
	private JLabel lblNewLabel;
	private JLabel lblDescripcin;
	private JLabel lblMontoNecesario;
	private JLabel lblFechaEspectculo;
	private JLabel lblFechaDePublicacin;
	private JLabel lblLugar;
	private JLabel lblPrecioEntrada;
	private JLabel lblTipoRetorno;
	private JLabel lblMontoRecaudado;
	private JLabel lblImagenPropuesta;
	private JTable tablaPropuestas;
	private Double monto;
	private JLabel lblColaboraciones;
	private JLabel lblMensaje;
	
	public VerProponentesEliminados(IUsuarioController IUC, IPropuestaController IPC) {
		
		iUsuarioController = IUC;
		iPropuestaController = IPC;
		
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        getContentPane().setLayout(null);
        setTitle("Ver Proponentes Eliminados");
        setBounds(10, 10, 1110, 648);
		
        getContentPane().setLayout(null);
		
		cmbUsuario = new JComboBox<String>();
		cmbUsuario.setBounds(28, 116, 230, 20);
		cmbUsuario.addItemListener(new ItemListener() {	
			@Override
			public void itemStateChanged(ItemEvent e) {
				if ((e.getStateChange() == ItemEvent.SELECTED)) {
	                Object selected = cmbUsuario.getSelectedItem();
	                if (!selected.toString().equals(TEXTO_COMBO_UNO)) {
	                	try {
							actualizarDatos(e);
						} catch (UsuarioNoExisteElUsuarioException e1) {
							e1.printStackTrace();
						}
	                }
				}
			}
		});
		
		getContentPane().add(cmbUsuario);
		
		cmbPropuestas = new JComboBox<String>();
		cmbPropuestas.setBounds(312, 116, 230, 20);
		cmbPropuestas.addItemListener(new ItemListener() {	
			@Override
			public void itemStateChanged(ItemEvent e) {
				if ((e.getStateChange() == ItemEvent.SELECTED)) {
	                Object selected = cmbPropuestas.getSelectedItem();
	                if (!selected.toString().equals(TEXTO_COMBO_DOS_INICIAL) && 
	                		!selected.toString().equals(TEXTO_COMBO_DOS)) {
	                	setearDatosPropuesta();
	                }
				}
			}
		});
		getContentPane().add(cmbPropuestas);
		cmbPropuestas.addItem(TEXTO_COMBO_DOS_INICIAL);
		
		lblSeleecionarUsuario = new JLabel("<html>Seleecione uno de los proponentes <br/>para ver sus datos:</html>");
		lblSeleecionarUsuario.setBounds(28, 36, 197, 69);
		getContentPane().add(lblSeleecionarUsuario);
		
		lblSeleccionarPropuesta = new JLabel("<html>Seleecione una de sus propuestas<br/>para ver "
				+ "sus detalles y colaboraciones:</html>");
		lblSeleccionarPropuesta.setBounds(312, 36, 197, 69);
		getContentPane().add(lblSeleccionarPropuesta);
			
		txtNickname = new JTextField();
		txtNickname.setEditable(false);
		txtNickname.setColumns(10);
		txtNickname.setBounds(118, 345, 160, 20);
		getContentPane().add(txtNickname);
		
		JLabel label = new JLabel("Nickname:");
		label.setBounds(28, 348, 80, 14);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Nombre:");
		label_1.setBounds(28, 379, 80, 14);
		getContentPane().add(label_1);
		
		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setColumns(10);
		txtNombre.setBounds(118, 376, 160, 20);
		getContentPane().add(txtNombre);
		
		txtApellido = new JTextField();
		txtApellido.setEditable(false);
		txtApellido.setColumns(10);
		txtApellido.setBounds(118, 406, 160, 20);
		getContentPane().add(txtApellido);
		
		JLabel label_2 = new JLabel("Apellido:");
		label_2.setBounds(28, 409, 80, 14);
		getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("E-mail:");
		label_3.setBounds(28, 437, 80, 14);
		getContentPane().add(label_3);
		
		txtEmail = new JTextField();
		txtEmail.setEditable(false);
		txtEmail.setColumns(10);
		txtEmail.setBounds(118, 437, 160, 20);
		getContentPane().add(txtEmail);
		
		JLabel label_4 = new JLabel("<html>Fecha de <br/>nacimiento:</html>");
		label_4.setBounds(28, 465, 63, 28);
		getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("<html>Fecha de <br/>eliminación:</html>");
		label_5.setBounds(28, 507, 65, 28);
		getContentPane().add(label_5);
		
		txtFechaDeNacimiento = new JTextField();
		txtFechaDeNacimiento.setEditable(false);
		txtFechaDeNacimiento.setColumns(10);
		txtFechaDeNacimiento.setBounds(118, 473, 160, 20);
		getContentPane().add(txtFechaDeNacimiento);
		
		txtFechaDeEliminacion = new JTextField();
		txtFechaDeEliminacion.setEditable(false);
		txtFechaDeEliminacion.setColumns(10);
		txtFechaDeEliminacion.setBounds(118, 504, 160, 20);
		getContentPane().add(txtFechaDeEliminacion);
		
		lblImagen = new JLabel("");
		lblImagen.setBounds(52, 168, 150, 150);
		getContentPane().add(lblImagen);
		
		entTitulo = new JTextField();
		entTitulo.setEditable(false);
		entTitulo.setBounds(475, 338, 160, 19);
		getContentPane().add(entTitulo);
		entTitulo.setColumns(10);
		
		entDescripcion = new JTextField();
		entDescripcion.setEditable(false);
		entDescripcion.setColumns(10);
		entDescripcion.setBounds(475, 365, 160, 19);
		getContentPane().add(entDescripcion);
		
		entMontoNecesario = new JTextField();
		entMontoNecesario.setEditable(false);
		entMontoNecesario.setColumns(10);
		entMontoNecesario.setBounds(475, 388, 160, 19);
		getContentPane().add(entMontoNecesario);
		
		entFechaEspectaculo = new JTextField();
		entFechaEspectaculo.setEditable(false);
		entFechaEspectaculo.setBounds(475, 411, 160, 19);
		getContentPane().add(entFechaEspectaculo);
		
		entFechaPublicacion = new JTextField();
		entFechaPublicacion.setEditable(false);
		entFechaPublicacion.setBounds(475, 438, 160, 19);
		getContentPane().add(entFechaPublicacion);
		
		entLugar = new JTextField();
		entLugar.setEditable(false);
		entLugar.setColumns(10);
		entLugar.setBounds(475, 469, 160, 19);
		getContentPane().add(entLugar);
		
		entPrecioEntrada = new JTextField();
		entPrecioEntrada.setEditable(false);
		entPrecioEntrada.setColumns(10);
		entPrecioEntrada.setBounds(475, 500, 160, 19);
		getContentPane().add(entPrecioEntrada);
		
		entMontoRecaudado = new JTextField();
		entMontoRecaudado.setEditable(false);
		entMontoRecaudado.setColumns(10);
		entMontoRecaudado.setBounds(475, 554, 160, 19);
		getContentPane().add(entMontoRecaudado);
		
		lblNewLabel = new JLabel("Título:");
		lblNewLabel.setBounds(312, 340, 156, 15);
		getContentPane().add(lblNewLabel);
		
		lblDescripcin = new JLabel("Descripción:");
		lblDescripcin.setBounds(312, 365, 156, 15);
		getContentPane().add(lblDescripcin);
		
		lblMontoNecesario = new JLabel("Monto necesario:");
		lblMontoNecesario.setBounds(312, 388, 156, 15);
		getContentPane().add(lblMontoNecesario);
		
		lblFechaEspectculo = new JLabel("Fecha espectáculo:");
		lblFechaEspectculo.setBounds(312, 411, 156, 15);
		getContentPane().add(lblFechaEspectculo);
		
		lblFechaDePublicacin = new JLabel("Fecha de publicación:");
		lblFechaDePublicacin.setBounds(312, 438, 156, 15);
		getContentPane().add(lblFechaDePublicacin);
		
		lblLugar = new JLabel("Lugar");
		lblLugar.setBounds(312, 469, 156, 15);
		getContentPane().add(lblLugar);
		
		lblPrecioEntrada = new JLabel("Precio entrada:");
		lblPrecioEntrada.setBounds(312, 500, 156, 15);
		getContentPane().add(lblPrecioEntrada);
		
		lblTipoRetorno = new JLabel("Tipo retorno");
		lblTipoRetorno.setBounds(312, 530, 156, 15);
		getContentPane().add(lblTipoRetorno);
		
		lblMontoRecaudado = new JLabel("Monto recaudado:");
		lblMontoRecaudado.setBounds(312, 554, 156, 15);
		getContentPane().add(lblMontoRecaudado);
		
		lblImagenPropuesta = new JLabel("");
		lblImagenPropuesta.setBounds(370, 168, 150, 150);
		getContentPane().add(lblImagenPropuesta);
		
		entTipoRetorno = new JTextField();
		entTipoRetorno.setEditable(false);
		entTipoRetorno.setBounds(475, 525, 160, 24);
		getContentPane().add(entTipoRetorno);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(658, 108, 392, 427);
		getContentPane().add(scrollPane);
		scrollPane.setEnabled(false);
		
		tablaPropuestas = new JTable() {
			public boolean isCellEditable(int rowIndex, int vColIndex) {
	            return false;
			}
		};
		tablaPropuestas.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Colaborador", "Monto", "Tipo de retorno", "Fecha"
			}
		));
		scrollPane.setViewportView(tablaPropuestas);
		
		lblColaboraciones = new JLabel("Colaboraciones:");
		lblColaboraciones.setBounds(658, 56, 124, 14);
		getContentPane().add(lblColaboraciones);
		
		lblMensaje = new JLabel("No hay colaboraciones registradas para esta propuesta.");
		lblMensaje.setForeground(Color.RED);
		lblMensaje.setBounds(658, 83, 320, 14);
		lblMensaje.setVisible(false);
		getContentPane().add(lblMensaje);
		
	}
	
	protected void actualizarDatos(ItemEvent arg0) throws UsuarioNoExisteElUsuarioException {
		try {
			setearPerfilProponente();
			setListaDePropuestas(txtNickname.getText());
        } catch (UsuarioNoExisteElUsuarioException e) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error inesperado", "Ver Proponentes Eliminados", JOptionPane.ERROR_MESSAGE);
        }
	}
	
	public void setListaDeProponentesEliminados() {
		cmbUsuario.removeAllItems();
        DtUsuario[] usuarios;
		try {
			usuarios = iUsuarioController.verProponentesEliminados();
            for (int i = 0; i < usuarios.length; i++) {
            	cmbUsuario.addItem(usuarios[i].getNickname());
            }
		} catch (NoExistenProponentesEliminadosException e) {
			cmbUsuario.addItem(TEXTO_COMBO_UNO);
		}
	}
	
	private void setListaDePropuestas(String nicknameProponente) {
		cmbPropuestas.removeAllItems();
        DtPropuestaMinificado[] propuestas;
		try {
			propuestas = iPropuestaController.listarPropuestasProponente(nicknameProponente);
            for (int i = 0; i < propuestas.length; i++) {
            	cmbPropuestas.addItem(propuestas[i].getTitulo());
            }
		} catch (PropuestaNoExisteException e) {
			cmbPropuestas.addItem(TEXTO_COMBO_DOS);
		}
	}
	
	private void setearDatosPropuesta(){
		DtDatosPropuesta dtPropuesta = iPropuestaController.consultarPropuesta(cmbPropuestas.getSelectedItem().toString());
		if(dtPropuesta.getImagen() != null) {
			ImageIcon imageIcon = new ImageIcon(dtPropuesta.getImagen());
			lblImagenPropuesta.setIcon(imageIcon);
		}
		entTitulo.setText(dtPropuesta.getTitulo());
		entDescripcion.setText(dtPropuesta.getDescripcion());
		entMontoNecesario.setText(Double.toString(dtPropuesta.getMontoNecesario()));
		if (dtPropuesta.getFechaEspecatulo().getTime() != null)
			entFechaEspectaculo.setText(dtPropuesta.getFechaEspecatulo().toZonedDateTime()
				       .format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		if (dtPropuesta.getFechaPublicacion() != null)
			entFechaPublicacion.setText(dtPropuesta.getFechaPublicacion().toZonedDateTime()
				       .format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		entLugar.setText(dtPropuesta.getLugar());
		entPrecioEntrada.setText(Double.toString(dtPropuesta.getPrecioEntrada()));
		entTipoRetorno.setText(dtPropuesta.getTipo().toString());
		entMontoRecaudado.setText(Double.toString(dtPropuesta.getRecaudado()));
		DtColaboracion[] dtColaboraciones = iPropuestaController.listarColaboraciones(dtPropuesta.getTitulo());
		if (dtColaboraciones != null) {
			listarColaboraciones(dtColaboraciones);
		} else {
			lblMensaje.setVisible(true);
		}
	}
	
	private void setearPerfilProponente() throws UsuarioNoExisteElUsuarioException {
		DtUsuario dtUsuario = iUsuarioController.verPerfilUsuario(cmbUsuario.getSelectedItem().toString());
		DtProponente dtProponente = (DtProponente) dtUsuario;
		txtNickname.setText(dtProponente.getNickname());
		txtNombre.setText(dtProponente.getNombre());
		txtApellido.setText(dtProponente.getApellido());
		txtEmail.setText(dtProponente.getEmail());
		txtFechaDeNacimiento.setText(dtProponente.getFechaNacimiento().toZonedDateTime()
			       .format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		txtFechaDeEliminacion.setText(dtProponente.getFechaDeEliminacion().toZonedDateTime()
			       .format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		if(dtProponente.getImagen() != null) {
			ImageIcon imageIcon = new ImageIcon(dtProponente.getImagen());
			lblImagen.setIcon(imageIcon);
		}
	}
	
	private void listarColaboraciones(DtColaboracion[] dtC) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
		
		DefaultTableModel tableModel = new DefaultTableModel();
		tableModel.addColumn("Colaborador");
		tableModel.addColumn("Monto");
		tableModel.addColumn("Tipo de retorno");
		tableModel.addColumn("Fecha");
		monto = (double) 0;
		for(DtColaboracion colab : dtC) {
			tableModel.addRow(new String[] {colab.getColaborador(), String.valueOf(colab.getMonto()), colab.getTipo().toString(), sdf.format(colab.getFechaAporte().getTime())});
			monto += colab.getMonto();
		}
		tablaPropuestas.setModel(tableModel);
	}
	
    private void limpiarFormulario() {
        txtNickname.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtEmail.setText("");
        txtFechaDeNacimiento.setText("");
        txtFechaDeEliminacion.setText("");
        lblImagen.setIcon(null);
        lblImagenPropuesta.setIcon(null);
        entTitulo.setText("");
        entDescripcion.setText("");
        entFechaEspectaculo.setText("");
        entFechaPublicacion.setText("");
        entLugar.setText("");
        entMontoNecesario.setText("");
        entMontoRecaudado.setText("");
        entTipoRetorno.setText("");
        lblMensaje.setVisible(false);
    }
}
