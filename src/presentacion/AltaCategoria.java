package presentacion;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import logica.CategoriaController;
import logica.ICategoriaController;
import datatype.DtCategoria;
import excepciones.CategoriaNoExisteException;
import excepciones.CategoriaYaExisteException;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;


@SuppressWarnings("serial")
public class AltaCategoria extends JInternalFrame {

	private ICategoriaController iCategoriaController;
	private DefaultMutableTreeNode categorias;
	private JTree treeCategorias;
	private JTextField entCategoria;
	private String categoria;
	
	/**
	 * Create the frame.
	 */
	public AltaCategoria(ICategoriaController ICC) {
		
		iCategoriaController = ICC;
		
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
		
		listarCategorias();
		
		entCategoria = new JTextField();
		entCategoria.setBounds(155, 246, 233, 19);
		getContentPane().add(entCategoria);
		entCategoria.setColumns(10);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiarFormulario();
				setVisible(false);
			}
		});
		btnCancelar.setBounds(239, 312, 114, 25);
		getContentPane().add(btnCancelar);
		
		JButton btnAceptar = new JButton("Confirmar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				altaDeCategoria();
				limpiarFormulario();
			}
		});
		btnAceptar.setBounds(45, 312, 114, 25);
		getContentPane().add(btnAceptar);
		
		JLabel lblNuevaCategoria = new JLabel("Nueva categoria");
		lblNuevaCategoria.setBounds(12, 248, 125, 15);
		getContentPane().add(lblNuevaCategoria);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 37, 376, 199);
		getContentPane().add(scrollPane);
		treeCategorias = new JTree(categorias);
		scrollPane.setViewportView(treeCategorias);
		treeCategorias.setToolTipText("Seleccione mas de una manteniendo presionada la tecla Ctrl");
	}
	
	public void listarCategorias() {
		DtCategoria dtC[] = null;
//		treeCategorias.removeAll();
		dtC = iCategoriaController.listarCategorias();
		
		if (dtC != null) {
			for (DtCategoria i : dtC) {
				categorias = hermosaRecursion(i);
			}
		}else
			categorias = new DefaultMutableTreeNode("Categorías");
//		treeCategorias.repaint();
	}
	
	private DefaultMutableTreeNode hermosaRecursion(DtCategoria raiz) {
		if (raiz==null) {
			return null;
		}else {
//			System.out.println(raiz.getNombre());
			DefaultMutableTreeNode categoria = new DefaultMutableTreeNode(raiz.getNombre());
			if (raiz.getSubCategorias()!=null) {
				for (DtCategoria d : raiz.getSubCategorias()) {
					DefaultMutableTreeNode local = new DefaultMutableTreeNode(d.getNombre());
					local = hermosaRecursion(d);
					if (local != null)
						categoria.add(local);
				}
			}
			return categoria;
		}
	}
	
	
	private void altaDeCategoria() {
		if (formularioOk()) {
			ArrayList<DtCategoria> padres = new ArrayList<>();
			if (treeCategorias.getSelectionPaths() != null) {
				TreePath[] tpCol = treeCategorias.getSelectionPaths();
				for (TreePath tp : tpCol) {
					padres.add(new DtCategoria(tp.getLastPathComponent().toString()));
				}
			}
			
			DtCategoria dtC = new DtCategoria(categoria, padres, null);
			try {
				iCategoriaController.agregarCategoria(dtC);
				JOptionPane.showMessageDialog(this, "La categoría se ha creado con éxito", "Alta de categoría", JOptionPane.INFORMATION_MESSAGE);
			} catch (CategoriaYaExisteException e) {
				JOptionPane.showMessageDialog(this, e, "Alta de categoría", JOptionPane.INFORMATION_MESSAGE);
			} catch (CategoriaNoExisteException e) {
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
		listarCategorias();
		// habría que refrescar el arbol de categorías
	}
}