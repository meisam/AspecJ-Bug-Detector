/**
 * Sep 29, 2008
 */
package mfs.aspectj.parser;

import mfs.aspectj.parser.Constants.FileNames;
import abc.main.Main;

/**
 * This java class simply compiles a java class and an aspect associated with
 * it.
 * 
 * @author Meisam Fathi Salmi as Administrator
 * 
 */
public class SampleParser {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {

			args = new String[] { "-verbose", FileNames.TIMERLOG_ASPECT,
					FileNames.TIMER_CLASS };
			Main.main(args);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
