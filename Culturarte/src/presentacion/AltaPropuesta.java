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
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.GregorianCalendar;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
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
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextPane;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class AltaPropuesta extends JInternalFrame {
	
	private static final String DEFAULT_IMAGEN_PROPUESTA = "b71c029e-18e4-4216-8703-f94eed67d540";
	
	private IUsuarioController iUsuarioController;
	private ICategoriaController iCategoriaController;
	private IPropuestaController iPropuestaController;
	private JLabel lblNombre;
	private JLabel lblDescripcion;
	private JLabel lblFechaEspectculo;
	private JLabel lblLugar;
	private JLabel lblPrecioEntrada;
	private JLabel lblCategora;
	private JLabel lblMontoNecesario;
	private JLabel lblProponente;
	private JLabel lblTipoRetorno;
	private JTextField entTitulo;
	private JTextField entMontoNecesario;
	private JTextField entLugar;
	private JTextField entPrecioEntrada;
	private JDateChooser entFechaEspectaculo;
	private JComboBox<String> entProponente;
	private JComboBox<TipoRetorno> entTipoRetorno;
	private JComboBox<String> entCategoria;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private ImageIcon imagenPropuesta;
	private JButton btnSeleecionarImagen;
	private JFileChooser fileChooser = new JFileChooser();
	private JLabel lblImagen;
	
	private String titulo;
	private String descripcion;
	private byte[] imagen;
	private float montoNecesario;
	private GregorianCalendar fechaEspecatulo;
	private String lugar;
	private float precioEntrada;
	private TipoRetorno tipo;
	private String nicknameProponente;
	private String categoria;
	private JPanel panelDatos;
	private JPanel panelProponente;
	private JTextPane entDescripcion;
	private JPanel panelImagen;
	private JPanel panel;
	private JPanel panelContenedorImg;
	
	
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
        setBounds(10, 40, 683, 569);
		
        getContentPane().setLayout(null);
        
        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e) {
                limpiarFormulario();
                setVisible(false);
            }
        });
        btnCancelar.setBounds(425, 357, 114, 25);
        getContentPane().add(btnCancelar);
        
        btnAceptar = new JButton("Confirmar");
        btnAceptar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		registrarPropuesta(arg0);
        		limpiarFormulario();
        	}
        });
        btnAceptar.setBounds(549, 357, 114, 25);
        getContentPane().add(btnAceptar);
        {
        	this.panelDatos = new JPanel();
        	this.panelDatos.setBorder(new TitledBorder(null, "Datos b\u00E1sicos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        	this.panelDatos.setBounds(318, 8, 345, 234);
        	getContentPane().add(this.panelDatos);
        	GridBagLayout gbl_panelDatos = new GridBagLayout();
        	gbl_panelDatos.columnWidths = new int[]{0, 0, 0, 0};
        	gbl_panelDatos.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
        	gbl_panelDatos.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
        	gbl_panelDatos.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        	this.panelDatos.setLayout(gbl_panelDatos);
        	
        	lblNombre = new JLabel("Titulo");
        	GridBagConstraints gbc_lblNombre = new GridBagConstraints();
        	gbc_lblNombre.anchor = GridBagConstraints.EAST;
        	gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
        	gbc_lblNombre.gridx = 0;
        	gbc_lblNombre.gridy = 0;
        	this.panelDatos.add(this.lblNombre, gbc_lblNombre);
        	
        	entTitulo = new JTextField();
        	GridBagConstraints gbc_entTitulo = new GridBagConstraints();
        	gbc_entTitulo.fill = GridBagConstraints.HORIZONTAL;
        	gbc_entTitulo.weightx = 10.0;
        	gbc_entTitulo.insets = new Insets(0, 0, 5, 5);
        	gbc_entTitulo.gridx = 1;
        	gbc_entTitulo.gridy = 0;
        	this.panelDatos.add(this.entTitulo, gbc_entTitulo);
        	entTitulo.setColumns(10);
        	
        	lblDescripcion = new JLabel("Descripcion");
        	GridBagConstraints gbc_lblDescripcion = new GridBagConstraints();
        	gbc_lblDescripcion.anchor = GridBagConstraints.NORTHEAST;
        	gbc_lblDescripcion.insets = new Insets(0, 0, 5, 5);
        	gbc_lblDescripcion.gridx = 0;
        	gbc_lblDescripcion.gridy = 1;
        	this.panelDatos.add(this.lblDescripcion, gbc_lblDescripcion);
        	
        	this.entDescripcion = new JTextPane();
        	GridBagConstraints gbc_entDescripcion = new GridBagConstraints();
        	gbc_entDescripcion.weighty = 3.0;
        	gbc_entDescripcion.weightx = 10.0;
        	gbc_entDescripcion.insets = new Insets(0, 0, 5, 5);
        	gbc_entDescripcion.fill = GridBagConstraints.BOTH;
        	gbc_entDescripcion.gridx = 1;
        	gbc_entDescripcion.gridy = 1;
        	this.panelDatos.add(this.entDescripcion, gbc_entDescripcion);
        	
        	lblMontoNecesario = new JLabel("Monto necesario");
        	
        	GridBagConstraints gbc_lblMontoNecesario = new GridBagConstraints();
        	gbc_lblMontoNecesario.anchor = GridBagConstraints.EAST;
        	gbc_lblMontoNecesario.insets = new Insets(0, 0, 5, 5);
        	gbc_lblMontoNecesario.gridx = 0;
        	gbc_lblMontoNecesario.gridy = 2;
        	this.panelDatos.add(this.lblMontoNecesario, gbc_lblMontoNecesario);
        	
        	entMontoNecesario = new JTextField();
        	GridBagConstraints gbc_entMontoNecesario = new GridBagConstraints();
        	gbc_entMontoNecesario.anchor = GridBagConstraints.WEST;
        	gbc_entMontoNecesario.insets = new Insets(0, 0, 5, 5);
        	gbc_entMontoNecesario.gridx = 1;
        	gbc_entMontoNecesario.gridy = 2;
        	this.panelDatos.add(this.entMontoNecesario, gbc_entMontoNecesario);
//        	entMontoNecesario.setValue(montoNecesario);
        	entMontoNecesario.setColumns(10);
        	
        	
        	lblFechaEspectculo = new JLabel("Fecha espectaculo");
        	GridBagConstraints gbc_lblFechaEspectculo = new GridBagConstraints();
        	gbc_lblFechaEspectculo.anchor = GridBagConstraints.EAST;
        	gbc_lblFechaEspectculo.insets = new Insets(0, 0, 5, 5);
        	gbc_lblFechaEspectculo.gridx = 0;
        	gbc_lblFechaEspectculo.gridy = 3;
        	this.panelDatos.add(this.lblFechaEspectculo, gbc_lblFechaEspectculo);
        	
        	entFechaEspectaculo = new JDateChooser();
        	GridBagConstraints gbc_entFechaEspectaculo = new GridBagConstraints();
        	gbc_entFechaEspectaculo.anchor = GridBagConstraints.WEST;
        	gbc_entFechaEspectaculo.insets = new Insets(0, 0, 5, 5);
        	gbc_entFechaEspectaculo.gridx = 1;
        	gbc_entFechaEspectaculo.gridy = 3;
        	this.panelDatos.add(this.entFechaEspectaculo, gbc_entFechaEspectaculo);
        	
        	lblLugar = new JLabel("Lugar");
        	GridBagConstraints gbc_lblLugar = new GridBagConstraints();
        	gbc_lblLugar.anchor = GridBagConstraints.EAST;
        	gbc_lblLugar.insets = new Insets(0, 0, 5, 5);
        	gbc_lblLugar.gridx = 0;
        	gbc_lblLugar.gridy = 4;
        	this.panelDatos.add(this.lblLugar, gbc_lblLugar);
        	
        	entLugar = new JTextField();
        	GridBagConstraints gbc_entLugar = new GridBagConstraints();
        	gbc_entLugar.anchor = GridBagConstraints.WEST;
        	gbc_entLugar.insets = new Insets(0, 0, 5, 5);
        	gbc_entLugar.gridx = 1;
        	gbc_entLugar.gridy = 4;
        	this.panelDatos.add(this.entLugar, gbc_entLugar);
        	entLugar.setColumns(10);
        	
        	lblPrecioEntrada = new JLabel("Precio entrada");
        	GridBagConstraints gbc_lblPrecioEntrada = new GridBagConstraints();
        	gbc_lblPrecioEntrada.anchor = GridBagConstraints.EAST;
        	gbc_lblPrecioEntrada.insets = new Insets(0, 0, 5, 5);
        	gbc_lblPrecioEntrada.gridx = 0;
        	gbc_lblPrecioEntrada.gridy = 5;
        	this.panelDatos.add(this.lblPrecioEntrada, gbc_lblPrecioEntrada);
        	
        	entPrecioEntrada = new JTextField();
        	GridBagConstraints gbc_entPrecioEntrada = new GridBagConstraints();
        	gbc_entPrecioEntrada.anchor = GridBagConstraints.WEST;
        	gbc_entPrecioEntrada.insets = new Insets(0, 0, 5, 5);
        	gbc_entPrecioEntrada.gridx = 1;
        	gbc_entPrecioEntrada.gridy = 5;
        	this.panelDatos.add(this.entPrecioEntrada, gbc_entPrecioEntrada);
//        	entPrecioEntrada.setValue(precioEntrada);
        	entPrecioEntrada.setColumns(10);
        }
        
        this.panelProponente = new JPanel();
        this.panelProponente.setBorder(new TitledBorder(null, "Publicado por", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        this.panelProponente.setBounds(10, 8, 298, 234);
        getContentPane().add(this.panelProponente);
        GridBagLayout gbl_panelProponente = new GridBagLayout();
        gbl_panelProponente.columnWidths = new int[]{0, 0, 0, 0};
        gbl_panelProponente.rowHeights = new int[]{0, 0};
        gbl_panelProponente.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_panelProponente.rowWeights = new double[]{0.0, Double.MIN_VALUE};
        this.panelProponente.setLayout(gbl_panelProponente);
        
        lblProponente = new JLabel("Proponente");
        GridBagConstraints gbc_lblProponente = new GridBagConstraints();
        gbc_lblProponente.insets = new Insets(0, 0, 0, 5);
        gbc_lblProponente.gridx = 0;
        gbc_lblProponente.gridy = 0;
        this.panelProponente.add(this.lblProponente, gbc_lblProponente);
        
        entProponente = new JComboBox<>();      
        GridBagConstraints gbc_entProponente = new GridBagConstraints();
        gbc_entProponente.fill = GridBagConstraints.HORIZONTAL;
        gbc_entProponente.gridwidth = 5;
        gbc_entProponente.gridx = 2;
        gbc_entProponente.gridy = 0;
        this.panelProponente.add(this.entProponente, gbc_entProponente);
        
        this.panelImagen = new JPanel();
        this.panelImagen.setBorder(new TitledBorder(null, "Imagen", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        this.panelImagen.setBounds(10, 253, 298, 278);
        getContentPane().add(this.panelImagen);
        GridBagLayout gbl_panelImagen = new GridBagLayout();
        gbl_panelImagen.columnWidths = new int[]{0, 0, 0, 0};
        gbl_panelImagen.rowHeights = new int[]{0, 0, 0};
        gbl_panelImagen.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
        gbl_panelImagen.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        this.panelImagen.setLayout(gbl_panelImagen);
		
		btnSeleecionarImagen = new JButton("Selecionar Imagen");
		GridBagConstraints gbc_btnSeleecionarImagen = new GridBagConstraints();
		gbc_btnSeleecionarImagen.insets = new Insets(0, 0, 5, 5);
		gbc_btnSeleecionarImagen.gridx = 1;
		gbc_btnSeleecionarImagen.gridy = 0;
		this.panelImagen.add(this.btnSeleecionarImagen, gbc_btnSeleecionarImagen);
		btnSeleecionarImagen.setHorizontalAlignment(SwingConstants.LEFT);
		
		panelContenedorImg = new JPanel();
		GridBagConstraints gbc_panelContenedorImg = new GridBagConstraints();
		gbc_panelContenedorImg.insets = new Insets(0, 0, 0, 5);
		gbc_panelContenedorImg.fill = GridBagConstraints.BOTH;
		gbc_panelContenedorImg.gridx = 1;
		gbc_panelContenedorImg.gridy = 1;
		panelImagen.add(panelContenedorImg, gbc_panelContenedorImg);
		{
			lblImagen = new JLabel("");
			panelContenedorImg.add(lblImagen);
			setearImagenPropuesta();
		}
		btnSeleecionarImagen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int retorno = fileChooser.showOpenDialog(getContentPane());
                if (retorno == JFileChooser.APPROVE_OPTION) {
                    String pathName = fileChooser.getSelectedFile().getPath();
                    JOptionPane.showMessageDialog(null, pathName);
                    imagenPropuesta = new ImageIcon(pathName);
                    Image imagenPrevia = imagenPropuesta.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                    imagenPropuesta = new ImageIcon(imagenPrevia, pathName);
                    lblImagen.setIcon(imagenPropuesta);
                    try {
                    	imagen = levantarImagen(pathName);
                    	imagen = scale(imagen, 200, 200);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
                }
			}
		});
		
		this.panel = new JPanel();
		this.panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Categoria y tipo retorno", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		this.panel.setBounds(318, 253, 345, 93);
		getContentPane().add(this.panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		this.panel.setLayout(gbl_panel);
		
		lblCategora = new JLabel("Categoria");
		GridBagConstraints gbc_lblCategora = new GridBagConstraints();
		gbc_lblCategora.insets = new Insets(0, 0, 5, 5);
		gbc_lblCategora.anchor = GridBagConstraints.EAST;
		gbc_lblCategora.gridx = 0;
		gbc_lblCategora.gridy = 0;
		this.panel.add(this.lblCategora, gbc_lblCategora);
		
		entCategoria = new JComboBox<>();
		GridBagConstraints gbc_entCategoria = new GridBagConstraints();
		gbc_entCategoria.insets = new Insets(0, 0, 5, 0);
		gbc_entCategoria.fill = GridBagConstraints.HORIZONTAL;
		gbc_entCategoria.gridx = 2;
		gbc_entCategoria.gridy = 0;
		this.panel.add(this.entCategoria, gbc_entCategoria);
		
		lblTipoRetorno = new JLabel("Tipo retorno");
		GridBagConstraints gbc_lblTipoRetorno = new GridBagConstraints();
		gbc_lblTipoRetorno.insets = new Insets(0, 0, 0, 5);
		gbc_lblTipoRetorno.gridx = 0;
		gbc_lblTipoRetorno.gridy = 1;
		this.panel.add(this.lblTipoRetorno, gbc_lblTipoRetorno);
		
		entTipoRetorno = new JComboBox<>();
		GridBagConstraints gbc_entTipoRetorno = new GridBagConstraints();
		gbc_entTipoRetorno.gridx = 2;
		gbc_entTipoRetorno.gridy = 1;
		this.panel.add(this.entTipoRetorno, gbc_entTipoRetorno);
		entTipoRetorno.setModel(new DefaultComboBoxModel<>(TipoRetorno.values()));
		
		// Una vez cargado el panel, levanto los proponentes y las categorias.
		setListaDeProponentes();
        setListaDeCategorias();
	}
	
	private void registrarPropuesta(ActionEvent arg0) {
		if (formularioOk()) {
			DtProponente dtProponente = new DtProponente(nicknameProponente, "", "", "", null, null, null, "", "", "");
			DtCategoria dtCat = new DtCategoria(categoria);
			DtPropuesta dtPropuesta = new DtPropuesta(titulo, descripcion, imagen, montoNecesario, new GregorianCalendar(), fechaEspecatulo, lugar, precioEntrada, tipo, 0, dtProponente, null, null, dtCat, null);
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
		
		montoNecesario = Float.parseFloat(entMontoNecesario.getText());
		precioEntrada = Float.parseFloat(entPrecioEntrada.getText());
		fechaEspecatulo = new GregorianCalendar();
		if (entFechaEspectaculo.getDate() != null)
			fechaEspecatulo.setTime(entFechaEspectaculo.getDate());
		lugar = entLugar.getText();
		tipo = (TipoRetorno) entTipoRetorno.getSelectedItem();
		if (entProponente.getSelectedItem() != null)
			nicknameProponente = entProponente.getSelectedItem().toString();
		categoria = entCategoria.getSelectedItem().toString();
		
		// acá hay que poner las validaciones del caso de uso
		return (!(entProponente.getSelectedItem().toString().isEmpty() || titulo.isEmpty() || descripcion.isEmpty() || 
				entFechaEspectaculo.getDate() == null || lugar.isEmpty() || tipo == null || categoria == null));
	}
	
	private void limpiarFormulario() {
		entTitulo.setText("");
		entDescripcion.setText("");
		entMontoNecesario.setText("");;
		entLugar.setText("");
		entPrecioEntrada.setText("");;
		entFechaEspectaculo.setDate(null);
	}
	
	public void refreshFrame() {
		setListaDeProponentes();
		setListaDeCategorias();
	}
	
	private void setListaDeProponentes() {
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
	
	private void setListaDeCategorias() {
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
    
    private byte[] scale(byte[] fileData, int width, int height) throws IOException {
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
    
    private void setearImagenPropuesta() {
    	try {
			imagen = obtenerImagen(DEFAULT_IMAGEN_PROPUESTA);
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
		String pathName = Principal.class.getResource("/datosDePrueba/imagenes/" + DEFAULT_IMAGEN_PROPUESTA + ".jpg").getPath();
        imagenPropuesta = new ImageIcon(pathName);
        Image imagenPrevia = imagenPropuesta.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        imagenPropuesta = new ImageIcon(imagenPrevia, pathName);
        lblImagen.setIcon(imagenPropuesta);
    }
    
	private byte[] obtenerImagen(String pathName) throws IOException, URISyntaxException {
		URL resource = Principal.class.getResource("/datosDePrueba/imagenes/" + pathName + ".jpg");
    	File file = Paths.get(resource.toURI()).toFile();
    	byte[] picInBytes = new byte[(int) file.length()];
    	FileInputStream fileInputStream = new FileInputStream(file);
    	fileInputStream.read(picInBytes);
    	fileInputStream.close();
		return picInBytes;
	}
}
