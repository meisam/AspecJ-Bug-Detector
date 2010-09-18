/**
 * Oct 14, 2008
 */
package mfs.aspectj.findbugs.visitors;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import polyglot.ast.Node;
import polyglot.visit.NodeVisitor;

/**
 * @author Meisam Fathi Salmi as Administrator
 * 
 */
public class ASTExtractor extends NodeVisitor {

	private final ASTExtractorObserver observer;

	private final Collection<Node> nodes;

	private final Map<Node, Set<Node>> parentChildren;

	/**
	 * @param observer
	 */
	public ASTExtractor(ASTExtractorObserver observer) {
		this.observer = observer;
		this.observer.setASTExtractor(this);
		nodes = new LinkedList<Node>();
		parentChildren = new LinkedHashMap<Node, Set<Node>>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see polyglot.visit.NodeVisitor#visitEdge(polyglot.ast.Node,
	 *      polyglot.ast.Node)
	 */
	@Override
	public Node visitEdge(Node parent, Node child) {
		if (!nodes.contains(parent)) {
			nodes.add(parent);
		}
		Set<Node> children = parentChildren.get(parent);
		if (children == null) {
			parentChildren.put(parent, new HashSet<Node>());
			children = parentChildren.get(parent);
		}
		children.add(child);

		return super.visitEdge(parent, child);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see polyglot.visit.NodeVisitor#finish()
	 */
	@Override
	public void finish() {
		this.observer.notifyASTExtractcingFinished();
	}

	/**
	 * @return the nodes
	 */
	public Collection<Node> getNodes() {
		return nodes;
	}

	/**
	 * @return the parentChildren
	 */
	public Map<Node, Set<Node>> getParentChildren() {
		return parentChildren;
	}

}
