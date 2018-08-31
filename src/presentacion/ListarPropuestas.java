package presentacion;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.GridLayout;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class ListarPropuestas extends JPanel {
	
	final Object[][] data = {
		    {"Mary", "Campione", "Snowboarding", "5"},
		    {"Alison", "Huml", "Rowing", "3"},
		    {"Kathy", "Walrath", "Chasing toddlers", "2"},
		    {"Mark", "Andrews", "Speed reading", "20"},
		    {"Angela", "Lih", "Teaching high school", "4"}
		    };
	final Object[] columnNames = {"First Name", 
	                              "Last Name",
	                              "Sport",
	                              "Est. Years Experience"};
		    
	
	private JScrollPane grilla;
	private JTable table;
	private JLabel lblNewLabel;
	
	public ListarPropuestas() {
		setLayout(new GridLayout(0, 2, 0, 0));
		
		lblNewLabel = new JLabel("PROPUESTAS");
		lblNewLabel.setBounds(49, 22, 105, 14);
		add(this.lblNewLabel);
		table = new JTable(data,columnNames);
		grilla = new JScrollPane(table);
        add(grilla);
		
		

    }
}
