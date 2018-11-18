package presentacion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

@SuppressWarnings("serial")
public class VerRegistroDeAccesosAlSitio extends JInternalFrame {
	private JTable table;
	public VerRegistroDeAccesosAlSitio() {
		
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        getContentPane().setLayout(null);
        setTitle("Ver Registro de Accesos al Sitio");
        setBounds(10, 10, 875, 648);
		
        getContentPane().setLayout(null);
        
    	JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 839, 596);
		getContentPane().add(scrollPane);
		scrollPane.setEnabled(false);
        
		table = new JTable() {
			public boolean isCellEditable(int rowIndex, int vColIndex) {
	            return false;
			}
		};
        table.setColumnSelectionAllowed(true);
        table.setBounds(10, 11, 769, 278);
        table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"IP", "URL", "Navegador", "Sistema Operativo", "Fecha y Hora"
				}
			));
        table.setAutoCreateRowSorter(true);
        scrollPane.setViewportView(table);
        
        String header[] = {"IP", "URL", "Navegador", "Sistema Operativo", "Fecha y Hora"};
        
        for(int i=0; i < table.getColumnCount(); i++) {
        	TableColumn column1 = table.getTableHeader().getColumnModel().getColumn(i);
        	column1.setHeaderValue(header[i]);
        } 
        
	}
	
    void setListaDeAccesosAlSitio() throws UnsupportedEncodingException, IOException {
		DefaultTableModel tableModel = new DefaultTableModel();
		tableModel.addColumn("IP");
		tableModel.addColumn("URL");
		tableModel.addColumn("Navegador");
		tableModel.addColumn("Sistema Operativo");
		tableModel.addColumn("Fecha y Hora");
    	
        String line = "";
        String cvsSplitBy = ",";
        
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("accesos.csv");
        
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"))) {
            while ((line = br.readLine()) != null) {
            	String[] datosAcceso = line.split(cvsSplitBy);
            	String ip = datosAcceso[0];
            	String url = datosAcceso[1];
            	String navegador = datosAcceso[2];
            	String sistemaOperativo = datosAcceso[3];
            	String fecha = datosAcceso[4];
            	tableModel.addRow(new String[] {ip, url, navegador, sistemaOperativo, fecha});
            }
        }
		table.setModel(tableModel);
    }
    
}
