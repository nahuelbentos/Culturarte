package presentacion.gen;

import javax.swing.JPanel;

import datatype.DtPropuesta;

import javax.swing.JLabel;
import java.awt.Font;
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

	/**
	 * Create the panel.
	 */
	public PropuestaSeleccionada() {
		setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		setLayout(null);
		{
			lblTitulo = new JLabel("Titulo: ");
			lblTitulo.setFont(new Font("Sitka Small", Font.BOLD, 11));
			lblTitulo.setBounds(10, 12, 46, 14);
			add(lblTitulo);
		}
		{
			lblCargoTitulo = new JLabel("New label");
			lblCargoTitulo.setBounds(66, 11, 173, 14);
			lblCargoTitulo.setText("cargo titulo");
			add(lblCargoTitulo);
		}
		{
			lblDescripcin = new JLabel("Descripci\u00F3n: ");
			lblDescripcin.setFont(new Font("Sitka Small", Font.BOLD, 11));
			lblDescripcin.setBounds(10, 37, 77, 14);
			add(lblDescripcin);
		}
		{
			lblFecha = new JLabel("Fecha: ");
			lblFecha.setFont(new Font("Sitka Small", Font.BOLD, 11));
			lblFecha.setBounds(10, 62, 46, 14);
			add(lblFecha);
		}
		{
			lblPublicada = new JLabel("Publicada: ");
			lblPublicada.setFont(new Font("Sitka Small", Font.BOLD, 11));
			lblPublicada.setBounds(10, 87, 77, 14);
			add(lblPublicada);
		}
		{
			lblLugar = new JLabel("Lugar: ");
			lblLugar.setFont(new Font("Sitka Small", Font.BOLD, 11));
			lblLugar.setBounds(10, 112, 46, 14);
			add(lblLugar);
		}
		{
			lblARecaudar = new JLabel("A recaudar:");
			lblARecaudar.setFont(new Font("Sitka Small", Font.BOLD, 11));
			lblARecaudar.setBounds(10, 137, 77, 14);
			add(lblARecaudar);
		}
		{
			lblValorEntrada = new JLabel("Valor entrada?");
			lblValorEntrada.setFont(new Font("Sitka Small", Font.BOLD, 11));
			lblValorEntrada.setBounds(10, 162, 92, 14);
			add(lblValorEntrada);
		}
		{
			lblProponente = new JLabel("De: ");
			lblProponente.setFont(new Font("Sitka Small", Font.BOLD, 11));
			lblProponente.setBounds(75, 249, 46, 14);
			add(lblProponente);
		}
		{
			lblCargoDescripcion = new JLabel("cargo descripcion");
			lblCargoDescripcion.setBounds(97, 36, 142, 14);
			add(lblCargoDescripcion);
		}
		{
			lblCargoFecha = new JLabel("cargo fecha");
			lblCargoFecha.setBounds(66, 61, 173, 14);
			add(lblCargoFecha);
		}
		{
			lblCargoFechapublicada = new JLabel("cargo fecha_publicada");
			lblCargoFechapublicada.setBounds(91, 86, 148, 14);
			add(lblCargoFechapublicada);
		}
		{
			lblCargoLugar = new JLabel("cargo lugar");
			lblCargoLugar.setBounds(66, 111, 173, 14);
			add(lblCargoLugar);
		}
		{
			lblCargoArecaudar = new JLabel("cargo a_recaudar");
			lblCargoArecaudar.setBounds(97, 136, 142, 14);
			add(lblCargoArecaudar);
		}
		{
			lblCargoValorEntrada = new JLabel("cargo valor entrada");
			lblCargoValorEntrada.setBounds(112, 161, 127, 14);
			add(lblCargoValorEntrada);
		}
		{
			lblCargoProponente = new JLabel("cargo proponente");
			lblCargoProponente.setBounds(112, 248, 127, 14);
			add(lblCargoProponente);
		}
		
	}
	
	public void setPropuesta(DtPropuesta prop) {
		lblCargoTitulo.setText(prop.getTitulo());
		lblCargoDescripcion.setText(prop.getDescripcion());
		lblCargoFecha.setText(prop.getFechaEspecatulo().toString());
		lblCargoFechapublicada.setText(prop.getFechaPublicacion().toString());
		lblCargoLugar.setText(prop.getLugar());
		lblCargoProponente.setText(prop.getProponenteACargo().getNickname());
		lblCargoValorEntrada.setText(Double.toString(prop.getPrecioEntrada()));
		lblCargoArecaudar.setText(Double.toString(prop.getMontoNecesario()));
		
	}

}
