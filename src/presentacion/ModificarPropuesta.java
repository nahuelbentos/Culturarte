package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DecimalFormat;
import java.util.GregorianCalendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import datatype.DtCategoria;
import datatype.DtProponente;
import datatype.DtPropuesta;
import datatype.DtUsuario;
import datatype.TipoRetorno;
import excepciones.CategoriaNoExisteException;
import excepciones.ProponenteNoExisteException;
import excepciones.PropuestaRepetidaException;
import excepciones.UsuarioNoExisteElUsuarioException;
import logica.ICategoriaController;
import logica.IPropuestaController;
import logica.IUsuarioController;
import logica.PropuestaController;

@SuppressWarnings("serial")
public class ModificarPropuesta extends JInternalFrame {

	private static final String TEXTO_COMBO_PROPUESTA = "Seleccione una propuesta";
	
	private IUsuarioController iUsuarioController;
	private ICategoriaController iCategoriaController;
	private IPropuestaController iPropuestaController;
	private JLabel lblNombre;
	private JLabel lblImagen;
	private JLabel lblDescripcion;
	private JLabel lblFechaEspectculo;
	private JLabel lblLugar;
	private JLabel lblPrecioEntrada;
	private JLabel lblCategora;
	private JLabel lblMontoNecesario;
	private JLabel lblFechaPublicacin;
	private JLabel lblProponente;
	private JLabel lblTipoRetorno;
	private JTextField entTitulo;
	private JTextField entDescripcion;
	private JTextField entImagen;
	private JFormattedTextField entMontoNecesario;
	private JTextField entLugar;
	private JFormattedTextField entPrecioEntrada;
	private JDateChooser entFechaPublicacion;
	private JDateChooser entFechaEspectaculo;
	private JComboBox<String> entProponente;
	private JComboBox<TipoRetorno> entTipoRetorno;
	private JComboBox<String> entCategoria;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JComboBox<String> cmbPropuesta;
	
	private String titulo;
	private String descripcion;
	private String imagen;
	private float montoNecesario;
	private GregorianCalendar fechaPublicacion;
	private GregorianCalendar fechaEspecatulo;
	private String lugar;
	private float precioEntrada;
	private TipoRetorno tipo;
	private String nicknameProponente;
	private String categoria;

	/**
	 * Create the frame.
	 */
	public ModificarPropuesta(IUsuarioController IUC, ICategoriaController ICC, IPropuestaController IPC) {
		
		iUsuarioController = IUC;
		iCategoriaController = ICC;
		iPropuestaController = IPC;
		
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Modificar Propuesta");
        setBounds(10, 40, 408, 486);
		
        getContentPane().setLayout(null);
        
        lblNombre = new JLabel("Titulo");
        lblNombre.setBounds(10, 89, 66, 15);
        getContentPane().add(lblNombre);
        
        entTitulo = new JTextField();
        entTitulo.setBounds(141, 87, 239, 19);
        getContentPane().add(entTitulo);
        entTitulo.setColumns(10);
        
        lblDescripcion = new JLabel("Descripcion");
        lblDescripcion.setBounds(10, 115, 79, 15);
        getContentPane().add(lblDescripcion);
        
        entDescripcion = new JTextField();
        entDescripcion.setColumns(10);
        entDescripcion.setBounds(141, 113, 239, 19);
        getContentPane().add(entDescripcion);
        
        lblImagen = new JLabel("Imagen");
        lblImagen.setBounds(10, 144, 66, 15);
        getContentPane().add(lblImagen);
        
        entImagen = new JTextField();
        entImagen.setColumns(10);
        entImagen.setBounds(141, 142, 239, 19);
        getContentPane().add(entImagen);
        
        lblMontoNecesario = new JLabel("Monto necesario");
        lblMontoNecesario.setBounds(10, 173, 131, 15);
        getContentPane().add(lblMontoNecesario);
        
        entMontoNecesario = new JFormattedTextField(DecimalFormat.getInstance());
        entMontoNecesario.setValue(montoNecesario);
        entMontoNecesario.setColumns(10);
        entMontoNecesario.setBounds(141, 171, 239, 19);
        getContentPane().add(entMontoNecesario);
        
        lblFechaPublicacin = new JLabel("Fecha publicacion");
        lblFechaPublicacin.setBounds(10, 202, 131, 15);
        getContentPane().add(lblFechaPublicacin);
        
        entFechaPublicacion = new JDateChooser();
        entFechaPublicacion.setBounds(141, 200, 239, 20);
        getContentPane().add(entFechaPublicacion);
        
        lblFechaEspectculo = new JLabel("Fecha espectaculo");
        lblFechaEspectculo.setBounds(10, 231, 131, 15);
        getContentPane().add(lblFechaEspectculo);
        
        entFechaEspectaculo = new JDateChooser();
        entFechaEspectaculo.setBounds(141, 229, 239, 20);
        getContentPane().add(entFechaEspectaculo);
        
        lblLugar = new JLabel("Lugar");
        lblLugar.setBounds(10, 260, 131, 15);
        getContentPane().add(lblLugar);
        
        entLugar = new JTextField();
        entLugar.setColumns(10);
        entLugar.setBounds(141, 258, 239, 19);
        getContentPane().add(entLugar);
        
        lblPrecioEntrada = new JLabel("Precio entrada");
        lblPrecioEntrada.setBounds(10, 289, 131, 15);
        getContentPane().add(lblPrecioEntrada);
        
        entPrecioEntrada = new JFormattedTextField(DecimalFormat.getInstance());
        entPrecioEntrada.setValue(precioEntrada);
        entPrecioEntrada.setColumns(10);
        entPrecioEntrada.setBounds(141, 287, 239, 19);
        getContentPane().add(entPrecioEntrada);
        
        lblTipoRetorno = new JLabel("Tipo retorno");
        lblTipoRetorno.setBounds(10, 323, 131, 15);
        getContentPane().add(lblTipoRetorno);
        
        entTipoRetorno = new JComboBox<>();
        entTipoRetorno.setBounds(141, 318, 239, 24);
        getContentPane().add(entTipoRetorno);
        
        lblProponente = new JLabel("Proponente");
        lblProponente.setBounds(10, 59, 131, 15);
        getContentPane().add(lblProponente);
        
        entProponente = new JComboBox<>();
        entProponente.setBounds(141, 54, 239, 24);
        getContentPane().add(entProponente);
        
        lblCategora = new JLabel("Categoria");
        lblCategora.setBounds(10, 354, 131, 15);
        getContentPane().add(lblCategora);
        
        entCategoria = new JComboBox<>();
        entCategoria.setBounds(141, 349, 239, 24);
        getContentPane().add(entCategoria);
        
        deshabilitarModificacion();
        
        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e) {
                limpiarFormulario();
                setVisible(false);
            }
        });
        btnCancelar.setBounds(235, 402, 114, 25);
        getContentPane().add(btnCancelar);
        
        btnAceptar = new JButton("Confirmar");
        btnAceptar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		modificarPropuesta(arg0);
        		limpiarFormulario();
        		deshabilitarModificacion();
        	}
        });
        btnAceptar.setBounds(51, 402, 114, 25);
        getContentPane().add(btnAceptar);
        
        JLabel lblPropuesta = new JLabel("Propuesta");
        lblPropuesta.setBounds(10, 24, 131, 15);
        getContentPane().add(lblPropuesta);
        
        cmbPropuesta = new JComboBox<String>();
        cmbPropuesta.addItemListener(new ItemListener() {	
			@Override
			public void itemStateChanged(ItemEvent e) {
				if ((e.getStateChange() == ItemEvent.SELECTED)) {
	                Object selected = cmbPropuesta.getSelectedItem();
	                if (!selected.toString().equals(TEXTO_COMBO_PROPUESTA)) {
	                	try {
							actualizarDatos(e);
							habilitarModificacion();
						} catch (UsuarioNoExisteElUsuarioException e1) {
							e1.printStackTrace();
						}
	                } else {
						limpiarFormulario();
						deshabilitarModificacion();
					}
				}
			}
		});
        cmbPropuesta.setBounds(141, 19, 239, 24);
        getContentPane().add(cmbPropuesta);
		
	}
	
	private void modificarPropuesta(ActionEvent arg0) {
		if (!cmbPropuesta.getSelectedItem().equals(TEXTO_COMBO_PROPUESTA)) {
			if (formularioOk()) {
				DtProponente dtProponente = new DtProponente(nicknameProponente, "", "", "", null, "", "", "", "");
				DtCategoria dtCat = new DtCategoria(categoria);
				DtPropuesta dtPropuesta = new DtPropuesta(titulo, descripcion, imagen, montoNecesario, fechaPublicacion, fechaEspecatulo, lugar, precioEntrada, tipo, 0, dtProponente, null, null, dtCat, null);
				iPropuestaController.modificarPropuesta(dtPropuesta);
				JOptionPane.showMessageDialog(this, "La propuesta se ha modificado con exito", "Modificar Propuesta", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this, "Debe completar todos los campos", "Modificar Propuesta", JOptionPane.INFORMATION_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(this, "Debe seleccionar una propuesta", "Modificar Propuesta", JOptionPane.INFORMATION_MESSAGE);
		}

	}
//	
	private boolean formularioOk() {
		titulo = entTitulo.getText();
		descripcion = entDescripcion.getText();
		montoNecesario = Float.valueOf(entMontoNecesario.getText());
		precioEntrada = Float.valueOf(entPrecioEntrada.getText());
		fechaPublicacion = new GregorianCalendar();
		fechaEspecatulo = new GregorianCalendar();
		if (entFechaPublicacion.getDate()!=null)
			fechaPublicacion.setTime(entFechaPublicacion.getDate());
		if (entFechaEspectaculo.getDate()!=null)
			fechaEspecatulo.setTime(entFechaEspectaculo.getDate());
		lugar = entLugar.getText();
		tipo = (TipoRetorno) entTipoRetorno.getSelectedItem();
		if (entProponente.getSelectedItem() != null)
			nicknameProponente = entProponente.getSelectedItem().toString();
		categoria = entCategoria.getSelectedItem().toString();
		
		// acá hay que poner las validaciones del caso de uso
//		return (!(nicknameProponente.isEmpty() || titulo.isEmpty() || descripcion.isEmpty() || imagen.isEmpty() || fechaPublicacion == null || 
//				fechaEspecatulo == null || lugar.isEmpty() || tipo == null || categoria == null));
		return true;
	}
	
	private void limpiarFormulario() {
		entTitulo.setText("");
		entDescripcion.setText("");;
		entImagen.setText("");
		entMontoNecesario.setText("");
		entLugar.setText("");
		entPrecioEntrada.setText("");
		entFechaPublicacion.setDate(null);
		entFechaEspectaculo.setDate(null);
		entCategoria.removeAllItems();
		entProponente.removeAllItems();
		entTipoRetorno.removeAllItems();
	}
	
	public void setListaDePropuestas() {
		cmbPropuesta.removeAllItems();
        DtPropuesta[] propuestas = iPropuestaController.listarPropuestasExistentes();
        if (propuestas != null) {
            for (int i = 0; i < propuestas.length; i++) {
            	cmbPropuesta.addItem(propuestas[i].getTitulo());
            }
            cmbPropuesta.addItem(TEXTO_COMBO_PROPUESTA);
            cmbPropuesta.setSelectedItem(TEXTO_COMBO_PROPUESTA);
            limpiarFormulario();
            deshabilitarModificacion();
        } else {
        	cmbPropuesta.addItem("No hay propuestas registradas en el sistema");
        }
	}
	
	public void setListaDeProponentes() {
		entProponente.removeAllItems();
        DtUsuario[] proponentes = iUsuarioController.listarProponentes();
        if (proponentes != null) {
            for (int i = 0; i < proponentes.length; i++) {
            	entProponente.addItem(proponentes[i].getNickname());
            }
        } else {
        	entProponente.addItem("No hay proponentes registrados en el sistema");
        }
	}
	
	public void setListaDeCategorias() {
		entCategoria.removeAllItems();
		DtCategoria[] categorias = iCategoriaController.listarCategorias();
        if (categorias != null) {
            for (int i = 0; i < categorias.length; i++) {
            	entCategoria.addItem(categorias[i].getNombre());
            }
        } else {
        	entProponente.addItem("No hay categorias registradas en el sistema");
        }        
	}
	
	public void setListaDeTiposDeRetorno() {
		entTipoRetorno.removeAllItems();
		entTipoRetorno.setModel(new DefaultComboBoxModel<>(TipoRetorno.values()));
	}
	
	protected void actualizarDatos(ItemEvent arg0) throws UsuarioNoExisteElUsuarioException {
		DtPropuesta dtPropuesta = iPropuestaController.seleccionarPropuesta(cmbPropuesta.getSelectedItem().toString());
		if (dtPropuesta != null) {
			setListaDeProponentes();
			entProponente.setSelectedItem(dtPropuesta.getProponenteACargo().getNickname());
			entTitulo.setText(dtPropuesta.getTitulo());
			entDescripcion.setText(dtPropuesta.getDescripcion());
			entImagen.setText(dtPropuesta.getImagen());
			entMontoNecesario.setText(String.valueOf(dtPropuesta.getMontoNecesario()));
			entLugar.setText(dtPropuesta.getLugar());
			entPrecioEntrada.setText(String.valueOf(dtPropuesta.getPrecioEntrada()));
			setListaDeCategorias();
			entCategoria.setSelectedItem(dtPropuesta.getCategoria().getNombre());
			setListaDeTiposDeRetorno();
			entTipoRetorno.setSelectedItem(dtPropuesta.getTipo());
			entFechaPublicacion.setDate(dtPropuesta.getFechaPublicacion().getTime());
			entFechaEspectaculo.setDate(dtPropuesta.getFechaEspecatulo().getTime());
		}
	}
	
	private void habilitarModificacion() {
		entProponente.setEnabled(true);
		entDescripcion.setEditable(true);
		entImagen.setEditable(true);
		entMontoNecesario.setEditable(true);
		entLugar.setEditable(true);
		entPrecioEntrada.setEditable(true);
		entCategoria.setEnabled(true);
		entTipoRetorno.setEnabled(true);
		entFechaEspectaculo.setEnabled(true);
		entFechaPublicacion.setEnabled(true);
	}
	
	private void deshabilitarModificacion() {
		entTitulo.setEditable(false);
		entProponente.setEnabled(false);
		entDescripcion.setEditable(false);
		entImagen.setEditable(false);
		entMontoNecesario.setEditable(false);
		entLugar.setEditable(false);
		entPrecioEntrada.setEditable(false);
		entCategoria.setEnabled(false);
		entTipoRetorno.setEnabled(false);
		entFechaEspectaculo.setEnabled(false);
		entFechaPublicacion.setEnabled(false);
	}
}
