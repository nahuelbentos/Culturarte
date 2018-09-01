package presentacion;

import javax.swing.JPanel;

import datatype.DtColaborador;
import datatype.DtUsuario;
import logica.IUsuarioController;


import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ListarProponentes extends JPanel {
	
	private IUsuarioController iUsuController;
	private JList<String> listProponentes;
	private DefaultListModel<String> modelNicknames;
	/**
	 * Create the panel.
	 */
	public ListarProponentes(IUsuarioController IUC) {
		setLayout(null);
		iUsuController = IUC;
		
		
		modelNicknames = new DefaultListModel<String>();
		listProponentes = new JList<>(modelNicknames);
		listProponentes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listProponentes.setBorder(new LineBorder(Color.DARK_GRAY, 1, true));
		listProponentes.setBounds(27, 52, 163, 184);
		add(listProponentes);
		
		JLabel lblSeleccioneUnColaborador = new JLabel("Seleccione un proponente");
		lblSeleccioneUnColaborador.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		lblSeleccioneUnColaborador.setBounds(12, 12, 308, 15);
		add(lblSeleccioneUnColaborador);
		
		
		JButton btnNewButton = new JButton("Listar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actualizarProponentes();				
			}
		});
		btnNewButton.setBounds(76, 258, 114, 25);
		add(btnNewButton);
		
	}
	
	public void actualizarProponentes() {
		modelNicknames.removeAllElements();
		
//			ArrayList<String> props = iUsuController.listarProponentes();
			DtUsuario[] props = iUsuController.listarProponentes();
			if (props != null) {
				for (DtUsuario p : props) {
					modelNicknames.addElement(p.getNickname());
					
				}
			}else {
				System.out.println("No anda esto che");
				modelNicknames.addElement("No hay usuarios.");
			}
			
		
	}
	
	public String getColaboradorSeleccionado() {
		return listProponentes.getSelectedValue();
	}
}
