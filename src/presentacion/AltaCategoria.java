package presentacion;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import logica.CategoriaController;
import datatype.DtCategoria;
import excepciones.CategoriaYaExisteException;
import excepciones.ExcepcionCategoriaNoExiste;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


@SuppressWarnings("serial")
public class AltaCategoria extends JInternalFrame {

	private JTree treeListaDeCategorias;
	private JTextField entCategoria;
	private String categoria;
	
	/**
	 * Create the frame.
	 */
	public AltaCategoria() {
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Alta de Categoría");
        setBounds(10, 40, 414, 381);
		
        getContentPane().setLayout(null);
		
		JLabel lblListaDeCategorias = new JLabel("Lista de categorías existentes");
		lblListaDeCategorias.setBounds(65, 11, 250, 14);
		getContentPane().add(lblListaDeCategorias);
		
		// Ejemplo de listar categor�as con JTree.
		listarCategorias();
		getContentPane().add(treeListaDeCategorias);
		
		entCategoria = new JTextField();
		entCategoria.setBounds(155, 233, 233, 19);
		getContentPane().add(entCategoria);
		entCategoria.setColumns(10);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiarFormulario();
				setVisible(false);
			}
		});
		btnCancelar.setBounds(239, 305, 114, 25);
		getContentPane().add(btnCancelar);
		
		JButton btnAceptar = new JButton("Confirmar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				altaDeCategoria();
				limpiarFormulario();
			}
		});
		btnAceptar.setBounds(45, 305, 114, 25);
		getContentPane().add(btnAceptar);
		
		JLabel lblNuevaCategoria = new JLabel("Nueva categoria");
		lblNuevaCategoria.setBounds(12, 235, 125, 15);
		getContentPane().add(lblNuevaCategoria);
	}
	
	public void listarCategorias() {
		CategoriaController cc = new CategoriaController();
		DtCategoria dtC[] = null;
		DefaultMutableTreeNode categorias = new DefaultMutableTreeNode("Categorías");
		try {
			dtC = cc.listarCategorias();
			for(int i = 0; i < dtC.length; i++) {
				DefaultMutableTreeNode categoria;
				categoria = new DefaultMutableTreeNode(dtC[i].getNombre());
				
				categorias.add(categoria);
			}
		} catch (ExcepcionCategoriaNoExiste e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        treeListaDeCategorias = new JTree(categorias);
        treeListaDeCategorias.setToolTipText("Seleccione mas de una manteniendo presionada la tecla Ctrl");
        treeListaDeCategorias.setBounds(12, 33, 376, 174);
	}
	
	private void altaDeCategoria() {
		if (formularioOk()) {
			CategoriaController cc = new CategoriaController();
			
			// FALTA AGREGAR QUE SE SUBAN LOS PADRES. El DtCategoria está mal hecho, ya que el ArrayList de padres es del tipo Categoria
			
			DtCategoria dtC = new DtCategoria(categoria, null);
			try {
				cc.agregarCategoria(dtC);
				JOptionPane.showMessageDialog(this, "La categoría se ha creado con éxito", "Alta de categoría", JOptionPane.INFORMATION_MESSAGE);
			} catch (CategoriaYaExisteException e) {
				JOptionPane.showMessageDialog(this, e, "Alta de categoría", JOptionPane.INFORMATION_MESSAGE);
			}
		}else
			JOptionPane.showMessageDialog(this, "Debe completar todos los campos", "Alta de categoría", JOptionPane.INFORMATION_MESSAGE);
	}
	
	private boolean formularioOk() {
		categoria = entCategoria.getText();
		
		return (!(categoria.isEmpty()));
	}
	
	private void limpiarFormulario() {
		entCategoria.setText("");
		// habría que refrescar el arbol de categorías
	}
}
