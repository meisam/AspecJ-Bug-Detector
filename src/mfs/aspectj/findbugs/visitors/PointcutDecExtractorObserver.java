/**
 * Oct 28, 2008
 */
package mfs.aspectj.findbugs.visitors;

/**
 * @author Meisam Fathi Salmi as Administrator
 * 
 */
public interface PointcutDecExtractorObserver {

	void setPointcutDecExtractor(PointcutDecExtractor pointcutDecExtractor);

	void notifyPointcutDecExtracingFinished();

}
