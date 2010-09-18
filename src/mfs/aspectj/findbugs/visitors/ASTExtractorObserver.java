/**
 * Oct 14, 2008
 */
package mfs.aspectj.findbugs.visitors;

/**
 * @author Meisam Fathi Salmi as Administrator
 * 
 */
public interface ASTExtractorObserver {
	void setASTExtractor(ASTExtractor extractor);

	void notifyASTExtractcingFinished();

}
