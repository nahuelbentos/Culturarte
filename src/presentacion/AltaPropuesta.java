package presentacion;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.GregorianCalendar;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

import datatype.DtCategoria;
import datatype.DtProponente;
import datatype.DtPropuesta;
import datatype.DtUsuario;
import datatype.TipoRetorno;
import excepciones.CategoriaNoExisteException;
import excepciones.ProponenteNoExisteException;
import excepciones.PropuestaRepetidaException;
import logica.ICategoriaController;
import logica.IPropuestaController;
import logica.IUsuarioController;
import logica.PropuestaController;

@SuppressWarnings("serial")
public class AltaPropuesta extends JInternalFrame {
	
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
	private ImageIcon imagenPropuesta;
	private JButton btnSeleecionarImagen;
	private JFileChooser fileChooser = new JFileChooser();
	
	private String titulo;
	private String descripcion;
	private byte[] imagen;
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
	public AltaPropuesta(IUsuarioController IUC, ICategoriaController ICC, IPropuestaController IPC) {
		
		iUsuarioController = IUC;
		iCategoriaController = ICC;
		iPropuestaController = IPC;
		
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Alta de Propuesta");
        setBounds(10, 40, 408, 445);
		
        getContentPane().setLayout(null);
        
        lblNombre = new JLabel("Título");
        lblNombre.setBounds(12, 44, 66, 15);
        getContentPane().add(lblNombre);
        
        entTitulo = new JTextField();
        entTitulo.setBounds(143, 42, 239, 19);
        getContentPane().add(entTitulo);
        entTitulo.setColumns(10);
        
        lblDescripcion = new JLabel("Descripción");
        lblDescripcion.setBounds(12, 70, 79, 15);
        getContentPane().add(lblDescripcion);
        
        entDescripcion = new JTextField();
        entDescripcion.setColumns(10);
        entDescripcion.setBounds(143, 68, 239, 19);
        getContentPane().add(entDescripcion);
        
		btnSeleecionarImagen = new JButton("Selecionar Imagen");
		btnSeleecionarImagen.setHorizontalAlignment(SwingConstants.LEFT);
		btnSeleecionarImagen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int retorno = fileChooser.showOpenDialog(getContentPane());
                if (retorno == JFileChooser.APPROVE_OPTION) {
                    String pathName = fileChooser.getSelectedFile().getPath();
                    JOptionPane.showMessageDialog(null, pathName);
                    imagenPropuesta = new ImageIcon(pathName);
                    Image imagenPrevia = imagenPropuesta.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                    imagenPropuesta = new ImageIcon(imagenPrevia, pathName);
                    lblImagen.setIcon(imagenPropuesta);
                    try {
                    	imagen = levantarImagen(pathName);
                    	imagen = scale(imagen, 150, 150);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
                }
			}
		});
		btnSeleecionarImagen.setBounds(389, 226, 133, 20);
		getContentPane().add(btnSeleecionarImagen);
        
        entImagen = new JTextField();
        entImagen.setColumns(10);
        entImagen.setBounds(143, 97, 239, 19);
        getContentPane().add(entImagen);
        
        lblMontoNecesario = new JLabel("Monto necesario");
        lblMontoNecesario.setBounds(12, 128, 131, 15);
        getContentPane().add(lblMontoNecesario);
        
        entMontoNecesario = new JFormattedTextField(DecimalFormat.getInstance());
        entMontoNecesario.setValue(montoNecesario);
        entMontoNecesario.setColumns(10);
        entMontoNecesario.setBounds(143, 126, 239, 19);
        getContentPane().add(entMontoNecesario);
        
        lblFechaPublicacin = new JLabel("Fecha publicación");
        lblFechaPublicacin.setBounds(12, 157, 131, 15);
        getContentPane().add(lblFechaPublicacin);
        
        entFechaPublicacion = new JDateChooser();
        entFechaPublicacion.setBounds(143, 155, 239, 20);
        getContentPane().add(entFechaPublicacion);
        
        lblFechaEspectculo = new JLabel("Fecha espectáculo");
        lblFechaEspectculo.setBounds(12, 186, 131, 15);
        getContentPane().add(lblFechaEspectculo);
        
        entFechaEspectaculo = new JDateChooser();
        entFechaEspectaculo.setBounds(143, 184, 239, 20);
        getContentPane().add(entFechaEspectaculo);
        
        lblLugar = new JLabel("Lugar");
        lblLugar.setBounds(12, 215, 131, 15);
        getContentPane().add(lblLugar);
        
        entLugar = new JTextField();
        entLugar.setColumns(10);
        entLugar.setBounds(143, 213, 239, 19);
        getContentPane().add(entLugar);
        
        lblPrecioEntrada = new JLabel("Precio entrada");
        lblPrecioEntrada.setBounds(12, 244, 131, 15);
        getContentPane().add(lblPrecioEntrada);
        
        entPrecioEntrada = new JFormattedTextField(DecimalFormat.getInstance());
        entPrecioEntrada.setValue(precioEntrada);
        entPrecioEntrada.setColumns(10);
        entPrecioEntrada.setBounds(143, 242, 239, 19);
        getContentPane().add(entPrecioEntrada);
        
        lblTipoRetorno = new JLabel("Tipo retorno");
        lblTipoRetorno.setBounds(12, 278, 131, 15);
        getContentPane().add(lblTipoRetorno);
        
        entTipoRetorno = new JComboBox<>();
        entTipoRetorno.setModel(new DefaultComboBoxModel<>(TipoRetorno.values()));
        entTipoRetorno.setBounds(143, 273, 239, 24);
        getContentPane().add(entTipoRetorno);
        
        lblProponente = new JLabel("Proponente");
        lblProponente.setBounds(12, 13, 131, 15);
        getContentPane().add(lblProponente);
        
        entProponente = new JComboBox<>();      
        setListaDeProponentes();
        entProponente.setBounds(143, 8, 239, 24);
        getContentPane().add(entProponente);
        
        lblCategora = new JLabel("Categoría");
        lblCategora.setBounds(12, 309, 131, 15);
        getContentPane().add(lblCategora);
        
        entCategoria = new JComboBox<>();
        setListaDeCategorias();
        entCategoria.setBounds(143, 304, 239, 24);
        getContentPane().add(entCategoria);
        
        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e) {
                limpiarFormulario();
                setVisible(false);
            }
        });
        btnCancelar.setBounds(237, 357, 114, 25);
        getContentPane().add(btnCancelar);
        
        btnAceptar = new JButton("Confirmar");
        btnAceptar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		registrarPropuesta(arg0);
        		limpiarFormulario();
        	}
        });
        btnAceptar.setBounds(53, 357, 114, 25);
        getContentPane().add(btnAceptar);
		
	}
	
	private void registrarPropuesta(ActionEvent arg0) {
		if (formularioOk()) {
			DtProponente dtProponente = new DtProponente(nicknameProponente, "", "", "", null, null, "", "", "");
			DtCategoria dtCat = new DtCategoria(categoria);
			DtPropuesta dtPropuesta = new DtPropuesta(titulo, descripcion, imagen, montoNecesario, fechaPublicacion, fechaEspecatulo, lugar, precioEntrada, tipo, 0, dtProponente, null, null, dtCat, null);
			try {
				iPropuestaController.altaPropuesta(dtPropuesta);
				JOptionPane.showMessageDialog(this, "La propuesta se ha creado con éxito", "Alta de propuesta", JOptionPane.INFORMATION_MESSAGE);
			} catch (PropuestaRepetidaException e) {
				JOptionPane.showMessageDialog(this, e, "Alta de propuesta", JOptionPane.INFORMATION_MESSAGE);
			} catch (ProponenteNoExisteException e) {
				JOptionPane.showMessageDialog(this, e, "Alta de propuesta", JOptionPane.INFORMATION_MESSAGE);
			} catch (CategoriaNoExisteException e) {
				JOptionPane.showMessageDialog(this, e, "Alta de propuesta", JOptionPane.INFORMATION_MESSAGE);
			}
		}else
			JOptionPane.showMessageDialog(this, "Debe completar todos los campos", "Alta de propuesta", JOptionPane.INFORMATION_MESSAGE);
	}
	
	private boolean formularioOk() {
		titulo = entTitulo.getText();
		descripcion = entDescripcion.getText();
		
		montoNecesario = Float.valueOf(entMontoNecesario.getText());
		precioEntrada = Float.valueOf(entPrecioEntrada.getText());
		fechaPublicacion = new GregorianCalendar();
		fechaEspecatulo = new GregorianCalendar();
		if (entFechaPublicacion.getDate()!=null)
			fechaPublicacion.setTime(entFechaPublicacion.getDate());
		if (entFechaEspectaculo.getDate() != null)
			fechaEspecatulo.setTime(entFechaEspectaculo.getDate());
		lugar = entLugar.getText();
		tipo = (TipoRetorno) entTipoRetorno.getSelectedItem();
		if (entProponente.getSelectedItem() != null)
			nicknameProponente = entProponente.getSelectedItem().toString();
		categoria = entCategoria.getSelectedItem().toString();
		
		// acá hay que poner las validaciones del caso de uso
		return (!(entProponente.getSelectedItem().toString().isEmpty() || titulo.isEmpty() || descripcion.isEmpty() || entFechaPublicacion.getDate() == null || 
				entFechaEspectaculo.getDate() == null || lugar.isEmpty() || tipo == null || categoria == null));
	}
	
	private void limpiarFormulario() {
		entTitulo.setText("");
		entDescripcion.setText("");;
		entImagen.setText("");
		entMontoNecesario.setValue(0);
		entLugar.setText("");
		entPrecioEntrada.setValue(0);
		entFechaPublicacion.setDate(null);
		entFechaEspectaculo.setDate(null);
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
	
    private byte[] levantarImagen(String pathName) throws IOException {
    	File file = new File(pathName);
    	byte[] picInBytes = new byte[(int) file.length()];
    	FileInputStream fileInputStream = new FileInputStream(file);
    	fileInputStream.read(picInBytes);
    	fileInputStream.close();
		return picInBytes;
    }
    
    public byte[] scale(byte[] fileData, int width, int height) throws IOException {
        ByteArrayInputStream in = new ByteArrayInputStream(fileData);
        BufferedImage img = ImageIO.read(in);
        if(height == 0) {
            height = (width * img.getHeight())/ img.getWidth(); 
        }
        if(width == 0) {
            width = (height * img.getWidth())/ img.getHeight();
        }
        Image scaledImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage imageBuff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        imageBuff.getGraphics().drawImage(scaledImage, 0, 0, new Color(0,0,0), null);
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        ImageIO.write(imageBuff, "jpg", buffer);
        return buffer.toByteArray();
    }
}
