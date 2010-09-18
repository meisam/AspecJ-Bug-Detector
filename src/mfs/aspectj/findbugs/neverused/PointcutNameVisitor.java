/**
 * October 8, 2008
 */
package mfs.aspectj.findbugs.neverused;

import java.util.Collection;
import java.util.List;

import mfs.aspectj.findbugs.visitors.PointcutExtractor;
import polyglot.ast.Block;
import polyglot.ast.Node;
import polyglot.visit.NodeVisitor;
import abc.aspectj.ast.AdviceDecl;
import abc.aspectj.ast.PCCall_c;
import abc.aspectj.ast.Pointcut;
import abc.aspectj.ast.PointcutDecl;
import abc.aspectj.ast.PointcutDecl_c;

/**
 * This is the class that is assumed to do the job. This class should override
 * necessary methods in order to parse the AJ program and extract the bug
 * patterns. This could be done in an awesome provided that I develop this class
 * perfectly.
 * 
 * @author Meisam Fathi Salmi as Administrator
 * 
 */
public class PointcutNameVisitor extends NodeVisitor {

	private final PointcutExtractor pccnv;

	public PointcutNameVisitor() {
		pccnv = new PointcutExtractor(null);
	}

	@Override
	public NodeVisitor enter(Node parent, Node n) {
		if (n instanceof PointcutDecl) {
			return pccnv;
		}
		return this;
	}

	@Override
	public void finish() {
		System.out.println("<<<<<<<<<<<    FINISH       >>>>>>>>>>>>>>");
		Collection<Pointcut> pointcuts = pccnv.getPointcuts();
		for (Pointcut pointcut : pointcuts) {
			System.out.print("[ ");

			System.out.print(pointcut);

			System.out.print(", ");
			System.out.print(pointcut.position().nameAndLineString());

			if (pointcut instanceof PCCall_c) {
				PCCall_c pccall = (PCCall_c) pointcut;
			}

			System.out.print("]");
			System.out.println();
		}

		System.out.println();
		System.out.println();
	}

	@Override
	public Node leave(Node old, Node n, NodeVisitor v) {
		if (n instanceof PointcutDecl) {
			PointcutDecl pointcutDecl = (PointcutDecl) n;
			handlePointcut(pointcutDecl);
		} else if (n instanceof AdviceDecl) {
			AdviceDecl adviceDecl = (AdviceDecl) n;
			handleAdvice(adviceDecl);
		} else if (n instanceof Pointcut) {
			System.out.println(n.position());
			Pointcut pointcut = (Pointcut) n;
			handlePointcut(pointcut);
		}

		return n;
	}

	private void handlePointcut(Pointcut pointcut) {
		System.out.println();
		System.out.print("pointcut: ");
		System.out.println(pointcut);
		System.out.print("\tprecedence: ");
		System.out.println(pointcut.precedence());
	}

	private void handleAdvice(AdviceDecl adviceDecl) {
		System.out.println();
		System.out.print("Advice: ");
		System.out.println(adviceDecl);
	}

	private void handlePointcut(PointcutDecl pointcutDecl) {
		System.out.println();
		System.out.print("Pointcut Declaration: ");
		System.out.println(pointcutDecl.toString());
		if (pointcutDecl instanceof PointcutDecl_c) {
			PointcutDecl_c pointcutDecl_c = (PointcutDecl_c) pointcutDecl;

			// Node child = pointcutDecl_c.visitChild(n, v);
			//
			// System.out.print("\t");
			// System.out.println("child: " + child);
			//
			extractBody(pointcutDecl_c);
		}
	}

	private void extractBody(PointcutDecl_c pointcutDecl_c) {
		Block body = pointcutDecl_c.body();
		if (body == null) {

			System.out.print("\t");
			System.out.println("BODY IS NULL");

		} else {
			List<?> statements = body.statements();
			for (Object statement : statements) {
				System.out.print("\t");
				System.out.print(statement.getClass());
				System.out.print(":\t");
				System.out.println(statement);
			}
		}
	}
}
