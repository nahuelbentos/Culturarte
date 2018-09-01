package presentacion.gen;

import javax.swing.JPanel;

import datatype.DtPropuesta;

import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;

@SuppressWarnings("serial")
public class PropuestaSeleccionada extends JPanel {
	private JLabel lblCargoTitulo;
	private JLabel lblTitulo;
	private JLabel lblDescripcin;
	private JLabel lblFecha;
	private JLabel lblPublicada;
	private JLabel lblLugar;
	private JLabel lblARecaudar;
	private JLabel lblValorEntrada;
	private JLabel lblProponente;
	private JLabel lblCargoDescripcion;
	private JLabel lblCargoFecha;
	private JLabel lblCargoFechapublicada;
	private JLabel lblCargoLugar;
	private JLabel lblCargoArecaudar;
	private JLabel lblCargoValorEntrada;
	private JLabel lblCargoProponente;
	private JLabel lblDatosDeLa;

	/**
	 * Create the panel.
	 */
	public PropuestaSeleccionada() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, new Color(51, 102, 102)));
		setLayout(null);
		{
			lblTitulo = new JLabel("Titulo: ");
			lblTitulo.setFont(new Font("Dialog", Font.BOLD, 12));
			lblTitulo.setBounds(10, 77, 142, 14);
			add(lblTitulo);
		}
		{
			lblCargoTitulo = new JLabel("New label");
			lblCargoTitulo.setFont(new Font("Georgia", Font.PLAIN, 11));
			lblCargoTitulo.setBounds(210, 77, 173, 14);
			lblCargoTitulo.setText("cargo titulo");
			add(lblCargoTitulo);
		}
		{
			lblDescripcin = new JLabel("Descripci\u00F3n: ");
			lblDescripcin.setFont(new Font("Dialog", Font.BOLD, 12));
			lblDescripcin.setBounds(10, 102, 142, 14);
			add(lblDescripcin);
		}
		{
			lblFecha = new JLabel("Fecha: ");
			lblFecha.setFont(new Font("Dialog", Font.BOLD, 12));
			lblFecha.setBounds(10, 127, 142, 14);
			add(lblFecha);
		}
		{
			lblPublicada = new JLabel("Publicada: ");
			lblPublicada.setFont(new Font("Dialog", Font.BOLD, 12));
			lblPublicada.setBounds(10, 152, 142, 14);
			add(lblPublicada);
		}
		{
			lblLugar = new JLabel("Lugar: ");
			lblLugar.setFont(new Font("Dialog", Font.BOLD, 12));
			lblLugar.setBounds(10, 177, 142, 14);
			add(lblLugar);
		}
		{
			lblARecaudar = new JLabel("A recaudar:");
			lblARecaudar.setFont(new Font("Dialog", Font.BOLD, 12));
			lblARecaudar.setBounds(10, 202, 142, 14);
			add(lblARecaudar);
		}
		{
			lblValorEntrada = new JLabel("Valor entrada?");
			lblValorEntrada.setFont(new Font("Dialog", Font.BOLD, 12));
			lblValorEntrada.setBounds(10, 227, 142, 14);
			add(lblValorEntrada);
		}
		{
			lblProponente = new JLabel("De: ");
			lblProponente.setFont(new Font("Dialog", Font.BOLD, 12));
			lblProponente.setBounds(219, 275, 46, 14);
			add(lblProponente);
		}
		{
			lblCargoDescripcion = new JLabel("cargo descripcion");
			lblCargoDescripcion.setFont(new Font("Georgia", Font.PLAIN, 11));
			lblCargoDescripcion.setBounds(210, 103, 142, 14);
			add(lblCargoDescripcion);
		}
		{
			lblCargoFecha = new JLabel("cargo fecha");
			lblCargoFecha.setFont(new Font("Georgia", Font.PLAIN, 11));
			lblCargoFecha.setBounds(210, 127, 173, 14);
			add(lblCargoFecha);
		}
		{
			lblCargoFechapublicada = new JLabel("cargo fecha_publicada");
			lblCargoFechapublicada.setFont(new Font("Georgia", Font.PLAIN, 11));
			lblCargoFechapublicada.setBounds(210, 151, 148, 14);
			add(lblCargoFechapublicada);
		}
		{
			lblCargoLugar = new JLabel("cargo lugar");
			lblCargoLugar.setFont(new Font("Georgia", Font.PLAIN, 11));
			lblCargoLugar.setBounds(210, 177, 173, 14);
			add(lblCargoLugar);
		}
		{
			lblCargoArecaudar = new JLabel("cargo a_recaudar");
			lblCargoArecaudar.setFont(new Font("Georgia", Font.PLAIN, 11));
			lblCargoArecaudar.setBounds(210, 201, 142, 14);
			add(lblCargoArecaudar);
		}
		{
			lblCargoValorEntrada = new JLabel("cargo valor entrada");
			lblCargoValorEntrada.setFont(new Font("Georgia", Font.PLAIN, 11));
			lblCargoValorEntrada.setBounds(210, 226, 127, 14);
			add(lblCargoValorEntrada);
		}
		{
			lblCargoProponente = new JLabel("cargo proponente");
			lblCargoProponente.setFont(new Font("Georgia", Font.BOLD, 12));
			lblCargoProponente.setBounds(256, 274, 184, 14);
			add(lblCargoProponente);
		}
		{
			lblDatosDeLa = new JLabel("Datos de la propuesta");
			lblDatosDeLa.setForeground(new Color(51, 102, 102));
			lblDatosDeLa.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblDatosDeLa.setBounds(10, 11, 255, 28);
			add(lblDatosDeLa);
		}
		
	}
	
	public void setPropuesta(DtPropuesta prop) {
		try {
			lblCargoTitulo.setText(prop.getTitulo());
			lblCargoDescripcion.setText(prop.getDescripcion());
			lblCargoFechapublicada.setText(prop.getFechaPublicacion().toString());
			lblCargoLugar.setText(prop.getLugar());
			lblCargoProponente.setText(prop.getProponenteACargo().getNickname());
			lblCargoValorEntrada.setText(Double.toString(prop.getPrecioEntrada()));
			lblCargoArecaudar.setText(Double.toString(prop.getMontoNecesario()));
			lblCargoFecha.setText(prop.getFechaEspecatulo().toString());
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}

		
	}
}
