/**
 * Oct 12, 2008
 */
package mfs.aspectj.findbugs.visitors;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;

import polyglot.ast.Node;
import polyglot.util.Position;
import polyglot.visit.NodeVisitor;
import abc.aspectj.extension.AJCall_c;
import abc.eaj.extension.EAJAdviceDecl_c;

/**
 * @author Meisam Fathi Salmi as Administrator
 */
public class StaticNullDereferenceExtractor extends NodeVisitor {

  private final Collection<EAJAdviceDecl_c> potentialAdvices = new LinkedHashSet<EAJAdviceDecl_c>();

  private final Map<EAJAdviceDecl_c, Local_cExtractor> map = new HashMap<EAJAdviceDecl_c, Local_cExtractor>();

  private final Collection<Position> positions = new LinkedList<Position>();

  /*
   * (non-Javadoc)
   * @see polyglot.visit.NodeVisitor#enter(polyglot.ast.Node)
   */
  @Override
  public NodeVisitor enter(final Node n) {
    if (n instanceof EAJAdviceDecl_c) {
      final EAJAdviceDecl_c adviceDec = (EAJAdviceDecl_c) n;
      final Local_cExtractor local_cExtractor = new Local_cExtractor(adviceDec);
      map.put(adviceDec, local_cExtractor);
      return local_cExtractor;
    }
    return this;
  }

  /*
   * (non-Javadoc)
   * @see polyglot.visit.NodeVisitor#leave(polyglot.ast.Node, polyglot.ast.Node,
   * polyglot.visit.NodeVisitor)
   */
  @Override
  public Node leave(final Node old, final Node n, final NodeVisitor v) {
    if (n instanceof EAJAdviceDecl_c) {
      final Local_cExtractor local_cExtractor = map.get(n);
      if (local_cExtractor != null) {
        final Collection<AJCall_c> calls = local_cExtractor.getCalls();
        if (!calls.isEmpty()) {
          System.out.println("One potential fault found");
          potentialAdvices.add((EAJAdviceDecl_c) n);
        }
      }
    }
    return super.leave(old, n, v);
  }

  public Collection<Position> getPositions() {
    return positions;
  }

  /**
   * @return the potentialAdvices
   */
  public Collection<EAJAdviceDecl_c> getPotentialAdvices() {
    return potentialAdvices;
  }

}
