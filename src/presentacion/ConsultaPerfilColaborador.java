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

public class ConsultaPerfilColaborador extends JInternalFrame {

	/**
	 * Create the frame.
	 * @throws UsuarioNoExisteElUsuarioException 
	 */
	private VerPerfilColaborador verPerfilColaborador;
	
	public ConsultaPerfilColaborador(IUsuarioController IUC) throws UsuarioNoExisteElUsuarioException, PropuestaNoExisteException{
		setNormalBounds(new Rectangle(0, 0, 800, 800));
		setClosable(true);
		

//		setNormalBounds(new Rectangle(0, 0, 0, 50));
		//setBounds(100, 100, 673, 425);
		setBounds(10, 10, 883, 681);
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        getContentPane().setLayout(null);
        
        JPanel panelListarColaboradores = new JPanel();
        panelListarColaboradores.setBounds(0, 0, 400, 250);
        getContentPane().add(panelListarColaboradores);
        panelListarColaboradores.setLayout(null);
        
        ListarColaboradores listarColaboradores = new ListarColaboradores(IUC);
        listarColaboradores.actualizarColaboradores();
        listarColaboradores.setBounds(0, 0, 400, 250);
        panelListarColaboradores.add(listarColaboradores);
        


        JPanel panelVerPerfilColaborador= new JPanel();
        panelVerPerfilColaborador.setBounds(0, 255, 900, 500);
        
        
		if(listarColaboradores.getColaboradorSeleccionado() != null)
        	verPerfilColaborador = new VerPerfilColaborador(IUC, listarColaboradores.getColaboradorSeleccionado());
		else {
			listarColaboradores.listColaboradores.setSelectedIndex(0);
			verPerfilColaborador = new VerPerfilColaborador(IUC, listarColaboradores.getColaboradorSeleccionado());
		}	
        verPerfilColaborador.setBounds(0, 25, 500, 355);
        panelVerPerfilColaborador.add(verPerfilColaborador);
        getContentPane().add(panelVerPerfilColaborador);
        panelVerPerfilColaborador.setLayout(null);
        
        JButton btnVerPerfil = new JButton("Ver Perfil");
        btnVerPerfil.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		try {
        			verPerfilColaborador.removeAll();
        			verPerfilColaborador = new VerPerfilColaborador(IUC, listarColaboradores.getColaboradorSeleccionado());
					verPerfilColaborador.setBounds(0, 25, 500, 355);
			        panelVerPerfilColaborador.add(verPerfilColaborador);
			        
			        getContentPane().add(panelVerPerfilColaborador);
			        panelVerPerfilColaborador.setLayout(null);
			        
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
