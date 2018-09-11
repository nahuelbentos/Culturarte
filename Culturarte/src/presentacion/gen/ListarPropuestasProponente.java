package presentacion.gen;


import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import datatype.DtPropuesta;
import datatype.DtPropuestaMinificado;
import excepciones.PropuestaNoExisteException;
import logica.IPropuestaController;

import java.awt.GridLayout;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class ListarPropuestasProponente extends JPanel {

	private Object[][] data;
	
	private final Object[] columnNames = { 
	                              "Titulo:",
	                              "Descripcion:"};		    
	private JScrollPane grilla;
	private JTable table;
	
	private IPropuestaController iPC;
	
	public ListarPropuestasProponente(ArrayList<DtPropuesta> props) {
		setLayout(new GridLayout(1,1));
//		System.out.println("ListarPropuestaProponente \n");
//
//		System.out.println("Propuesta1: " + props.get(0).getTitulo()+ " \n");
//		System.out.println("PropuestaDescripcion0: " + props.get(0).getDescripcion()+ " \n");
		
		data = new Object[props.size()][columnNames.length];
		
		for (int i = 0; i < props.size(); i++) {
			for (int j = 0; j < columnNames.length; j++) {
				switch (j) {
				case 0:
					data[i][j] = props.get(i).getTitulo();
					break;
				case 1:
					data[i][j] = props.get(i).getDescripcion();
					break;
				}
			}
		}
		this.table = new JTable(data,columnNames) {
			public boolean isCellEditable(int rowIndex, int vColIndex) {
	            return false;
			}
		};
			
		this.grilla = new JScrollPane(table);
        this.add(grilla);
    }
	
	public DtPropuesta getPropuestaSeleccionada() {
		int filaSeleccionada = table.getSelectedRow();
		DtPropuestaMinificado[] colProp;
		try {
			colProp = iPC.listarPropuestas();
			
			DtPropuesta propuestaCompleto = iPC.seleccionarPropuesta(colProp[filaSeleccionada].getTitulo());
			
			return propuestaCompleto;
		} catch (PropuestaNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
		
	}
	
}






