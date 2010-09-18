/**
 * Nov 29, 2008
 */
package mfs.aspectj.findbugs.visitors;

/**
 * @author Meisam Fathi Salmi as Administrator
 * 
 */
public interface StaticMethodObserver {

	void setStaticMethodExtractor(StaticMethodExtractor staticMethodExtractor);

	void notifyStaticMethodExtracingFinished();

}
