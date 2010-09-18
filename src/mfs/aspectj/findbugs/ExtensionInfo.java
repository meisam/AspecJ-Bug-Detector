/*
 * abc - The AspectBench Compiler Copyright (C) 2004 Julian Tibble This compiler
 * is free software; you can redistribute it and/or modify it under the terms of
 * the GNU Lesser General Public License as published by the Free Software
 * Foundation; either version 2.1 of the License, or (at your option) any later
 * version. This compiler is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License
 * for more details. You should have received a copy of the GNU Lesser General
 * Public License along with this compiler, in the file LESSER-GPL; if not,
 * write to the Free Software Foundation, Inc., 59 Temple Place - Suite 330,
 * Boston, MA 02111-1307, USA.
 */

/**
 * October 8, 2008
 */

package mfs.aspectj.findbugs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import mfs.aspectj.findbugs.visitors.ASTExtractor;
import mfs.aspectj.findbugs.visitors.AdviceDecExtractor;
import mfs.aspectj.findbugs.visitors.Observer;
import mfs.aspectj.findbugs.visitors.ParentChildVisitor;
import mfs.aspectj.findbugs.visitors.PointcutDecExtractor;
import mfs.aspectj.findbugs.visitors.PointcutExtractor;
import mfs.aspectj.findbugs.visitors.StaticMethodExtractor;
import mfs.aspectj.findbugs.visitors.StaticNullDereferenceExtractor;
import polyglot.frontend.Job;
import polyglot.frontend.Pass;
import polyglot.frontend.PrettyPrintPass;
import polyglot.frontend.VisitorPass;
import polyglot.frontend.Pass.ID;
import polyglot.util.CodeWriter;
import polyglot.visit.PrettyPrinter;

/**
 * @author Meisam Fathi Salmi as Administrator
 */
public class ExtensionInfo extends abc.eaj.ExtensionInfo {

  private static final ID MFS_FINDBUGS_POINTCUT = new Pass.ID(
      "mfs-findbugs-pointcut");

  private static final ID MFS_FINDBUGS_POINTCUT_DEC = new Pass.ID(
      "mfs-findbugs-pointcut-dec");

  private static final ID MFS_FINDBUGS_ADVICE = new Pass.ID(
      "mfs-findbugs-advice");

  private static final ID MFS_FINDBUGS_PARENT_CHILD = new Pass.ID(
      "mfs-findbugs-parent-child");

  private static final ID MFS_FINDBUGS_DUMPAST = new Pass.ID(
      "mfs-findbugs-dum-past");

  private static final ID MFS_FINDBUGS_AST_EXTRACTOR = new Pass.ID(
      "mfs-findbugs-extract-ast");

  private static final ID MFS_FINDBUGS_STATIC_NULL_DEREFERENCE = new Pass.ID(
      "mfs-findbugs-static-null-deference");

  private static final ID MFS_FINDBUGS_STATIC_METHODS = new Pass.ID(
      "mfs-findbugs-static-methods");

  private final ParentChildVisitor pcv;
  private final Observer observer;
  private final StaticMethodExtractor smev;
  private final PointcutExtractor pcev;
  private final PointcutDecExtractor pcdev;
  private final AdviceDecExtractor aev;
  private final ASTExtractor astv;
  private final StaticNullDereferenceExtractor sndExtractor;

  private final PrettyPrinter pp;

  private final CodeWriter cw;

  static {
    // force Topics to load
    new Topics();
  }

  public ExtensionInfo(final Collection<String> jar_classes,
      final Collection<String> source_files) {
    super(jar_classes, source_files);
    observer = new Observer();

    smev = new StaticMethodExtractor();
    pcev = new PointcutExtractor(observer);
    pcdev = new PointcutDecExtractor(observer);
    aev = new AdviceDecExtractor(observer);
    pcv = new ParentChildVisitor();
    astv = new ASTExtractor(observer);
    sndExtractor = new StaticNullDereferenceExtractor();

    cw = new CodeWriter(System.out, 100);
    pp = new PrettyPrinter();
  }

  @Override
  public List<Pass> passes(final Job job) {
    final List<Pass> l = super.passes(job);
    // final ArrayList<Pass> l = new ArrayList<Pass>(25);
    // l.add(new InitClasses(ExtensionInfo.INIT_CLASSES, this, ts));
    //
    // passes_parse_and_clean(l, job);
    // // passes_patterns_and_parents(l, job);
    // passes_precedence_relation(l, job);
    // passes_disambiguate_signatures(l, job);
    // passes_add_members(l, job);
    // passes_interface_ITDs(l, job);
    // passes_disambiguate_all(l, job);
    //
    // passes_print_pointcuts(l, job);
    //
    passes_print_methods(l, job);
    //
    // passes_esxtract_bugs(l, job);
    // // passes_dumpAST(l, job);
    //
    return l;

  }

  private void passes_print_methods(final List<Pass> l, final Job job) {
    l.add(new VisitorPass(MFS_FINDBUGS_STATIC_METHODS, job, smev));

  }

  private void passes_dumpAST(final ArrayList<Pass> l, final Job job) {
    l.add(new PrettyPrintPass(MFS_FINDBUGS_DUMPAST, job, cw, pp));

  }

  protected void passes_print_pointcuts(final ArrayList<Pass> l, final Job job) {

    l.add(new VisitorPass(MFS_FINDBUGS_POINTCUT, job, pcev));

    l.add(new VisitorPass(MFS_FINDBUGS_POINTCUT_DEC, job, pcdev));

    l.add(new VisitorPass(MFS_FINDBUGS_ADVICE, job, aev));

    // l.add(new VisitorPass(MFS_FINDBUGS_PARENT_CHILD, job, pcv));

    l.add(new VisitorPass(MFS_FINDBUGS_AST_EXTRACTOR, job, astv));
  }

  private void passes_esxtract_bugs(final ArrayList<Pass> l, final Job job) {
    l.add(new VisitorPass(MFS_FINDBUGS_STATIC_NULL_DEREFERENCE, job,
        sndExtractor));

  }
}
