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
import java.awt.event.ComponentListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

@SuppressWarnings("serial")
public class ListarProponentes extends JPanel {
	
	private IUsuarioController iUsuController;
	public JList<String> listProponentes;
	private DefaultListModel<String> modelNicknames;
	/**
	 * Create the panel.
	 */
	public ListarProponentes(IUsuarioController IUC) {
		setLayout(null);
		iUsuController = IUC;
		
		
		modelNicknames = new DefaultListModel<String>();
		listProponentes = new JList<>(modelNicknames);
		listProponentes.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent l) {
				System.out.println(getColaboradorSeleccionado());
			}
		});
		setListaDeProponentes();
		
		listProponentes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listProponentes.setBorder(new LineBorder(Color.DARK_GRAY, 1, true));
		listProponentes.setBounds(27, 52, 375, 94);
		add(listProponentes);
		
		JLabel lblSeleccioneUnColaborador = new JLabel("Seleccione un proponente");
		lblSeleccioneUnColaborador.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		lblSeleccioneUnColaborador.setBounds(12, 12, 308, 15);
		add(lblSeleccioneUnColaborador);
				
	}
	
	public void setListaDeProponentes() {
		System.out.println("Entro en setListaDeProponentes \n");
		modelNicknames.removeAllElements();
        DtUsuario[] proponentes = iUsuController.listarProponentes();
        if (proponentes != null) {
            for (int i = 0; i < proponentes.length; i++) {
            	modelNicknames.addElement(proponentes[i].getNickname());
            	
        		System.out.println("Proponente [" +i+ "]: " +  proponentes[i].getNickname() + " \n");
            }
        } else {
        	modelNicknames.addElement("No hay proponentes registrados en el sistema");
        }
		System.out.println("Termino setListaDeProponentes \n");
	}
	
	public String getColaboradorSeleccionado() {
		return listProponentes.getSelectedValue();
	}
}
