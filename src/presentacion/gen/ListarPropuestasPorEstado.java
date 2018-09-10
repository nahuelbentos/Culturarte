package presentacion.gen;



import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import datatype.DtPropuesta;
import datatype.DtPropuestaMinificado;
import datatype.EstadoPropuesta;
import excepciones.PropuestaNoExisteException;
import logica.IPropuestaController;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ListarPropuestasPorEstado extends JPanel {

	private JScrollPane grilla;
	private JTable table;
	private JComboBox<EstadoPropuesta> comboBox;
	
	private IPropuestaController iPC;
	
	public ListarPropuestasPorEstado(IPropuestaController IPU) {
		
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
		
        setLayout(null);
        
        comboBox = new JComboBox<>();
        comboBox.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		actualizarPropuestas();
        	}
        });
        comboBox.setBounds(12, 12, 225, 31);
        comboBox.setModel(new DefaultComboBoxModel<>(EstadoPropuesta.values()));
        add(comboBox);
        
		this.grilla = new JScrollPane();
		grilla.setBounds(12, 54, 225, 300);
		grilla.setViewportView(table);
		this.add(grilla);
    }
	
	public DtPropuesta getPropuestaSeleccionada() {
		int filaSeleccionada = table.getSelectedRow();
		// Creo que sería mejor obtener el título acá para hacer menos consultas
//		String titulo = table.getValueAt(table.getSelectedRow(),0).toString();
		
		DtPropuestaMinificado[] colProp;
		try {
			colProp = iPC.listarPropuestasPorEstado((EstadoPropuesta) comboBox.getSelectedItem());
			
			DtPropuesta propuestaCompleto = iPC.seleccionarPropuesta(colProp[filaSeleccionada].getTitulo());
			
			return propuestaCompleto;
		} catch (PropuestaNoExisteException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String getPropuestaTituloSeleccionada() {
		int filaSeleccionada = table.getSelectedRow();
		String s = "";
		System.out.println(filaSeleccionada);
		if (filaSeleccionada>=0) {
			DtPropuestaMinificado[] colProp;
			try {
				colProp = iPC.listarPropuestasPorEstado((EstadoPropuesta) comboBox.getSelectedItem());
							
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
			colProp = iPC.listarPropuestasPorEstado((EstadoPropuesta) comboBox.getSelectedItem());
			
			return colProp[0].getTitulo();
		} catch (PropuestaNoExisteException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void actualizarPropuestas() {
		try {
			
			DtPropuestaMinificado[] props = iPC.listarPropuestasPorEstado((EstadoPropuesta) comboBox.getSelectedItem());
			
			DefaultTableModel tableModel = new DefaultTableModel();
			tableModel.addColumn("Titulo:");
			tableModel.addColumn("Por:");
			for(DtPropuestaMinificado prop : props) {
				tableModel.addRow(new String[] {prop.getTitulo(), prop.getProponente()});
			}
			table.setModel(tableModel);
			
		} catch (PropuestaNoExisteException e) {
//			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage(),"Grilla propuestas", JOptionPane.ERROR_MESSAGE);
		}
		

	}
	
	
	
}
