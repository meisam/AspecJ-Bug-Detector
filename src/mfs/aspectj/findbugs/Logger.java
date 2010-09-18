/**
 * Oct 18, 2008
 */
package mfs.aspectj.findbugs;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Meisam Fathi Salmi as Administrator
 * 
 */
public class Logger {

	// /*
	// * (non-Javadoc)
	// *
	// * @see java.lang.Object#finalize()
	// */
	// @Override
	// protected void finalize() throws Throwable {
	// out.close();
	// super.finalize();
	// }
	//
	private final FileWriter out;

	public Logger(String fileName) throws IOException {
		File f = new File(fileName + ".jlog");
		out = new FileWriter(f);
	}

	public void log(String s) {
		try {
			out.write(s + "\n");
			out.flush();
			// System.out.println(s);
		} catch (IOException e) {
			e.printStackTrace();
			new RuntimeException(e.getMessage());
		}
	}
}
