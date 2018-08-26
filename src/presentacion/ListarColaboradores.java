package presentacion;

import javax.swing.JPanel;

import datatype.DtColaborador;
import logica.IUsuarioController;
import logica.exceptions.ColaboradorNoExisteException;

import javax.swing.JList;
import javax.swing.ListSelectionModel;

@SuppressWarnings("serial")
public class ListarColaboradores extends JPanel {
	
	private IUsuarioController iUsuController;
	private String[] nombres;
	private JList listColaboradores;
	/**
	 * Create the panel.
	 */
	public ListarColaboradores(IUsuarioController IUC) {
		setLayout(null);
		{
			
			iUsuController = IUC;
			try {
				DtColaborador[] cols = iUsuController.listarColaboradores();
				nombres = new String[cols.length];
				for (int i = 0; i < cols.length; i++) {
					nombres[i] = cols[i].getNombre()+" "+cols[i].getApellido();
				}
			} catch (ColaboradorNoExisteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

			
			listColaboradores = new JList(nombres);
			listColaboradores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listColaboradores.setBounds(70, 36, 320, 184);
			add(listColaboradores);
		}
		
		
		
	}
}
