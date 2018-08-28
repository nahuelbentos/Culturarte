package presentacion;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import logica.IUsuarioController;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ConsultaColaboracionPropuesta extends JInternalFrame {
	
	private ListarColaboradores panelColaboradores;
	private JButton btnVerColaboraciones;
	private String colSeleccionado;
	/**
	 * Create the frame.
	 */
	public ConsultaColaboracionPropuesta(IUsuarioController IUC) {
		setBounds(50, 50, 800, 600);
		getContentPane().setLayout(null);
		
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setClosable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		panelColaboradores = new ListarColaboradores(IUC);
		panelColaboradores.setBounds(12, 12, 250, 244);
		getContentPane().add(panelColaboradores);
		
		{
			btnVerColaboraciones = new JButton("Ver colaboraciones");
			btnVerColaboraciones.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					colSeleccionado = panelColaboradores.getColaboradorSeleccionado();
					System.out.println(colSeleccionado);
				}
			});
			btnVerColaboraciones.setBounds(71, 281, 191, 25);
			getContentPane().add(btnVerColaboraciones);
		}

	}
	
	public void setListarColaboradores() {
		panelColaboradores.actualizarColaboradores();
	}
}
