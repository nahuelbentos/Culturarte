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
public class ListarPropuestasIngresadas extends JPanel {

	private Object[][] data;
	
	private final Object[] columnNames = { 
	                              "Titulo:",
	                              "Por:"};		    
	private JScrollPane grilla;
	private JTable table;
	
	private IPropuestaController iPC;
	private DtPropuestaMinificado[] props;
	
	public ListarPropuestasIngresadas(IPropuestaController IPU) {
		setLayout(new GridLayout(1,1));
		
		iPC = IPU;
		data = new Object[1][columnNames.length];
		props = new DtPropuestaMinificado[1];
		
		this.table = new JTable(data,columnNames) {
			public boolean isCellEditable(int rowIndex, int vColIndex) {
	            return false;
			}
		};
			
		this.grilla = new JScrollPane(table);
        this.add(grilla);
    }
	
	public DtPropuesta getPropuestaSeleccionada() {
		DtPropuesta propuestaCompleto = iPC.seleccionarPropuesta(props[table.getSelectedRow()].getTitulo());
		
		return propuestaCompleto;
	}
	
	public String getPropuestaTituloSeleccionada() {
		return props[table.getSelectedRow()].getTitulo();
	}
	
	public void actualizarPropuestas() {
		try {
			
			props = iPC.listadoPropuestasIngresadas();
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
			
			this.table = new JTable(data,columnNames) {
				public boolean isCellEditable(int rowIndex, int vColIndex) {
		            return false;
				}
			};

			// borro la grilla que estaba.
			this.remove(grilla);
			
			grilla = new JScrollPane(table);
	        this.add(grilla);
			
		} catch (PropuestaNoExisteException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage(),"Grilla propuestas", JOptionPane.ERROR_MESSAGE);
		}
		

	}
	
	
	
}
