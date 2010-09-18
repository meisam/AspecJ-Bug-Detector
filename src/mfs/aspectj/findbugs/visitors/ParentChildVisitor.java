/**
 * Oct 15, 2008
 */
package mfs.aspectj.findbugs.visitors;

import polyglot.ast.Node;
import polyglot.visit.NodeVisitor;

/**
 * @author Meisam Fathi Salmi as Administrator
 * 
 */
public class ParentChildVisitor extends NodeVisitor {

	/*
	 * (non-Javadoc)
	 * 
	 * @see polyglot.visit.NodeVisitor#visitEdge(polyglot.ast.Node,
	 *      polyglot.ast.Node)
	 */
	@Override
	public Node visitEdge(Node parent, Node child) {
		System.out.println(parent + "\t-->\n\t\t" + child + "\t["
				+ child.getClass() + "]");
		return super.visitEdge(parent, child);
	}

}
