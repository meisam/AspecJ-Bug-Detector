/**
 * Oct 14, 2008
 */
package mfs.aspectj.findbugs.neverused;

import java.util.Collection;
import java.util.LinkedList;

import polyglot.ast.Node;
import polyglot.visit.NodeVisitor;

/**
 * @author Meisam Fathi Salmi as Administrator
 * 
 */
public class ExtractorVisitor<N extends Node, O extends ObserverInterface>
		extends NodeVisitor {

	private final O observer;

	/**
	 * 
	 */
	public ExtractorVisitor(O observer) {
		this.observer = observer;
		observer.setExtractor(this);
	}

	private final Collection<N> nodesCollection = new LinkedList<N>();

	@Override
	public Node leave(Node old, Node n, NodeVisitor v) {
		if (n != null) {
			// if (n instanceof N) {
			try {
				N node = (N) n;
				nodesCollection.add(node);
			} catch (RuntimeException e) {
				// ignored
			}
			// }
		}
		return n;
	}

	public Collection<N> getPointcuts() {
		return nodesCollection;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see polyglot.visit.NodeVisitor#finish()
	 */
	@Override
	public void finish() {
		observer.notifyFinished(this);
	}

}
