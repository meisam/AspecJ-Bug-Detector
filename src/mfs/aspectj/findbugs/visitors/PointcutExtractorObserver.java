/**
 * Oct 14, 2008
 */
package mfs.aspectj.findbugs.visitors;

/**
 * @author Meisam Fathi Salmi as Administrator
 * 
 */
public interface PointcutExtractorObserver {
	void notifyPointcutExtracingFinished();

	void setPointcutExtractor(PointcutExtractor pointcutExtractorVisitor);
}
