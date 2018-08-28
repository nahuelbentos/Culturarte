package presentacion;

import javax.swing.JPanel;

import datatype.DtColaborador;
import logica.IUsuarioController;
import logica.exceptions.ColaboradorNoExisteException;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

@SuppressWarnings("serial")
public class ListarColaboradores extends JPanel {
	
	private IUsuarioController iUsuController;
	private JList<String> listColaboradores;
	private DefaultListModel<String> modelNicknames;
	/**
	 * Create the panel.
	 */
	public ListarColaboradores(IUsuarioController IUC) {
		setLayout(null);
		iUsuController = IUC;
		
		
		modelNicknames = new DefaultListModel<String>();
		listColaboradores = new JList<>(modelNicknames);
		listColaboradores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listColaboradores.setBorder(new LineBorder(Color.DARK_GRAY, 1, true));
		listColaboradores.setBounds(27, 52, 163, 184);
		add(listColaboradores);
		
		JLabel lblSeleccioneUnColaborador = new JLabel("Seleccione un colaborador");
		lblSeleccioneUnColaborador.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		lblSeleccioneUnColaborador.setBounds(12, 12, 308, 15);
		add(lblSeleccioneUnColaborador);
	}
	
	public void actualizarColaboradores() {
		modelNicknames.removeAllElements();
		try {
			DtColaborador[] cols = iUsuController.listarColaboradores();
			for (int i = 0; i < cols.length; i++) {
				modelNicknames.addElement(cols[i].getNickname());
			}
		} catch (ColaboradorNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage(),"Lista colaboradores", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public String getColaboradorSeleccionado() {
		return listColaboradores.getSelectedValue();
	}
}
