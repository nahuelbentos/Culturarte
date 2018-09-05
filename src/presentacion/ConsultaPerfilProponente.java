package presentacion;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JTextField;

import excepciones.PropuestaNoExisteException;
import excepciones.UsuarioNoExisteElUsuarioException;
import logica.IUsuarioController;
import presentacion.gen.ListarColaboradores;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class ConsultaPerfilProponente extends JInternalFrame {

	/**
	 * Create the frame.
	 * @throws UsuarioNoExisteElUsuarioException 
	 */
	private VerPerfilProponente verPerfilProponente;
	private JPanel panelVerPerfilProponente;
	
	public ConsultaPerfilProponente(IUsuarioController IUC) throws UsuarioNoExisteElUsuarioException, PropuestaNoExisteException{
		setClosable(true);
		

//		setNormalBounds(new Rectangle(0, 0, 0, 50));
		//setBounds(100, 100, 673, 425);
		setBounds(0, 0, 1366, 768);
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        getContentPane().setLayout(null);
        
        JPanel panelListarProponentes = new JPanel();
        panelListarProponentes.setBounds(15, 11, 199, 300);
        getContentPane().add(panelListarProponentes);
        panelListarProponentes.setLayout(null);
        
        ListarProponentes listarProponentes = new ListarProponentes(IUC);
        listarProponentes.setBounds(10, 11, 176, 244);
        panelListarProponentes.add(listarProponentes);
        
        panelVerPerfilProponente = new JPanel();
        panelVerPerfilProponente.setBorder(new TitledBorder(null, "Perfil proponente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelVerPerfilProponente.setBounds(224, 11, 1116, 535);
        
		if(listarProponentes.getColaboradorSeleccionado() != null)
        	verPerfilProponente = new VerPerfilProponente(IUC, listarProponentes.getColaboradorSeleccionado());
		else {
	        listarProponentes.listProponentes.setSelectedIndex(0);
	        verPerfilProponente = new VerPerfilProponente(IUC, listarProponentes.getColaboradorSeleccionado());
		}	
        verPerfilProponente.setBounds(0, 25, 655, 343);
        panelVerPerfilProponente.add(verPerfilProponente);
        
        getContentPane().add(panelVerPerfilProponente);
        panelVerPerfilProponente.setLayout(null);

        
        JButton btnVerPerfil = new JButton("Ver Perfil");
        btnVerPerfil.setBounds(72, 266, 114, 25);
        panelListarProponentes.add(btnVerPerfil);
        btnVerPerfil.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		try {
        			System.out.println("01");
        			verPerfilProponente.removeAll();
        			System.out.println("01");
        			verPerfilProponente = new VerPerfilProponente(IUC, listarProponentes.getColaboradorSeleccionado());
        			System.out.println("02");
					verPerfilProponente.setBounds(0, 25, 500, 355);
					System.out.println("03");
			        panelVerPerfilProponente.add(verPerfilProponente);
			        System.out.println("04");
			        
			        getContentPane().add(panelVerPerfilProponente);
			        System.out.println("05");
			        panelVerPerfilProponente.setLayout(null);
			        System.out.println("06");
			        
				} catch (UsuarioNoExisteElUsuarioException e) {
					e.printStackTrace();
				} catch (PropuestaNoExisteException e) {
					e.printStackTrace();
				}
        	}
        });
        
		if(listarProponentes.getColaboradorSeleccionado() != null)
        	verPerfilProponente = new VerPerfilProponente(IUC, listarProponentes.getColaboradorSeleccionado());
		else {
	        listarProponentes.listProponentes.setSelectedIndex(0);
	        verPerfilProponente = new VerPerfilProponente(IUC, listarProponentes.getColaboradorSeleccionado());
		}
        
        
        

	}
}
