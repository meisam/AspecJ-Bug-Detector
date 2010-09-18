/**
 * October 5, 2008
 */
package mfs.aspectj.parser;

import abc.main.Main;

/**
 * This class tries to run a compile a simple AOP.
 * 
 * @author Meisam Fathi Salmi as Administrator
 * 
 */
public class ParseSimpleAspect {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {

			args = getSimpleAspectJSampleArgs();
			Main.main(args);
			System.out.println("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String[] getSimpleAspectJSampleArgs() {
		return new String[] {

		// all parameters

				"-verbose", // print verbose debugging info

				// Java Classes
				"D:/Documents/Projects/AspectJ Samples/AspectJ BenchMark/src/mfs/faultmodels/SimpleAspect.aj", //

				// other parameters
				"-ext", // switch to set extension
				"mfs.aspectj.findbugs" // let the extension be my extension
		};
	}

}