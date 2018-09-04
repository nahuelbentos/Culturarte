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

public class ConsultaPerfilProponente extends JInternalFrame {

	/**
	 * Create the frame.
	 * @throws UsuarioNoExisteElUsuarioException 
	 */
	private VerPerfilProponente verPerfilProponente;
	public ConsultaPerfilProponente(IUsuarioController IUC) throws UsuarioNoExisteElUsuarioException, PropuestaNoExisteException{
		setClosable(true);
		

//		setNormalBounds(new Rectangle(0, 0, 0, 50));
		//setBounds(100, 100, 673, 425);
		setBounds(0, 0, 1000, 1000);
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        getContentPane().setLayout(null);
        
        JPanel panelListarProponentes = new JPanel();
        panelListarProponentes.setBounds(0, 0, 503, 320);
        getContentPane().add(panelListarProponentes);
        panelListarProponentes.setLayout(null);
        
        ListarProponentes listarProponentes = new ListarProponentes(IUC);
        listarProponentes.setBounds(0, 0, 499, 305);
        panelListarProponentes.add(listarProponentes);
        


        JPanel panelVerPerfilProponente = new JPanel();
        panelVerPerfilProponente.setBounds(10, 368, 914, 535);
        
		if(listarProponentes.getColaboradorSeleccionado() != null)
        	verPerfilProponente = new VerPerfilProponente(IUC, listarProponentes.getColaboradorSeleccionado());
		else {
	        listarProponentes.listProponentes.setSelectedIndex(0);
	        verPerfilProponente = new VerPerfilProponente(IUC, listarProponentes.getColaboradorSeleccionado());
		}	
//        verPerfilProponente.setBounds(0, 25, 500, 355);
//        panelVerPerfilProponente.add(verPerfilProponente);
        
        getContentPane().add(panelVerPerfilProponente);
        panelVerPerfilProponente.setLayout(null);
        
        JButton btnVerPerfil = new JButton("Ver Perfil");
        btnVerPerfil.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		try {
        			verPerfilProponente.removeAll();
					verPerfilProponente = new VerPerfilProponente(IUC, listarProponentes.getColaboradorSeleccionado());
					verPerfilProponente.setBounds(0, 25, 500, 355);
			        panelVerPerfilProponente.add(verPerfilProponente);
			        
			        getContentPane().add(panelVerPerfilProponente);
			        panelVerPerfilProponente.setLayout(null);
			        
				} catch (UsuarioNoExisteElUsuarioException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (PropuestaNoExisteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        });
        btnVerPerfil.setBounds(528, 170, 114, 25);
        getContentPane().add(btnVerPerfil);
        
        
        

	}
}
