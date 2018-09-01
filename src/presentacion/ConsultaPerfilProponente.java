package presentacion;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JTextField;

import logica.IUsuarioController;
import presentacion.gen.ListarColaboradores;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConsultaPerfilProponente extends JInternalFrame {

	/**
	 * Create the frame.
	 */
	public ConsultaPerfilProponente(IUsuarioController IUC) {
		setClosable(true);
		

//		setNormalBounds(new Rectangle(0, 0, 0, 50));
		//setBounds(100, 100, 673, 425);
		setBounds(10, 10, 700, 600);
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        getContentPane().setLayout(null);
        
        JPanel panelListarProponentes = new JPanel();
        panelListarProponentes.setBounds(0, 0, 690, 568);
        getContentPane().add(panelListarProponentes);
        panelListarProponentes.setLayout(null);
        
        ListarColaboradores listarProponentes = new ListarColaboradores((IUsuarioController) null);
        listarProponentes.setBounds(0, 0, 690, 568);
        panelListarProponentes.add(listarProponentes);

//        ListarProponentes listarProponentes = new ListarProponentes((IUsuarioController) null);
//        listarProponentes.setBounds(0, 0, 0, 0);
//        panelListarProponentes.add(listarProponentes);
        
        JPanel panelVerPerfilProponente = new JPanel();
        panelVerPerfilProponente.setBounds(10, 10, 690, 355);
        getContentPane().add(panelVerPerfilProponente);
        panelVerPerfilProponente.setLayout(null);
         
        VerPerfilProponente verPerfilProponente = new VerPerfilProponente(IUC, "pepe");
        verPerfilProponente.setBounds(0, 0, 690, 355);
        panelVerPerfilProponente.add(verPerfilProponente);
        
        panelListarProponentes.setVisible(true);
        panelVerPerfilProponente.setVisible(false);

	}
}
