package presentacion;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import logica.CategoriaController;
import logica.exceptions.ExcepcionCategoriaNoExiste;
import datatype.DtCategoria;


@SuppressWarnings("serial")
public class AltaCategoria extends JInternalFrame {

	private JTree treeListaDeCategorias;
	
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
        setBounds(10, 40, 417, 351);
		
        getContentPane().setLayout(null);
		
		JLabel lblListaDeCategorias = new JLabel("Lista de categorías existentes");
		lblListaDeCategorias.setBounds(65, 11, 250, 14);
		getContentPane().add(lblListaDeCategorias);
		
		CategoriaController cc = new CategoriaController();
		DtCategoria dtC = new DtCategoria("Música", null);
		cc.agregarCategoria(dtC);
		
		dtC = new DtCategoria("Teatro", null);
		cc.agregarCategoria(dtC);
		
		dtC = new DtCategoria("Espectaculos", null);
		cc.agregarCategoria(dtC);
		
		// Ejemplo de listar categor�as con JTree.
		listarCategorias();
		getContentPane().add(treeListaDeCategorias);
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
        treeListaDeCategorias.setBounds(31, 49, 376, 343);
		
		
		
//        DefaultMutableTreeNode categorias = new DefaultMutableTreeNode("Categorías");
//        DefaultMutableTreeNode teatro = new DefaultMutableTreeNode("Teatro");
//        DefaultMutableTreeNode musica = new DefaultMutableTreeNode("Música");
//        DefaultMutableTreeNode danza = new DefaultMutableTreeNode("Danza");
//        DefaultMutableTreeNode rock = new DefaultMutableTreeNode("Rock");
//        DefaultMutableTreeNode salsa = new DefaultMutableTreeNode("Salsa");
//        DefaultMutableTreeNode pop = new DefaultMutableTreeNode("Pop");
//        DefaultMutableTreeNode rockClasico = new DefaultMutableTreeNode("Rock clásico");
//        DefaultMutableTreeNode rockAlternativo = new DefaultMutableTreeNode("Rock alternativo");
//        categorias.add(teatro);
//        categorias.add(musica);
//        categorias.add(danza);
//        rock.add(rockClasico);
//        rock.add(rockAlternativo);
//        rockClasico.add(new DefaultMutableTreeNode("Hoja"));
//        rockAlternativo.add(new DefaultMutableTreeNode("Hoja"));
//        teatro.add(new DefaultMutableTreeNode("Hoja"));
//        danza.add(new DefaultMutableTreeNode("Hoja"));
//        salsa.add(new DefaultMutableTreeNode("Hoja"));
//        pop.add(new DefaultMutableTreeNode("Hoja"));
//        musica.add(rock);
//        musica.add(salsa);
//        musica.add(pop);
//        treeListaDeCategorias = new JTree(categorias);
//        treeListaDeCategorias.setBounds(31, 49, 376, 343);
	}

}
