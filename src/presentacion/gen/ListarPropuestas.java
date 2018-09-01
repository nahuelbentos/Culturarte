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

@SuppressWarnings("serial")
public class ListarPropuestas extends JPanel {

	private Object[][] data;
	
	private final Object[] columnNames = { 
	                              "Titulo:",
	                              "Por:"};		    
	private JScrollPane grilla;
	private JTable table;
	
	private IPropuestaController iPC;
	
	public ListarPropuestas(IPropuestaController IPU) {
		setLayout(new GridLayout(1,1));
		
		iPC = IPU;
		
		try {
			
			DtPropuestaMinificado[] props = iPC.listarPropuestas();
			data = new Object[props.length][columnNames.length];
			
			for (int i = 0; i < props.length; i++) {
				for (int j = 0; j < columnNames.length; j++) {
					switch (j) {
					case 0:
						data[i][j] = props[i].getTitulo();
						break;
					case 1:
						data[i][j] = props[i].getProponente();
						break;
					}
				}
				
			}
		} catch (PropuestaNoExisteException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage(),"Grilla propuestas", JOptionPane.ERROR_MESSAGE);
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