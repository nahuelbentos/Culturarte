package presentacion.gen;

import javax.swing.JPanel;

import datatype.DtUsuario;
import excepciones.ColaboradorNoExisteException;
import excepciones.ProponenteNoExisteException;
import logica.IUsuarioController;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class ListarProponentes extends JPanel {
	
	private IUsuarioController iUsuController;
	public JList<String> listProponentes;
	private DefaultListModel<String> modelNicknames;
	/**
	 * Create the panel.
	 */
	public ListarProponentes(IUsuarioController IUC) {
		setBorder(new TitledBorder(null, "Proponentes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(null);
		iUsuController = IUC;
		
		
		modelNicknames = new DefaultListModel<String>();
		listProponentes = new JList<>(modelNicknames);
		listProponentes.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent l) {
//				System.ousntln(getColaboradorSeleccionado());
			}
		});
		setListaDeProponentes();
		
		listProponentes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listProponentes.setBorder(new LineBorder(Color.DARK_GRAY, 1, true));
		listProponentes.setBounds(10, 29, 155, 204);
		add(listProponentes);
				
	}
	
	public void setListaDeProponentes() {
		modelNicknames.removeAllElements();
		try {
			DtUsuario[] proponentes = iUsuController.listarProponentes();
	        for (int i = 0; i < proponentes.length; i++) {
	        	modelNicknames.addElement(proponentes[i].getNickname());
	        }
        } catch (ProponenteNoExisteException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(),"Lista Proponente", JOptionPane.ERROR_MESSAGE);
        }
	}
	
	public String getColaboradorSeleccionado() {
		return listProponentes.getSelectedValue();
	}
}
