/**
 * Nov 1, 2008
 */
package mfs.aspectj.parser;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 * @author Meisam Fathi Salmi as Administrator
 * 
 */
public class ASTinJTRee extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTree tree;

	public ASTinJTRee() {
		this.setSize(1000, 700);
		this.getContentPane().setLayout(new BorderLayout());

		addTreeToFrame();

		addDatatoTree();
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private void addTreeToFrame() {
		DefaultMutableTreeNode node = new DefaultMutableTreeNode("AST");
		DefaultTreeModel behind = new DefaultTreeModel(node);
		node.add(new DefaultMutableTreeNode("package declaration"));
		node.add(new DefaultMutableTreeNode("import declaration"));
		node.add(new DefaultMutableTreeNode("eval {stm}"));
		tree = new JTree(behind);

		// behind.
		this.getContentPane().add(tree, BorderLayout.CENTER);
	}

	private void addDatatoTree() {
		// tree.a

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		new ASTinJTRee();

	}

}
