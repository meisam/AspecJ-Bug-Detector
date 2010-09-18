/**
 * Oct 18, 2008
 */
package mfs.aspectj.findbugs.visitors;

/**
 * @author Meisam Fathi Salmi as Administrator
 * 
 */
public interface StaticNullDereferenceObserver {
	void notifyStaticNullDereferenceExtracingFinished();

	void setStaticNullDereferenceExtractor(
			StaticNullDereferenceExtractor staticNullDereferenceExtractor);

}
