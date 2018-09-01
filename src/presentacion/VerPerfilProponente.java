package presentacion;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;

import datatype.DtPerfilProponente;
import logica.IUsuarioController;

import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.FlowLayout;
import java.util.GregorianCalendar;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;

public class VerPerfilProponente extends JPanel {
	private JTable tableDatos;
	private IUsuarioController iUsuController;
	private JList<String> listPropuestas;
	private DefaultListModel<String> modelTitulos;
	private GregorianCalendar f;

	/**
	 * Create the panel.
	 */
	public VerPerfilProponente(IUsuarioController IUC,String nickname) {
		setLayout(null);
		iUsuController = IUC;
		
//		DtPerfilProponente dtp = iUsuController.verPerfilProponente(nickname);
		modelTitulos = new DefaultListModel<String>();
		listPropuestas = new JList<>(modelTitulos);
		f = null;
//		f.set(2018, 8, 8);
		DtPerfilProponente dtp = new DtPerfilProponente("prueba", "prueba", "prueba", "prueba", f, "prueba", "prueba", "prueba", "prueba",
				null, null, null, null, null); 
		tableDatos = new JTable();
		tableDatos.setBounds(0, 38, 633, 326);
		tableDatos.setModel(new DefaultTableModel(
			new Object[][] {
				{"Nickname:", dtp.getNickname()},
				{"Nombre:", dtp.getNombre()},
				{"Apellido:", dtp.getApellido()},
				{"Correo electronico:", dtp.getEmail()},
				{"Fecha de nacimiento:", ""},
				{"Imagen:", dtp.getImagen()},
				{"Direccion:", dtp.getDireccion()},
				{"Biografia:", dtp.getBiografia()},
				{"Link web:", dtp.getSitioWeb()},
				{"Propuestas publicadas:", dtp.getPrPublicadas()},
				{"Propuestas canceladas:", dtp.getPrCanceladas()},
				{"Propuestas en financiacion:", dtp.getPrEnFinanciacion()},
				{"Propuestas financiadas:", dtp.getPrFinanciadas()},
				{"Propuestas no financiadas:", dtp.getPrNoFinanciadas()},
			},
			new String[] {
				"New column", "New column"
			}
		));
		tableDatos.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(tableDatos);

	}
}
