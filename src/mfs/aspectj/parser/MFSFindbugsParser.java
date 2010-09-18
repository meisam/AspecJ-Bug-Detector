/**
 * October 5, 2008
 */
package mfs.aspectj.parser;

import mfs.aspectj.parser.Constants.FileNames;
import abc.main.Main;

/**
 * This class tries to run a compile a simple AOP.
 * 
 * @author Meisam Fathi Salmi as Administrator
 * 
 */
public class MFSFindbugsParser {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {

			args = getTelecomArgs();
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

	/**
	 * @return
	 */
	public static String[] getTelecomArgs() {
		return new String[] { // all parameters
		"-verbose", // print verbose debugging info

				// Java Classes
				FileNames.ABSTRACTSIMULATION_CLASS,//
				FileNames.BASICSIMULATION_CLASS,//
				FileNames.BILLINGSIMULATION_CLASS,//
				FileNames.CALL_CLASS,//
				FileNames.CONNECTION_CLASS,//
				FileNames.CUSTOMER_CLASS,//
				FileNames.LOCAL_CLASS, //
				FileNames.LONGDISTANCE_CLASS, //
				FileNames.TIMER_CLASS,//
				FileNames.TIMINGSIMULATION_CLASS, //

				// aspects
				FileNames.BILLING_ASPECT,// 
				FileNames.TIMING_ASPECT, //
				FileNames.TIMERLOG_ASPECT,//

				// other parameters
				"-ext", // switch to set extension
				"mfs.aspectj.findbugs" // let the extension be my extension
		};
	}
}