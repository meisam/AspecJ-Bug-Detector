/**
 * Oct 8, 2008
 */
package mfs.aspectj.parser;

import abc.main.Main;

/**
 * This class runs AspectBench Compile without any argument. The purpose of this
 * actions is to show aspect bench compiler's help command.
 * 
 * @author Meisam Fathi Salmi as Administrator
 * 
 */
public class RunWithoutAnyArgument {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		args = new String[] { "-help" };
		Main.main(args);
	}

}
