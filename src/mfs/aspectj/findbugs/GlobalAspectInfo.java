/**
 * Nov 30, 2008
 */
package mfs.aspectj.findbugs;

import java.util.Collection;
import java.util.LinkedList;

import polyglot.ext.jl.ast.MethodDecl_c;

/**
 * @author Meisam Fathi Salmi as Administrator
 * 
 */
public class GlobalAspectInfo extends abc.weaving.aspectinfo.GlobalAspectInfo {

	private final Collection<MethodDecl_c> staticMethods = new LinkedList<MethodDecl_c>();

	public void addStaticMethod(MethodDecl_c method) {
		staticMethods.add(method);
	}

	/**
	 * @return the staticMethods
	 */
	public Collection<MethodDecl_c> getStaticMethods() {
		return staticMethods;
	}

}
