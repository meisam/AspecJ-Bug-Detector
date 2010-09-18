/**
 * Oct 14, 2008
 */
package mfs.aspectj.findbugs.visitors;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import mfs.aspectj.findbugs.Logger;
import polyglot.ast.Node;
import polyglot.ext.jl.ast.Node_c;
import polyglot.util.Position;
import abc.aspectj.ast.AdviceDecl;
import abc.aspectj.ast.Pointcut;
import abc.aspectj.ast.PointcutDecl_c;
import abc.eaj.extension.EAJAdviceDecl_c;

/**
 * @author Meisam Fathi Salmi as Administrator
 * 
 */

public class Observer implements ASTExtractorObserver,
		AdviceDecExtractorObserver, PointcutExtractorObserver,
		PointcutDecExtractorObserver, StaticNullDereferenceObserver {

	private AdviceDecExtractor adviceDecExtractor;
	private final Logger aeLogger;

	private ASTExtractor astExtractor;
	private final Logger astLogger;

	private PointcutExtractor pointcutExtractor;
	private final Logger pcLogger;

	private PointcutDecExtractor pointcutDecExtractor;
	private final Logger pcDecLogger;

	private StaticNullDereferenceExtractor staticNullDereferenceExtractor;
	private final Logger nullRefLogger;

	private StaticMethodExtractor staticMethodExtractor;
	private final Logger staticMethodLogger;

	public Observer() {

		try {
			aeLogger = new Logger("advice");
			astLogger = new Logger("ast");
			pcLogger = new Logger("pointcut");
			pcDecLogger = new Logger("pointcut-dec");
			nullRefLogger = new Logger("null-ref");
			staticMethodLogger = new Logger("static-method");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("File not found exception");
		}
	}

	/**
	 * @param node
	 * @return
	 */
	private Position getPosition(Node node) {
		if (node instanceof Node_c) {
			return ((Node_c) node).position();
		}
		throw new IllegalArgumentException(
				"Node urgument should be of type polyglot.ext.jl.ast.Node_c");
	}

	/**
	 * @param adviceDecl
	 */
	private void handleAdviceDec(AdviceDecl adviceDecl) {
		aeLogger.log(adviceDecl + "\t|-->\t" + getPosition(adviceDecl));
	}

	/**
	 * @param pointcut
	 */
	private void handlePointcut(Pointcut pointcut) {
		pcLogger.log(pointcut + "\t:-->\t" + getPosition(pointcut));
	}

	/**
	 * @param parents
	 * @param child
	 */
	private void handlePointcutChildren(Map<Node, Pointcut> parents, Node child) {
		Pointcut parent = parents.get(child);
		pcLogger.log("location:[" + getPosition(parent) + "];\t" + parent
				+ "\t>>>\t" + "\t");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seemfs.aspectj.findbugs.visitors.AdviceDecExtractorObserver#
	 * notifyAdviceDecExtractcingFinished()
	 */
	@Override
	public void notifyAdviceDecExtractcingFinished() {
		aeLogger.log("Here are all AdviceDecs found in the program");

		Collection<AdviceDecl> pointcuts = adviceDecExtractor.getAdvices();
		for (AdviceDecl adviceDecl : pointcuts) {
			handleAdviceDec(adviceDecl);
		}

		aeLogger.log("");
	}

	@Override
	public void notifyASTExtractcingFinished() {
		astLogger.log("ALL PARENT-CHILD RELATIONSHIPS IN AST");
		astLogger.log("----------------------------------------");
		astLogger.log("");
		Collection<Node> nodes = astExtractor.getNodes();
		Map<Node, Set<Node>> parentChildren = astExtractor.getParentChildren();
		for (Node node : nodes) {
			if (node != null) {
				astLogger.log(node.toString() + "        [" + node.getClass()
						+ "]");
				Set<Node> children = parentChildren.get(node);
				if (children != null) {
					for (Node child : children) {
						astLogger.log("            " + child + "        ["
								+ child.getClass() + "]");
					}
				}
			}
		}
	}

	@Override
	public void notifyPointcutDecExtracingFinished() {
		Collection<PointcutDecl_c> parents = pointcutDecExtractor.getParents();
		Map<PointcutDecl_c, Set<Node>> parentChildrenMap;
		parentChildrenMap = pointcutDecExtractor.getParentChildrenMap();
		for (PointcutDecl_c pointcutDecl_c : parents) {
			pcDecLogger.log(pointcutDecl_c + ":");
			Set<Node> childrenSet = parentChildrenMap.get(pointcutDecl_c);
			for (Node node : childrenSet) {
				pcDecLogger.log("\t\t" + node);
			}

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mfs.aspectj.findbugs.PointcutExtractorObserver#notifyFinished()
	 */
	@Override
	public void notifyPointcutExtracingFinished() {
		pcLogger.log("Here are all pointcuts found in the program");

		Collection<Pointcut> pointcuts = pointcutExtractor.getPointcuts();
		for (Pointcut pointcut : pointcuts) {
			handlePointcut(pointcut);
		}

		pcLogger.log("");
		pcLogger.log("Here are all their children found in the program");

		Map<Node, Pointcut> parents = pointcutExtractor.getParents();
		Collection<Node> children = pointcutExtractor.getChildren();
		for (Node child : children) {
			handlePointcutChildren(parents, child);
		}

		pcLogger.log("");
	}

	@Override
	public void notifyStaticNullDereferenceExtracingFinished() {
		Collection<EAJAdviceDecl_c> potentialAdvices;
		potentialAdvices = staticNullDereferenceExtractor.getPotentialAdvices();
		for (EAJAdviceDecl_c adviceDecl_c : potentialAdvices) {
			nullRefLogger.log(adviceDecl_c.toString() + "; position["
					+ adviceDecl_c.position() + "]");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seemfs.aspectj.findbugs.visitors.AdviceDecExtractorObserver#
	 * setAdviceDecExtractor(mfs.aspectj.findbugs.visitors.AdviceDecExtractor)
	 */
	@Override
	public void setAdviceDecExtractor(AdviceDecExtractor advicedecExtractor) {
		this.adviceDecExtractor = advicedecExtractor;
	}

	@Override
	public void setASTExtractor(ASTExtractor astExtractor) {
		this.astExtractor = astExtractor;
	}

	@Override
	public void setPointcutDecExtractor(
			PointcutDecExtractor pointcutDecExtractor) {
		this.pointcutDecExtractor = pointcutDecExtractor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mfs.aspectj.findbugs.visitors.PointcutExtractorObserver#setPointcutExtractor
	 * (mfs.aspectj.findbugs.visitors.PointcutExtractor)
	 */
	@Override
	public void setPointcutExtractor(PointcutExtractor pointcutExtractor) {
		this.pointcutExtractor = pointcutExtractor;
	}

	@Override
	public void setStaticNullDereferenceExtractor(
			StaticNullDereferenceExtractor staticNullDereferenceExtractor) {
		this.staticNullDereferenceExtractor = staticNullDereferenceExtractor;
	}

}
