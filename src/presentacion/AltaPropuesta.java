package presentacion;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;

import logica.CategoriaController;
import logica.UsuarioController;
import logica.exceptions.ExcepcionCategoriaNoExiste;
import datatype.DtCategoria;
import datatype.DtProponente;
import datatype.TipoRetorno;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class AltaPropuesta extends JInternalFrame {
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
	private JTextField entMontoNecesario;
	private JTextField entLugar;
	private JTextField entPrecioEntrada;
	private JDateChooser entFechaPublicacion;
	private JDateChooser entFechaEspectaculo;
	private JComboBox<String> entProponente;
	private JComboBox<TipoRetorno> entTipoRetorno;
	private JComboBox<String> entCategoria;
	private JButton btnAceptar;
	private JButton btnCancelar;

	/**
	 * Create the frame.
	 */
	public AltaPropuesta() {
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Alta de Propuesta");
        setBounds(10, 40, 405, 445);
		
        getContentPane().setLayout(null);
        
        lblNombre = new JLabel("Título");
        lblNombre.setBounds(12, 14, 66, 15);
        getContentPane().add(lblNombre);
        
        entTitulo = new JTextField();
        entTitulo.setBounds(143, 12, 239, 19);
        getContentPane().add(entTitulo);
        entTitulo.setColumns(10);
        
        lblDescripcion = new JLabel("Descripción");
        lblDescripcion.setBounds(12, 40, 79, 15);
        getContentPane().add(lblDescripcion);
        
        entDescripcion = new JTextField();
        entDescripcion.setColumns(10);
        entDescripcion.setBounds(143, 38, 239, 19);
        getContentPane().add(entDescripcion);
        
        lblImagen = new JLabel("Imagen");
        lblImagen.setBounds(12, 69, 66, 15);
        getContentPane().add(lblImagen);
        
        entImagen = new JTextField();
        entImagen.setColumns(10);
        entImagen.setBounds(143, 67, 239, 19);
        getContentPane().add(entImagen);
        
        lblMontoNecesario = new JLabel("Monto necesario");
        lblMontoNecesario.setBounds(12, 98, 131, 15);
        getContentPane().add(lblMontoNecesario);
        
        entMontoNecesario = new JTextField();
        entMontoNecesario.setColumns(10);
        entMontoNecesario.setBounds(143, 96, 239, 19);
        getContentPane().add(entMontoNecesario);
        
        lblFechaPublicacin = new JLabel("Fecha publicación");
        lblFechaPublicacin.setBounds(12, 127, 131, 15);
        getContentPane().add(lblFechaPublicacin);
        
        entFechaPublicacion = new JDateChooser();
        entFechaPublicacion.setBounds(143, 125, 239, 20);
        getContentPane().add(entFechaPublicacion);
        
        lblFechaEspectculo = new JLabel("Fecha espectáculo");
        lblFechaEspectculo.setBounds(12, 156, 131, 15);
        getContentPane().add(lblFechaEspectculo);
        
        entFechaEspectaculo = new JDateChooser();
        entFechaEspectaculo.setBounds(143, 154, 239, 20);
        getContentPane().add(entFechaEspectaculo);
        
        lblLugar = new JLabel("Lugar");
        lblLugar.setBounds(12, 185, 131, 15);
        getContentPane().add(lblLugar);
        
        entLugar = new JTextField();
        entLugar.setColumns(10);
        entLugar.setBounds(143, 183, 239, 19);
        getContentPane().add(entLugar);
        
        lblPrecioEntrada = new JLabel("Precio entrada");
        lblPrecioEntrada.setBounds(12, 214, 131, 15);
        getContentPane().add(lblPrecioEntrada);
        
        entPrecioEntrada = new JTextField();
        entPrecioEntrada.setColumns(10);
        entPrecioEntrada.setBounds(143, 212, 239, 19);
        getContentPane().add(entPrecioEntrada);
        
        lblTipoRetorno = new JLabel("Tipo retorno");
        lblTipoRetorno.setBounds(12, 248, 131, 15);
        getContentPane().add(lblTipoRetorno);
        
        entTipoRetorno = new JComboBox<>();
        entTipoRetorno.setModel(new DefaultComboBoxModel<>(TipoRetorno.values()));
        entTipoRetorno.setBounds(143, 243, 239, 24);
        getContentPane().add(entTipoRetorno);
        
        lblProponente = new JLabel("Proponente");
        lblProponente.setBounds(12, 277, 131, 15);
        getContentPane().add(lblProponente);
        
        entProponente = new JComboBox<>();
//        UsuarioController uc = new UsuarioController();
//        DtProponente[] dtP = uc.listarProponentes();
//        for (DtProponente i : dtP) {
//        	entProponente.addItem(i.getNickname());
//        }
        entProponente.setBounds(143, 272, 239, 24);
        getContentPane().add(entProponente);
        
        lblCategora = new JLabel("Categoría");
        lblCategora.setBounds(12, 309, 131, 15);
        getContentPane().add(lblCategora);
        
        entCategoria = new JComboBox<>();
        CategoriaController cc = new CategoriaController();
        DtCategoria[] dtC = null;
        try {
			dtC = cc.listarCategorias();
		} catch (ExcepcionCategoriaNoExiste e) {
			e.printStackTrace();
		}
        for (DtCategoria i : dtC) {
        	entCategoria.addItem(i.getNombre());
        }
        entCategoria.setBounds(143, 304, 239, 24);
        getContentPane().add(entCategoria);
        
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(237, 357, 114, 25);
        getContentPane().add(btnCancelar);
        
        btnAceptar = new JButton("Confirmar");
        btnAceptar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		registrarPropuesta(arg0);
        	}
        });
        btnAceptar.setBounds(53, 357, 114, 25);
        getContentPane().add(btnAceptar);
		
	}
	
	private void registrarPropuesta(ActionEvent arg0) {
		
	}
	
	private boolean formularioOk() {
		if (
				entTitulo.getText().isEmpty() || entDescripcion.getText().isEmpty() 
				private JTextField entImagen.getText().isEmpty()
				private JTextField entMontoNecesario.getText().isEmpty()
				private JTextField entLugar.getText().isEmpty()
				private JTextField entPrecioEntrada.getText().isEmpty()
				private JDateChooser entFechaPublicacion;
				private JDateChooser entFechaEspectaculo;
				private JComboBox<String> entProponente;
				private JComboBox<TipoRetorno> entTipoRetorno;
				private JComboBox<String> entCategoria;
				
				);
		return true;
	}
}
