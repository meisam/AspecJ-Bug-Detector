/**
 * Oct 12, 2008
 */
package mfs.aspectj.findbugs.visitors;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import polyglot.ast.Node;
import polyglot.visit.NodeVisitor;
import abc.aspectj.ast.Pointcut;
import abc.aspectj.ast.PointcutDecl_c;

/**
 * @author Meisam Fathi Salmi as Administrator
 * 
 */
public class PointcutDecExtractor extends NodeVisitor {

	private final PointcutDecExtractorObserver observer;

	/**
	 * 
	 */
	public PointcutDecExtractor(PointcutDecExtractorObserver observer) {
		this.observer = observer;
		observer.setPointcutDecExtractor(this);
	}

	private final Collection<Pointcut> pointcuts = new LinkedList<Pointcut>();
	private final Map<PointcutDecl_c, Set<Node>> parentChildrenMap = new HashMap<PointcutDecl_c, Set<Node>>();
	private final Collection<PointcutDecl_c> parents = new LinkedList<PointcutDecl_c>();

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
		observer.notifyPointcutDecExtracingFinished();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see polyglot.visit.NodeVisitor#visitEdge(polyglot.ast.Node,
	 *      polyglot.ast.Node)
	 */
	@Override
	public Node visitEdge(Node parent, Node child) {
		if (parent instanceof PointcutDecl_c) {
			PointcutDecl_c pcDec = (PointcutDecl_c) parent;
			if (parentChildrenMap.get(pcDec) == null) {
				parentChildrenMap.put(pcDec, new HashSet<Node>());
			}
			if (!parentChildrenMap.get(pcDec).contains(child)) {
				parentChildrenMap.get(pcDec).add(child);
			}
			if (!parents.contains(pcDec)) {
				parents.add(pcDec);
			}
		} 
		return super.visitEdge(parent, child);
	}

	/**
	 * @return the parentChildrenMap
	 */
	public Map<PointcutDecl_c, Set<Node>> getParentChildrenMap() {
		return parentChildrenMap;
	}

	/**
	 * @return the parents
	 */
	public Collection<PointcutDecl_c> getParents() {
		return parents;
	}

}
