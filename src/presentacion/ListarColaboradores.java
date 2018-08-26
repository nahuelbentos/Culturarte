package presentacion;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import datatype.DtColaborador;
import logica.IUsuarioController;
import logica.exceptions.ColaboradorNoExisteException;

import java.awt.Dimension;

import javax.swing.JList;

@SuppressWarnings("serial")
public class ListarColaboradores extends JPanel {
	
	private IUsuarioController iUsuarioController;
	@SuppressWarnings("rawtypes")
	private JList listColaboradores;
	private JScrollPane listaScroll;
	
	/**
	 * Create the panel.
	 */
	public ListarColaboradores(IUsuarioController IUC) {
		setLayout(null);
		iUsuarioController = IUC;
		
		{
			/*
			try {
				DtColaborador[] colaboradores;
				colaboradores = iUsuarioController.listarColaboradores();
				Object[] data = colaboradores;
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
			}*/
			listColaboradores.setBounds(38, 35, 1, 1);
			//add(listColaboradores);
			add(listaScroll);
		}
		

		
		

	}
}
