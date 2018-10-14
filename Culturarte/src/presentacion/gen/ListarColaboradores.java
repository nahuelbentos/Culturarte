package presentacion.gen;

import javax.swing.JPanel;

import datatype.DtUsuario;
import excepciones.ColaboradorNoExisteException;
import logica.IUsuarioController;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class ListarColaboradores extends JPanel {
	
	private IUsuarioController iUsuController;
	public JList<String> listColaboradores;
	private DefaultListModel<String> modelNicknames;
	/**
	 * Create the panel.
	 */
	public ListarColaboradores(IUsuarioController IUC) {
		setBorder(new TitledBorder(null, "Colaboradores", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(null);
		iUsuController = IUC;
		
		
		modelNicknames = new DefaultListModel<String>();
		listColaboradores = 	new JList<>(modelNicknames);
		listColaboradores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listColaboradores.setBorder(new LineBorder(Color.DARK_GRAY, 1, true));
		listColaboradores.setBounds(27, 52, 163, 184);
		add(listColaboradores);
	}
	
	public void actualizarColaboradores() {
		modelNicknames.removeAllElements();
		try {
			DtUsuario[] cols = iUsuController.listarColaboradores();
			for (int i = 0; i < cols.length; i++) {
				modelNicknames.addElement(cols[i].getNickname());
			}
		} catch (ColaboradorNoExisteException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(),"Lista colaboradores", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public String getColaboradorSeleccionado() {
		return listColaboradores.getSelectedValue();
	}
	
	public String getPrimerColaborador() {
		listColaboradores.setSelectedIndex(0);
		return listColaboradores.getSelectedValue();
	}
}

