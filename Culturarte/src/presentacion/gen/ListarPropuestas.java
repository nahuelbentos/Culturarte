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
public class ListarPropuestas extends JPanel {
   
	private JScrollPane grilla;
	private JTable table;
	
	private DtPropuestaMinificado[] props;
	private IPropuestaController iPC;
	
	public ListarPropuestas(IPropuestaController IPU) {
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
			
		grilla = new JScrollPane(table);
        add(grilla);
    }
	
	public DtPropuesta getPropuestaSeleccionada() {
		DtPropuesta propuestaCompleto = iPC.seleccionarPropuesta(props[table.getSelectedRow()].getTitulo());
		
		return propuestaCompleto;
	}
	
	public String getPropuestaTituloSeleccionada() {
		int filaSeleccionada = table.getSelectedRow();
		String s = "";
		System.out.println(filaSeleccionada);
		if (filaSeleccionada>=0) {
			DtPropuestaMinificado[] colProp;
			try {
				colProp = iPC.listarPropuestas();
							
				return colProp[filaSeleccionada].getTitulo();
			} catch (PropuestaNoExisteException e) {
				e.printStackTrace();
				return s;
			}
		}else {
			return s;
		}
		
	}
	
	public String getPrimerTitulo() {
		DtPropuestaMinificado[] colProp;
		try {
			colProp = iPC.listarPropuestas();
			
			return colProp[0].getTitulo();
		} catch (PropuestaNoExisteException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void actualizarPropuestas() {
		try {
			
			props = iPC.listarPropuestas();
			
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
			JOptionPane.showMessageDialog(this, e.getMessage(),"Grilla propuestas", JOptionPane.ERROR_MESSAGE);
		}
		

	}
	
	
	
}
