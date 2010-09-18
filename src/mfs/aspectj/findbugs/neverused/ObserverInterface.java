/**
 * Oct 14, 2008
 */
package mfs.aspectj.findbugs.neverused;

/**
 * @author Meisam Fathi Salmi as Administrator
 * 
 */
public interface ObserverInterface<E extends ExtractorVisitor<?, ?>> {
	public void setExtractor(E e);

	public void notifyFinished(E e);

}
