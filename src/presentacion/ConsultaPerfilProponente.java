package presentacion;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JTextField;

import logica.IUsuarioController;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConsultaPerfilProponente extends JInternalFrame {
	private JTextField txtNickname;

	/**
	 * Create the frame.
	 */
	public ConsultaPerfilProponente(IUsuarioController IUC) {
		setBounds(100, 100, 673, 425);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(3, 0, 0, 0));
		
		JLabel lblProponentes = new JLabel("Proponentes:");
		panel.add(lblProponentes);
		
		JComboBox proponentes = new JComboBox();
		panel.add(proponentes);
		
		JLabel lblNickname = new JLabel("Nickname:");
		panel.add(lblNickname);
		
		txtNickname = new JTextField();
		panel.add(txtNickname);
		txtNickname.setColumns(10);
		
		JButton btnConsultaPerfilProponente = new JButton("Consultar");
		btnConsultaPerfilProponente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panel.add(btnConsultaPerfilProponente);

	}

}
