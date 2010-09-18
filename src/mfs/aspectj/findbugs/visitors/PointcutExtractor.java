/**
 * Oct 12, 2008
 */
package mfs.aspectj.findbugs.visitors;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import polyglot.ast.Node;
import polyglot.visit.NodeVisitor;
import abc.aspectj.ast.Pointcut;

/**
 * @author Meisam Fathi Salmi as Administrator
 * 
 */
public class PointcutExtractor extends NodeVisitor {

	private final PointcutExtractorObserver observer;

	/**
	 * 
	 */
	public PointcutExtractor(PointcutExtractorObserver observer) {
		this.observer = observer;
		observer.setPointcutExtractor(this);
	}

	private final Collection<Pointcut> pointcuts = new LinkedList<Pointcut>();
	private final Map<Node, Pointcut> parents = new HashMap<Node, Pointcut>();
	private final Collection<Node> children = new LinkedList<Node>();

	@Override
	public Node leave(Node old, Node n, NodeVisitor v) {
		if (n instanceof Pointcut) {
			Pointcut pointcut = (Pointcut) n;
			pointcuts.add(pointcut);
		}
		return n;
	}

	public Collection<Pointcut> getPointcuts() {
		return pointcuts;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see polyglot.visit.NodeVisitor#finish()
	 */
	@Override
	public void finish() {
		observer.notifyPointcutExtracingFinished();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see polyglot.visit.NodeVisitor#visitEdge(polyglot.ast.Node,
	 *      polyglot.ast.Node)
	 */
	@Override
	public Node visitEdge(Node parent, Node child) {
		if (parent instanceof Pointcut) {
			parents.put(child, (Pointcut) parent);
			children.add(child);
		}
		return super.visitEdge(parent, child);
	}

	/**
	 * @return the parents
	 */
	public Map<Node, Pointcut> getParents() {
		return parents;
	}

	/**
	 * @return the children
	 */
	public Collection<Node> getChildren() {
		return children;
	}

}
