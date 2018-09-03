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
		setNormalBounds(new Rectangle(0, 0, 800, 800));
		setClosable(true);
		

//		setNormalBounds(new Rectangle(0, 0, 0, 50));
		//setBounds(100, 100, 673, 425);
		setBounds(10, 10, 745, 681);
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        getContentPane().setLayout(null);
        
        JPanel panelListarProponentes = new JPanel();
        panelListarProponentes.setBounds(0, 0, 400, 250);
        getContentPane().add(panelListarProponentes);
        panelListarProponentes.setLayout(null);
        
        ListarProponentes listarProponentes = new ListarProponentes(IUC);
        listarProponentes.setBounds(0, 0, 400, 250);
        panelListarProponentes.add(listarProponentes);
        


        JPanel panelVerPerfilProponente = new JPanel();
        panelVerPerfilProponente.setBounds(0, 255, 500, 355);
        
        
		if(listarProponentes.getColaboradorSeleccionado() == null)
        	verPerfilProponente = new VerPerfilProponente(IUC, "proponente1");
        else
        	verPerfilProponente = new VerPerfilProponente(IUC, listarProponentes.getColaboradorSeleccionado());
        	
        verPerfilProponente.setBounds(0, 25, 500, 355);
        panelVerPerfilProponente.add(verPerfilProponente);
        
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
        btnVerPerfil.setBounds(405, 225, 114, 25);
        getContentPane().add(btnVerPerfil);
        
        
        

	}
}
