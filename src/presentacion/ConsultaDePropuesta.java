package presentacion;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;

import datatype.DtColaboracion;
import datatype.DtPropuesta;
import datatype.TipoRetorno;

import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class ConsultaDePropuesta extends JPanel {
	private JTextField entTitulo;
	private JTextField entDescripcion;
	private JTextField entMontoNecesario;
	private JDateChooser entFechaEspectaculo;
	private JDateChooser entFechaPublicacion;
	private JTextField entLugar;
	private JTextField entPrecioEntrada;
	private JTextField entMontoRecaudado;
	private JComboBox<TipoRetorno> entTipoRetorno;
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

	/**
	 * Create the frame.
	 */
	public ConsultaDePropuesta() {
		setBounds(100, 100, 730, 546);
		this.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 12, 687, 270);
		this.add(panel);
		
		panel.setLayout(null);
		
		entTitulo = new JTextField();
		entTitulo.setEditable(false);
		entTitulo.setBounds(432, 23, 124, 19);
		panel.add(entTitulo);
		entTitulo.setColumns(10);
		
		entDescripcion = new JTextField();
		entDescripcion.setEditable(false);
		entDescripcion.setColumns(10);
		entDescripcion.setBounds(432, 50, 124, 19);
		panel.add(entDescripcion);
		
		entMontoNecesario = new JTextField();
		entMontoNecesario.setEditable(false);
		entMontoNecesario.setColumns(10);
		entMontoNecesario.setBounds(432, 73, 124, 19);
		panel.add(entMontoNecesario);
		
		entFechaEspectaculo = new JDateChooser();
		entFechaEspectaculo.getCalendarButton().setEnabled(false);
		entFechaEspectaculo.setBounds(432, 96, 124, 19);
		panel.add(entFechaEspectaculo);
		
		entFechaPublicacion = new JDateChooser();
		entFechaPublicacion.getCalendarButton().setEnabled(false);
		entFechaPublicacion.setBounds(432, 123, 124, 19);
		panel.add(entFechaPublicacion);
		
		entLugar = new JTextField();
		entLugar.setEditable(false);
		entLugar.setColumns(10);
		entLugar.setBounds(432, 154, 124, 19);
		panel.add(entLugar);
		
		entPrecioEntrada = new JTextField();
		entPrecioEntrada.setEditable(false);
		entPrecioEntrada.setColumns(10);
		entPrecioEntrada.setBounds(432, 185, 124, 19);
		panel.add(entPrecioEntrada);
		
		entMontoRecaudado = new JTextField();
		entMontoRecaudado.setEditable(false);
		entMontoRecaudado.setColumns(10);
		entMontoRecaudado.setBounds(432, 239, 124, 19);
		panel.add(entMontoRecaudado);
		
		lblNewLabel = new JLabel("Título");
		lblNewLabel.setBounds(269, 25, 156, 15);
		panel.add(lblNewLabel);
		
		lblDescripcin = new JLabel("Descripción");
		lblDescripcin.setBounds(269, 50, 156, 15);
		panel.add(lblDescripcin);
		
		lblMontoNecesario = new JLabel("Monto necesario");
		lblMontoNecesario.setBounds(269, 73, 156, 15);
		panel.add(lblMontoNecesario);
		
		lblFechaEspectculo = new JLabel("Fecha espectáculo");
		lblFechaEspectculo.setBounds(269, 96, 156, 15);
		panel.add(lblFechaEspectculo);
		
		lblFechaDePublicacin = new JLabel("Fecha de publicación");
		lblFechaDePublicacin.setBounds(269, 123, 156, 15);
		panel.add(lblFechaDePublicacin);
		
		lblLugar = new JLabel("Lugar");
		lblLugar.setBounds(269, 154, 156, 15);
		panel.add(lblLugar);
		
		lblPrecioEntrada = new JLabel("Precio entrada");
		lblPrecioEntrada.setBounds(269, 185, 156, 15);
		panel.add(lblPrecioEntrada);
		
		lblTipoRetorno = new JLabel("Tipo retorno");
		lblTipoRetorno.setBounds(269, 215, 156, 15);
		panel.add(lblTipoRetorno);
		
		lblMontoRecaudado = new JLabel("Monto recaudado");
		lblMontoRecaudado.setBounds(269, 239, 156, 15);
		panel.add(lblMontoRecaudado);
		
		lblImagenPropuesta = new JLabel("");
		lblImagenPropuesta.setBounds(32, 35, 196, 196);
		panel.add(lblImagenPropuesta);
		
		entTipoRetorno = new JComboBox<>();
		entTipoRetorno.setEnabled(false);
		entTipoRetorno.setModel(new DefaultComboBoxModel<>(TipoRetorno.values()));
		entTipoRetorno.setBounds(432, 210, 124, 24);
		panel.add(entTipoRetorno);
		
		JPanel panelColaboraciones = new JPanel();
		panelColaboraciones.setBorder(new TitledBorder(null, "Colaboraciones", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelColaboraciones.setBounds(12, 294, 687, 200);
		this.add(panelColaboraciones);
		panelColaboraciones.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBounds(12, 30, 663, 150);
		panelColaboraciones.add(scrollPane);
		
		tablaPropuestas = new JTable();
		tablaPropuestas.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Colaborador", "Monto", "Tipo de retorno", "Fecha"
			}
		));
		scrollPane.setViewportView(tablaPropuestas);

	}
	
	public void setPropuesta(DtPropuesta dtP, DtColaboracion[] dtC){
		if(dtP.getImagen() != null) {
			ImageIcon imageIcon = new ImageIcon(dtP.getImagen());
			lblImagenPropuesta.setIcon(imageIcon);
		}
		
		entTitulo.setText(dtP.getTitulo());
		entDescripcion.setText(dtP.getDescripcion());
		entMontoNecesario.setText(Double.toString(dtP.getMontoNecesario()));
		if (dtP.getFechaEspecatulo().getTime() != null)
			entFechaEspectaculo.setCalendar(dtP.getFechaEspecatulo());
		if (dtP.getFechaPublicacion() != null)
			entFechaPublicacion.setCalendar(dtP.getFechaPublicacion());
		entLugar.setText(dtP.getLugar());
		entPrecioEntrada.setText(Double.toString(dtP.getPrecioEntrada()));
		entTipoRetorno.setSelectedItem(dtP.getTipo());
		
		listarColaboraciones(dtC);
		
		entMontoRecaudado.setText(Double.toString(monto));
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
}
