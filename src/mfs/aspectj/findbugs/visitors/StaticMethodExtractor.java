/**
 * Oct 12, 2008
 */
package mfs.aspectj.findbugs.visitors;

import mfs.aspectj.findbugs.GlobalAspectInfo;
import polyglot.ast.Node;
import polyglot.ext.jl.ast.MethodDecl_c;
import polyglot.visit.NodeVisitor;
import abc.main.Main;

/**
 * @author Meisam Fathi Salmi as Administrator
 * 
 */
public class StaticMethodExtractor extends NodeVisitor {

	private final mfs.aspectj.findbugs.GlobalAspectInfo globalAspectInfo;

	/**
	 * 
	 */
	public StaticMethodExtractor() {
		globalAspectInfo = (GlobalAspectInfo) Main.v().getAbcExtension()
				.getGlobalAspectInfo();

	}

	@Override
	public Node leave(Node old, Node n, NodeVisitor v) {
		if (n instanceof MethodDecl_c) {
			MethodDecl_c method = (MethodDecl_c) n;
			if (method.flags().isStatic()) {
				globalAspectInfo.addStaticMethod(method);
			}
		}
		return n;
	}

}
