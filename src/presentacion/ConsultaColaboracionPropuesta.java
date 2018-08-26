package presentacion;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import logica.IUsuarioController;
import logica.exceptions.ColaboradorNoExisteException;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JList;
import javax.swing.ListSelectionModel;

import datatype.DtColaborador;

@SuppressWarnings("serial")
public class ConsultaColaboracionPropuesta extends JInternalFrame {

	private IUsuarioController iUsuarioController;
	private JList list;
	
	/**
	 * Create the frame.
	 */
	public ConsultaColaboracionPropuesta(IUsuarioController IUC) {
		setBounds(100, 100, 450, 300);
		
		iUsuarioController = IUC;
		Object[] data = {"prueba 1", "prueba 2"};
		list = new JList(data);
		/*
		try {
			DtColaborador[] colaboradores;
			colaboradores = iUsuarioController.listarColaboradores();
			
			listColaboradores = new JList<Object>(data);
			listColaboradores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listColaboradores.setLayoutOrientation(JList.VERTICAL);
			listaScroll = new JScrollPane(listColaboradores);
			listaScroll.setPreferredSize(new Dimension(250,80));
			
			//for (int i = 0; i < colaboradores.length; i++) {
			//	
			//}
			
			
		} catch (ColaboradorNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//lblNewLabel.setText(e.getMessage());
		}
		*/
		
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getContentPane().add(list, BorderLayout.CENTER);

	}

}
