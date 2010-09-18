/**
 * Nov 2, 2008
 */
package mfs.aspectj.findbugs.visitors;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import polyglot.ast.Node;
import polyglot.ext.jl.ast.Local_c;
import polyglot.visit.NodeVisitor;
import abc.aspectj.extension.AJCall_c;
import abc.eaj.extension.EAJAdviceDecl_c;

/**
 * This class finds occurrences of <code>thisJoinPoint.getThis()</code>
 * 
 * @author Meisam Fathi Salmi as Administrator
 * 
 */
public class Local_cExtractor extends NodeVisitor {

	private static final String jOINPOINT = "org.aspectj.lang.JoinPoint";

	private final EAJAdviceDecl_c adviceDec;

	private final Collection<AJCall_c> calls;

	private final Map<AJCall_c, Set<Local_c>> callsLocalcs;

	public Local_cExtractor(EAJAdviceDecl_c adviceDec) {
		this.adviceDec = adviceDec;
		calls = new LinkedList<AJCall_c>();
		callsLocalcs = new HashMap<AJCall_c, Set<Local_c>>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see polyglot.visit.NodeVisitor#visitEdge(polyglot.ast.Node,
	 *      polyglot.ast.Node)
	 */
	@Override
	public Node visitEdge(Node parent, Node child) {
		if (parent instanceof AJCall_c) {
			AJCall_c call = (AJCall_c) parent;
			if (child instanceof Local_c) {
				Local_c local = (Local_c) child;

				if (local.localInstance().type().toString().equals(jOINPOINT)) {

					if (!calls.contains(call)) {
						calls.add(call);
					}
					Set<Local_c> set = callsLocalcs.get(call);
					if (set == null) {
						set = new HashSet<Local_c>();
						callsLocalcs.put(call, set);
					}
					callsLocalcs.get(call).add(local);
				}

			}
		}
		return super.visitEdge(parent, child);
	}

	/**
	 * @return the adviceDec
	 */
	public EAJAdviceDecl_c getAdviceDec() {
		return adviceDec;
	}

	/**
	 * @return the calls
	 */
	public Collection<AJCall_c> getCalls() {
		return calls;
	}

	/**
	 * @return the callsLocalcs
	 */
	public Map<AJCall_c, Set<Local_c>> getCallsLocalcs() {
		return callsLocalcs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see polyglot.visit.NodeVisitor#finish()
	 */
	@Override
	// FIXME Why this method is not invoked
	public void finish() {
		System.out.println("Local_cExtractor.finish()");
		for (AJCall_c call : calls) {
			Set<Local_c> locals = callsLocalcs.get(call);
			for (Local_c local : locals) {
				System.out.println(call + "  -->  " + local);
			}
		}
	}

}