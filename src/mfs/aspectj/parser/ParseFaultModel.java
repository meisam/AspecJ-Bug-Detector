/**
 * October 5, 2008
 */
package mfs.aspectj.parser;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import polyglot.ext.jl.ast.MethodDecl_c;
import soot.Local;
import soot.SootMethod;
import abc.main.Main;
import abc.weaving.aspectinfo.AbcClass;
import abc.weaving.aspectinfo.AbstractAdviceDecl;
import abc.weaving.aspectinfo.Pointcut;
import abc.weaving.matching.AdviceApplication;
import abc.weaving.matching.MethodAdviceList;
import abc.weaving.matching.ShadowMatch;
import abc.weaving.weaver.WeavingState;

/**
 * This class tries to run a compile a simple AOP.
 * 
 * @author Meisam Fathi Salmi as Administrator
 */
public class ParseFaultModel {

  /**
   * @param args
   */
  public static void main(final String[] args) {
    try {
      Main.main(getAllFilesInDir("../AspectJ BenchMark"));
      System.out.println("");

      mfs.aspectj.findbugs.GlobalAspectInfo globalAspectInfo;
      globalAspectInfo = (mfs.aspectj.findbugs.GlobalAspectInfo) Main.v()
          .getAbcExtension().getGlobalAspectInfo();
      final Collection<MethodDecl_c> staticMethods = globalAspectInfo
          .getStaticMethods();

      System.out.println();
      System.out.println();
      System.out.println();
      System.out.println("===============Static Methods===============");
      for (final MethodDecl_c methodDecl_c : staticMethods) {
        System.out.println(methodDecl_c);

      }

      System.out.println();
      System.out.println("===============All Advice===============");
      final List<AbstractAdviceDecl> adviceDecls = globalAspectInfo
          .getAdviceDecls();
      for (final AbstractAdviceDecl adviceDecl : adviceDecls) {
        final Pointcut pointcut = adviceDecl.getPointcut();
        System.out.println(adviceDecl.hashCode() + ": " + adviceDecl);
        System.out.print("\t\t");
        System.out.println(pointcut);
        System.out.print("\t\t\t\t");
        System.out.println();

      }

      System.out.println();
      System.out.println("===============Woven Classes ===============");
      final Set<AbcClass> abcClasses = globalAspectInfo.getWeavableClasses();
      for (final AbcClass abcClass : abcClasses) {
        System.out.println(abcClass);
      }

      System.out.println();
      System.out.println("=======Soot Methods + Their Shadow Matches=======");
      for (final AbcClass abcClass : abcClasses) {
        final List<SootMethod> methods = abcClass.getSootClass().getMethods();
        for (final SootMethod sootMethod : methods) {

          final List<ShadowMatch> shadowMatchList = globalAspectInfo
              .getShadowMatchList(sootMethod);
          if (shadowMatchList.size() > 0) {
            System.out.println(sootMethod);
            for (final ShadowMatch shadowMatch : shadowMatchList) {
              System.out.print("\t");
              System.out.print(shadowMatch.getClass());
              System.out.print(": ");
              System.out.println(shadowMatch);

              System.out.print("\t\t Name: \t");
              System.out.println(shadowMatch.joinpointName());

              System.out.print("\t\t Host: \t");
              System.out.print(shadowMatch.getHost().getClass());
              System.out.print(": ");
              System.out.println(shadowMatch.getHost());

              System.out.print("\t\t Container: \t");
              System.out.print(shadowMatch.getContainer().getClass());
              System.out.print(": ");
              System.out.println(shadowMatch.getContainer());

              System.out.print("\t\t Enclosing: \t");
              System.out.print(shadowMatch.getEnclosing().getClass());
              System.out.print(": ");
              System.out.println(shadowMatch.getEnclosing());

              System.out.print("\t\t SJPInfo: \t");
              System.out.print(shadowMatch.getSJPInfo().getClass());
              System.out.print(": ");
              System.out.println(shadowMatch.getSJPInfo());

              final Local local = WeavingState.v()
                  .get_JoinPointInfo_thisJoinPoint(shadowMatch);
              System.out.print("\t\t");
              System.out.println(local.getType());

              final MethodAdviceList adviceList = globalAspectInfo
                  .getAdviceList(sootMethod);
              final List<AdviceApplication> allAdvice = adviceList.allAdvice();
              for (final AdviceApplication advice : allAdvice) {
                System.out.print("\t\t\t\t advice:\t");
                System.out.println(advice.advice.hashCode() + ": "
                    + advice.advice);
              }
            }
            System.out.println();
          }
        }
      }

      System.out.println();
      System.out.println("===============Extra Things===============");
    } catch (final Exception e) {
      e.printStackTrace();
    }
  }

  public static String[] getAllFilesInDir(final String dirPath) {

    final String[] result = new String[9];
    int i = 0;
    result[i++] = "-verbose";

    result[i++] = "-sourceroots";
    result[i++] = dirPath + "/src";

    result[i++] = "-cp";
    result[i++] = allJars(dirPath + "/lib");

    result[i++] = "-source";
    result[i++] = "1.5";

    result[i++] = "-ext";
    result[i++] = "mfs.aspectj.findbugs";

    // result[i++] = "-dava";
    return result;
  }

  private static String allJars(final String dirPath) {

    // StringBuffer str = new StringBuffer();
    //
    // File dir = new File(dirPath);
    // File[] listFiles = dir.listFiles();
    // for (int i = 0; i < listFiles.length; i++) {
    // if (isJarFile(listFiles[i])) {
    // try {
    // str.append(listFiles[i].getCanonicalPath());
    // str.append("; ");// listFiles[i].;
    // break;
    // } catch (IOException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }
    // }
    // }
    // // str.append("\"");
    // System.out.println(str.toString());
    // return str.toString();
    return dirPath + "/abc-complete.jar";
  }

  private static boolean isJarFile(final File file) {
    return file.isFile() && file.getName().toLowerCase().endsWith(".jar");
  }
}