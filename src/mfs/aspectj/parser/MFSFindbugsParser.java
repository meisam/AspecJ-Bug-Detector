/**
 * October 5, 2008
 */
package mfs.aspectj.parser;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;

import mfs.aspectj.parser.Constants.FileNames;
import abc.main.Main;

/**
 * This class tries to run a compile a simple AOP.
 * 
 * @author Meisam Fathi Salmi as Administrator
 * 
 */
public class MFSFindbugsParser {

	private static final String DEFAULT_BASE_DIR = "src";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			String[] abcArgs;
			String baseDir;
			if (args == null || args.length == 0) {
				System.out.println("No source folder specified");
				System.out
						.println("Using default source ($CURRENT_DIRECTORY/src) ...");
				abcArgs = getArgsForDirectory(DEFAULT_BASE_DIR);
			} else if (args[0].trim().equals("-dava")) {
				if (args.length == 1) {
					baseDir = DEFAULT_BASE_DIR;
				} else {
					baseDir = args[1];
				}
				abcArgs = getDavaArgsForDirectory(baseDir);
			} else {
				baseDir = args[0];
				abcArgs = getArgsForDirectory(baseDir);
			}

			Main.main(abcArgs);
			System.out.println("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String[] getArgsForDirectory(final String baseDir) {
		File directory = new File(baseDir);
		Collection<File> allFiles = FileSystemUtility.listAllFile(directory);

		String[] abcArgs = new String[allFiles.size() + 3];
		abcArgs[0] = "-verbose";
		abcArgs[allFiles.size() + 1] = "-ext";
		abcArgs[allFiles.size() + 2] = "mfs.aspectj.findbugs";

		int currentIndex = 1;
		Iterator<File> iterator = allFiles.iterator();
		while (iterator.hasNext()) {
			File file = iterator.next();
			abcArgs[currentIndex] = file.getAbsolutePath();
			currentIndex++;
		}
		return abcArgs;
	}

	private static String[] getDavaArgsForDirectory(final String baseDir) {
		File directory = new File(baseDir);
		Collection<File> allFiles = FileSystemUtility.listAllFile(directory);

		String[] abcArgs = new String[allFiles.size() + 4];
		abcArgs[0] = "-verbose";
		abcArgs[allFiles.size() + 1] = "-ext";
		abcArgs[allFiles.size() + 2] = "mfs.aspectj.findbugs";
		abcArgs[allFiles.size() + 3] = "-dava";

		int currentIndex = 1;
		Iterator<File> iterator = allFiles.iterator();
		while (iterator.hasNext()) {
			File file = iterator.next();
			abcArgs[currentIndex] = file.getAbsolutePath();
			currentIndex++;
		}
		return abcArgs;
	}
}
