package presentacion.gen;



import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import datatype.DtPropuesta;
import datatype.DtPropuestaMinificado;
import excepciones.PropuestaNoExisteException;
import logica.IPropuestaController;

import java.awt.GridLayout;

@SuppressWarnings("serial")
public class ListarPropuestasIngresadas extends JPanel {
  
	private JScrollPane grilla;
	private JTable table;
	
	
	private IPropuestaController iPC;
	private DtPropuestaMinificado[] props;
	
	public ListarPropuestasIngresadas(IPropuestaController IPU) {
		setLayout(new GridLayout(1,1));
		
		iPC = IPU;
		
		table = new JTable() {
			public boolean isCellEditable(int rowIndex, int vColIndex) {
	            return false;
			}
		};
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Titulo:", "Por:"
			}
		));
			
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
			
			DefaultTableModel tableModel = new DefaultTableModel();
			tableModel.addColumn("Titulo:");
			tableModel.addColumn("Por:");
			for(DtPropuestaMinificado prop : props) {
				tableModel.addRow(new String[] {prop.getTitulo(), prop.getProponente()});
			}
			table.setModel(tableModel);

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
