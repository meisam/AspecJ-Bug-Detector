/**
 * Oct 14, 2008
 */
package mfs.aspectj.findbugs.visitors;

import java.util.Collection;
import java.util.LinkedList;

import polyglot.ast.Node;
import polyglot.ast.Receiver;
import polyglot.visit.NodeVisitor;
import abc.aspectj.ast.AdviceDecl;
import abc.aspectj.ast.PCName_c;

/**
 * @author Meisam Fathi Salmi as Administrator
 * 
 */
public class AdviceDecExtractor extends NodeVisitor {

	private final Collection<AdviceDecl> advices = new LinkedList<AdviceDecl>();

	private final AdviceDecExtractorObserver observer;

	private Node root = null;

	/**
	 * @param observer
	 */
	public AdviceDecExtractor(AdviceDecExtractorObserver observer) {
		this.observer = observer;
		observer.setAdviceDecExtractor(this);
	}

	@Override
	public Node leave(Node old, Node n, NodeVisitor v) {
		if (root == null) {
			root = n;
		}
		if (old instanceof AdviceDecl) {
			AdviceDecl advice = (AdviceDecl) old;
			advices.add(advice);
		}

		if (n instanceof PCName_c) {
			PCName_c pcName = (PCName_c) n;
			Receiver target = pcName.target();

			System.out.println(target);
			if (target != null) {
				System.out.println(target.getClass());
			}
		}
		return n;
	}

	/**
	 * @return the advices
	 */
	public Collection<AdviceDecl> getAdvices() {
		return advices;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see polyglot.visit.NodeVisitor#finish()
	 */
	@Override
	public void finish() {
		this.observer.notifyAdviceDecExtractcingFinished();
	}
}
