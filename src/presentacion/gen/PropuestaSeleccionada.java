package presentacion.gen;

import javax.swing.JPanel;

import datatype.DtPropuesta;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.text.SimpleDateFormat;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class PropuestaSeleccionada extends JPanel {
	private JPanel panelImagen;
	private JLabel lblImagen;
	private JLabel lblTitulo;
	private JTextField entTitulo;
	private JLabel lblDescripcin;
	private JTextField entDescripcion;
	private JLabel lblPublicadoPor;
	private JTextField entProponente;
	private JLabel lblFecha;
	private JTextField entFechaEspectaculo;
	private JLabel lblMontoARecaudar;
	private JLabel lblRecaudado;
	private JTextField entMontoNecesario;
	private JTextField entRecaudado;

	/**
	 * Create the panel.
	 */
	public PropuestaSeleccionada() {
		setBorder(new TitledBorder(null, "Datos de la propuesta", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{89, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{30, 14, 47, 14, 15, 0, 14, 184, 33, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, 1.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		{
			lblTitulo = new JLabel("Titulo:");
			GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
			gbc_lblTitulo.anchor = GridBagConstraints.EAST;
			gbc_lblTitulo.insets = new Insets(0, 0, 5, 5);
			gbc_lblTitulo.gridx = 2;
			gbc_lblTitulo.gridy = 1;
			add(lblTitulo, gbc_lblTitulo);
		}
		{
			entTitulo = new JTextField();
			entTitulo.setEditable(false);
			entTitulo.setEnabled(false);
			entTitulo.setDisabledTextColor(Color.DARK_GRAY);
			GridBagConstraints gbc_entTitulo = new GridBagConstraints();
			gbc_entTitulo.gridwidth = 2;
			gbc_entTitulo.insets = new Insets(0, 0, 5, 5);
			gbc_entTitulo.fill = GridBagConstraints.HORIZONTAL;
			gbc_entTitulo.gridx = 3;
			gbc_entTitulo.gridy = 1;
			add(entTitulo, gbc_entTitulo);
			entTitulo.setColumns(10);
		}
		{
			lblDescripcin = new JLabel("Descripci\u00F3n:");
			GridBagConstraints gbc_lblDescripcin = new GridBagConstraints();
			gbc_lblDescripcin.anchor = GridBagConstraints.NORTHEAST;
			gbc_lblDescripcin.insets = new Insets(0, 0, 5, 5);
			gbc_lblDescripcin.gridx = 2;
			gbc_lblDescripcin.gridy = 2;
			add(lblDescripcin, gbc_lblDescripcin);
		}
		{
			entDescripcion = new JTextField();
			entDescripcion.setEnabled(false);
			entDescripcion.setEditable(false);
			entDescripcion.setDisabledTextColor(Color.DARK_GRAY);
			GridBagConstraints gbc_entDescripcion = new GridBagConstraints();
			gbc_entDescripcion.gridwidth = 2;
			gbc_entDescripcion.insets = new Insets(0, 0, 5, 5);
			gbc_entDescripcion.fill = GridBagConstraints.BOTH;
			gbc_entDescripcion.gridx = 3;
			gbc_entDescripcion.gridy = 2;
			add(entDescripcion, gbc_entDescripcion);
			entDescripcion.setColumns(10);
		}
		{
			lblFecha = new JLabel("Fecha:");
			GridBagConstraints gbc_lblFecha = new GridBagConstraints();
			gbc_lblFecha.anchor = GridBagConstraints.EAST;
			gbc_lblFecha.insets = new Insets(0, 0, 5, 5);
			gbc_lblFecha.gridx = 0;
			gbc_lblFecha.gridy = 4;
			add(lblFecha, gbc_lblFecha);
		}
		{
			entFechaEspectaculo = new JTextField();
			entFechaEspectaculo.setEnabled(false);
			entFechaEspectaculo.setEditable(false);
			entFechaEspectaculo.setDisabledTextColor(Color.DARK_GRAY);
			GridBagConstraints gbc_entFechaEspectaculo = new GridBagConstraints();
			gbc_entFechaEspectaculo.gridwidth = 3;
			gbc_entFechaEspectaculo.insets = new Insets(0, 0, 5, 5);
			gbc_entFechaEspectaculo.fill = GridBagConstraints.HORIZONTAL;
			gbc_entFechaEspectaculo.gridx = 1;
			gbc_entFechaEspectaculo.gridy = 4;
			add(entFechaEspectaculo, gbc_entFechaEspectaculo);
			entFechaEspectaculo.setColumns(10);
		}
		{
			lblMontoARecaudar = new JLabel("Monto a recaudar:");
			GridBagConstraints gbc_lblMontoARecaudar = new GridBagConstraints();
			gbc_lblMontoARecaudar.anchor = GridBagConstraints.EAST;
			gbc_lblMontoARecaudar.insets = new Insets(0, 0, 5, 5);
			gbc_lblMontoARecaudar.gridx = 4;
			gbc_lblMontoARecaudar.gridy = 4;
			add(lblMontoARecaudar, gbc_lblMontoARecaudar);
		}
		{
			entMontoNecesario = new JTextField();
			entMontoNecesario.setEnabled(false);
			entMontoNecesario.setEditable(false);
			entMontoNecesario.setDisabledTextColor(Color.DARK_GRAY);
			GridBagConstraints gbc_entMontoNecesario = new GridBagConstraints();
			gbc_entMontoNecesario.gridwidth = 2;
			gbc_entMontoNecesario.insets = new Insets(0, 0, 5, 0);
			gbc_entMontoNecesario.fill = GridBagConstraints.HORIZONTAL;
			gbc_entMontoNecesario.gridx = 5;
			gbc_entMontoNecesario.gridy = 4;
			add(entMontoNecesario, gbc_entMontoNecesario);
			entMontoNecesario.setColumns(10);
		}
		{
			lblRecaudado = new JLabel("Recaudado:");
			GridBagConstraints gbc_lblRecaudado = new GridBagConstraints();
			gbc_lblRecaudado.anchor = GridBagConstraints.EAST;
			gbc_lblRecaudado.insets = new Insets(0, 0, 5, 5);
			gbc_lblRecaudado.gridx = 4;
			gbc_lblRecaudado.gridy = 5;
			add(lblRecaudado, gbc_lblRecaudado);
		}
		{
			entRecaudado = new JTextField();
			entRecaudado.setEnabled(false);
			entRecaudado.setEditable(false);
			entRecaudado.setDisabledTextColor(Color.DARK_GRAY);
			GridBagConstraints gbc_entRecaudado = new GridBagConstraints();
			gbc_entRecaudado.gridwidth = 2;
			gbc_entRecaudado.insets = new Insets(0, 0, 5, 0);
			gbc_entRecaudado.fill = GridBagConstraints.HORIZONTAL;
			gbc_entRecaudado.gridx = 5;
			gbc_entRecaudado.gridy = 5;
			add(entRecaudado, gbc_entRecaudado);
			entRecaudado.setColumns(10);
		}
		{
			panelImagen = new JPanel();
			GridBagConstraints gbc_panelImagen = new GridBagConstraints();
			gbc_panelImagen.insets = new Insets(0, 0, 5, 0);
			gbc_panelImagen.gridwidth = 7;
			gbc_panelImagen.fill = GridBagConstraints.BOTH;
			gbc_panelImagen.gridx = 0;
			gbc_panelImagen.gridy = 7;
			add(panelImagen, gbc_panelImagen);
			{
				lblImagen = new JLabel("");
				panelImagen.add(lblImagen);
			}
		}
		{
			lblPublicadoPor = new JLabel("Publicado por: ");
			GridBagConstraints gbc_lblPublicadoPor = new GridBagConstraints();
			gbc_lblPublicadoPor.anchor = GridBagConstraints.EAST;
			gbc_lblPublicadoPor.insets = new Insets(0, 0, 0, 5);
			gbc_lblPublicadoPor.gridx = 4;
			gbc_lblPublicadoPor.gridy = 8;
			add(lblPublicadoPor, gbc_lblPublicadoPor);
		}
		{
			entProponente = new JTextField();
			entProponente.setEnabled(false);
			entProponente.setEditable(false);
			entProponente.setDisabledTextColor(Color.DARK_GRAY);
			GridBagConstraints gbc_entProponente = new GridBagConstraints();
			gbc_entProponente.gridwidth = 2;
			gbc_entProponente.fill = GridBagConstraints.HORIZONTAL;
			gbc_entProponente.gridx = 5;
			gbc_entProponente.gridy = 8;
			add(entProponente, gbc_entProponente);
			entProponente.setColumns(10);
		}
		
	}
	
	public void setPropuesta(DtPropuesta prop) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
		
		entTitulo.setText(prop.getTitulo());
		entDescripcion.setText(prop.getDescripcion());
		entProponente.setText(prop.getProponenteACargo().getNickname());
		entFechaEspectaculo.setText(sdf.format(prop.getFechaEspecatulo().getTime()));
		entMontoNecesario.setText(Double.toString(prop.getMontoNecesario()));
		entRecaudado.setText(Double.toString(prop.getRecaudado()));
		if(prop.getImagen() != null) {
			ImageIcon imageIcon = new ImageIcon(prop.getImagen());
			lblImagen.setIcon(imageIcon);
		}
		
	}
}
